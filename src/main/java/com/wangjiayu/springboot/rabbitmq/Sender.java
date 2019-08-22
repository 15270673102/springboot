package com.wangjiayu.springboot.rabbitmq;

import com.wangjiayu.springboot.cons.MQConstant;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Sender {


    @Resource
    private AmqpTemplate amqpTemplate;

    public void send() {
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("20000");
                return message;
            }
        };
        String xx = "测试延迟发送消息";
        System.out.println("发消息-->" + xx);
        amqpTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE, MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, xx, processor);
    }

}
