package com.ldf.reptile.crawler4j;

import edu.uci.ics.crawler4j.crawler.CrawlController;

import java.util.Arrays;

/**
 * Created by ldf on 2018/9/25.
 */
public class DynamicController {


    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "D:\\test\\crawler1";
        CrawlController crawlController = Util.controllerInstance(crawlStorageFolder, Arrays.asList("http://you.ctrip.com/place/161.html"));
        crawlController.startNonBlocking(MyCrawler.class, 1);
        crawlController.waitUntilFinish();
        System.out.println("finish----DynamicController.main");
    }

}
