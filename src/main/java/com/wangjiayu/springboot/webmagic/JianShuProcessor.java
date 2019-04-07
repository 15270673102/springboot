package com.wangjiayu.springboot.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class JianShuProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("jianshu.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"list-container\"]/ul/li/a/@href").all());

        String title = page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/h1/text()").get();
        if (title != null) {
            page.putField("title", title);
            page.putField("author", page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span/a/text()").get());
            String imgUrl = page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/a/img/@src").get();
            page.putField("imgUrl", imgUrl.substring(0, imgUrl.indexOf("?")));
            page.putField("scout", page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/div/span[1]/i/text()").get());
            page.putField("publishDate", page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/div/span[2]/text()").get());
            page.putField("number_of_words", page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/div/span[3]/text()").get());
            page.putField("read", page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/div/span[4]/text()").get());
            page.putField("comment", page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/div/span[5]/text()").get());
            page.putField("love", page.getHtml().xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/div/span[6]/text()").get());

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new JianShuProcessor());
        spider.addUrl("http://www.jianshu.com");
        spider.thread(5);
        spider.start();
    }

}