package com.ldf.reptile.htmlunit;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidefu
 * @date 2018/12/24 10:04
 */
public class XieCheng {

    public static void main(String[] args) throws IOException {
        Map<String, List<String>> result = new HashMap<>(5);
        WebClient webClient = Util.webClientBuilder();
        HtmlPage rootPage = webClient.getPage("http://travel.qunar.com/p-oi711302-tianzhushan");
        //设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(30*1000);
        String html = rootPage.asXml();
        Document document = Jsoup.parse(html);
        Elements attributeValue = document.getElementsByAttributeValue("class", "e_comment_content");
        List<String> page1 = new ArrayList<>();
        attributeValue.forEach(v -> {
            System.out.println(v.text());
            page1.add(v.text());
        });
        result.put("page1", page1);
        //第二页
        HtmlAnchor anchor = rootPage.getAnchorByText("下一页");
        Page pageNext = anchor.click();
        HtmlPage htmlPageNext = (HtmlPage) pageNext;
        String htmNext = htmlPageNext.asXml();
        Document documentNext = Jsoup.parse(htmNext);
        Elements attributeValueNext = documentNext.getElementsByAttributeValue("class", "e_comment_content");
        List<String> page2 = new ArrayList<>();
        attributeValueNext.forEach(v -> {
            System.out.println(v.text());
            page2.add(v.text());
        });
        result.put("page2", page2);
        //第三页 xpath
        Object o = ((HtmlPage) pageNext).getByXPath("//*[@id=\"js_replace_box\"]/div[2]/div/a[9]").get(0);
        HtmlAnchor anchor2 = (HtmlAnchor)o;
        HtmlPage htmNext3 = anchor2.click();
        String html3 = htmNext3.asXml();
        Document documentNext3 = Jsoup.parse(html3);
        Elements attributeValueNext3 = documentNext3.getElementsByAttributeValue("class", "e_comment_content");
        List<String> page3 = new ArrayList<>();
        attributeValueNext3.forEach(v -> {
            System.out.println(v.text());
            page3.add(v.text());
        });
        result.put("page3", page3);
        webClient.close();
        System.out.println(1);
    }


}
