package com.alicms.example.demo.test;

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
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author zhenghao
 * @date 2021/5/10 18:21
 */
public class RestTemplateTest {

    public static SSLContext createCustomerSSLContext(String keystoreFile, String keystorePassword,
                                                      String truststoreFile, String truststorePassword) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, CertificateException, UnrecoverableKeyException {
        SSLContext context = SSLContext.getInstance("TLS");
        KeyStore keyStore = getKeyStore("JKS", new FileInputStream(keystoreFile), keystorePassword);
        KeyManager[] kms = createKeyManager(keyStore, keystorePassword);
//        KeyStore trustStore = getKeyStore("JKS", new FileInputStream(truststoreFile), truststorePassword);
//        TrustManager[] tms = createTrustManager(trustStore);
        //需要添加信任证书（需要公钥）
        //context.init(kms, tms, null);
        //不要信任证书
        TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        context.init(kms, new TrustManager[]{tm}, null);
        return context;
    }

    private static KeyManager[] createKeyManager(KeyStore keyStore, String password) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        factory.init(keyStore, password.toCharArray());
        return factory.getKeyManagers();
    }

    private static TrustManager[] createTrustManager(KeyStore trustStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        factory.init(trustStore);
        return factory.getTrustManagers();
    }


    public static KeyStore getKeyStore(String keyStoreType, InputStream stream, String password) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(stream, password.toCharArray());
        return keyStore;
    }

    public static RestTemplate restTemplate(String keystoreFile, String keystorePassword,
                                            String truststoreFile, String truststorePassword) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        SSLContext sslContext = createCustomerSSLContext(keystoreFile, keystorePassword, truststoreFile,truststorePassword);
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }


    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = restTemplate("D:/cert/01/private_full_node.jks", "123456",
//                "D:/cert/01/private_full_node.truststore", "123456");
                "", "");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        LinkedMultiValueMap params = new LinkedMultiValueMap();
        HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> result = restTemplate.postForEntity("https://chia.net:8555/get_blockchain_state",
                request, String.class);
        System.out.println( result.getBody());
    }

}
