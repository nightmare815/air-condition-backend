package com.bin.aircondition.controller;


import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.LevelRelation;
import com.bin.aircondition.service.LevelRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bintian
 * @since 2020-11-24
 */
@RestController
@CrossOrigin
@RequestMapping("/aircondition/levelrelation")
public class LevelRelationController {
    @Autowired
    LevelRelationService levelRelationService;

    @GetMapping("getAllLevel")
    public Result getAllLevel() {
        List<LevelRelation> relations = levelRelationService.getAllLevel();
        return Result.ok().data("data", relations);
    }

}

