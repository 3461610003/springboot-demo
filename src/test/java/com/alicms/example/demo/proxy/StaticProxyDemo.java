package com.alicms.example.demo.proxy;

/**
 * <p>
 *     静态代理
 * StaticProxyDemo
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/3 13:18
 */
public class StaticProxyDemo implements UserService {
    private UserService userService;

    public StaticProxyDemo(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean save(User user) {
        System.out.println("[Proxy]一些前置处理");
        System.out.println(String.format("[Proxy]打印保存用户信息：%s", user));
        boolean save = userService.save(user);
        System.out.println("[Proxy]一些后置处理");
        return save;
    }

    @Override
    public User get(String name) {
        System.out.println("[Proxy]一些前置处理");
        System.out.println(String.format("[Proxy]打印获取用户信息：用户名：%s", name));
        User user = userService.get(name);
        System.out.println("[Proxy]一些后置处理");
        return user;
    }

    public static void main(String[] args) {
        StaticProxyDemo staticProxyDemo = new StaticProxyDemo(new UserServiceImpl());
        User zhangsan = staticProxyDemo.get("zhangsan");
        System.out.println(zhangsan);
    }
}
