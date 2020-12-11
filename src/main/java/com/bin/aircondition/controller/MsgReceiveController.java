package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.MsgReceive;
import com.bin.aircondition.entity.MsgSend;
import com.bin.aircondition.service.MsgReceiveService;
import com.bin.aircondition.vo.MessageQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@RequestMapping("/aircondition/msgreceive")
public class MsgReceiveController {

    @Autowired
    MsgReceiveService msgReceiveService;

    //获取收到的历史消息
    @GetMapping("getReceiveMsg/{deviceId}")
    public Result getReceiveMsg(@PathVariable String deviceId) {

        QueryWrapper<MsgReceive> wrapper = new QueryWrapper<>();
        wrapper.eq("sender", deviceId);
        wrapper.orderByDesc("gmt_create");
        List<MsgReceive> msgReceiveList = msgReceiveService.list(wrapper);
        return Result.ok().data("msgReceiveList", msgReceiveList);
    }

    //分页获取收到的历史消息
    @GetMapping("getPageReceiveMsg/{deviceId}/{current}/{limit}")
    public Result getPageReceiveMsg(@PathVariable String deviceId, @PathVariable Long current, @PathVariable Long limit) {

        Map<String, Object> map = msgReceiveService.getPageReceiveMsg(deviceId, current, limit);
        return Result.ok().data(map);
    }

    //条件分页获取收到的历史消息
    @PostMapping("getPageReceiveMsgByCondition/{deviceId}/{current}/{limit}")
    public Result getPageReceiveMsgByCondition(@PathVariable String deviceId, @PathVariable Long current, @PathVariable Long limit, @RequestBody MessageQueryVo messageQueryVo) {

        Map<String, Object> map = msgReceiveService.getPageReceiveMsgByCondition(deviceId, current, limit, messageQueryVo);
        return Result.ok().data(map);
    }

    //批量删除历史消息
    @PostMapping("deleteBatchHistoryReceive")
    public Result deleteBatchHistoryReceive(@RequestBody List<MsgReceive> msgReceive) {
        List<String> collect = msgReceive.stream().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        msgReceiveService.removeByIds(collect);
        return Result.ok();
    }
}

