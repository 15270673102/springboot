package com.wangjiayu.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 迅捷羽翼
 */

@RestController
@Slf4j
public class IndexController {

    @GetMapping(value = "/")
    public String test1() {
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxx");
        log.warn("warn warn warn warn warn warn warn warn ");
        log.error("errorerrorerrorerrorerrorerrorerrorerrorerror ");
        return "hello word";
    }

    @GetMapping(value = "/2")
    public String test2() {
        log.info("ttttttttttttttttttttt");
        return "hello word";
    }
}
