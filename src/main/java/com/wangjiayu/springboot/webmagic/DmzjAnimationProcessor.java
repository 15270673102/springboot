package com.wangjiayu.springboot.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class DmzjAnimationProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1000).setSleepTime(1000).setCharset("utf8");

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().xpath("//*[@class=\"comic_img\"]/@href").all());

        String img_url = page.getHtml().xpath("//div[@class=\"comic_i_img\"]/a/img/@src").get();
        if (img_url == null) {
            page.setSkip(true);
        } else {
            page.putField("img_url", img_url);
            page.putField("title", page.getHtml().xpath("//div[@class=\"comic_deCon\"]/h1/a/text()").get());

            page.putField("author", page.getHtml().xpath("//ul[@class=\"comic_deCon_liO\"]/li[1]/text()").get());
            page.putField("state", page.getHtml().xpath("//ul[@class=\"comic_deCon_liO\"]/li[2]/text()").get());
            page.putField("classfify", page.getHtml().xpath("//ul[@class=\"comic_deCon_liO\"]/li[3]/text()").get());
            page.putField("type", page.getHtml().xpath("//ul[@class=\"comic_deCon_liO\"]/li[4]/text()").get());

            page.putField("moods", page.getHtml().xpath("//ul[@class=\"comic_deCon_liT\"]/li[1]/text()").get());
            page.putField("click", page.getHtml().xpath("//ul[@class=\"comic_deCon_liT\"]/li[2]/text()").get());
            page.putField("subscription", page.getHtml().xpath("//ul[@class=\"comic_deCon_liT\"]/li[3]/text()").get());
            page.putField("Tsukkomi", page.getHtml().xpath("//ul[@class=\"comic_deCon_liT\"]/li[4]/text()").get());

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.out.println("开始爬取...");
        long startTime = System.currentTimeMillis();
        Spider.create(new DmzjAnimationProcessor()).addUrl("https://www.dmzj.com/category/0-0-3262-0-0-0-1.html").thread(5).run();
        long endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }
}
