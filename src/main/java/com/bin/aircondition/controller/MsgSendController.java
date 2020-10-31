package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Conditioner;
import com.bin.aircondition.entity.MsgSend;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.service.MsgSendService;
import com.bin.aircondition.service.TopicService;
import com.bin.aircondition.vo.ConditionStatusVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
@RestController
@CrossOrigin
@RequestMapping("/aircondition/msgsend")
public class MsgSendController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgSendService msgSendService;

    /**
     *  发布消息
     * @return
     *      deviceID: 消息发往那个设备
     */
    @PostMapping("publishMessage/{sender}")
    public Result publishMessage(@RequestBody Conditioner conditioner, @PathVariable String sender) {

        Boolean isSuccess = msgSendService.publishMessage(conditioner,sender);

        if(!isSuccess) return Result.error();
        return Result.ok();
    }

    //获取某个设备的历史修改记录
    @GetMapping("getHistoryData/{deviceId}")
    public Result getHistoryData(@PathVariable String deviceId) {

        QueryWrapper<MsgSend> wrapper = new QueryWrapper<>();
        wrapper.eq("destination", deviceId);
        wrapper.orderByDesc("gmt_create");
        List<MsgSend> msgSendList = msgSendService.list(wrapper);
        return Result.ok().data("msgSendList", msgSendList);
    }

}

