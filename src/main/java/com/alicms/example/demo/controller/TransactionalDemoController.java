package com.alicms.example.demo.controller;

import com.alicms.example.demo.service.TransactionalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * TransactionalDemoController
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/8 14:29
 */
@RestController
@RequestMapping("/tst")
public class TransactionalDemoController {
    @Resource
    private TransactionalService transactionalService;

    @RequestMapping("/demo1")
    public String demo1(String name) throws Exception {
        boolean result = transactionalService.demo1(name);
        return "success";
    }
}
