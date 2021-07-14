package com.alicms.example.demo.encryption.md5;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 计算文件和字符串的md5值
 * </p>
 *
 * @author zhenghao
 * @date 2021/7/14 13:31
 */
public class Md5UtilTest {
    public static void main(String[] args) throws Exception {
        String md5ByFile = getMd5ByFile("D:\\workspace\\IdeaProjects\\demo0819\\springboot-demo1\\src\\test\\java\\com\\alicms\\example\\demo\\file\\Md5FileTest.java");
        System.out.println(md5ByFile);
        String str = "D:\\workspace\\IdeaProjects\\demo0819\\springboot-demo1\\src\\test\\java\\com\\alicms\\example\\demo\\file\\md5\\Md5FileTest.java";
        System.out.println(md5(str));
        System.out.println(DigestUtils.md5Hex(str));
    }

    /**
     * 计算文件MD5
     * @return 文件MD5
     */
    public static String getMd5ByFile(String path) throws IOException {
        FileInputStream in = new FileInputStream(new File(path));
        return DigestUtils.md5Hex(in);
//        String value = null;
//        try {
//            MappedByteBuffer byteBuffer =
//                    in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            md5.update(byteBuffer);
//            BigInteger bi = new BigInteger(1, md5.digest());
//            value = bi.toString(16);
//        } catch (Exception e) {
//            return null;
//        } finally {
//            try {
//                in.close();
//            } catch (IOException e) {
//                return null;
//            }
//        }
//        return value;
    }

    /**
     * 获取MD5加密后的字符串
     *
     * @param str 明文
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException 异常
     */
    public static String md5(String str) throws NoSuchAlgorithmException {
//        return DigestUtils.md5Hex(str);
        return md5(str.getBytes());
    }

    public static String md5(byte[] bytes) throws NoSuchAlgorithmException {
//        return DigestUtils.md5Hex(bytes);
        /** 创建MD5加密对象 */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        /** 进行加密 */
        md5.update(bytes);
        /** 获取加密后的字节数组 */
        BigInteger bi = new BigInteger(1, md5.digest());
        return bi.toString(16);
    }
}
