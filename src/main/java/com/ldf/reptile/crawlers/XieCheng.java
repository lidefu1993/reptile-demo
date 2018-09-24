package com.ldf.reptile.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import org.seimicrawler.xpath.JXDocument;
import java.util.List;
/**
 * Created by ldf on 2018/9/24.
 */
@Crawler(name = "XieCheng")
public class XieCheng extends BaseSeimiCrawler {

    @Override
    public String[] startUrls() {
        return new String[]{"http://you.ctrip.com/place/161.html"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//div[@class='journalslist undis']//a[@class='journal-item cf']/@href");
            logger.info("xiecheng-size:{}", urls.size());
            for (Object s:urls){
                push(Request.build("http://you.ctrip.com/" + s.toString(),XieCheng::getTitle));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTitle(Response response){
        JXDocument doc = response.document();
        try {
            List<Object> title = doc.sel("//div[@class='ctd_head_left']//h2");
            List<Object> content = doc.sel("//div[@class='ctd_content']");
            logger.info("url-test:{} {}", response.getUrl(), title);
            //do something
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
