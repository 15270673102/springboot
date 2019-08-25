package com.wangjiayu.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncServiceImpl implements IAsyncService {


    @Override
    @Async
    public Future<String> test1() throws InterruptedException {
        System.out.println("test1  start" + System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("test1  end" + System.currentTimeMillis());
        return new AsyncResult<>("123");
    }

}
