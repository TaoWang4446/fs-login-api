package com.jmdz.fushan.quartz;

import com.jmdz.common.util.LogUtil;
import com.jmdz.fushan.model.config.ConfigData;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 定时触发器
 *
 * @author LiCongLu
 * @date 2020-06-15 10:59
 */
@Configuration
public class GlobalTrigger {

    @Resource
    private ConfigData configData;

    /**
     * 定义定时工作详情
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-06-15 10:59
     */
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder
                .newJob(GlobalJob.class)
                .withIdentity("globalJob")
                .storeDurably().build();
    }

    /**
     * 定义定时触发器
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-06-15 10:59
     */
    @Bean
    public Trigger cronTrigger() {

        LogUtil.info("准备触发器");
        if (!configData.isStartup()) {
            LogUtil.info("未开启触发器");
            return null;
        }

        LogUtil.info("已开启，定时cron:" + configData.getCronExpression());

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("globalTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(configData.getCronExpression()))
                .build();
    }
}