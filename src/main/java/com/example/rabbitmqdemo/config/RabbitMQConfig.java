package com.example.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 新商家审核通过->new_merchant_queue  ->  死信交换机 ->  lock_merchant_dead_queue
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 死信队列
     */
    public static final  String LOCK_MERCHANT_DEAD_QUEUE = "lock_merchant_dead_queue";

    /**
     * 死信交换机
     */
    public static final String LOCK_MERCHANT_DEAD_EXCHANGE = "lock_merchant_dead_exchange";

    /**
     * 死信routingkey
     */
    public static final String LOCK_MERCHANT_ROUTING_KEY = "lock_merchant_routing_key";

    /**
     * 创建死信交换机
     * @return
     */
    @Bean
    public Exchange lockMerchantExchange(){
        return new TopicExchange(LOCK_MERCHANT_DEAD_EXCHANGE,true,false);
    }

    /**
     * 创建死信队列
     * @return
     */
    @Bean
    public Queue lockMerchantDeadQueue(){
        return QueueBuilder.durable(LOCK_MERCHANT_DEAD_QUEUE).build();
    }

    /**
     * 绑定死信交换机和死信队列
     * @return
     */
    @Bean
    public Binding lockMerchantBinding(){
        return new Binding(LOCK_MERCHANT_DEAD_QUEUE,Binding.DestinationType.QUEUE,LOCK_MERCHANT_DEAD_EXCHANGE,LOCK_MERCHANT_ROUTING_KEY,null);
    }

    /**
     * 普通队列，绑定死信交换机
     */
    public static final  String NEW_MERCHANT_QUEUE = "new_merchant_queue";
    /**
     * 绑定普通交换机
     */
    public static final String NEW_MERCHANT_EXCHANGE = "new_merchant_exchange";
    /**
     * routingkey
     */
    public static final String NEW_MERCHANT_ROUTING_KEY = "new_merchant_routing_key";

    /**
     * 创建普通交换机
     * @return
     */
    @Bean
    public Exchange newMerchantExchange(){
        return new TopicExchange(NEW_MERCHANT_EXCHANGE,true,false);
    }

    /**
     * 创建普通队列绑定死信交换机
     * 修改队列需要先删除原有的队列再创建
     * @return
     */
    @Bean
    public Queue newMerchantQueue(){
        Map<String,Object> args = new HashMap<>(3);
        args.put("x-dead-letter-exchange",LOCK_MERCHANT_DEAD_EXCHANGE);
        args.put("x-dead-letter-routing-key",LOCK_MERCHANT_ROUTING_KEY);
        args.put("x-message-ttl",10000);//10s

        return QueueBuilder.durable(NEW_MERCHANT_QUEUE).withArguments(args).build();
    }

    /**
     * 绑定交换机和队列
     * @return
     */
    @Bean
    public Binding newMerchantBinding(){
        return new Binding(NEW_MERCHANT_QUEUE,Binding.DestinationType.QUEUE,NEW_MERCHANT_EXCHANGE,NEW_MERCHANT_ROUTING_KEY,null);
    }
/////////////////////////////////////////////////////////////////////////////////////

//    public static final String EXCHANGE_NAME = "order_exchange";
//    public static final String QUEUE_NAME = "order_queue";
//    /**
//     * topic交换机
//     * @return
//     */
//    @Bean
//    public Exchange orderExchange() {
//        //durable是否持久化
//        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
//        //return new TopicExchange(EXCHANGE_NAME, true, false);
//    }
//
//    /**
//     * 队列
//     * @return
//     */
//    @Bean
//    public Queue orderQueue() {
//        return QueueBuilder.durable(QUEUE_NAME).build();
//        //return new Queue(QUEUE_NAME, true, false, false, null);
//    }
//
//    /**
//     * 交换机和队列绑定关系
//     */
//    @Bean
//    public Binding orderBinding(Queue queue, Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("order.#").noargs();
//        //return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "order.#", null);
//    }

}
