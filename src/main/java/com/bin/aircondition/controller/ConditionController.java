package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Conditioner;
import com.bin.aircondition.entity.MsgReceive;
import com.bin.aircondition.service.ConditionerService;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.service.MsgReceiveService;
import com.bin.aircondition.service.impl.ConditionerServiceImpl;
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
@RequestMapping("/aircondition/condition")
public class ConditionController {

    @Autowired
    private ConditionerService conditionerService;

    //获取某个空调的详情信息
    @GetMapping("getConditionInfo/{deviceId}")
    public Result getConditionInfo(@PathVariable String deviceId) {

        QueryWrapper<Conditioner> wrapper = new QueryWrapper<>();
        wrapper.eq("device_id", deviceId);
        Conditioner status = conditionerService.getOne(wrapper);
        return Result.ok().data("status", status);
    }

}

