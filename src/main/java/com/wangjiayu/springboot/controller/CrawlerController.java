package com.wangjiayu.springboot.controller;

import com.wangjiayu.springboot.webcontroller.DemoAutoNewsCrawler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "crawler")
public class CrawlerController {

    @ApiOperation(value = "crawler", notes = "crawler")
    @GetMapping(value = "crawler")
    public String crawler() throws Exception {

        DemoAutoNewsCrawler crawler = new DemoAutoNewsCrawler("crawl", true);
        crawler.addSeed("http://news.hfut.edu.cn/list-1-1.html");
        crawler.start(2);

        return "ok";
    }

}
