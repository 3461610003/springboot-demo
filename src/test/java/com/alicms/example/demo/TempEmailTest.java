package com.alicms.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alicms.example.demo.utils.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 临时邮箱生成
 * </p>
 *
 * @author zhenghao
 * @date 2021/6/24 10:45
 */
public class TempEmailTest {
    private static final String COOKIE = "_gid=GA1.2.1832434.1624520045; XSRF-TOKEN=eyJpdiI6InlvUU5YS0ZGMERteUdaeTJaYVdpVnc9PSIsInZhbHVlIjoiQVFKd3pSZWY1VDR5K0lKMXJWeStpMmFwd0NldTYzdU9kdUhWaFhVUEpUdW1VZ2dnY1VOQUhka0hNdDJzdVNoOTVuRGpacmMwb3ZFNnRQTkNmWkdzYkhSZjM1Nnh1eU45UmxUQVVYWnNId2NQbVVVSnoyNURnYmVLVFk3NFJUWFkiLCJtYWMiOiI3MjRlNTEzNzNjZjdlN2IyN2E4YmY0NDM3ZjY1NjliNjQyMjRiZGQwNWEzOWYzYTc0NTJkODJkZTJkNjQ5Yzg4In0%3D; smailpro_session=eyJpdiI6ImQrbFZlTmxKcktJdWF2cFpkVFdCS2c9PSIsInZhbHVlIjoiY2lEZW9kYkt2RCtOQXk2cHlSMUtkc3d5UkpuYlB3YTl6TFFqMWF0K2M3V2xDMGMzdFF3Qmc4STJJZ0FGbFhtcE9mV0NnOU1pV3BoMytBb2NCdnZRNU9jbjZwa1QxK0JISlVid2FSWjFZVDlYV3JOT1pScDduRTNJZ3A5ZnVBelwvIiwibWFjIjoiZTQ2MGJiMzcxN2IzOGZlZjY3MThmMTQ3NWI2ODJkMWRiZDE3MGNiNDg4MzEwODk2ZGMyMmQzNDcwMmM5YjMyNSJ9; q69Fc1Wc8cmVo9QgEtwQOUYaxyI8oqxq3Fcs6esR=eyJpdiI6IjZtYXVvWW82MmxvTXdHM0YyeFBMUmc9PSIsInZhbHVlIjoic0c0OXhVN0UrYTVkcEI5RjVjWmlEZVM2ODFDcDFEZ01oaXl4SE5iOXlFZUZxTVVUUlluN3R0U3FSWWhRaDVTQmpmTzJTd1NZeHczYU50VzdOTHhrbCtYdjM2WW9mREtiTzErdk5tblJCZVNhYUI4TUdYNkpOSlNyenNTVzlITEJyZ2pJSnhhejBVKzNQdEpzY0RoemRydnEyN0VmY3BuU2FGd2VpR0NkTnY2aGVQZDE4a0cxcjFLaTdueEJCRHJNVGNLV3ZaOUVGZDRmd1ZTbmlKY2tXcWFhTWtcL3p0NytaYTlrQ1Y2aDJBa3BPTDdNUzE1VHU5NTc5dHQzOFNRUWVqM3J4dmUzZFdxRUkxK2dCcnlEaEZzb3JEVDJ4XC9GS3FpdlRXelVIUnRpNTZ0NTZQVkZtcVpEbU1iaFRBakloYkswTFlqMjhRdEthdUVkbXN1VW1LMnJhbEtzWlllSjRDbThRczgyYW5rRXRVZjEwa2I2TFNMRjNKVjR0am0zTHIiLCJtYWMiOiI5ZjJlMjBhYTAzODk4OGVhMjllNTE4YWM0YjM3MzI1N2Q0NjMwNjhjNDFiZThjOTljZDZmYWJmMzc2MThlY2U1In0%3D; _ga_GSFQ1G81R7=GS1.1.1624520043.10.1.1624520186.0; _ga=GA1.2.1388413368.1624520045; _gat_gtag_UA_145345867_1=1";
    private static final String X_XSRF_TOKEN = "eyJpdiI6InlvUU5YS0ZGMERteUdaeTJaYVdpVnc9PSIsInZhbHVlIjoiQVFKd3pSZWY1VDR5K0lKMXJWeStpMmFwd0NldTYzdU9kdUhWaFhVUEpUdW1VZ2dnY1VOQUhka0hNdDJzdVNoOTVuRGpacmMwb3ZFNnRQTkNmWkdzYkhSZjM1Nnh1eU45UmxUQVVYWnNId2NQbVVVSnoyNURnYmVLVFk3NFJUWFkiLCJtYWMiOiI3MjRlNTEzNzNjZjdlN2IyN2E4YmY0NDM3ZjY1NjliNjQyMjRiZGQwNWEzOWYzYTc0NTJkODJkZTJkNjQ5Yzg4In0=";

