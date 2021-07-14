package com.alicms.example.demo.encryption;

import com.alicms.example.demo.utils.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description TODO
 * @Author zhenghao
 * @Date 2020/1/15 14:50
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
public class GZipTest {
//    @Test
    public static void main(String[] args) {
        String dateStr = "hello world, 张三, 哈哈.";
        String gzip = ZipUtil.gzip(dateStr);
        String gunzip = ZipUtil.gunzip(gzip);
        System.out.println(dateStr);
        System.out.println("------------------------------");
        System.out.println(gzip);
        System.out.println("------------------------------");
        System.out.println(gunzip);
        System.out.println("========================================");
        String zip = ZipUtil.zip(dateStr);
        System.out.println(zip);
        System.out.println(ZipUtil.unzip(zip));

    }
}
