package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.AirportStation;
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
 * @since 2020-10-27
 */
@RestController
@CrossOrigin
@RequestMapping("/aircondition/station")
public class AirportStationController {

    @Autowired
    private AirportStationService stationService;

    //添加廊道
    @PostMapping("addAirportStation")
    public Result addAirportStation(@RequestBody AirportStation airportStation) {

        boolean save = stationService.save(airportStation);
        if(!save) {
            return Result.error();
        }
        return Result.ok();
    }
    //删除廊道
    @DeleteMapping("deleteAirportStation/{id}")
    public Result deleteAirportStation(@PathVariable String id) {

        boolean remove = stationService.removeById(id);
        if(!remove) {
            return Result.error();
        }
        return Result.ok();
    }
    //编辑廊道信息
    @PostMapping("editAirportStation")
    public Result editAirportStation(@RequestBody AirportStation airportStation) {

        boolean update = stationService.updateById(airportStation);
        if(!update) {
            return Result.error();
        }
        return Result.ok();
    }
    //查询所有航站楼信息
    @GetMapping("findAllStation")
    public Result findAllStation() {
        QueryWrapper<AirportStation> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        List<AirportStation> airportStationList = stationService.list(wrapper);
        return Result.ok().data("airportStationList", airportStationList);
    }
    //分页查询所有廊道信息
    @GetMapping("getPageAirportStation/{current}/{limit}")
    public Result getPageAirportStation(@PathVariable Long current, @PathVariable Long limit) {
        Map<String, Object> map = stationService.getPageAirportStation(current, limit);
        return Result.ok().data(map);
    }
    //条件分页查询所有廊道信息
    @PostMapping("getPageStationByCondition/{current}/{limit}")
    public Result getPageStationByCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody CommonQueryVo queryVo) {
        Map<String, Object> map = stationService.getPageStationByCondition(current, limit, queryVo);
        return Result.ok().data(map);
    }

}

