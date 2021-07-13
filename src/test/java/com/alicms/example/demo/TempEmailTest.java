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
    private static final String COOKIE = "_gid=GA1.2.1854663686.1624846183; _pbjs_userid_consent_data=3524755945110770; __gads=ID=6070e2f7c4fbc4bd-22b9135f07ca007e:T=1624846183:S=ALNI_MbPL-GL-ceyg-FnK3jw7V_dYgcIbg; _gat_gtag_UA_128776493_30=1; XSRF-TOKEN=eyJpdiI6IkRmenpyZzNWWEdCS2JMY1dSb3E4bnc9PSIsInZhbHVlIjoiVU9Vdm5Dc29IQlkzTTlZUDBkOEtyTlRHQTVvK1wvM0dUTGYyZXZcL3NQN2YxbnNmaTBwV2Y2OXRsXC9ubWtTeEpVWkdubm9QYmNIVWFzT29RUm9tMkhEZllFazVGZHAyeVZxNnBjelJteFRUTFVvdEdoeEZGbFwvVGVsQXhEVldRZ3NhIiwibWFjIjoiNjlmYzcwZGU2MjcxNTFmNTQ4MGM5ZDIwNDVkNzIzOTJjNTRiNzc1ZTNmNjFmMWFkOWRiYWY3ODlmYWEyODU2YyJ9; smailpro_session=eyJpdiI6IlZzZzNpMnUzUlVLcDcwQzZjZFlpdWc9PSIsInZhbHVlIjoiRVBuT1F3cHgzTHRsVjBONHdZeEw2OUlucW5nRFd0dHU3OTRFSVBFekRDQjc3TXpyWkkzSEp1bmZ5aFBWamtmSENndnNJaG13ak5ueU5TUnE3aGIxbEI4MU41Qk1SRk93K3FhckNwWVwvVTYxRUIxUEtSc2Frc0tvRnQ1OTgzdFwvciIsIm1hYyI6IjVlYTY5ZTUxMGRjM2E0OTc5NTkwZTgxMmYwZThlZmEyNTQ4N2E0M2U3MGU1ODY4NDQ0NzgwMDNlNDJiOTAyYmEifQ%3D%3D; Oorp9BjYh3YqA3kvTtxio5BpV2uHo05ezAqsePXc=eyJpdiI6InhCQzlmR1IxMmpBeEhaSmhORDBaeFE9PSIsInZhbHVlIjoiclhieFhDWGV2ZFg3a2hnYXJHQjh3dTMxTnlSdWNDRjdXXC9sdlBwNVJBKzY5ZVhXRFExVmhKam9adGZBTFc2YWQwVEhsMEExbDFcLzljRXBSUHpHOHhZMHV4V2tsXC8yemlLMlF4WFV0NXkrdWdiblRMeklEN3ZnZTc5VW0xVzZTWTRiWkdRd0YzMnExWmVNZjhjcmdRZllmNUpNUXhuNllPUWZlMGliaUtYZ1o4MlNIa21hYUZ0YVIzQlV6eHdxVkJ1cHQwM1lrbUtWMHVzNmhhZFwvdFlwYTdwcXVsVCtFeU9TbXB6S1dYa3pPdlY3S0M4d2x5T0RPQndtbDJKeEVmajdwcWE0clFLcXowRkdsTVwvaExHV1hmT2sxd0llUk1MMEJYamJvb05RcUU1QUVWKzZxZWF6UU01RjNNb0F5ZWhZR2NlTGhyZkFRYTIxV2tITG1CM25IdFBqcXN1dVphMFhTMGFvWG4wYzd4SjROYmhiazA0NGxBVWJDWXhkbGF3YjgiLCJtYWMiOiJjMmZkNjJkN2YyMGZkMDdiNGRmNzAzYTVhNjcwMjdjYTdkNThmYmQwZjZmNzdhMDdmYWUxM2UzZGM0NmRjYWY1In0%3D; _ga_GSFQ1G81R7=GS1.1.1624846182.1.1.1624846258.0; _ga=GA1.2.801691743.1624846183; _gat_gtag_UA_145345867_1=1; cto_bidid=Q6EkU19vdDJMJTJCV255SzJjS1YzMGRqQTRPZUlMQlpzUEJFRVE4NmFUNFFSdkdaNm5vSDdOVHIzeTFFSXNISGtGOVcyd09CcHlsVFByREFuNmVYYWFPaFNFSVNRJTNEJTNE; cto_bundle=FV1o0l9TcDFObEZOQUhFckMxR0RIUnY1MGh4SWF4SXhTcVElMkZ3a2F1eWxmMmR5V1lic2RWWVlXblU0WVJFVFk5d3ZkWnNtUlFOQ2RrQWNFd1VzSll4UUZIbERjUVNkelJUOXZOOHZxYlQ0MUZOa1dnJTJGalJWU2kxd3BjJTJCWVFQRFBiWDdCZA; __viCookieActive=true";
    private static final String X_XSRF_TOKEN = "eyJpdiI6IkRmenpyZzNWWEdCS2JMY1dSb3E4bnc9PSIsInZhbHVlIjoiVU9Vdm5Dc29IQlkzTTlZUDBkOEtyTlRHQTVvK1wvM0dUTGYyZXZcL3NQN2YxbnNmaTBwV2Y2OXRsXC9ubWtTeEpVWkdubm9QYmNIVWFzT29RUm9tMkhEZllFazVGZHAyeVZxNnBjelJteFRUTFVvdEdoeEZGbFwvVGVsQXhEVldRZ3NhIiwibWFjIjoiNjlmYzcwZGU2MjcxNTFmNTQ4MGM5ZDIwNDVkNzIzOTJjNTRiNzc1ZTNmNjFmMWFkOWRiYWY3ODlmYWEyODU2YyJ9";

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Map<String, String> emailMap = getEmail();
            String email = emailMap.get("email");
            String code = emailMap.get("code");
            System.out.println("-----------------------------------------------------------");
            System.out.println(email);
            System.out.println("-----------------------------------------------------------");
//        String code = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImdyYWNlbWF5NjkuNTUuNzQyQGdtYWlsLmNvbSIsInRpbWVzdGFtcCI6MTYyNDUyMzk3Nn0.UBcqq5UGQDXl0AUPHt9K4xOsLAPj1CAdJ4HFJQMgOf0";
//        String email = "gracemay69.55.742@gmail.com";
            printEmailMsgCode(code, email);
            Thread.sleep(1000L * 60 * 5); // 休眠5分钟
        }
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
        while (true) {
            String result = HttpClientUtil.doPostJson("https://smailpro.com/app/email/inbox", param, header);
            // [{"textFrom":"liuliangjiasuqi@gmail.com","textSubject":"验证码","mid":"17a3cf5837fcf52d","textDate":"2021-06-24 14:39:20","textSnippet":"..."}]
            if (result.startsWith("[")) {
                JSONArray resultJson = JSON.parseArray(result);
//                System.out.println(resultJson);
                System.out.print("-");
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
                        System.out.println(">");
                        System.out.println("======================================================");
                        System.out.println(email + ": " + m.group(0).substring(3));
                        System.out.println("======================================================");
                        break;
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
