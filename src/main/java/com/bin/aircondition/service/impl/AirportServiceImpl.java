package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.mapper.AirportMapper;
import com.bin.aircondition.service.AirportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.aircondition.vo.CommonQueryVo;
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

    @Override
    public Map<String, Object> getPageAirportByCondition(Long current, Long limit, CommonQueryVo queryVo) {

        Page<Airport> page = new Page<>(current, limit);
        QueryWrapper<Airport> wrapper = new QueryWrapper<>();
        String key = queryVo.getKey();
        String begin = queryVo.getBegin();
        String end = queryVo.getEnd();

        if(!StringUtils.isEmpty(key)) {
            wrapper.and(wrap->{
                wrap.eq("id", key).or().like("name", key);
            });
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        this.page(page, wrapper);
        List<Airport> records = page.getRecords();
        long total = page.getTotal();
        long size = page.getSize();

        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);
        map.put("size", size);
        return map;
    }
}
