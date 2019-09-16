package com.wangjiayu.springboot.demo.futureDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class demo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<RealData> list = new ArrayList<>();
        list.add(new RealData());
        list.add(new RealData());
        List<Future<String>> futures = executorService.invokeAll(list);

        System.out.println("111111111");
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }
}
