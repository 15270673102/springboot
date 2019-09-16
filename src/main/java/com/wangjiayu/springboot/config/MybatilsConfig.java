package com.wangjiayu.springboot.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wangjiayu.springboot.mapper")
public class MybatilsConfig {
}
