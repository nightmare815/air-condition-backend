package com.bin.aircondition.service;

import com.bin.aircondition.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
public interface TopicService extends IService<Topic> {

    //获取所有主题名
    List<String> findAllRoutingKey();

}
