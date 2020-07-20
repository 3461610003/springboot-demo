package com.alicms.example.demo.baidu;

import com.alibaba.fastjson.JSONObject;
import com.alicms.example.demo.utils.HttpClientUtils;
import com.alicms.example.demo.utils.MapUrlUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenghao
 * @description 文字转语音
 * @date 2020/7/11 13:57
 */
public class Text2VoiceDemo {

    public static void main(String[] args) throws IOException {
//        String url = "https://developer.baidu.com/vcast/getVcastInfo";
//        String content = "你好啊，我是java程序跑出来的！！哈哈";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("volumn", "7");
//        paramMap.put("pit", "5");
//        paramMap.put("method", "TRADIONAL");
//        paramMap.put("title", "test1");
//        // 情感女声:sex=4,情感男声:sex=3,非情感女声:sex=0,非情感男声:sex=1
//        // 播放速度:慢速:speed=3,正常:speed=5,快速:speed=7
//        paramMap.put("sex", "0");
//        paramMap.put("speed", "5");
//        paramMap.put("content", content);
//        String param = MapUrlUtil.getUrlParamsByMap(paramMap);
//        System.out.println(param);
//        String result = HttpClientUtils.doPost(url, param + content, getHeader());
//        System.out.println(result);

        // speed:0-10,sex:0-4
//        String content = "你好啊，我是java程序跑出来的！！哈哈你好啊，我是java程序跑出来的！！哈哈你好啊，我是java程序跑出来的！！哈哈";
//        for (int sex = 0; sex < 5; sex++) {
//            String result = text2Voice(sex, 5, content);
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            System.out.println(jsonObject.get("bosUrl"));
//        }

        BufferedReader br = new BufferedReader( new FileReader("D:\\Users\\book\\武神主宰.txt"));
        String line = null;
        // 遍历读取文件的一行内容
        StringBuilder  sb = new StringBuilder ();
        String fileName = "作者：暗魔师简介";
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0)  continue;
            if (line.startsWith("第")) {
                String[] split = line.split("\\d+");
                if (split.length > 1 && split[1] != null && split[1].startsWith("章")) {
                    if (sb.length() < 500) {
                        fileName = line;
                        sb.append(line);
                        continue;
                    }
                    System.out.println(sb.toString().length() + "," + sb.toString());
                    String result = text2Voice(4, 5, sb.toString(), fileName);
                    String bosUrl = (String) JSONObject.parseObject(result).get("bosUrl");
                    HttpClientUtils.doGetFileByName(bosUrl, getHeader(), fileName + ".mp3");
                    sb.delete(0, sb.length());
                }
            }
            sb.append(line);
        }
        br.close();

    }

    public static String text2Voice(int sex, int speed, String content, String title) {
        String url = "https://developer.baidu.com/vcast/getVcastInfo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("volumn", "7");
        paramMap.put("pit", "5");
        paramMap.put("method", "TRADIONAL");
        paramMap.put("title", title);
        // 情感女声:sex=4,情感男声:sex=3,非情感女声:sex=0,非情感男声:sex=1
        // 播放速度:慢速:speed=3,正常:speed=5,快速:speed=7
        paramMap.put("sex", sex);
        paramMap.put("speed", speed);
        paramMap.put("content", content);
        String param = MapUrlUtil.getUrlParamsByMap(paramMap);
        String result = HttpClientUtils.doPost(url, param, getHeader());
//        System.out.println("sex=" + sex + ",speed=" + speed + ",content=" + content + "," + result);
        return result;
    }

    private static Map<String, String> getHeader() {
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept", "application/json, text/javascript, */*; q=0.01");
        header.put("Accept-Encoding", "gzip, deflate, br");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        header.put("Connection", "keep-alive");
        header.put("Content-Length", "255");
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        header.put("Cookie", "PSTM=1594435978; BAIDUID=FEC49C52CE2ECAD8C08E58BEC3F61B50:FG=1; BIDUPSID=32DBD676997C56B831D7FDB2B7110C2D; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; yjs_js_security_passport=f1720e5107f04a0a77de98d561a46f024a0d2154_1594438493_js; H_PS_PSSID=31908_1424_31326_32139_31253_32045_32230_31322_32260_26350; BDUSS=zB1QmcwbW5jdDJuZTloZ2dGT3lQejhyaG5jN1dETHJCN3ZBU1J4THRndG8yekJmSUFBQUFBJCQAAAAAAAAAAAEAAABNdwpC0v7V318wMQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGhOCV9oTglfN; Hm_lvt_3abe3fb0969d25e335f1fe7559defcc6=1594446540; Hm_lpvt_3abe3fb0969d25e335f1fe7559defcc6=1594446594");
        header.put("Host", "developer.baidu.com");
        header.put("Origin", "https://developer.baidu.com");
        header.put("Referer", "https://developer.baidu.com/vcast");
        header.put("Sec-Fetch-Dest", "empty");
        header.put("Sec-Fetch-Mode", "cors");
        header.put("Sec-Fetch-Site", "same-origin");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        header.put("X-Requested-With", "XMLHttpRequest");
        return header;
    }

}
