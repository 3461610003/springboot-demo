package com.alicms.example.demo;

/**
 * @Description Jdk11Test
 * @Author zhenghao
 * @Date 2019/12/26 15:10
 */
public class Jdk11Test {
//    public static void main(String[] args) throws Exception {
//        while (true) {
////            HttpClient client = HttpClient.newHttpClient();
////            HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_2)
////                    .header(":authority", "blog.csdn.net")
////                    .header(":method", "GET")
////                    .header(":path", "/qq_31975227/article/details/103710018")
////                    .header(":scheme", "https")
////                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
////                    .header("accept-encoding", "gzip, deflate, br")
////                    .header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8")
////                    .header("cache-control", "max-age=0")
////                    .header("cookie", "uuid_tt_dd=10_19428311920-1573731316956-992815; dc_session_id=10_1573731316956.416879; UserName=qq_31975227; UserInfo=8528ee6fca77482785ef85e040844916; UserToken=8528ee6fca77482785ef85e040844916; UserNick=%E9%9A%90%E8%80%85_; AU=2F4; UN=qq_31975227; BT=1573810271935; p_uid=U000000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19428311920-1573731316956-992815!5744*1*qq_31975227; __gads=Test; Hm_ct_54fe4ccf954920016fce1940d850e139=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_54fe4ccf954920016fce1940d850e139=1575439747,1575439939; __yadk_uid=3Q5KgRbPFPOmXRUe2KztVmzLLNgbuVlo; Hm_ct_b771b9753a47e6a3f0cc5ebdb9e7eeaf=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_b771b9753a47e6a3f0cc5ebdb9e7eeaf=1576032078,1576041140,1576114459; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1576203165; Hm_ct_e5ef47b9f471504959267fd614d579cd=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; acw_tc=2760824f15764612998621287e8728745b75be6d968669ca800f74d8ce988b; firstDie=1; TY_SESSION_ID=3e56d170-decc-4b91-b4a9-c364b9e2cac6; Hm_lvt_c072e0d113e6b5841848d39d71a2c580=1577337859; Hm_lpvt_c072e0d113e6b5841848d39d71a2c580=1577337859; Hm_ct_c072e0d113e6b5841848d39d71a2c580=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1577337612,1577337860,1577337894,1577338391; acw_sc__v2=5e0455c509eacccf36134e41ea9e0833687e0897; announcement=%257B%2522isLogin%2522%253Atrue%252C%2522announcementUrl%2522%253A%2522https%253A%252F%252Fblog.csdn.net%252Fblogdevteam%252Farticle%252Fdetails%252F103603408%2522%252C%2522announcementCount%2522%253A0%252C%2522announcementExpire%2522%253A3600000%257D; c_adb=1; dc_tos=q33xcd; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1577342606")
////                    .header("sec-fetch-mode", "navigate")
////                    .header("sec-fetch-site", "none")
////                    .header("sec-fetch-user", "?1")
////                    .header("upgrade-insecure-requests", "1")
////                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36")
////                    .uri(URI.create("https://blog.csdn.net/qq_31975227/article/details/103710018"))
////                    .build();
////            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
////                    .thenApply(HttpResponse::body)
////                    .thenAccept(System.out::println)
////                    .join();
//            // 建立一个请求对象，指定uri和请求类型（默认为GET）
//            HttpRequest request = HttpRequest.newBuilder()
//                    .version(HttpClient.Version.HTTP_2)
////                    .header(":authority", "blog.csdn.net")
////                    .header(":method", "GET")
////                    .header(":path", "/qq_31975227/article/details/103710018")
////                    .header(":scheme", "https")
//                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
//                    .header("accept-encoding", "gzip, deflate, br")
//                    .header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8")
//                    .header("cache-control", "max-age=0")
//                    .header("cookie", "uuid_tt_dd=10_19428311920-1573731316956-992815; dc_session_id=10_1573731316956.416879; UserName=qq_31975227; UserInfo=8528ee6fca77482785ef85e040844916; UserToken=8528ee6fca77482785ef85e040844916; UserNick=%E9%9A%90%E8%80%85_; AU=2F4; UN=qq_31975227; BT=1573810271935; p_uid=U000000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19428311920-1573731316956-992815!5744*1*qq_31975227; __gads=Test; Hm_ct_54fe4ccf954920016fce1940d850e139=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_54fe4ccf954920016fce1940d850e139=1575439747,1575439939; __yadk_uid=3Q5KgRbPFPOmXRUe2KztVmzLLNgbuVlo; Hm_ct_b771b9753a47e6a3f0cc5ebdb9e7eeaf=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_b771b9753a47e6a3f0cc5ebdb9e7eeaf=1576032078,1576041140,1576114459; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1576203165; Hm_ct_e5ef47b9f471504959267fd614d579cd=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; acw_tc=2760824f15764612998621287e8728745b75be6d968669ca800f74d8ce988b; firstDie=1; TY_SESSION_ID=3e56d170-decc-4b91-b4a9-c364b9e2cac6; Hm_lvt_c072e0d113e6b5841848d39d71a2c580=1577337859; Hm_lpvt_c072e0d113e6b5841848d39d71a2c580=1577337859; Hm_ct_c072e0d113e6b5841848d39d71a2c580=5744*1*qq_31975227!6525*1*10_19428311920-1573731316956-992815; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1577337612,1577337860,1577337894,1577338391; acw_sc__v2=5e0455c509eacccf36134e41ea9e0833687e0897; announcement=%257B%2522isLogin%2522%253Atrue%252C%2522announcementUrl%2522%253A%2522https%253A%252F%252Fblog.csdn.net%252Fblogdevteam%252Farticle%252Fdetails%252F103603408%2522%252C%2522announcementCount%2522%253A0%252C%2522announcementExpire%2522%253A3600000%257D; c_adb=1; dc_tos=q33xcd; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1577342606")
//                    .header("sec-fetch-mode", "navigate")
//                    .header("sec-fetch-site", "none")
//                    .header("sec-fetch-user", "?1")
//                    .header("upgrade-insecure-requests", "1")
//                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36")
//                    .uri(URI.create("https://blog.csdn.net/qq_31975227/article/details/103710018"))
//                    .GET()
//                    .build();
//            // 建立HttpClient对象
//            HttpClient client = HttpClient.newHttpClient();
//
//            // 模拟一次同步请求
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//
//            // 模拟一次异步请求
//            HttpResponse<String> response2=client.sendAsync(request,HttpResponse.BodyHandlers.ofString()).get();
//            System.out.println(response2.body());
//            Thread.sleep(1000 * 66);
//        }
//
//    }
}
