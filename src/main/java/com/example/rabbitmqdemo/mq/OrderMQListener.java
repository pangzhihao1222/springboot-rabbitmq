package com.example.rabbitmqdemo.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//@RabbitListener(queues = "order_queue")
public class OrderMQListener {
//    @RabbitHandler
    public void messageHandler(String body, Message message, Channel channel){
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("deliveryTag="+deliveryTag);
        System.out.println("message="+message.toString());
        System.out.println("body="+body);

        try {
            //告诉broker,消息已经被确认并成功
            //channel.basicAck(deliveryTag,false);
            //告诉broker,消息拒绝确认并返回队列重复消费(可批量)
            //channel.basicNack(deliveryTag,false,true);
            //告诉broker,消息拒绝确认并返回队列重复消费
            channel.basicReject(deliveryTag,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
