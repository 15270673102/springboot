package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.rabbitmq.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 迅捷羽翼
 */

@RestController
@Slf4j
public class IndexController {

    @Resource
    private Sender sender;

    @GetMapping(value = "/")
    public void test1() {
        sender.send();
    }

}
