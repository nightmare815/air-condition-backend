package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.entity.Gallery;
import com.bin.aircondition.mapper.GalleryMapper;
import com.bin.aircondition.service.GalleryService;
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
public class GalleryServiceImpl extends ServiceImpl<GalleryMapper, Gallery> implements GalleryService {

    //分页查询所有廊道信息
    @Override
    public Map<String, Object> getPageGallery(Long current, Long limit) {

        Page<Gallery> galleryPage = new Page<>(current, limit);
        QueryWrapper<Gallery> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        this.page(galleryPage, wrapper);
        List<Gallery> records = galleryPage.getRecords();
        long total = galleryPage.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("galleryList", records);
        map.put("total", total);
        return map;
    }
}
