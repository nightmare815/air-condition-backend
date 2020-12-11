package com.bin.aircondition.service;

import com.bin.aircondition.entity.LevelRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-11-24
 */
public interface LevelRelationService extends IService<LevelRelation> {

    List<LevelRelation> getAllLevel();
}
