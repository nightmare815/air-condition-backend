package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.Device;
import com.bin.aircondition.mapper.DeviceMapper;
import com.bin.aircondition.service.DeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.aircondition.vo.DeviceQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    //分页查询
    @Override
    public Map<String, Object> getPageDevice(Long current, Long limit) {
        //创建分页对象
        Page<Device> devicePage = new Page<>(current, limit);
        //将分页后的device封装到devicePage
        this.page(devicePage);
        long total = devicePage.getTotal();
        List<Device> records = devicePage.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("deviceList",records);
        return map;
    }

    //分页条件查询
    @Override
    public Map<String, Object> getPageConditional(DeviceQueryVo deviceQueryVo, Long current, Long limit) {

        //创建分页
        Page<Device> devicePage = new Page<>(current, limit);
        //创建查询wrapper
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        String airport = deviceQueryVo.getAirport();
        String gallery = deviceQueryVo.getGallery();
        String deviceId = deviceQueryVo.getDeviceId();
        String begin = deviceQueryVo.getBegin();
        String end = deviceQueryVo.getEnd();
        if (!StringUtils.isEmpty(airport)) {
            wrapper.eq("airport_id", airport);
        }
        if (!StringUtils.isEmpty(gallery)) {
            wrapper.eq("gallery_id", gallery);
        }
        if (!StringUtils.isEmpty(deviceId)) {
            wrapper.eq("device_id", deviceId);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        //根据时间降序
        wrapper.orderByDesc("gmt_create");
        this.page(devicePage, wrapper);
        //取出查询出来的数据
        List<Device> records = devicePage.getRecords();
        long total = devicePage.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("deviceList", records);
        map.put("total", total);
        return map;
    }
}
