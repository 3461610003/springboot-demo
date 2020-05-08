package com.alicms.example.demo.test;

import com.alicms.example.demo.model.BuilderTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhenghao
 * @description lombok测试
 * @date 2020/4/29 13:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LombokBuilderTest {

    @Test
    public void test1() {
        System.out.println(BuilderTest.builder().build());
        System.out.println(BuilderTest.builder().code(200).build());
        System.out.println(BuilderTest.builder().message("success").build());
        System.out.println(BuilderTest.builder().code(200).message("success").error("测试成功").build());
    }
}
