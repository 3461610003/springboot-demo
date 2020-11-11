package com.alicms.example.demo.controller;

import com.alicms.example.demo.service.HelloService;
import com.alicms.example.demo.service.impl.TServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * SpringDemoController
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/3 14:36
 */
@RestController
@RequestMapping("/spr")
public class SpringDemoController {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private TServiceImpl tService3;

    @RequestMapping(value = "/t1", method = RequestMethod.GET)
    public String t1() {
        /*
        ClassPathXmlApplicationContext：new ClassPathXmlApplicationContext("applicationContext.xml", ...)加载类路径下的Spring配置文件
        FileSystemXmlApplicationContext：加载本地磁盘的Spring配置文件
         */
        HelloService helloService = (HelloService) applicationContext.getBean("helloServiceImpl");
        HelloService helloService2 = (HelloService) applicationContext.getBean("helloServiceImpl");
        System.out.println(helloService.getClass().getName());
        System.out.println(helloService2.getClass().getName());
        helloService.sayHello();

        TServiceImpl tService = (TServiceImpl) applicationContext.getBean("TServiceImpl");
        TServiceImpl tService2 = (TServiceImpl) applicationContext.getBean("TServiceImpl");
        System.out.println(tService);
        System.out.println(tService2);
        System.out.println(tService3);
        System.out.println(tService.getClass().getName());
        System.out.println(tService2.getClass().getName());
        System.out.println(tService3.getClass().getName());
        tService.sayHello();
        return "success";
    }

}
