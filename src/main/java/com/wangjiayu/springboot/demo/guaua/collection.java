package com.wangjiayu.springboot.demo.guaua;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.Map;

public class collection {

    public static void main(String[] args) {
        //Multiset();
        //MultiMap();
        BitMap();
    }

    private static void BitMap() {
        Map<Integer, String> idToName = Maps.newHashMap();
        idToName.put(42, "Bob");
        idToName.put(42, "Bob");
        System.out.println(idToName);


        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("sina", "sina.com");
        biMap.put("qq", "qq.com");
        biMap.put("sina", "sina.cn"); //会覆盖原来的value
       /*
         在BiMap中,如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常
         如果对特定值,你想要强制替换它的键，请使用 BiMap.forcePut(key, value)
        */
        //biMap.put("tecent", "qq.com"); //抛出异常
        biMap.forcePut("tecent", "qq.com"); //强制替换key
        System.out.println(biMap);
        System.out.println(biMap.get("tecent"));
        System.out.println(biMap.inverse().get("sina.com")); //通过value找key
        System.out.println(biMap.inverse().inverse() == biMap); //true
    }

    private static void MultiMap() {
        Multimap<String, Integer> map = HashMultimap.create(); //Multimap是把键映射到任意多个值的一般方式
        map.put("a", 1); //key相同时不会覆盖原value
        map.put("a", 2);
        map.put("a", 3);
        map.put("b", 3);
        System.out.println(map); //{a=[1, 2, 3]}
        System.out.println(map.get("a")); //返回的是集合
        System.out.println(map.size()); //返回所有”键-单个值映射”的个数,而非不同键的个数
        System.out.println(map.keySet().size()); //返回不同key的个数
        Map<String, Collection<Integer>> mapView = map.asMap();
        System.out.println(mapView);
    }

    private static void Multiset() {
        Multiset<String> set = LinkedHashMultiset.create();
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("b");
        set.add("b");
        set.setCount("a", 5); //添加或删除指定元素使其在集合中的数量是count

        System.out.println(set.count("a")); //给定元素在Multiset中的计数
        System.out.println(set);
        System.out.println(set.size()); //所有元素计数的总和,包括重复元素
        System.out.println(set.elementSet());
        System.out.println(set.elementSet().size()); //所有元素计数的总和,不包括重复元素
        set.clear(); //清空集合
        System.out.println(set);
    }
}
