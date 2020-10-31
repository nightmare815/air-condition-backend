package com.bin.aircondition.service.impl;

import com.bin.aircondition.entity.Topic;
import com.bin.aircondition.mapper.TopicMapper;
import com.bin.aircondition.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Override
    public List<String> findAllRoutingKey() {
        List<String> list = new ArrayList<>();
        List<Topic> topics = this.list();
        for (Topic topic : topics) {
            if(!StringUtils.isEmpty(topic.getRoutingKey())) {
                list.add(topic.getRoutingKey());
            }
        }
        return list;
    }
}
