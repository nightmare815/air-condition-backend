package com.bin.aircondition.service;

import com.bin.aircondition.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.DeviceQueryVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
public interface DeviceService extends IService<Device> {

    //分页查询
    Map<String, Object> getPageDevice(Long current, Long limit);

    //分页条件查询
    Map<String, Object> getPageConditional(DeviceQueryVo deviceQueryVo, Long current, Long limit);
}
