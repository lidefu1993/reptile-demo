package com.ldf.reptile.crawler4j;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ldf on 2018/9/25.
 */
public class MultipleCrawlerController {

    private static final Logger logger = LoggerFactory.getLogger(MultipleCrawlerController.class);

    public static void main(String[] args) throws Exception {

    /*
     * crawlStorageFolder is a folder where intermediate crawl data is
     * stored.
     */
        String crawlStorageFolder = "D:\\test\\crawler";

        CrawlConfig config1 = new CrawlConfig();
        CrawlConfig config2 = new CrawlConfig();

    /*
     * The two crawlers should have different storage folders for their
     * intermediate data
     */
        config1.setCrawlStorageFolder(crawlStorageFolder + "/crawler1");
        config2.setCrawlStorageFolder(crawlStorageFolder + "/crawler2");

        config1.setPolitenessDelay(1000);
        config2.setPolitenessDelay(2000);

        config1.setMaxPagesToFetch(50);
        config2.setMaxPagesToFetch(100);

    /*
     * We will use different PageFetchers for the two crawlers.
     */
        PageFetcher pageFetcher1 = new PageFetcher(config1);
        PageFetcher pageFetcher2 = new PageFetcher(config2);

    /*
     * We will use the same RobotstxtServer for both of the crawlers.
     */
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher1);

        CrawlController controller1 = new CrawlController(config1, pageFetcher1, robotstxtServer);
        CrawlController controller2 = new CrawlController(config2, pageFetcher2, robotstxtServer);

        String[] crawler1Domains = {"http://www.ics.uci.edu/", "http://www.cnn.com/"};
        String[] crawler2Domains = {"http://en.wikipedia.org/"};

        controller1.setCustomData(crawler1Domains);
        controller2.setCustomData(crawler2Domains);

        controller1.addSeed("http://you.ctrip.com/place/161.html");

        controller2.addSeed("http://you.ctrip.com/travels/tianzhushan161/3720048.html");

    /*
     * The first crawler will have 5 concurrent threads and the second
     * crawler will have 7 threads.
     */
        controller1.startNonBlocking(MyCrawler.class, 1);
        controller2.startNonBlocking(DetailCrawler.class, 2);

        controller1.waitUntilFinish();
        logger.info("Crawler 1 is finished.");

        controller2.waitUntilFinish();
        logger.info("Crawler 2 is finished.");
    }

}
