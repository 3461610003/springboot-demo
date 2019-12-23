package com.alicms.example.demo;

import com.alicms.example.demo.utils.RSAUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Description TODO
 * @Author zhenghao
 * @Date 2019/12/23 10:25
 */
public class RSAUtilsTest {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //        Map<String, String> keyMap = RSAUtils.createKeys(1024);
//        String publicKey = keyMap.get("publicKey");
//        String privateKey = keyMap.get("privateKey");
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCp_Rrszn4fhRYsk_PedvvLwMT-dMAorzBzvsAMTX0aET53I-p9qdGlkB0d0U6fo_P7X1SbOgzZ4jVNgF0Xx_v40knoOPMKI00B5hb_S_GKeg7GREeEt4RRgGCjo9if3i5NABq9MubhuRwaitg2356W8tXoP34s9sk_CkA-jZWhwwIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKn9GuzOfh-FFiyT8952-8vAxP50wCivMHO-wAxNfRoRPncj6n2p0aWQHR3RTp-j8_tfVJs6DNniNU2AXRfH-_jSSeg48wojTQHmFv9L8Yp6DsZER4S3hFGAYKOj2J_eLk0AGr0y5uG5HBqK2Dbfnpby1eg_fiz2yT8KQD6NlaHDAgMBAAECgYB-d7YudDpVTPiAVdrbqf4PSu3Euiu0mRIdKs9K1-O5HxcwIGJ2937oBF4FLxGlp57_Z59UlU22VJt_e37HEJwe_VEK27CIVbucHtOgU0Ty0wJcz1n2qd1nce9k9sxZNRJ7FXXjNYQWJisn5h6a8kqZxPpm8y93CwXbcLAPBcrb4QJBAOFWcdcjgCVawhGBbAbl5GFFlp6vPDVVzhi2jjWSakXfKmkNn9PYg7uuObavGgmcvWgS6sL8BLOr209HQlKATckCQQDBHplV5TvP066iC4khtsfQs65szWAa1QH8TKR7kmn3N6TXwJdyXEDuy3C8b8G3KHtnelsysiJpH4TQznVZKIkrAkBzSYDw6y7S7b5AqpCUbuYLhkjrYwhQ2VeaeTwE2KYanXTbTqcpAatUSG0qtCeEJ3cYWiVWIQ5exlxsES8zeoVRAkEAsPQrxmQU6Trq8nqlfenwr5BlDDcnj46MkjVcJgqj5C3tcvB7FQPpT6XEODYjB4Qlh0y7UfmdxDtoRLhZGIgnHwJAf61XYlKwTF0kbAGQjf2m4jD0ZLvKlI3cRbQAZjohARxkzEqJI0mHg8dlvi5mJs6gObFdHVvq00iuwIy2Qz9x-Q";
        System.out.println("公钥:" + publicKey);
        System.out.println("私钥:" + privateKey);

        System.out.println("=============公钥加密——私钥解密=====================");
        System.out.println("getPublicKey:" + RSAUtils.getPublicKey(publicKey));
        System.out.println("getPrivateKey:" + RSAUtils.getPrivateKey(privateKey));

        String str = "mh123456";
        System.out.println("明文：" + str);
        System.out.println("明文长度：" + str.length());
        for (int i = 1; i < 10; i++) {
            String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
            System.out.println("密文" + i + "：" + encodedData);
            String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
            System.out.println("解密后文字" + i + ": " + decodedData);
        }
    }
}
