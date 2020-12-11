package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.entity.AirportBridge;
import com.bin.aircondition.entity.AirportStation;
import com.bin.aircondition.mapper.AirportBridgeMapper;
import com.bin.aircondition.service.AirportBridgeService;
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
 * @since 2020-11-02
 */
@Service
public class AirportBridgeServiceImpl extends ServiceImpl<AirportBridgeMapper, AirportBridge> implements AirportBridgeService {

    @Override
    public Map<String, Object> getPageAirportBridge(Long current, Long limit) {
        Page<AirportBridge> airportBridgePage = new Page<>(current, limit);
        QueryWrapper<AirportBridge> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        this.page(airportBridgePage, wrapper);
        List<AirportBridge> records = airportBridgePage.getRecords();
        long total = airportBridgePage.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("bridgeList", records);
        map.put("total", total);
        return map;
    }

    @Override
    public Map<String, Object> getPageBridgeByCondition(Long current, Long limit, CommonQueryVo queryVo) {
        Page<AirportBridge> page = new Page<>(current, limit);
        QueryWrapper<AirportBridge> wrapper = new QueryWrapper<>();
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
        List<AirportBridge> records = page.getRecords();
        long total = page.getTotal();
        long size = page.getSize();

        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);
        map.put("size", size);
        return map;
    }
}
