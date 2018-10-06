package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.service.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserControlelr {

    @Autowired
    private IUserservice userservice;

    public void name() {
        System.out.println("123");
    }

    public void name1() {
        System.out.println("123");
    }
    public void name2() {
        System.out.println("123");
    }
}
