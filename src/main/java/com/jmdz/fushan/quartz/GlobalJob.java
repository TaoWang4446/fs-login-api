package com.jmdz.fushan.quartz;

import com.jmdz.common.util.LogUtil;
import com.jmdz.fushan.service.GlobalJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

/**
 * GlobalJob
 *
 * @author LiCongLu
 * @date 2020-06-15 10:58
 */
public class GlobalJob extends QuartzJobBean {

    @Resource
    private GlobalJobService globalJobService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LogUtil.info("开启定时查询");
        globalJobService.executeInternal(context);
        LogUtil.info("结束定时查询");
    }
}