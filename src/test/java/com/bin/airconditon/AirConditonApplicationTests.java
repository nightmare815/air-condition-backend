package com.bin.airconditon;

import com.bin.aircondition.AirConditonApplication;
import com.bin.aircondition.service.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootTest(classes = AirConditonApplication.class)
class AirConditonApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicService topicService;

    @Test
    public void test01() throws InterruptedException {
            rabbitTemplate.convertAndSend("amq.topic", ".gree.register", "hello world");
//            System.out.println("=============================");
    }

    @Test
    public void test02() {
        String[] array = topicService.findAllRoutingKey().toArray(new String[0]);
        String routingList = StringUtils.join(array, ",");
        System.out.println(routingList);

    }

}
