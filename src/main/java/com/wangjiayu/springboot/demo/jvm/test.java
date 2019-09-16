package com.wangjiayu.springboot.demo.jvm;

import java.util.ArrayList;

public class test {

    public byte[] placeholder = new byte[64 * 1024];

    public static void main(String[] args) throws InterruptedException {
        fillheap(10000000);
    }

    private static void fillheap(int i) throws InterruptedException {
        ArrayList<Object> objects = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            Thread.sleep(1);
            objects.add(new test());
        }
        System.gc();
    }
}
