package com.alicms.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;

@Component
@Slf4j
public class AiTencentUtil {
    /** appID */
    private static Integer appID;
    /** appKey */
    private static String appKey;
    /** 身份证OCR */
    private static String OCR_ID_CARD = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_idcardocr";
    /** 银行卡OCR */
    private static String OCR_CREDIT_CARD = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_creditcardocr";
    /** 语种识别 */
    private static String NLP_TEXT_DETECT = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textdetect";
    /** 文本翻译 */
    private static String NLP_TEXT_TRANSLATE = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_texttrans";
    //private static String NLP_TEXT_TRANSLATE = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_texttranslate";
    /** 文本翻译 */
    private static String NLP_IMAGE_TRANSLATE = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_imagetranslate";
    /** 智能闲聊 */
    private static String NLP_TEXT_CHAT = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";
    /** 多标签识别 */
    private static String IMAGE_TAG = "https://api.ai.qq.com/fcgi-bin/image/image_tag";
    /** 意图成分 */
    private static String NLP_WORD_COM = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_wordcom";

    public Integer getAppID() {
        return appID;
    }

    @Value("${ai.tencent.app-id}")
    public void setAppSecret(Integer appID) {
        AiTencentUtil.appID = appID;
    }

    public String getAppKey() {
        return appKey;
    }

    @Value("${ai.tencent.app-key}")
    public void setAppKey(String appKey) {
        AiTencentUtil.appKey = appKey;
    }

    public static String doPost(){
        return "";
    }

    public static void main(String[] args) {
        String appID = "2135181496";
        String appKey = "FhjDG6u2naRaybke";
        Integer time = Integer.valueOf(String.valueOf(System.currentTimeMillis()/1000));
        //Map<String, String> map = new HashMap<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("app_id",appID);
        map.put("nonce_str","sdasdas5323423");
//        map.put("nonce_str",CustomUUID.getUUID());
        map.put("text","苹果手机");
//        map.put("time_stamp","1588140120");
        map.put("time_stamp",time.toString());
        map.put("type","0");
        //map.put("source","zh");
        //map.put("target","en");
        //map.put("sign","");
        //map.put("session","10000");
        //map.put("nonce_str","20e3408a79");
        String sign = AiTencentSign.getReqSign(appKey,map);

        map.put("sign",sign);
//        String result = HttpClientUtil.doPost(AiTencentUtil.NLP_TEXT_TRANSLATE, map);
        String param = "";
        for (String key : map.keySet()) {
            param = param + key + "=" + map.get(key) + "&";
        }
        String result = sendPost(AiTencentUtil.NLP_TEXT_TRANSLATE, param.substring(0, param.length() - 1));
        System.out.println(result);
//        BigDecimal divide = new BigDecimal("51.3").divide(
//                BigDecimal.ONE.add(new BigDecimal("10").multiply(new BigDecimal("0.01"))),
//                8, BigDecimal.ROUND_DOWN);
//        System.out.println(divide);
    }
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
