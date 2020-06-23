package com.wangjiayu.springboot.util;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class Demo {

    public static void main(String[] args) {
        ArrayList<User> list = Lists.newArrayList();
        list.add(new User(1, 0, "xxxx"));
        list.add(new User(2, 0, "xxxx"));
        list.add(new User(3, 1, "xxxx"));
        list.add(new User(4, 3, "xxxx"));
        list.add(new User(5, 2, "xxxx"));
        list.add(new User(6, 5, "xxxx"));
        list.add(new User(7, 6, "xxxx"));

        Set<Integer> set = list.stream().map(User::getPid).collect(Collectors.toSet());

        list.stream()
                .filter(user -> !set.contains(user.getId()))
                .collect(Collectors.toList())
                .forEach(System.err::println);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class User {

        private Integer id;
        private Integer pid;
        private String name;
    }

}
