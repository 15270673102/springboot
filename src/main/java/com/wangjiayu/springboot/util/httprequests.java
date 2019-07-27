package com.wangjiayu.springboot.util;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import	java.util.Deque;
import	java.util.Map;

import net.dongliu.requests.*;

import java.util.HashMap;
import java.util.Objects;

public class httprequests {


    public static void main(String[] args) {
        //demo1();
        //demo2();
        demo3();
    }

    private static void demo3() {
        String string = "http://innerserver.thinkoonjt.com/ip-pool/getIPByIndex";
        String send = Requests.get(string).send().readToText();
        System.out.println(send);
    }

    private static void demo2() {
        String url = "http://www.baidu.com/s";
        Map<String, Object> params = new HashMap<>();
        params.put("wd", "python");
        String text = Requests.get(url).params(params).send().readToText();
        System.out.println(text);
    }

    private static void demo1() {
        String url = "http://www.baidu.com";
        Response<String> resp = Requests.get(url).send().toTextResponse();
        System.out.println(resp.body());
    }

}
