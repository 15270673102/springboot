package com.wangjiayu.springboot.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class CsdnBlogProcessor implements PageProcessor {

    private static String username = "yixiao1874";// 设置csdn用户名
    private static int size = 0;// 共抓取到的文章数量

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[2]/div/h4/a/@href").all());

        String title = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1/text()").get();
        if (title == null) {
            page.setSkip(true);
        } else {
            size++;// 文章数量加1
            String yuanc = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/span/text()").get();
            if (yuanc != null) {
                page.putField("yuanc", yuanc);
            }
            page.putField("title", title);
            String zding = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[2]/div[1]/span[@class=\"c-gray\"]/text()").get();
            if (zding != null) {
                page.putField("zding", zding);
            }
            page.putField("time", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[2]/div[1]/span[@class=\"time\"]/text()").get());
            page.putField("nickName", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[2]/div[1]/a[@class=\"follow-nickName\"]/text()").get());
            page.putField("read-count", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[2]/div[1]/span[@class=\"read-count\"]/text()").get());

        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CsdnBlogProcessor())
                .addUrl("http://blog.csdn.net/" + username)
                .thread(5)
                .run();
        System.out.println("文章总数为" + size);
    }
}
