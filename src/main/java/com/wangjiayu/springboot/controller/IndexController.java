package com.wangjiayu.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 迅捷羽翼
 */

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public String test1() {
        return "hello word";
    }



}
