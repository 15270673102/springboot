package com.wangjiayu.springboot.demo.futureDemo;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {

    @Override
    public String call() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

}
