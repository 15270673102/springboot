package com.wangjiayu.springboot.webcontroller;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * @author 技术部
 */

public class AjaxCrawler extends BreadthCrawler {

    public AjaxCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        System.out.println(page.jsonObject());
    }

    public static void main(String[] args) throws Exception {
        AjaxCrawler crawler = new AjaxCrawler("crawl", true);
        crawler.addSeed("https://m.weibo.cn/statuses/show?id=HsDQawjDO");
        crawler.start(1);
    }

}
