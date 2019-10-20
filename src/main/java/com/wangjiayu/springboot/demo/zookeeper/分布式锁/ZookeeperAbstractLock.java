package com.wangjiayu.springboot.demo.zookeeper.分布式锁;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements ZkLock {

    // zk连接地址
    private static final String CONNECTSTRING = "localhost:2181";
    // 创建zk连接
    protected ZkClient zkClient = new ZkClient(CONNECTSTRING, 5000);
    protected static final String PATH = "/lock";

    @Override
    public void getLock() {
        if (tryLock()) {
            System.out.println("##获取lock锁的资源####");
        } else {
            // 等待
            waitLock();
            // 重新获取锁资源
            getLock();
        }
    }

    // 获取锁资源
    abstract boolean tryLock();

    // 等待
    abstract void waitLock();

    @Override
    public void unLock() {
        if (zkClient != null) {
            zkClient.close();
            System.out.println("释放锁资源...");
        }
    }
}
