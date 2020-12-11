package com.bin.aircondition.controller;


import com.bin.aircondition.commonutils.Result;
import com.bin.aircondition.entity.Topic;
import com.bin.aircondition.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/aircondition/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * 添加主题
     */
    @PostMapping("addTopic")
    public Result addTopic(@RequestBody Topic topic) {
        Boolean isSuccess = topicService.save(topic);
        return Result.ok();
    }

    /**
     * 删除主题
     */
    @DeleteMapping("removeTopic")
    public Result removeTopic(String id) {
        Boolean isSuccess = topicService.removeById(id);
        if(!isSuccess) return Result.error();
        return Result.ok();
    }
    /**
     * 查询所有主题
     */
    @GetMapping("finAllTopic")
    public Result finAllTopic() {
        List<Topic> list = topicService.list();
        return Result.ok().data("topicList", list);
    }
    /**
     * 获取所有路由
     */
    @GetMapping("findAllTopicName")
    public List<String> findAllTopicName() {
        List<String> RoutingKeyList = topicService.findAllRoutingKey();
        return RoutingKeyList;
    }
}

