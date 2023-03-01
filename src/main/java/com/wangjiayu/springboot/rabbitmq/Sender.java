//package com.wangjiayu.springboot.rabbitmq;
//
//import com.wangjiayu.springboot.cons.MQConstant;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//public class Sender implements RabbitTemplate.ConfirmCallback {
//
//
//    @Resource
//    private AmqpTemplate amqpTemplate;
//
//
//    @Autowired
//    public Sender(RabbitTemplate amqpTemplate) {
//        this.amqpTemplate = amqpTemplate;
//        amqpTemplate.setConfirmCallback(this);
//    }
//
//
//    public void send() {
//        MessagePostProcessor processor = message -> {
//            message.getMessageProperties().setExpiration("2000");
//            return message;
//        };
//
//        String xx = "测试延迟发送消息";
//        System.out.println("发消息-->" + xx);
//        amqpTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE, MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, xx, processor);
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println(correlationData);
//        System.out.println(ack);
//        System.out.println(cause);
//        if (ack) {
//            System.out.println("消费成功");
//        } else {
//            System.out.println("消费失败");
//        }
//
//    }
//}
