package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.service.AirportService;
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
@RequestMapping("/aircondition/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    //添加机场
    @PostMapping("addAirport")
    public Result addAirport(@RequestBody Airport airport) {

        boolean save = airportService.save(airport);
        if(!save) {
            return Result.error();
        }
        return Result.ok();
    }
    //删除机场
    @DeleteMapping("deleteAirport/{id}")
    public Result deleteAirport(@PathVariable String id) {

        boolean remove = airportService.removeById(id);
        if(!remove) {
            return Result.error();
        }
        return Result.ok();
    }
    //编辑机场信息
    @PostMapping("editAirport")
    public Result editAirport(@RequestBody Airport airport) {

        boolean update = airportService.updateById(airport);
        if(!update) {
            return Result.error();
        }
        return Result.ok();
    }
    //查询所有机场信息
    @GetMapping("findAllAirport")
    public Result findAllAirport() {
        QueryWrapper<Airport> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        List<Airport> airportList = airportService.list(wrapper);
        return Result.ok().data("airportList", airportList);
    }

    //分页查询所有机场信息
    @GetMapping("getPageAirport/{current}/{limit}")
    public Result getPageAirport(@PathVariable Long current, @PathVariable Long limit) {
        Map<String, Object> map = airportService.getPageAirport(current, limit);
        return Result.ok().data(map);
    }

    //条件分页查询所有机场信息
    @PostMapping("getPageAirportByCondition/{current}/{limit}")
    public Result getPageAirportByCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody CommonQueryVo queryVo) {
        Map<String, Object> map = airportService.getPageAirportByCondition(current, limit, queryVo);
        return Result.ok().data(map);
    }
}

