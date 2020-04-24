package com.wangjiayu.springboot.demo.guaua;


import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class demo1 {

    public static void main(String[] args) {
        //optional();
        //joiner();
        splitterDemo();
    }

    private static void splitterDemo() {
         /*
         on():指定分隔符来分割字符串
         limit():当分割的子字符串达到了limit个时则停止分割
         fixedLength():根据长度来拆分字符串
         trimResults():去掉子串中的空格
         omitEmptyStrings():去掉空的子串
         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
        */
        System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d"));//[ a, b, c,d]
        System.out.println(Splitter.fixedLength(3).split("1 2 3"));//[1 2,  3]
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
        System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));//[1, 2, 3]
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3")); //[1, 2, 3],默认的连接符是,
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}
    }

    private static void joiner() {
        /*
         on:制定拼接符号，如：test1-test2-test3 中的 “-“ 符号
         skipNulls()：忽略NULL,返回一个新的Joiner实例
         useForNull(“Hello”)：NULL的地方都用字符串”Hello”来代替
        */
        StringBuilder sb = new StringBuilder();
        Joiner.on(",").skipNulls().appendTo(sb, "Hello", "guava");
        System.out.println(sb);
        System.out.println(Joiner.on(",").useForNull("嘻嘻嘻嘻嘻").join(1, null, 3));
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1, 2, 3, 4, null, 6)));

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
    }

    private static void optional() {
        Integer value1 = null;
        Integer value2 = 10;
       /*创建指定引用的Optional实例，若引用为null则快速失败返回absent()
         absent()创建引用缺失的Optional实例
        */
        Optional<Integer> a = Optional.fromNullable(value1);
        Optional<Integer> b = Optional.of(value2); //返回包含给定的非空引用Optional实例
        System.out.println(sum(a, b));
    }

    private static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //isPresent():如果Optional包含非null的引用（引用存在），返回true
        System.out.println("First param is present: " + a.isPresent());
        System.out.println("Second param is present: " + b.isPresent());
        Integer value1 = a.or(0);  //返回Optional所包含的引用,若引用缺失,返回指定的值
        Integer value2 = b.get(); //返回所包含的实例,它必须存在,通常在调用该方法时会调用isPresent()判断是否为null
        return value1 + value2;
    }
}
