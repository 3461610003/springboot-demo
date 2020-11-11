package com.alicms.example.demo.txt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * CrawlText
 * </p>
 *
 * @author zhenghao
 * @date 2020/10/28 16:55
 */
public class CrawlText {

    /***
     * 获取文本
     * @param Url 网站链接
     */
    public static void getText(String Url) throws IOException {
        List<String> urlList = new ArrayList<>();
        Document document = Jsoup.connect(Url)
                .timeout(4000)
                .ignoreContentType(true)
                .get();
        System.out.println(document.toString());
        Elements urlNode = document.select("body > div.Main.List > dl:nth-child(4) > dd > a[href$=.html]");
        for (Element element : urlNode) {
            urlList.add(element.attr("abs:href"));
        }
        CrawTextThread crawTextThread = new CrawTextThread(urlList);
        crawTextThread.start();
    }

    public static void main(String[] args) {
        try {
            CrawlText.getText("https://www.17k.com/list/924744.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
