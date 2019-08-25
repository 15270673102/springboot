package com.wangjiayu.springboot.service;

import java.util.concurrent.Future;

public interface IAsyncService {

    Future<String> test1() throws InterruptedException;

}
