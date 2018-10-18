package com.ldf.reptile.crawler4j.xinlang;

import com.ldf.reptile.crawler4j.MyCrawler;
import com.ldf.reptile.crawler4j.Util;
import edu.uci.ics.crawler4j.crawler.CrawlController;

import java.util.Arrays;

/**
 * Created by ldf on 2018/9/27.
 */
public class XinLangController {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "D:\\test\\crawler\\xinlang";
        CrawlController crawlController = Util.controllerInstance(crawlStorageFolder, Arrays.asList("https://weibo.com/tianzhushanlvyou?is_hot=1"));
        crawlController.startNonBlocking(XinLangCrawler.class, 1);
        crawlController.waitUntilFinish();
        System.out.println("finish----DynamicController.main");
    }

}
