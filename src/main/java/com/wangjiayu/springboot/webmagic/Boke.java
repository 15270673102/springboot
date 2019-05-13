package com.wangjiayu.springboot.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

public class Boke implements PageProcessor {

    public static void main(String[] args) {
        Spider.create(new Boke()).addUrl("https://m.weibo.cn/statuses/show?id=HtzCev4aW").thread(1).run();

    }

    @Override
    public void process(Page page) {
        Json json = page.getJson();
        System.out.println(json.jsonPath("data"));
    }

    @Override
    public Site getSite() {
        //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
        return Site.me().setRetryTimes(3).setSleepTime(100);
    }

}
