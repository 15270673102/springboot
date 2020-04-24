package com.wangjiayu.springboot.demo.guaua.eventBus;


import com.google.common.eventbus.EventBus;

public class EventBusDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("jack");
        eventBus.register(new EventListener()); //注册订阅者
        eventBus.register(new MultiEventListener());
        eventBus.post(new OrderEvent("hello")); //发布事件
        eventBus.post(new OrderEvent("world"));
        eventBus.post("!");
    }
}
