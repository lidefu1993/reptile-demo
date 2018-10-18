import com.bonc.tjs.reptile.ReptileApp;
import com.bonc.tjs.reptile.util.HtmlunitUtil;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReptileApp.class)
public class JsoupTest {

    private String url = "http://you.ctrip.com/travels/tianzhushan161/2148562.html";
    @org.junit.Test
    public void test() throws IOException {
        Document document = Jsoup.connect(url).timeout(30000).get();
        Elements aClass = document.getElementsByAttributeValue("class", "content-wrapper");
        Elements aClass1 = document.getElementsByAttributeValue("class", "main-wrapper");
        Elements aClass2 = document.getElementsByAttributeValue("class", "t-comments");
        System.out.println(aClass.size());
    }

    /**
     *  评论 以及 评论分页 爬取
     * @throws IOException
     */
    @org.junit.Test
    public void htmlunitTest() throws IOException {
        WebClient webClient = HtmlunitUtil.webClientBuilder();
        HtmlPage rootPage = null;
        try {
            rootPage = webClient.getPage("http://travel.qunar.com/p-oi711302-tianzhushan");
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(10*1000);
        String html = rootPage.asXml();
        Document document = Jsoup.parse(html);
        Elements attributeValue = document.getElementsByAttributeValue("class", "e_comment_title");
        //下一页
        Object o1 = rootPage.getByXPath("//a[@class='page next']").get(0);
        HtmlAnchor anchor = (HtmlAnchor) o1;
        Page clickPage = anchor.click();
        HtmlPage htmlPage = (HtmlPage) clickPage;
        String asXml = htmlPage.asXml();
        Document document1 = Jsoup.parse(asXml);
        Elements attributeValue1 = document1.getElementsByAttributeValue("class", "e_comment_title");

        DomElement replaceBox = rootPage.getElementById("js_replace_box");
        Object o = replaceBox.getByXPath("//div[@class='b_paging']").get(0);

        System.out.println(1);
    }

    @Test
    public void xiechengTest(){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(true);
        webClient.getOptions().setCssEnabled(true);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        HtmlPage rootPage = null;
        try {
            rootPage = webClient.getPage("http://piao.ctrip.com/ticket/dest/t126298.html");
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(30*1000);
        String html = rootPage.asXml();
        Document document = Jsoup.parse(html);
        Elements attributeValue = document.getElementsByAttributeValue("class", "comments");
        System.out.println(1);
    }


}
