package com.wangjiayu.springboot.demo.观察者模式;

import com.google.common.eventbus.EventBus;

public class test {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("123123");
    }
}
