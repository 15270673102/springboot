package com.wangjiayu.springboot.demo.单例;

public class Demo1 implements Cloneable{

    private static volatile Demo1 test = new Demo1();

    private Demo1() {
    }

    public static Demo1 getInstance() {
        return test;
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(Demo1.getInstance());
        System.out.println(Demo1.getInstance());

        System.out.println(Demo1.getInstance().clone());
    }

}
