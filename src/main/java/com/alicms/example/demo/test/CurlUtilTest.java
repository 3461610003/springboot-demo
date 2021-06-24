package com.alicms.example.demo.test;

import org.apache.http.HttpResponse;

import static org.toilelibre.libe.curl.Curl.curl;

/**
 * <p>
 * Curl 发送请求工具
 * </p>
 *
 * @author zhenghao
 * @date 2021/5/7 21:09
 */
public class CurlUtilTest {
    public static void main(String[] args) {
//        $("curl -k https://localhost:8443/public/");
//        curl("-k https://localhost:8443/public/");
        HttpResponse response = curl()
                .insecure()
                .cert("src/main/java/cert/private_full_node.crt")
                .key("src/main/java/cert/private_full_node.key")
                .d("{}")
                .header("Content-Type: application/json")
                .xUpperCase("POST")
                .run("https://192.168.10.236:8555/get_blockchain_state");
        System.out.println(response);

//        String result = $("curl -k --cert src/main/java/cert/private_full_node.crt --key src/main/java/cert/private_full_node.key -d '{}' -H 'Content-Type: application/json' -X POST https://192.168.10.236:8555/get_blockchain_state");
//        System.out.println(result);
    }
}
