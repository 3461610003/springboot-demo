package com.alicms.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * TransactionalMapper
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/8 14:32
 */
@Mapper
public interface TransactionalMapper {
    @Select("select user_name from alicms_admin limit 1")
    String getUserName();

    @Update("CREATE TABLE IF NOT EXISTS `alicms_test2` (" +
            "  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'id'," +
            "  `name` varchar(32) NOT NULL COMMENT '名称'," +
            "  PRIMARY KEY (`id`)" +
            ") engine=innodb COMMENT='测试表'")
    int createTest();

    @Update("update alicms_admin set user_name = #{name} where id = 1")
    int updateName(String name);
}
