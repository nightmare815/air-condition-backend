package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.AirportBridge;
import com.bin.aircondition.entity.AirportStation;
import com.bin.aircondition.service.AirportBridgeService;
import com.bin.aircondition.service.AirportStationService;
import com.bin.aircondition.vo.CommonQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bintian
 * @since 2020-11-02
 */
@RestController
@CrossOrigin
@RequestMapping("/aircondition/airportbridge")
public class AirportBridgeController {

    @Autowired
    private AirportBridgeService airportBridgeService;

    //添加登机桥
    @PostMapping("addAirportBridge")
    public Result addAirportBridge(@RequestBody AirportBridge airportBridge) {

        boolean save = airportBridgeService.save(airportBridge);
        if(!save) {
            return Result.error();
        }
        return Result.ok();
    }
    //删除登机桥
    @DeleteMapping("deleteAirportBridge/{id}")
    public Result deleteAirportBridge(@PathVariable String id) {

        boolean remove = airportBridgeService.removeById(id);
        if(!remove) {
            return Result.error();
        }
        return Result.ok();
    }
    //编辑登机桥信息
    @PostMapping("editAirportBridge")
    public Result editAirportBridge(@RequestBody AirportBridge airportBridge) {

        boolean update = airportBridgeService.updateById(airportBridge);
        if(!update) {
            return Result.error();
        }
        return Result.ok();
    }
    //查询所有登机桥信息
    @GetMapping("findAllAirportBridge")
    public Result findAllAirportBridge() {
        QueryWrapper<AirportBridge> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        List<AirportBridge> airportBridgeList = airportBridgeService.list(wrapper);
        return Result.ok().data("airportBridgeList", airportBridgeList);
    }
    //分页查询所有登机桥信息
    @GetMapping("getPageAirportBridge/{current}/{limit}")
    public Result getPageAirportBridge(@PathVariable Long current, @PathVariable Long limit) {
        Map<String, Object> map = airportBridgeService.getPageAirportBridge(current, limit);
        return Result.ok().data(map);
    }

    //条件分页查询所有登机桥信息
    @PostMapping("getPageBridgeByCondition/{current}/{limit}")
    public Result getPageBridgeByCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody CommonQueryVo queryVo) {
        Map<String, Object> map = airportBridgeService.getPageBridgeByCondition(current, limit, queryVo);
        return Result.ok().data(map);
    }
}

