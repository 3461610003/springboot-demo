package com.alicms.example.demo.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 * CglibProxyDemo
 * CGLib通过继承被代理类的方式实现代理
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/3 13:46
 */
public class CglibProxyDemo implements MethodInterceptor {
    private Object target;

    public Object getProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("[Proxy]一些前置处理");
        Object invoke = methodProxy.invoke(target, objects);
        System.out.println("[Proxy]一些后置处理");
        return invoke;
    }

    public static void main(String[] args) {
        // 1.
//        CglibProxyDemo cglibProxyDemo = new CglibProxyDemo();
//        UserService userService = (UserService) cglibProxyDemo.getProxy(new UserServiceImpl());
//        System.out.println(userService.get("zhangsan"));



        // 2.
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        MethodInterceptor methodInterceptor = (o, method, objects, methodProxy) -> {
            System.out.println("cglib前置增强----------");
            return methodProxy.invoke(userServiceImpl, objects);
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(methodInterceptor);
        UserService userService = (UserService) enhancer.create();
        User user = userService.get("");
        System.out.println(user);
    }
}
