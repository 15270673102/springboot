package com.wangjiayu.springboot.demo.分页;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.PageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        int pageNo = 2;
        int pageSize = 5;
        int[] ints = PageUtil.transToStartEnd(pageNo, pageSize);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(122);
        list.add(12);
        list.add(1);
        list.add(1);
        list.add(1);

        list.add(1444);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(16666);

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(567);
        List<Integer> integers = list.subList(ints[0], ints[1]);
        Console.log(new Test1(integers, list.size(), pageNo, pageSize));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Test1 {
        private Object name;
        private Integer total;
        private Integer pageNo;
        private Integer pageSize;
    }

}
