package com.wangjiayu.springboot.webcontroller;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class DemoAutoNewsCrawler extends BreadthCrawler {

    public DemoAutoNewsCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        this.addSeed("http://news.hfut.edu.cn/list-1-1.html");

        this.addRegex("http://news.hfut.edu.cn/show-.*html");
        /*不获取这样的格式 jpg|png|gif*/
        this.addRegex("-.*\\.(jpg|png|gif).*");
        /*也不要这样的 #*/
        this.addRegex("-.*#.*");
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        if (page.matchUrl("http://news.hfut.edu.cn/show-.*html")) {
            /*的提取新闻和标题的css选择器*/
            String title = page.select("div[id=Article]>h2").first().text();
            String content = page.select("div#artibody", 0).text();

            System.out.println(page.url());
            System.out.println(title);
            System.out.println(content);
        }
    }

    public static void main(String[] args) throws Exception {
        DemoAutoNewsCrawler crawler = new DemoAutoNewsCrawler("crawl", true);
        crawler.start(4);
    }
}
