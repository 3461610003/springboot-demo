package com.alicms.example.demo;

import com.alicms.example.demo.utils.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenghao
 * @description 临时测试专用
 * @date 2020/5/8 10:31
 */
public class TempTest {

    public static void main(String[] args) {
//        test1();
        final String url = "https://dev-api.sjsbtc.com/pact/contract/transfer";
        String param = "amount=0.02&coin=btc&type=3&userId=";
        final Map<String, String> header = new HashMap<>();
        header.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzYWx0IjoiT1dSaU9UQmlaV0UyT1dGbU5EazNNR0kyXzExNS4xOTIuNzMuMjM1IiwibG9naW5UeXBlIjoiY2xpZW50VXNlciIsInNlc3Npb25JZCI6InNoaXJvOnNlc3Npb246cGM6ZmQxMjRiNmItYmIzYy00MzI2LWE5MTEtMDlmN2YyNGU4Zjc3IiwiZXhwIjoxNTg5Nzk2NjU3LCJ1c2VySWQiOjE4MDMzMSwidXNlcm5hbWUiOiIxNTA5MDUwODIzNCJ9.gJFavaD5ucV26QZcnlr3irA3OY8s4eG-M3SCtUi6tpQ");
        int i = 1000;
        while (true) {
            if (i % 7 == 0) {
                param = "amount=0.02&coin=btc&type=4&userId=";
            } else {
                param = "amount=0.02&coin=btc&type=3&userId=";
            }
            String finalParam = param;
            new Thread(() -> {
                String result = HttpClientUtils.doPost(url, finalParam, header);
                System.out.println(result);
            }).start();
            if (--i < 0) {
                break;
            }
            if (i % 100 == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void test1() {
        String str = "1,2,3";
        String[] split = str.split(",3", -1);
        System.out.println(split.length);
        System.out.println(split[0]);
        System.out.println(split[1]);
//        System.out.println(Arrays.toString(split));
//        System.out.println(split.length);
//        System.out.println(split[0]);
//        System.out.println(split[1]);

        System.out.println("---------------------");
        int index = str.indexOf(",3");
        System.out.println(str.substring(index + 2));
//        System.out.println(str.length());
//        System.out.println(index);
//        System.out.println(index + 2 == str.length());
//        System.out.println(str.charAt(index));

    }

}
