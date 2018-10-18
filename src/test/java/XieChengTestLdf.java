import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bonc.tjs.reptile.ReptileApp;
import com.bonc.tjs.reptile.util.HtmlunitUtil;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by ldf on 2018/10/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReptileApp.class)
public class XieChengTestLdf {

    @org.junit.Test
    public void test() throws IOException {
        String url = "http://sec-m.ctrip.com/restapi/soa2/12530/json/viewCommentList";
        WebClient client = HtmlunitUtil.webClientBuilder();
        WebRequest request = webRequestBuilder(url);
        WebResponse response = client.loadWebResponse(request);
        String contentAsString = response.getContentAsString();
        JSONObject jsonObject = JSON.parseObject(contentAsString);
        System.out.println(jsonObject);
    }

    /**
     * 构建WebRequest
     * @param url
     * @return
     * @throws MalformedURLException
     */
    private WebRequest webRequestBuilder(String url) throws MalformedURLException {
        WebRequest request = new WebRequest(new URL(url));
        request.setAdditionalHeader("Accept", "*/*");
        request.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
        request.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        request.setAdditionalHeader("Connection", "keep-alive");
        request.setAdditionalHeader("Content-Type", "application/json");
        request.setAdditionalHeader("Cookie", cookies);
        request.setAdditionalHeader("cookieorigin", "http://piao.ctrip.com");
        request.setAdditionalHeader("Host", "sec-m.ctrip.com");
        request.setAdditionalHeader("Origin", "http://piao.ctrip.com");
        request.setAdditionalHeader("Referer", "http://piao.ctrip.com/ticket/dest/t142477.html");
        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/62.0");
        webRequestParam(request);
        return request;
    }

    /**
     * POST参数 赋值
     * @param webRequest
     */
    private void webRequestParam(WebRequest webRequest){
        List<NameValuePair> list = new ArrayList<>();
        Map<String, Object> headParam = headParam();
        list.add(new NameValuePair("contentType", "json"));
//        list.add(new NameValuePair("head", JSON.toJSONString(headParam)));
        list.add(new NameValuePair("pageid", "10650000804"));
        list.add(new NameValuePair("pagenum", "1"));
        list.add(new NameValuePair("pagesize", "10"));
        list.add(new NameValuePair("tagid", "-11"));
        list.add(new NameValuePair("ver", "7.10.3.0319180000"));
        list.add(new NameValuePair("viewid", "142477"));
        webRequest.setRequestParameters(list);
    }

    private Map<String, Object> headParam(){
        Map<String, Object> map = new HashMap<>();
        map.put("appid", "100013776");
        map.put("auth", "");
        map.put("ctok", "");
        map.put("cver", "1.0");
        map.put("lang", "01");
        map.put("sid", "8888");
        map.put("syscode", "09");
        map.put("extension", new ArrayList<>());
        return map;
    }

    private static String cookies = "_abtest_userid=1223fd97-7ca5-4c30-866b-4068905cf8d0; _ga=GA1.2.673664477.1537760847; _RF1=39.155.134.140; _RSG=BiKaRkzENt1KvOIJd6B2TA; _RDG=2872978e0e6f54212e373eccdecaae4073; _RGUID=1dd73bdb-9b91-4b0b-a742-2c69d3498e54; TicketSiteID=SiteID=1008; MKT_Pagesource=PC; Session=smartlinkcode=U130678&smartlinklanguage=zh&SmartLinkKeyWord=&SmartLinkQuary=&SmartLinkHost=; Union=AllianceID=4899&SID=130678&OUID=&Expires=1539743281105; gad_city=96617ee7af8aedd02bbece8583e0066e; _gid=GA1.2.2085718174.1539397563; _bfi=p1%3D10650000804%26p2%3D10650000804%26v1%3D57%26v2%3D56; Mkt_UnionRecord=%5B%7B%22aid%22%3A%224899%22%2C%22timestamp%22%3A1539398489468%7D%5D; _gat=1; _jzqco=%7C%7C%7C%7C1539397562843%7C1.1369504619.1538105291794.1539397578373.1539398489497.1539397578373.1539398489497.undefined.0.0.49.49; __zpspc=9.28.1539397562.1539398489.3%233%7Cwww.google.com.hk%7C%7C%7C%7C%23; appFloatCnt=46; _bfa=1.1538105289084.28kswj.1.1539138477320.1539397558400.14.58; _bfs=1.4";
}
