package com.bin.aircondition.service;

import com.bin.aircondition.entity.AirportStation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.CommonQueryVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
public interface AirportStationService extends IService<AirportStation> {

    //分页查询所有廊道信息
    Map<String, Object> getPageAirportStation(Long current, Long limit);

    Map<String, Object> getPageStationByCondition(Long current, Long limit, CommonQueryVo queryVo);
}
