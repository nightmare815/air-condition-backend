package com.bin.aircondition.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.entity.Conditioner;
import com.bin.aircondition.entity.Device;
import com.bin.aircondition.entity.MsgSend;
import com.bin.aircondition.entity.Topic;
import com.bin.aircondition.exceptionhandler.MyException;
import com.bin.aircondition.mapper.MsgSendMapper;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.service.MsgSendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.aircondition.service.TopicService;
import com.bin.aircondition.vo.ConditionStatusVo;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            rabbitTemplate.convertAndSend("amq.topic", routingKey, message.getContent());
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
