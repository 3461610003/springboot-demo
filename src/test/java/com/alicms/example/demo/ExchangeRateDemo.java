package com.alicms.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alicms.example.demo.utils.HttpClientUtil;

import java.math.BigDecimal;

/**
 * @author zhenghao
 * @description 请求
 * @date 2020/7/13 17:05
 */
public class ExchangeRateDemo {

    public static void main(String[] args) {
        String result = HttpClientUtil.doGet("http://www.safe.gov.cn/AppStructured/hlw/jsonRmb.do", null);
        System.out.println(result);
        JSONArray objects = JSON.parseArray(result);
        for (Object object : objects) {
            JSONArray coinInfo = JSON.parseArray(String.valueOf(object));
//            System.out.println(coinInfo.get(0) + "," + coinInfo.get(1) + "," + coinInfo.get(2) + "," + coinInfo.get(3));
//            System.out.println(coinInfo.get(2));
//            if ("美元".equals(coinInfo.get(1))) {
//                System.out.println("1美元=" + coinInfo.getBigDecimal(2).multiply(new BigDecimal("100")) + "人民币");
//            }
            System.out.println("1" + coinInfo.get(1) + "=" + coinInfo.getBigDecimal(2).divide(new BigDecimal("100"), 6, BigDecimal.ROUND_HALF_UP) + coinInfo.get(3));
        }
    }

}
