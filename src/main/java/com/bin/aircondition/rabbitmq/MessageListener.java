//package com.bin.aircondition.rabbitmq;
//
//import com.bin.aircondition.service.TopicService;
//import com.rabbitmq.client.Channel;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 消息监听器
// */
//@Component
//public class MessageListener implements ChannelAwareMessageListener {
//
//
//    //消费者, 用来监听mq队列的消息
//    @RabbitListener(bindings = {
//            @QueueBinding(
//                    value = @Queue(exclusive = "false",autoDelete = "true",durable = "true"), //创建临时队列
//                    exchange = @Exchange(value = "amq.topic", type = "topic"),  //指定交换机名称和类型
//                    key = {".gree.register"}
//            )
//    })
//    @Override
//    public void onMessage(Message message, Channel channel) throws Exception {
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        try {
////            System.out.println("消息体: " + new String(message.getBody()));
//////            System.out.println("分割的消息体: " + message.toString().split("'")[1]);
////            System.out.println("消费的消息来自队列的名字："+message.getMessageProperties().getConsumerQueue());
////            System.out.println("消费的消息来自路由键："+message.getMessageProperties().getReceivedRoutingKey());
////            channel.basicAck(deliveryTag, true); //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
////            System.out.println("消息已确认~!");
//			//channel.basicReject(deliveryTag, true);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
//        } catch (Exception e) {
//            System.out.println("消息未确认~!");
//            channel.basicReject(deliveryTag, true);
//            e.printStackTrace();
//        }
//    }
//}
