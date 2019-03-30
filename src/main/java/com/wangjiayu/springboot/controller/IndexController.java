package com.wangjiayu.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class IndexController {

    @RequestMapping("/index")
    public void index(HttpSession session) {
    }
}
