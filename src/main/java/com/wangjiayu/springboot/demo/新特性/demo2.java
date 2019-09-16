package com.wangjiayu.springboot.demo.新特性;

import java.util.stream.IntStream;

public class demo2 {

    public static void main(String[] args) {
        IntStream.range(1, 10).forEach(System.out::println);
    }
}
