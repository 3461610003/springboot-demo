package com.alicms.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author zhenghao
 * @date 2021/5/10 18:21
 */
public class RestTemplateTest2 {

    private static SSLContext createCustomerSSLContext(String keystoreFile, String keystorePassword) throws Exception {
        // 获取sslContext
        SSLContext context = SSLContext.getInstance("TLS");
        // 获取keystore
        KeyStore keyStore = getKeyStore("JKS", new FileInputStream(keystoreFile), keystorePassword);
        // keyStore管理器
        KeyManager[] kms = createKeyManager(keyStore, keystorePassword);
        // 不要信任证书
        TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                System.out.println("checkClientTrusted:" + Arrays.toString(chain) + "," + authType);
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                System.out.println("checkServerTrusted:" + Arrays.toString(chain) + "," + authType);
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers");
                return null;
            }
        };
        context.init(kms, new TrustManager[]{tm}, null);
        return context;
    }

    private static KeyManager[] createKeyManager(KeyStore keyStore, String password) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, IOException, CertificateException {
        KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        factory.init(keyStore, password.toCharArray());
//        factory.init(getKeyStore("JKS", new FileInputStream("D:\\cert\\02\\private_wallet.jks.old"), password), password.toCharArray());
        return factory.getKeyManagers();
    }

    private static KeyStore getKeyStore(String keyStoreType, InputStream stream, String password) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(stream, password.toCharArray());
        return keyStore;
    }

    public static RestTemplate restTemplate(String keystoreFile, String keystorePassword) throws Exception {
        SSLContext sslContext = createCustomerSSLContext(keystoreFile, keystorePassword);
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }


    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = restTemplate("D:\\cert\\02\\private_full_node.jks.old", "123456");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> result = restTemplate.postForEntity("https://chia.net:8555/get_blockchain_state",
                request, String.class);
        System.out.println(result.getBody());


        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Content-Type", "application/json");
        JSONObject param = new JSONObject();
        param.put("wallet_id", 1);
        param.put("new_address", true);
        HttpEntity<JSONObject> request2 = new HttpEntity<>(param, headers2);
//        ResponseEntity<String> result = restTemplate.postForEntity("https://chia.net:9256/get_wallet_balance", request2, String.class);
        ResponseEntity<String> result2 = restTemplate.postForEntity("https://chia.net:9256/get_next_address", request2, String.class);
        System.out.println(result2.getBody());
    }

}
