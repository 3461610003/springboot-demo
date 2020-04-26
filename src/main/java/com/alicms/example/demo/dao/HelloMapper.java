package com.alicms.example.demo.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @description
 * @author zhenghao
 * @date 2019/9/10 16:38
 */
//@Mapper
public interface HelloMapper {
    @Select("select cron from alicms_cron limit 1")
    String getCron();

    @Update("update alicms_cron set cron = #{cron} where cron_id=1")
    int updateCron(String cron);
}