    public static void main(String[] args) {
        Map<String, String> emailMap = getEmail();
        String email = emailMap.get("email");
        String code = emailMap.get("code");
        System.out.println("-----------------------------------------------------------");
        System.out.println(email);
        System.out.println("-----------------------------------------------------------");
//        String code = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImdyYWNlbWF5NjkuNTUuNzQyQGdtYWlsLmNvbSIsInRpbWVzdGFtcCI6MTYyNDUyMzk3Nn0.UBcqq5UGQDXl0AUPHt9K4xOsLAPj1CAdJ4HFJQMgOf0";
//        String email = "gracemay69.55.742@gmail.com";
        printEmailMsgCode(code, email);
    }

    private static Map<String, String> getEmail() {
        HashMap<String, String> resultMap = new HashMap<>();
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("cookie", COOKIE);
        header.put("x-xsrf-token", X_XSRF_TOKEN);
        String param = "{\"type\":\"random\",\"domains\":[1,2,3,5,6,7,12],\"format\":\"restore\",\"username\":null,\"locale\":\"en_US\",\"minStr\":6,\"maxStr\":15}";
//        String result = HttpClientUtils.doPost("https://smailpro.com/app/email", param, header);
        while (true) {
            String result = HttpClientUtil.doPostJson("https://smailpro.com/app/email", param, header);
            // {"code":200,"msg":"OK","items":{"email":{"username":"sgoyette9935@instasmail.com","timestamp":1624503547},"code":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InNnb3lldHRlOTkzNUBpbnN0YXNtYWlsLmNvbSIsInRpbWVzdGFtcCI6MTYyNDUwMzU0N30.oGs7oFoNEaR6gJ-n7T9_gsDZToiUpuE4hloA0Z8zOpg"}}
//           String result = "{\"code\":200,\"msg\":\"OK\",\"items\":{\"email\":{\"username\":\"sgoyette9935@instasmail.com\",\"timestamp\":1624503547},\"code\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InNnb3lldHRlOTkzNUBpbnN0YXNtYWlsLmNvbSIsInRpbWVzdGFtcCI6MTYyNDUwMzU0N30.oGs7oFoNEaR6gJ-n7T9_gsDZToiUpuE4hloA0Z8zOpg\"}}";
            System.out.println("result:" + result);
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.getInteger("code").equals(200)) {
                JSONObject emailJsonObj = jsonObject.getJSONObject("items").getJSONObject("email");
                String email = emailJsonObj.getString("username");
                if (email.endsWith("@gmail.com") && email.length() <= 32) {
                    resultMap.put("email", email);
                    resultMap.put("code", jsonObject.getJSONObject("items").getString("code"));
                    return resultMap;
                }
            }
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void printEmailMsgCode(String code, String email) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("cookie", COOKIE);
        header.put("x-xsrf-token", X_XSRF_TOKEN);
        String param = "{\"code\":\"" + code + "\"}";
        int i = 100;
        while (--i > 0) {
            String result = HttpClientUtil.doPostJson("https://smailpro.com/app/email/inbox", param, header);
            // [{"textFrom":"liuliangjiasuqi@gmail.com","textSubject":"验证码","mid":"17a3cf5837fcf52d","textDate":"2021-06-24 14:39:20","textSnippet":"..."}]
            if (result.startsWith("[")) {
                JSONArray resultJson = JSON.parseArray(result);
                System.out.println(resultJson);
                if (resultJson.size() > 0) {
                    JSONObject jsonObject = resultJson.getJSONObject(0);
                    String msgStr = HttpClientUtil.doPostJson("https://smailpro.com/app/email/inbox/" + jsonObject.getString("mid"), param, header);
                    // {"code":200,"msg":"OK","items":"\u60a8\u672c\u6b21\u64cd\u4f5c\u7684\u9a8c\u8bc1\u7801\u4e3a <b>599746<\/b>\uff0c\u6709\u6548\u671f30\u5206\u949f\uff0c\u8bf7\u52ff\u6cc4\u9732\u7ed9\u4ed6\u4eba\uff0c\u5982\u975e\u672c\u4eba\u64cd\u4f5c\uff0c\u8bf7\u5ffd\u7565!"}
//                    String items = JSON.parseObject(msgStr).getString("items");
                    String pattern = "<b>\\d{4,10}";
                    // 创建 Pattern 对象
                    Pattern r = Pattern.compile(pattern);
                    // 现在创建 matcher 对象
                    Matcher m = r.matcher(msgStr);
                    if (m.find()) {
                        System.out.println("======================================================");
                        System.out.println(email + ": " + m.group(0).substring(3));
                        System.out.println("======================================================");
                    }
                }
            }
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
