package com.wangjiayu.springboot.demo.zookeeper.分布式锁;

public interface ZkLock {

    //获取到锁的资源
    public void getLock();

    // 释放锁
    public void unLock();
}
