package com.bin.aircondition.service;

import com.bin.aircondition.entity.Airport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.CommonQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
public interface AirportService extends IService<Airport> {

    //分页查询机场
    Map<String, Object> getPageAirport(Long current, Long limit);

    Map<String, Object> getPageAirportByCondition(Long current, Long limit, CommonQueryVo queryVo);
}
