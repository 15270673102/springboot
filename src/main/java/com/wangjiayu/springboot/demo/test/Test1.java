package com.wangjiayu.springboot.demo.test;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {


    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user1 = new User(1, 0, "广东");
        User user2 = new User(2, 0, "湖南");
        User user3 = new User(3, 1, "深圳");
        User user4 = new User(4, 3, "郴州");
        User user5 = new User(4, 3, "福田");
        User user6 = new User(5, 3, "南山");
        User user7 = new User(6, 3, "宝安");
        User user8 = new User(7, 6, "固戍社区");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);
        list.add(user8);


        // java5
        //List<User> collect = list.stream().filter(user -> user.getPid() == 0).collect(Collectors.toList());
        //for (User user : collect) {
        //    getList(user, list);
        //}
        //System.err.println(collect);

        //java8
        List<User> collect8 = list.stream()
                .filter(user -> user.getPid() == 0)
                .map(user -> toResourceGroup(user, list))
                .peek(ResourceGroup::autoSet)
                .map(ResourceGroup::getParent)
                .collect(Collectors.toList());
        System.err.println(collect8);

    }

    private static ResourceGroup toResourceGroup(User user, List<User> list) {
        ResourceGroup resourceGroup = new ResourceGroup();
        resourceGroup.setParent(user);
        List<User> children = findChildren(user.getId(), list);
        resourceGroup.setChildren(children);

        return resourceGroup;
    }

    private static List<User> findChildren(int id, List<User> list) {
        ArrayList<User> objects = Lists.newArrayList();
        for (User user : list) {
            if (user.getPid() == id) {
                objects.add(user);
            }
        }
        System.err.println(objects);
        return objects;
    }

    @Data
    public static class ResourceGroup {

        private User parent;
        private List<User> children;

        public ResourceGroup autoSet() {
            this.parent.setChildren(this.children);
            return this;
        }
    }


    private static void getList(User user, List<User> list) {
        ArrayList<User> childrens = Lists.newArrayList();
        for (User u : list) {
            if (u.getPid() == user.getId()) {
                childrens.add(u);
                getList(u, list);
            }
        }
        user.setChildren(childrens);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private int id;
        private int pid;
        private String name;
        private List<User> children;

        public User(int id, int pid, String name) {
            this.id = id;
            this.pid = pid;
            this.name = name;
        }
    }

}
