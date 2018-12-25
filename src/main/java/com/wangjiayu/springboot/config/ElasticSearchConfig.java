package com.wangjiayu.springboot.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 配置es
 */

@Configuration
public class ElasticSearchConfig {

    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
