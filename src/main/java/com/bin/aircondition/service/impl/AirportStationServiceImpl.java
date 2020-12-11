package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.entity.AirportStation;
import com.bin.aircondition.mapper.AirportStationMapper;
import com.bin.aircondition.service.AirportStationService;
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
public class AirportStationServiceImpl extends ServiceImpl<AirportStationMapper, AirportStation> implements AirportStationService {

    //分页查询所有廊道信息
    @Override
    public Map<String, Object> getPageAirportStation(Long current, Long limit) {

        Page<AirportStation> galleryPage = new Page<>(current, limit);
        QueryWrapper<AirportStation> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        this.page(galleryPage, wrapper);
        List<AirportStation> records = galleryPage.getRecords();
        long total = galleryPage.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("stationList", records);
        map.put("total", total);
        return map;
    }

    @Override
    public Map<String, Object> getPageStationByCondition(Long current, Long limit, CommonQueryVo queryVo) {
        Page<AirportStation> page = new Page<>(current, limit);
        QueryWrapper<AirportStation> wrapper = new QueryWrapper<>();
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
        List<AirportStation> records = page.getRecords();
        long total = page.getTotal();
        long size = page.getSize();

        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);
        map.put("size", size);
        return map;
    }
}
