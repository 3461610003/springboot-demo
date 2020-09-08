package com.alicms.example.demo.service.impl;

import com.alicms.example.demo.dao.TransactionalMapper;
import com.alicms.example.demo.service.TransactionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * TransactionalServiceImpl
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/8 14:32
 */
@Service
public class TransactionalServiceImpl implements TransactionalService {

    @Resource
    private TransactionalMapper transactionalMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean demo1(String name) throws Exception {
        int count1 = transactionalMapper.updateName(name);
//        int count2 = transactionalMapper.updateName(name + "test");
//        if (count2 > 0) {
//            throw new Exception();
//        }
        // Mybatis 事务中有创表语句异常，上面的语句不能回滚问题
        try {
            int count2 = transactionalMapper.createTest();
            System.out.println(count1 + "-->" + count2);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
}
