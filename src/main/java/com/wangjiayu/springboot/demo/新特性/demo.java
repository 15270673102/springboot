package com.wangjiayu.springboot.demo.新特性;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class demo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> collect = list.parallelStream().map(x -> {
            System.out.println("123");
            return x + 1;
        }).collect(Collectors.toList());

        System.out.println(list);
        System.out.println(collect);


        Random random = new Random();
        int[] ints = new int[10];
        Arrays.setAll(ints, (i) -> random.nextInt());
        Arrays.stream(ints).forEach(System.out::println);
    }
}
