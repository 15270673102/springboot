package com.wangjiayu.springboot.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class Boke implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private static int count = 0;

    @Override
    public void process(Page page) {
        //加入满足条件的链接
        page.addTargetRequests(page.getHtml().xpath("//*[@id='post_list']/div/div[@class='post_item_body']/h3/a/@href").all());

        String author = page.getHtml().xpath("//*[@id=\"Header1_HeaderTitle\"]/text()").get();
        if (author == null) {
            page.setSkip(true);
        } else {
            //获取页面需要的内容//*[@id="Header1_HeaderTitle"]
            page.putField("author", page.getHtml().xpath("//*[@id=\"Header1_HeaderTitle\"]/text()").get());
            page.putField("title", page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/text()").get());
            page.putField("publishDate", page.getHtml().xpath("//*[@id=\"post-date\"]/text()").get());
            page.putField("read", page.getHtml().xpath("//*[@id=\"post_view_count\"]/text()").get());
            page.putField("comment", page.getHtml().xpath("//*[@id=\"post_comment_count\"]/text()").get());
            count++;
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(new Boke()).addUrl("https://www.cnblogs.com/").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了" + count + "条记录");
    }

}
