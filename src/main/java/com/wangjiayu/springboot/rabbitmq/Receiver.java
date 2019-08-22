package com.wangjiayu.springboot.rabbitmq;

import com.wangjiayu.springboot.cons.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class Receiver {


    @RabbitHandler
    public void handler(String content) {
        System.out.println("接收到：" + content);
    }

}