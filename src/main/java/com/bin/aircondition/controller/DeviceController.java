package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Device;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.vo.DeviceQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/aircondition/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 获取所有设备
     * @return
     */
    @GetMapping("getAllDevice")
    public Result getAllDevice() {

        QueryWrapper<Device> deviceQueryWrapper = new QueryWrapper<>();
        deviceQueryWrapper.orderByDesc("gmt_create");
        List<Device> deviceList = deviceService.list(deviceQueryWrapper);
        return Result.ok().data("deviceList", deviceList);
    }

    //分页查询
    @GetMapping("getPageDevice/{current}/{limit}")
    public Result getPageDevice(@PathVariable Long current, @PathVariable Long limit) {

        Map<String, Object> map = deviceService.getPageDevice(current, limit);
        return Result.ok().data(map);
    }

    //分页条件查询
    @PostMapping("getPageConditional/{current}/{limit}")
    public Result getPageConditional(@RequestBody(required = false) DeviceQueryVo deviceQueryVo, @PathVariable Long current, @PathVariable Long limit) {

        Map<String, Object> map = deviceService.getPageConditional(deviceQueryVo, current, limit);
        return Result.ok().data(map);
    }
    //删除设备
    @DeleteMapping("deleteDevice/{id}")
    public Result deleteDevice(@PathVariable String id) {

        boolean remove = deviceService.removeById(id);
        if(!remove) {
            return Result.error();
        }
        return Result.ok();
    }
    //添加设备
    @PostMapping("addDevice")
    public Result addDevice(@RequestBody Device device) {
        boolean save = deviceService.save(device);
        if(!save) {
            return Result.error().message("添加设备失败!");
        }
        return Result.ok().message("添加成功");
    }
}

