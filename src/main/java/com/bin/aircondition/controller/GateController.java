package com.bin.aircondition.controller;


import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Gate;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.service.GateService;
import com.bin.aircondition.vo.CommonQueryVo;
import com.bin.aircondition.vo.GateControlVo;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bintian
 * @since 2020-11-11
 */
@RestController
@CrossOrigin
@RequestMapping("/aircondition/gate")
public class GateController {

    @Autowired
    GateService gateService;
    @Autowired
    DeviceService deviceService;

    //获取卷帘门的状态
    @GetMapping("getGateStates/{current}/{limit}")
    public Result getPageGateStates(@PathVariable Long current, @PathVariable Long limit) {

        Map<String, Object> map = gateService.getPageGateStates(current, limit);
        return Result.ok().data(map);
    }

    //改变卷帘门的状态
    @PostMapping("updateGateStatus/{sender}")
    public Result updateGateStatus(@RequestBody GateControlVo gateControlVo, @PathVariable String sender) {
        Boolean status = gateService.updateGateStatus(gateControlVo, sender);
        if (!status) {
            return Result.error().message("修改出错了");
        }
        return Result.ok();
    }

    //条件分页获取卷帘门的状态
    @PostMapping("getPageGateByCondition/{current}/{limit}")
    public Result getPageGateByCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody CommonQueryVo queryVo) {

        Map<String, Object> map = gateService.getPageGateByCondition(current, limit,queryVo);
        return Result.ok().data(map);
    }

    //删除卷帘门
    @DeleteMapping("deleteGateById/{gateId}")
    public Result deleteGateById(@PathVariable String gateId) {
        gateService.removeById(gateId);
        return Result.ok();
    }

    //添加卷帘门
    @PostMapping("addGate")
    public Result addGate(@RequestBody Gate gate) {
        Gate gate1 = new Gate();
        BeanUtils.copyProperties(gate, gate1);
        gateService.save(gate1);
        return Result.ok();
    }
}

