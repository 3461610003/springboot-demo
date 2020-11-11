package com.alicms.example.demo.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * <p>
 * GenerateProxyDemo
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/3 14:15
 */
public class GenerateProxyDemo {
    public static void main(String[] args) {
//        UserServiceImpl userService = new UserServiceImpl();
//        saveFile("./", "$Proxy02", userService);

        UserServiceImpl userService = new UserServiceImpl();
        $Proxy02 $Proxy02 = new $Proxy02((proxy, method, args1) -> method.invoke(userService, args1));
        User zz = $Proxy02.get("zz");
        System.out.println("get:" + zz);
        System.out.println($Proxy02.save(new User("zhangsan", "yy", 22, new Date())));

    }

    public static void saveFile(String pathDir, String className, Object obj) {
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(className, obj.getClass().getInterfaces(), 1);
        Path path1 = Paths.get(pathDir);
        if (!path1.toFile().exists()) {
            path1.toFile().mkdirs();
        }
        String path = pathDir + className + ".class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(proxyClassFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}
