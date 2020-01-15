package com.alicms.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description
 * @Author zhenghao
 * @Date 2019/9/10 16:38
 */
@Mapper
public interface ForUpTestMapper {
    @Select("select cron from alicms_user_bank limit 1")
    String getCron();

    @Update("update alicms_user_bank set update_time = now() where id=1")
    int updateCron(String cron);
}
