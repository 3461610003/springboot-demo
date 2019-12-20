package com.alicms.example.demo.task;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.quartz.CronExpression;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author zhenghao
 * @Date 2019/8/29 14:28
 */
//@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
public class TaskTest2 implements SchedulingConfigurer {
    @Mapper
    public interface CronMapper {
        @Select("select cron from alicms_cron limit 1")
        String getCron();

        @Update("update alicms_cron set cron = #{cron} where cron_id=1")
        int updateCron(String cron);
    }

    @Resource      //注入mapper
            CronMapper cronMapper;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    /*String[] crones = {"0/1 * * * * ?", "0/3 * * * * ?", "0/5 * * * * ?"};
                    String randomCron = crones[new Random().nextInt(crones.length)];
                    int result = cronMapper.updateCron(randomCron);
                    System.out.println("update corn ============ " + randomCron + " result:" + result + " ==========");*/

                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();
                    //2.2 合法性校验.
                    boolean validExpression = CronExpression.isValidExpression(cron);
                    if (!validExpression) {
                        // Omitted Code ..
                        System.out.println("表达式有错误！");
                        return null;
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }


}
