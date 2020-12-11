package com.bin.aircondition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.entity.LevelRelation;
import com.bin.aircondition.mapper.LevelRelationMapper;
import com.bin.aircondition.service.LevelRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bintian
 * @since 2020-11-24
 */
@Service
public class LevelRelationServiceImpl extends ServiceImpl<LevelRelationMapper, LevelRelation> implements LevelRelationService {

    @Override
    public List<LevelRelation> getAllLevel() {
        List<LevelRelation> levelRelations = this.list();
        //找到父id为0的节点
        List<LevelRelation> collect = levelRelations.stream().filter(levelRelation -> {
            return "0".equals(levelRelation.getParentId());
        }).map(item -> {
            item.setChildren(getChildren(item, levelRelations));
            return item;
        }).collect(Collectors.toList());
        return collect;
    }

    private List<LevelRelation> getChildren(LevelRelation root, List<LevelRelation> all) {
        List<LevelRelation> collect = all.stream().filter(item1 -> {
            return item1.getParentId().equals(root.getId());
        }).map(item2 -> {
            item2.setChildren(getChildren(item2, all));
            return item2;
        }).collect(Collectors.toList());
        return collect;
    }
}
