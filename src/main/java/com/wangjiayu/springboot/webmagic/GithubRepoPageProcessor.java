package com.wangjiayu.springboot.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class GithubRepoPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"js-pjax-container\"]/div/div[3]/div/ul/li/div[1]/h3/a/@href").all());
        page.addTargetRequest("https://github.com" + page.getHtml().xpath("//*[@id=\"js-pjax-container\"]/div/div[3]/div/div[3]/div/a[@rel=\"next\"]/@href").get());

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/search?q=java")
                .thread(5)
                .run();
    }
}
