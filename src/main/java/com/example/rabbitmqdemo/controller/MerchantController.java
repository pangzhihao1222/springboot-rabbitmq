package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/admin/merchant")
@RestController
public class MerchantController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("check")
    public Object check(){
        //修改数据库的商家账号状态
        rabbitTemplate.convertAndSend(RabbitMQConfig.NEW_MERCHANT_EXCHANGE,RabbitMQConfig.NEW_MERCHANT_ROUTING_KEY,"商家账号通过审核");
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","账号审核通过,请10秒内上传一个商品");
        return map;

    }
}
