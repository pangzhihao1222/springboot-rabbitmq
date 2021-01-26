package com.example.rabbitmqdemo;

import com.example.rabbitmqdemo.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqDemoApplicationTests {

	@Autowired
	private RabbitTemplate template;


//	@Test
//	void testSend(){
//
//			template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"order.new","新订单");
//
//	}
//
//	/**
//	 * 处理发送者到交换机
//	 */
//	@Test
//	void testConfirmCallback(){
//		//配置发送端到broker交换机的可靠性
//		template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//			/**
//			 *
//			 * @param correlationData 配置
//			 * @param ack 交换机是否收到消息，true是成功，false是失败
//			 * @param cause 失败的原因
//			 */
//			@Override
//			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//				System.out.println("ConfirmCallback===>");
//				System.out.println("ConfirmCallback===>"+correlationData);
//				System.out.println("ConfirmCallback===>"+ack);
//				System.out.println("ConfirmCallback===>"+cause);
//				if(ack){
//					System.out.println("发送成功");
//					//更新数据库消息的状态为成功
//				}else{
//					System.out.println("发送失败，记录到日志或数据库");
//					//更新数据库消息的状态为失败
//				}
//			}
//		});
//
//		//数据库新增一个消息记录，状态是发送 TODO
//		//发送消息
//		template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"order.new","新订单");
//	}
//
//
//	/**
//	 * 处理交换机到路由队列
//	 */
//	@Test
//	void testReturnCallback(){
//		template.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//			@Override
//			public void returnedMessage(ReturnedMessage returnedMessage) {
//				int code = returnedMessage.getReplyCode();
//				System.out.println("code="+code);
//				System.out.println("returnedMessage="+returnedMessage.toString());
//			}
//		});
//		//数据库新增一个消息记录，状态是发送 TODO
//		//发送消息
//		template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"oo.order.new","新订单");
//	}

}
