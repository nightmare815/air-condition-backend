package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Device;
import com.bin.aircondition.exceptionhandler.MyException;
import com.bin.aircondition.service.DeviceService;
import com.bin.aircondition.vo.DeviceQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    //获取单个设备信息
    @GetMapping("getDeviceById/{deviceId}")
    public Result getDeviceById(@PathVariable String deviceId) {

        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("device_id", deviceId);
        Device device = deviceService.getOne(wrapper);
        List<Device> devices = new ArrayList<>();
        devices.add(device);
        return Result.ok().data("device", devices);
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
    //添加设备, 同时添加topic
    @PostMapping("addDevice")
    public Result addDevice(@RequestBody Device device) {
        Device device1 = deviceService.getOne(new QueryWrapper<Device>().eq("device_id", device.getDeviceId()));
        if(device1 == null) {
            boolean save = deviceService.save(device);
        }
        return Result.ok();
    }
}

