package com.ldf.reptile.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author lidefu
 * @date 2018/12/24 9:53
 */
public class XinLang {

    public static void main(String[] args) throws IOException {
        String url = "http://weibo.com/tianzhushanlvyou?is_hot=1 ";
        WebClient client = new WebClient(BrowserVersion.FIREFOX_45);
        //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        client.getOptions().setThrowExceptionOnScriptError(false);
        WebRequest request = Util.webRequest(url, cookies);
        HtmlPage page = client.getPage(request);
        client.waitForBackgroundJavaScript(30*1000);
        String xml = page.asXml();
        Document document = Jsoup.parse(xml);
        Elements elements = document.getElementsByAttributeValue("class", "WB_detail");
        Elements byAttributeValue = document.getElementsByAttributeValue("class", "WB_text");
        Elements aClass = document.getElementsByAttributeValue("class", "line S_line1");
        Elements pageNext = document.getElementsByAttributeValue("class", "page next S_txt1 S_line1");
        System.out.println(1);
        client.close();
    }

    /**
     * 模拟登陆
     * @throws IOException
     */
    public static void loginTest() throws IOException {
        WebClient client = new WebClient(BrowserVersion.FIREFOX_45);
        //默认执行js，如果不执行js，则可能会登录失败，因为用户名密码框需要js来绘制。
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setCssEnabled(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        client.getOptions().setThrowExceptionOnScriptError(false);
        HtmlPage page = client.getPage("http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.3.16)");
        HtmlInput ln = page.getHtmlElementById("username");
        HtmlInput pwd = page.getHtmlElementById("password");
        HtmlInput btn = page.getFirstByXPath(".//input[@class='W_btn_a btn_34px']");
        ln.setAttribute("value", "17610391665");
        pwd.setAttribute("value", "836421293");
        HtmlPage clickPage = (HtmlPage)btn.click();
        String asXml = clickPage.asXml();
        System.out.println(1);
    }


    private static String cookies = "SINAGLOBAL=4536310543915.678.1532674626862; SCF=AuqQ6tjauy2jY3zL-xJSPFwsK2lA1JLk1WOQ1ME_7qHTHm4hRyLJdNbon4ypVVblw689-oyJXwNRtciM0Has3JQ.; SUHB=0V51N_dpTOMmyq; SUB=_2AkMs4euAdcNxrAZXm_AVymLraoVH-jyfNIJ2An7uJhMyAxh87gYiqSVutBF-XJ9twKsHa_g-jWUpWsVjIMqH71DQ; SUBP=0033WrSXqPxfM72wWs9jqgMF55529P9D9WWA4NbOERdy2Ckf8nqsFrnE; login_sid_t=7c18b7309f53468f8d9d0330e0219932; cross_origin_proto=SSL; YF-V5-G0=fec5de0eebb24ef556f426c61e53833b; _s_tentry=www.google.com.hk; UOR=coolshell.cn,widget.weibo.com,www.google.com.hk; Apache=5313349189720.302.1539309087368; ULV=1539309087392:4:2:2:5313349189720.302.1539309087368:1539048633747; YF-Page-G0=1ffbef18656bf02c17e45a764e3d5336";


}
