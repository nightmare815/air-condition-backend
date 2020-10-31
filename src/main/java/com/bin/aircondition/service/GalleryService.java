package com.bin.aircondition.service;

import com.bin.aircondition.entity.Gallery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
public interface GalleryService extends IService<Gallery> {

    //分页查询所有廊道信息
    Map<String, Object> getPageGallery(Long current, Long limit);
}
