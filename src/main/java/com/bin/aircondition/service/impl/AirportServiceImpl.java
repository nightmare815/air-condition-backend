package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.mapper.AirportMapper;
import com.bin.aircondition.service.AirportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class AirportServiceImpl extends ServiceImpl<AirportMapper, Airport> implements AirportService {

    //分页查询机场
    @Override
    public Map<String, Object> getPageAirport(Long current, Long limit) {

        Page<Airport> airportPage = new Page<>(current, limit);
        QueryWrapper<Airport> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        this.page(airportPage, wrapper);
        List<Airport> records = airportPage.getRecords();
        long total = airportPage.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("airportList", records);
        map.put("total", total);
        return map;
    }
}
