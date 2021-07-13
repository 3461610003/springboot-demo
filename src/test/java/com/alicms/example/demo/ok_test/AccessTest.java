package com.alicms.example.demo.ok_test;

import com.alicms.example.demo.utils.HttpClientUtil;
import com.alicms.example.demo.utils.HttpClientUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccessTest {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 10; i < 2000; i++) {
                int t = new Random().nextInt(2 * i);
                long startTime = 1625056873819L + t;
                long endTime = 1625056933925L + t;
                // api.trongrid.io
                // apiasia.tronscan.io
                String result = HttpClientUtil.doGet("https://api.trongrid.io/api/token_trc20/transfers?start=20&limit=40&contract_address=TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t&start_timestamp=" + startTime + "&end_timestamp=" + endTime,
                        new HashMap<>());
                if (!result.startsWith("{")) {
                    System.out.println(result);
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();
    }

    public static void main2(String[] args) {
        String timestamp = System.currentTimeMillis() + "";
        Map<String, String> header = new HashMap<>();
        // 字符串类型的APIKey
        header.put("OK-ACCESS-KEY", "d3d4c833-605e-4626-8c1a-dca43f54b30e");
        // 使用Base64编码签名(请参阅签名)。
        // OK-ACCESS-SIGN的请求头是对 timestamp + method + requestPath + body字符串(+表示字符串连接)，以及SecretKey，使用HMAC SHA256方法加密，通过Base64编码输出而得到的。
        //例如：sign=CryptoJS.enc.Base64.Stringify(CryptoJS.HmacSHA256(timestamp + 'GET' + '/users/self/verify', SecretKey))
        // 其中，timestamp的值与OK-ACCESS-TIMESTAMP请求头相同，必须是UTC时区Unix时间戳的十进制秒数格式或ISO8601标准的时间格式，精确到毫秒。
        //method是请求方法，字母全部大写：GET/POST。
        //requestPath是请求接口路径。例如：/api/spot/v3/orders?instrument_id=OKB-USDT&state=2
        //body是指请求主体的字符串，如果请求没有主体(通常为GET请求)则body可省略。例如：{"product_id":"BTC-USD-0309","order_id":"377454671037440"}
        //SecretKey为用户申请APIKey时所生成。例如：22582BD0CFF14C41EDBF1AB98506286D’
        header.put("OK-ACCESS-SIGN", sign(timestamp, "GET", "/users/self/verify"));
        // OK-ACCESS-TIMESTAMP发起请求的时间戳。
        header.put("OK-ACCESS-TIMESTAMP", timestamp);
        // 您在创建API密钥时指定的Passphrase。
        header.put("OK-ACCESS-PASSPHRASE", "f62WJGhPLphT");
//        String result = HttpClientUtils.doPost("https://www.okexcn.com/users/self/verify", "", header);
        String result = HttpClientUtils.doGet("https://www.okexcn.com/users/self/verify", header);
        System.out.println(result);
//        String sign = sign(timestamp, "GET", "/users/self/verify");
//        System.out.println(sign);
    }

    private static String sign(String timestamp, String method, String requestPath) {
//        return CryptoJS.enc.Base64.Stringify(CryptoJS.HmacSHA256(timestamp + 'GET' + '/users/self/verify', SecretKey));
        String secret = "3D71569D7E0AFDFA2A3307841C1EBD4D";
        String message = timestamp + method + requestPath;
        String sign = null;
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            sign = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

}
