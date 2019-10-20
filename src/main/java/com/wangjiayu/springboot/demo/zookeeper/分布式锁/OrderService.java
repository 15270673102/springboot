package com.wangjiayu.springboot.demo.zookeeper.分布式锁;

public class OrderService implements Runnable {

    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    private ZkLock lock = new ZookeeperDistrbuteLock();

    @Override
    public void run() {
        try {
            lock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + ",生成订单ID:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unLock();
        }
    }

    public static void main(String[] args) {
        System.out.println("####生成唯一订单号###");
        //OrderService orderService = new OrderService();
        for (int i = 0; i < 100; i++) {
            new Thread(new OrderService()).start();
        }
    }
}
