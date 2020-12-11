package com.bin.aircondition.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.entity.Device;
import com.bin.aircondition.entity.Gate;
import com.bin.aircondition.entity.MsgSend;
import com.bin.aircondition.mapper.GateMapper;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.service.GateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.aircondition.service.MsgSendService;
import com.bin.aircondition.vo.CommonQueryVo;
import com.bin.aircondition.vo.GateControlVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bintian
 * @since 2020-11-11
 */
@Service
public class GateServiceImpl extends ServiceImpl<GateMapper, Gate> implements GateService {

    @Autowired
    DeviceService deviceService;
    @Autowired
    MsgSendService msgSendService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${myrabbitmq.exchange.send}")
    private String exchange;

    @Override
    public Map<String, Object> getPageGateStates(Long current, Long limit) {
        Page<Gate> page = new Page<>(current, limit);
        this.page(page);
        HashMap<String, Object> map = new HashMap<>();
        List<Gate> records = page.getRecords();
        long total = page.getTotal();
        long size = page.getSize();

        map.put("records", records);
        map.put("total", total);
        map.put("size", size);
        return map;
    }

    @Override
    public Map<String, Object> getPageGateByCondition(Long current, Long limit, CommonQueryVo queryVo) {
        Page<Gate> page = new Page<>(current, limit);
        QueryWrapper<Gate> wrapper = new QueryWrapper<>();
        String key = queryVo.getKey();
        String begin = queryVo.getBegin();
        String end = queryVo.getEnd();

        if(!StringUtils.isEmpty(key)) {
            wrapper.and(wrap->{
                wrap.eq("id", key).or().like("name", key);
            });
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        this.page(page, wrapper);
        List<Gate> records = page.getRecords();
        long total = page.getTotal();
        long size = page.getSize();

        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);
        map.put("size", size);
        return map;
    }

    @Override
    public Boolean updateGateStatus(GateControlVo gateControlVo, String sender) {

        //先拿到消息发往的设备id
        String deviceId = gateControlVo.getDeviceId();
        //根据设备id获取RoutingKey
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("device_id", deviceId);
        Device device = deviceService.getOne(wrapper);
        String routingKey = device.getRoutingKey();

        try {
            //将消息存入数据库
            MsgSend message = new MsgSend();
            String content = JSON.toJSONString(gateControlVo);
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
}
