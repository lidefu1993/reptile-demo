package com.ldf.reptile.crawler4j;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by ldf on 2018/9/24.
 */
public class DetailCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://you.ctrip.com/place/161.html");
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
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            dataUrls(links);
            Document document = Jsoup.parse(html);
            //喜欢的数量
            Element likeElements = document.getElementById("TitleLike");
            Element likeElement = likeElements.children().tagName("span").get(0);
            String likeNum = likeElement.text();
            System.out.println("喜欢数量：" + likeNum);
            //发表时间
            Element timeElement = document.getElementsByAttributeValueContaining("class", "ctd_content").get(0);
            Element h3 = timeElement.children().tagName("h3").get(0);
            String time = h3.text();
            System.out.println("发表时间：" + time);
            //获取浏览量
            Elements browseElements = document.getElementsByAttributeValue("title", "我要评论");
            Element browseElement = browseElements.tagName("span").first();
            String browseNum = browseElement.text();
            System.out.println("浏览量:" + browseNum);

            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
        }
        System.out.println("ending-------------------------------------");
    }

    /**
     * 筛选url
     * @param urls
     */
    private void dataUrls(Set<WebURL> urls){
        Set<WebURL> dataUrl = new HashSet<>();
        urls.stream().forEach(url->{
            String y = url.getURL();
            if(!url.getURL().contains("html") && !url.getURL().contains(".jpg") && !url.getURL().contains(".png"))
                dataUrl.add(url);
        });
        System.out.println(dataUrl);
    }

}
