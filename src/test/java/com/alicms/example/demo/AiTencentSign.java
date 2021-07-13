package com.alicms.example.demo;

import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

public class AiTencentSign {

    /**
     * Description: 鉴权
     *
     * @param appKey appKey
     * @param params 参数
     * @return: java.lang.String
     * @author: Mxc<xc@alicms.com>
     * @date: 2020/4/27 14:04
     */
    public static String getReqSign(String appKey ,Map<String, String> params){
        TreeMap<String, String> map = new TreeMap<>();
        map.putAll(params);
        try {
            String sign;
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key);
                sb.append("=");
                String val = URLEncoder.encode(value,"UTF-8");
                sb.append(val);
                sb.append("&");
            }
            sb.append("app_key");
            sb.append("=");
            sb.append(appKey);
            String str = sb.toString();
            sign = Encrypt.phpMD5(str).toUpperCase();
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
