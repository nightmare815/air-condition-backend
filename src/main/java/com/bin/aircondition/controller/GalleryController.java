package com.bin.aircondition.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Airport;
import com.bin.aircondition.entity.Gallery;
import com.bin.aircondition.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
@RestController
@CrossOrigin
@RequestMapping("/aircondition/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    //添加廊道
    @PostMapping("addGallery")
    public Result addGallery(@RequestBody Gallery gallery) {

        boolean save = galleryService.save(gallery);
        if(!save) {
            return Result.error();
        }
        return Result.ok();
    }
    //删除廊道
    @DeleteMapping("deleteGallery/{id}")
    public Result deleteGallery(@PathVariable String id) {

        boolean remove = galleryService.removeById(id);
        if(!remove) {
            return Result.error();
        }
        return Result.ok();
    }
    //编辑廊道信息
    @PostMapping("editGallery")
    public Result editGallery(@RequestBody Gallery gallery) {

        boolean update = galleryService.updateById(gallery);
        if(!update) {
            return Result.error();
        }
        return Result.ok();
    }
    //查询所有廊道信息
    @GetMapping("findAllGallery")
    public Result findAllGallery() {
        QueryWrapper<Gallery> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        List<Gallery> galleryList = galleryService.list(wrapper);
        return Result.ok().data("galleryList", galleryList);
    }
    //分页查询所有廊道信息
    @GetMapping("getPageGallery/{current}/{limit}")
    public Result getPageGallery(@PathVariable Long current, @PathVariable Long limit) {
        Map<String, Object> map = galleryService.getPageGallery(current, limit);
        return Result.ok().data(map);
    }
}

