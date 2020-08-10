package com.jmdz.fushan.service;

import com.jmdz.common.util.LogUtil;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

/**
 * 全局定时任务Service类
 *
 * @author LiCongLu
 * @date 2020-06-15 10:59
 */
@Service("globalJobService")
public class GlobalJobService {

    /**
     * 执行任务
     *
     * @param context 任务Context
     * @return
     * @author LiCongLu
     * @date 2020-06-15 10:59
     */
    public void executeInternal(JobExecutionContext context) {
        LogUtil.info("执行定时任务");
    }
}