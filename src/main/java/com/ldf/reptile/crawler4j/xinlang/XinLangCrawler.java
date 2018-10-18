package com.ldf.reptile.crawler4j.xinlang;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;

/**
 * Created by ldf on 2018/9/27.
 */
public class XinLangCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");


    @Override
    public void onStart(){
        System.out.println("onStart---------------------------------------------");
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        if (myController.getConfig().isRespectNoFollow()) {
            return !((referringPage != null &&
                    referringPage.getContentType() != null &&
                    referringPage.getContentType().contains("html") &&
                    ((HtmlParseData)referringPage.getParseData())
                            .getMetaTagValue("robots")
                            .contains("nofollow")) ||
                    url.getAttribute("rel").contains("nofollow"));
        }

        return true;
    }

    @Override
    public void visit(Page page) {
        if (page.getParseData() instanceof HtmlParseData){
            System.out.println("visit--------------------------------");
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            Document document = Jsoup.parse(html);
            //当前URL
            String url = page.getWebURL().getURL();
            Elements elements = document.getElementsByAttributeValue("class", "WB_main clearfix");
            Elements allElements = document.getAllElements();
            System.out.println("------------------------visit end------------------");
        }
    }

}
