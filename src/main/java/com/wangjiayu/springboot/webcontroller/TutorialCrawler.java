package com.wangjiayu.springboot.webcontroller;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class TutorialCrawler extends BreadthCrawler {

    /**
     * 构造一个基于伯克利DB的爬虫 伯克利DB文件夹为crawlPath，crawlPath中维护了历史URL等信息 不同任务不要使用相同的crawlPath 两个使用相同crawlPath的爬虫并行爬取会产生错误
     * @param crawlPath 伯克利DB使用的文件夹
     * @param autoParse 是否根据设置的正则自动探测新URL
     */
    public TutorialCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        setThreads(3);
        getConf().setExecuteInterval(100);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        if (page.matchUrl("https://blog.csdn.net/.*/article/details/.*")) {
            String title = page.select("h1[class=title-article]").first().text();
            String author = page.select("a[id=uid]").first().text();
            System.out.println("title:" + title + "  author:" + author);
        }
    }

    public static void main(String[] args) throws Exception {
        TutorialCrawler crawler = new TutorialCrawler("crawler", true);
        crawler.addSeed("https://blog.csdn.net/");
        crawler.addRegex("https://blog.csdn.net/.*/article/details/.*");
        crawler.start(2);
    }
}
