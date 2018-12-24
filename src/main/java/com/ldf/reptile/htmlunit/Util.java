package com.ldf.reptile.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author lidefu
 * @date 2018/12/24 9:57
 */
public class Util {

    public static WebRequest webRequest(String url, String cookies) throws MalformedURLException {
        WebRequest request = new WebRequest(new URL(url));
        request.setAdditionalHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        request.setAdditionalHeader("Accept-Encoding", "gzip, deflate, br");
        request.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        request.setAdditionalHeader("Cache-Control", "max-age=0");
        request.setAdditionalHeader("Connection", "keep-alive");
        request.setAdditionalHeader("Cookie", cookies);
        request.setAdditionalHeader("Host", "weibo.com");
        request.setAdditionalHeader("Upgrade-Insecure-Requests", "1");
        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.75 Safari/537.36");
        return request;
    }

    public static WebClient webClientBuilder(){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        return webClient;
    }

}
