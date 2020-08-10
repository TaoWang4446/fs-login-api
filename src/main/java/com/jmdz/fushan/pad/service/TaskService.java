package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.TaskRemindDao;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.model.config.TaskClientType;
import com.jmdz.fushan.model.config.TaskSourceModule;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.task.TaskNoticeData;
import com.jmdz.fushan.pad.model.task.TaskNoticeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;

/**
 * TaskService
 *
 * @author LiCongLu
 * @date 2020-07-29 13:57
 */
@Service("taskService")
public class TaskService extends BaseService {

    @Resource
    private TaskRemindDao taskRemindDao;

    @Resource
    private ConfigData configData;

    /**
     * 加载提醒信息接口
     *
     * @param data 加载类型
     * @return
     * @author LiCongLu
     * @date 2020-07-29 13:02
     */
    public BaseResult<ArrayList<TaskNoticeVo>> loadTaskNotice(LoginItem loginItem, TaskNoticeData data) {

        ArrayListExt<String> sourceList = new ArrayListExt<>();
        switch (data.getClientType()) {
            case TaskClientType.BUSINESS:
                sourceList.addExt(TaskSourceModule.SongLingRenWu)
                        .addExt(TaskSourceModule.WX_YuYueJieYun)
                        .addExt(TaskSourceModule.WX_YuYueGaoBie);
                break;
            case TaskClientType.VEHICLE:
                sourceList.addExt(TaskSourceModule.JieYunRenWu);
                break;
            case TaskClientType.FAREWELL:
                sourceList.addExt(TaskSourceModule.GaoBieRenWu);
                break;
            case TaskClientType.HALL:
                sourceList.addExt(TaskSourceModule.RuTingJieYun)
                        .addExt(TaskSourceModule.ChuTingJieYun);
                break;
        }

        // 判断任务是否存在
        if (DataUtil.invalid(sourceList)) {
            return failure("客户端类型错误，不存在提醒任务");
        }

        // 加载提醒任务
        ArrayList<TaskNoticeVo> loadItems = taskRemindDao.listTaskNoticeForView(sourceList, loginItem, configData.getTaskNoticeViewTime());
        if (DataUtil.valid(loadItems)) {
            // 设置加载阅读
            for (TaskNoticeVo loadItem : loadItems) {
                taskRemindDao.insertTaskRemindView(loadItem.getId(), loginItem);
            }
        }

        return success(loadItems);
    }
}
