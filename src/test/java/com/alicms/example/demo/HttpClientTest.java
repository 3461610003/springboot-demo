package com.alicms.example.demo;

import com.alicms.example.demo.utils.HttpClientUtils;

/**
 * @Description 测试HttpClient
 * @Author zhenghao
 * @Date 2019/12/26 11:05
 */
public class HttpClientTest {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            String s = HttpClientUtils.doGet("https://blog.csdn.net/qq_31975227/article/details/103710018");
//            System.out.println(s);
            System.out.println(".....");
            Thread.sleep(1000 * 61);
        }
    }

}
