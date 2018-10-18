package com.ldf.reptile.crawler4j;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by ldf on 2018/9/24.
 */

public class MyCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://you.ctrip.com/place/161.html"); //http://www.ics.uci.edu/
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Document document = Jsoup.parse(html);
            //定位
            Elements elements = document.getElementsByAttributeValue("class", "journalslist undis");
            Elements childeren = elements.get(0).children().tagName("a");
            Element element = childeren.get(0);
            //获取href
            Elements href = element.getElementsByAttribute("href");
            String href_value = element.attr("href");
            //获取标题
            Elements titleElement = element.select(".ellipsis");
            String title_value = titleElement.get(0).text();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());

            String crawlStorageFolder = "D:\\test\\crawler2";
            try {
                CrawlController crawlController = Util.controllerInstance(crawlStorageFolder, Arrays.asList("http://you.ctrip.com/travels/tianzhushan161/3720048.html"));
                crawlController.startNonBlocking(DetailCrawler.class, 1);
                crawlController.waitUntilFinish();
                System.out.println("------------------finish DetailCrawler");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ending-------------------------------------");
    }

}
