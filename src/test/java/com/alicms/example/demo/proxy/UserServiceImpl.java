package com.alicms.example.demo.proxy;


import java.util.Date;

/**
 * <p>
 * UserServiceImpl
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/3 11:27
 */
public class UserServiceImpl implements UserService {

    @Override
    public boolean save(User user) {
        System.out.println("save:" + user);
        return "zhangsan".equals(user.getName());
    }

    @Override
    public User get(String name) {
        return new User("zhangsan", "123", 18, new Date());
    }
}
