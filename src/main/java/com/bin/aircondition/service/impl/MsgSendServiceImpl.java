package com.bin.aircondition.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.*;
import com.bin.aircondition.exceptionhandler.MyException;
import com.bin.aircondition.mapper.MsgSendMapper;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.service.MsgSendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.aircondition.service.TopicService;
import com.bin.aircondition.vo.ConditionStatusVo;
import com.bin.aircondition.vo.MessageQueryVo;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.plaf.metal.MetalBorders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
@Service
public class MsgSendServiceImpl extends ServiceImpl<MsgSendMapper, MsgSend> implements MsgSendService {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private MsgSendService msgSendService;
    @Value("${myrabbitmq.exchange.send}")
    private String exchange;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发布消息
     *
     * @param conditioner
     * @return
     */
    @Override
    public Boolean publishMessage(Conditioner conditioner, String sender) {

        //先拿到消息发往的设备id
        String deviceId = conditioner.getDeviceId();
        //根据设备id获取RoutingKey
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("device_id", deviceId);
        Device device = deviceService.getOne(wrapper);
        String routingKey = device.getRoutingKey();

        try {
            //将消息存入数据库
            MsgSend message = new MsgSend();
            String content = JSON.toJSONString(conditioner);
            message.setContent(content);
            message.setDestination(deviceId);
            message.setRoutingKey(routingKey);
            message.setSender(sender);

            boolean save = msgSendService.save(message);
            //再将消息放入消息队列, 发content即可
            rabbitTemplate.convertAndSend(exchange, routingKey, message.getContent());
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getPageHistoryData(String deviceId, Long current, Long limit) {
        Page<MsgSend> page = new Page<>(current, limit);
        QueryWrapper<MsgSend> wrapper = new QueryWrapper<>();
        wrapper.eq("destination", deviceId);
        wrapper.orderByDesc("gmt_create");
        this.page(page, wrapper);
        List<MsgSend> records = page.getRecords();
        long total = page.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);

        return map;
    }

    @Override
    public Map<String, Object> getPageHistoryDataByCondition(String deviceId, Long current, Long limit, MessageQueryVo messageQueryVo) {
        Page<MsgSend> page = new Page<>(current, limit);
        QueryWrapper<MsgSend> wrapper = new QueryWrapper<>();
        wrapper.eq("destination", deviceId);
        wrapper.orderByDesc("gmt_create");

        String begin = messageQueryVo.getBegin();
        String end = messageQueryVo.getEnd();
        String user = messageQueryVo.getUser();
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);
        }
        if (!StringUtils.isEmpty(user)){
            wrapper.eq("sender", user);
        }

        this.page(page, wrapper);
        List<MsgSend> records = page.getRecords();
        long total = page.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);

        return map;
    }
}
