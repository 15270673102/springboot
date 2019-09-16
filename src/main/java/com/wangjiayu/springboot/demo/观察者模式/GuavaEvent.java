package com.wangjiayu.springboot.demo.观察者模式;

import com.google.common.eventbus.Subscribe;

public class GuavaEvent {

    @Subscribe
    public void name(String str) {
        System.out.println(str);
    }
}
