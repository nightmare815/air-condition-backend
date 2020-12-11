package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.MsgReceive;
import com.bin.aircondition.mapper.MsgReceiveMapper;
import com.bin.aircondition.service.MsgReceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.aircondition.vo.MessageQueryVo;
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
public class MsgReceiveServiceImpl extends ServiceImpl<MsgReceiveMapper, MsgReceive> implements MsgReceiveService {

    @Override
    public Map<String, Object> getPageReceiveMsg(String deviceId, Long current, Long limit) {

        Page<MsgReceive> page = new Page<>(current, limit);
        QueryWrapper<MsgReceive> wrapper = new QueryWrapper<>();
        wrapper.eq("sender", deviceId);
        wrapper.orderByDesc("gmt_create");
        this.page(page, wrapper);
        List<MsgReceive> records = page.getRecords();
        long total = page.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);

        return map;
    }

    @Override
    public Map<String, Object> getPageReceiveMsgByCondition(String deviceId, Long current, Long limit, MessageQueryVo messageQueryVo) {
        Page<MsgReceive> page = new Page<>(current, limit);
        QueryWrapper<MsgReceive> wrapper = new QueryWrapper<>();
        wrapper.eq("sender", deviceId);
        wrapper.orderByDesc("gmt_create");

        String begin = messageQueryVo.getBegin();
        String end = messageQueryVo.getEnd();
        String type = messageQueryVo.getType();

        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);
        }
        if (!StringUtils.isEmpty(type)){
            wrapper.eq("type", type);
        }

        this.page(page, wrapper);
        List<MsgReceive> records = page.getRecords();
        long total = page.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);

        return map;
    }
}
