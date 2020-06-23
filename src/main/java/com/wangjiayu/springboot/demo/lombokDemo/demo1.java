package com.wangjiayu.springboot.demo.lombokDemo;

import lombok.NonNull;
import lombok.val;

import java.util.*;

public class demo1 {


    public static void main(String[] args) {
        val sets = new HashSet<String>();
        val lists = new ArrayList<String>();
        val maps = new HashMap<String, String>();
        //=>相当于如下
        final Set<String> sets2 = new HashSet<>();
        final List<String> lists2 = new ArrayList<>();
        final Map<String, String> maps2 = new HashMap<>();

        notNullExample(null);
    }


    public static void notNullExample(@NonNull String string) {
        string.length();

    }

}
