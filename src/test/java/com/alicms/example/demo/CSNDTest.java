package com.alicms.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description TODO
 * @Author zhenghao
 * @Date 2019/12/26 13:25
 */
public class CSNDTest {
//    /*
//     * --------------------自动刷CSDN博客访问量程序--------------------
//     *
//     * 将要刷访问量的博客id填写入24行的变量userId中，点击运行
//     * 本程序访问该博主【用户ID】名下所有博客链接
//     *
//     * 仅供学习测试使用，不要真的用于刷访问量~
//     */
//    static String userId = "qq_31975227";
//
//    public static void main(String[] args) throws InterruptedException, IOException {
//        while(true) {
//            run();
//            Thread.sleep(1000 * 66);
//        }
//    }
//    public static void run() throws IOException, InterruptedException {
//
//        Set<String> urls = new HashSet<String>();
//
//        // ----------------------------------------------遍历每一页 获取文章链接----------------------------------------------
//        final String homeUrl = "https://blog.csdn.net/" + userId + "/article/list/";// 后面加pageNum即可
//        int totalPage = 0;
//        InputStream is;
//        String pageStr;
//        StringBuilder curUrl = null;
//        for (int i = 1; i < 100; i++) {
//            Thread.sleep(1000);
//            System.out.println("finding page " + i);
//            curUrl = new StringBuilder(homeUrl);
//            curUrl.append(i);
//            System.out.println(curUrl);
//            is = doGet(curUrl.toString());
//            pageStr = inputStreamToString(is, "UTF-8");// 一整页的html源码
//
//            List<String> list = getMatherSubstrs(pageStr, "(?<=href=\")https://blog.csdn.net/" + userId + "/article/details/[0-9]{8,9}(?=\")");
//            urls.addAll(list);
//
//            if (pageStr.lastIndexOf("空空如也") != -1) {
//                System.out.println("No This Page!");
//                break;
//            } else {
//                System.out.println("Success~");
//            }
//            totalPage = i;
//        }
//        System.out.println("总页数为: " + totalPage);
//
//        // ---------------------------------------------------打印每个链接---------------------------------------------------
////        System.out.println("打印每个链接");
////        for (String s : urls) {
////            System.out.println(s);
////        }
////        System.out.println("打印每个链接完毕");
//
//        // ---------------------------------------------------访问每个链接---------------------------------------------------
//        int i = 0;
//        for (String s : urls) {
//            doGet(s);
//            System.out.println("成功访问第" + (++i) + "个链接,共" + urls.size() + "个:" + s);
//        }
//
//        // ---------------------------------------------------程序结束---------------------------------------------------
//        System.out.println("运行完毕，成功增加访问数：" + urls.size());
//    }
//
//    public static InputStream doGet(String urlstr) throws IOException {
//        URL url = new URL(urlstr);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestProperty(get(":authority"), "blog.csdn.net");
//        conn.setRequestProperty(get(":method"), "GET");
//        conn.setRequestProperty(get(":path"), "/qq_31975227/article/details/103710018");
//        conn.setRequestProperty(get(":scheme"), "https");
//        conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//        conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
//        conn.setRequestProperty("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
//        conn.setRequestProperty("cache-control", "max-age=0");
//        conn.setRequestProperty("cookie", "uuid_tt_dd=10_19428311920-1573731316956-992815; dc_session_id=10_1573731316956.416879; UserName=qq_31975227; UserInfo=8528ee6fca77482785ef85e040844916; UserToken=8528ee6fca77482785ef85e040844916; UserNick=%E9%9A%90%E8%80%85_; AU=2F4; UN=qq_31975227; BT=1573810271935; p_uid=U000000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19428311920-1573731316956-992815!5744*1*qq_31975227; __gads=Test; Hm_ct_54fe4ccf954920016fce1940d850e139=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_54fe4ccf954920016fce1940d850e139=1575439747,1575439939; __yadk_uid=3Q5KgRbPFPOmXRUe2KztVmzLLNgbuVlo; Hm_ct_b771b9753a47e6a3f0cc5ebdb9e7eeaf=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_b771b9753a47e6a3f0cc5ebdb9e7eeaf=1576032078,1576041140,1576114459; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1576203165; Hm_ct_e5ef47b9f471504959267fd614d579cd=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; acw_tc=2760824f15764612998621287e8728745b75be6d968669ca800f74d8ce988b; firstDie=1; TY_SESSION_ID=3e56d170-decc-4b91-b4a9-c364b9e2cac6; Hm_lvt_c072e0d113e6b5841848d39d71a2c580=1577337859; Hm_lpvt_c072e0d113e6b5841848d39d71a2c580=1577337859; Hm_ct_c072e0d113e6b5841848d39d71a2c580=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1577337612,1577337860,1577337894,1577338391; acw_sc__v2=5e0455c509eacccf36134e41ea9e0833687e0897; announcement=%257B%2522isLogin%2522%253Atrue%252C%2522announcementUrl%2522%253A%2522https%253A%252F%252Fblog.csdn.net%252Fblogdevteam%252Farticle%252Fdetails%252F103603408%2522%252C%2522announcementCount%2522%253A0%252C%2522announcementExpire%2522%253A3600000%257D; c_adb=1; dc_tos=q33xcd; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1577342606");
//        conn.setRequestProperty("sec-fetch-mode", "navigate");
//        conn.setRequestProperty("sec-fetch-site", "none");
//        conn.setRequestProperty("sec-fetch-user", "?1");
//        conn.setRequestProperty("upgrade-insecure-requests", "1");
//        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36");
//        InputStream inputStream = conn.getInputStream();
//        return inputStream;
//    }
//    private static String get(String str) {
//        return str;
////        return Base64.getEncoder().encodeToString(
////                str.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public static String inputStreamToString(InputStream is, String charset) throws IOException {
//        byte[] bytes = new byte[1024];
//        int byteLength = 0;
//        StringBuffer sb = new StringBuffer();
//        while ((byteLength = is.read(bytes)) != -1) {
//            sb.append(new String(bytes, 0, byteLength, charset));
//        }
//        return sb.toString();
//    }
//
//    // 正则匹配
//    public static List<String> getMatherSubstrs(String str, String regex) {
//        List<String> list = new ArrayList<>();
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(str);
//        while (m.find()) {
//            list.add(m.group());
//        }
//        return list;
//    }
}
