package com.alicms.example.demo.proxy;

/**
 * <p>
 * UserService
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/3 11:25
 */
public interface UserService {
    boolean save(User user);
    User get(String name);
}
