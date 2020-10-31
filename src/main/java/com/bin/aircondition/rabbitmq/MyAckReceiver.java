package com.bin.aircondition.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bin.aircondition.entity.Conditioner;
import com.bin.aircondition.entity.MsgReceive;
import com.bin.aircondition.service.ConditionerService;
import com.bin.aircondition.service.MsgReceiveService;
import com.bin.aircondition.vo.ConditionStatusVo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 手动确认消息监听类
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private ConditionerService conditionerService;

    @Value("${myrabbitmq.queue.receiver}")
    private String receiveQueue;

    @Value("${myrabbitmq.queue.register}")
    private String registerQueue;

    //重发最大次数
    @Value("${myrabbitmq.queue.redeliver-count}")
    private int maxCount;

    private int count = 0;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String queue = message.getMessageProperties().getConsumerQueue();
            if (registerQueue.equals(queue)) {
                //如果是来自注册队列的消息
            } else {
                //得到消息体
                String content = new String(message.getBody());
                MsgReceive message_receive = new MsgReceive();
                message_receive.setContent(content);

                //获取消息发送者deviceId
                ConditionStatusVo conditionStatusVo = JSON.parseObject(content, ConditionStatusVo.class);
                String deviceId = conditionStatusVo.getDeviceId();
                message_receive.setSender(deviceId);

                //将空调表中的状态更新
                QueryWrapper<Conditioner> wrapper = new QueryWrapper<>();
                wrapper.eq("device_id", deviceId);
                Conditioner conditioner = conditionerService.getOne(wrapper);
                Conditioner condition1 = new Conditioner();
                BeanUtils.copyProperties(conditionStatusVo, condition1);
                if (conditioner != null) { //如果空调已经存在, 则更新数据
                    conditionerService.update(condition1, wrapper);
                } else { //空调不存在, 就直接将信息放入数据库
                    conditionerService.save(condition1);
                }
                //消息类型
                int type = StringUtils.isEmpty(conditionStatusVo.getSource()) ? 0 : 1;
                message_receive.setType(type);
                //路由键
                String routingKey = message.getMessageProperties().getReceivedRoutingKey();
                message_receive.setRoutingKey(routingKey);
                //将消息放入数据库
                msgReceiveService.save(message_receive);
                //确认消息
                channel.basicAck(deliveryTag, true);
            }
//            System.out.println("消息体: " + new String(message.getBody()));
////            System.out.println("分割的消息体: " + message.toString().split("'")[1]);
//            System.out.println("消费的消息来自队列的名字："+message.getMessageProperties().getConsumerQueue());
//            System.out.println("消费的消息来自路由键："+message.getMessageProperties().getReceivedRoutingKey());
//            channel.basicAck(deliveryTag, true); //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
//            System.out.println("消息已确认~!");
            //channel.basicReject(deliveryTag, true);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
        } catch (Exception e) {
//            System.out.println("消息未确认~!");
            //如果重发次数大于5次, 丢弃此条消息
            if (count > maxCount) {
                channel.basicNack(deliveryTag, false, false);
                System.out.println("重发次数大于"+maxCount+"次, 消息被丢弃!");
                count = 0;
            } else {
                channel.basicReject(deliveryTag, true);
                count++;
            }
            e.printStackTrace();
        }
//        try {
//            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
//            String msg = message.toString();
//            String[] msgArray = msg.split("'");//可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
//            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim(),3);
//            String messageId=msgMap.get("messageId");
//            String messageData=msgMap.get("messageData");
//            String createTime=msgMap.get("createTime");
//            System.out.println("  MyAckReceiver  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
//            System.out.println(message);
//            System.out.println("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());
//            channel.basicAck(deliveryTag, true); //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
////			channel.basicReject(deliveryTag, true);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
//        } catch (Exception e) {
//            channel.basicReject(deliveryTag, true);
//            e.printStackTrace();
//        }
    }

//    //{key=value,key=value,key=value} 格式转换成map
//    private Map<String, String> mapStringToMap(String str,int entryNum ) {
//        str = str.substring(1, str.length() - 1);
//        String[] strs = str.split(",",entryNum);
//        Map<String, String> map = new HashMap<String, String>();
//        for (String string : strs) {
//            String key = string.split("=")[0].trim();
//            String value = string.split("=")[1];
//            map.put(key, value);
//        }
//        return map;
//    }
}