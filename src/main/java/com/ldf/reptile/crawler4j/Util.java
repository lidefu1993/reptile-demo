package com.ldf.reptile.crawler4j;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.util.List;

/**
 * Created by ldf on 2018/9/25.
 */
public class Util {


    public static CrawlController controllerInstance(String crawlStorageFolder, List<String> seedUrls) throws Exception {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(1000);
        config.setMaxPagesToFetch(50);
        PageFetcher pageFetcher1 = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher1);
        CrawlController controller = new CrawlController(config, pageFetcher1, robotstxtServer);
        seedUrls.stream().forEach(seedUrl->{
            controller.addSeed(seedUrl);
        });
        return controller;
    }
}
