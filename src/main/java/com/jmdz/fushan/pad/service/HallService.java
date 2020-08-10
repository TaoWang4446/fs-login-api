package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.FarewellDao;
import com.jmdz.fushan.dao.TaskRemindDao;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.FarewellTaskFlag;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.config.TaskSourceModule;
import com.jmdz.fushan.pad.model.farewell.FarewellDeadRemindListItem;
import com.jmdz.fushan.pad.model.farewell.FarewellTaskItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.task.TaskRemindItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * HallService
 *
 * @author LiCongLu
 * @date 2020-07-15 15:21
 */
@SuppressWarnings("unchecked")
@Service("hallService")
public class HallService extends BaseService {

    @Resource
    private FarewellDao farewellDao;

    @Resource
    private TaskRemindDao taskRemindDao;

    @Resource
    private FarewellService farewellService;

    /**
     * 加载出入厅告别任务提醒列表
     *
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-15 15:24
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<ArrayList<FarewellDeadRemindListItem>> loadFarewellDeadRemindList(LoginItem loginItem) throws ActionException {
        ArrayList<FarewellDeadRemindListItem> loadItems = new ArrayList<>();

        // 获取业务信息
        ArrayList<TaskRemindItem> remindItems = taskRemindDao.listTaskRemindByTableName(TableNames.Mourn);

        HashMap<Integer, TaskRemindItem> ruTingMap = new HashMap<>(16);
        HashMap<Integer, TaskRemindItem> chuTingMap = new HashMap<>(16);

        ArrayList<Integer> idList = new ArrayList<>();
        for (TaskRemindItem remindItem : remindItems) {
            try {
                Integer businessId = Integer.parseInt(remindItem.getBusinessId());
                if (DataUtil.equals(remindItem.getSourceModule(), TaskSourceModule.RuTingJieYun)) {
                    ruTingMap.put(remindItem.getId(), remindItem);
                    idList.add(businessId);
                } else if (DataUtil.equals(remindItem.getSourceModule(), TaskSourceModule.ChuTingJieYun)) {
                    chuTingMap.put(remindItem.getId(), remindItem);
                    idList.add(businessId);
                }
            } catch (Exception ex) {

            }
        }

        // 加载提醒任务
        if (DataUtil.valid(idList)) {
            ArrayList<FarewellTaskItem> farewellTaskItems = farewellDao.listFarewellTaskByIds(idList);
            HashMap<String, FarewellTaskItem> taskMap = new HashMap<>(16);
            for (FarewellTaskItem taskItem : farewellTaskItems) {
                taskMap.put(taskItem.getFarewellId().toString(), taskItem);
            }

            ArrayList<Integer> remindIdList = new ArrayList<>();

            // 入厅提醒任务
            for (Map.Entry<Integer, TaskRemindItem> entry : ruTingMap.entrySet()) {
                TaskRemindItem remindItem = entry.getValue();
                if (taskMap.containsKey(remindItem.getBusinessId())) {
                    FarewellTaskItem taskItem = taskMap.get(remindItem.getBusinessId());
                    // 未入厅提醒
                    if (DataUtil.equals(taskItem.getFarewellState(), Constants.MournState.Normal)) {
                        farewellService.resetFarewellTime(taskItem);
                        FarewellDeadRemindListItem deadRemindItem = BeanUtil.copy2Bean(taskItem, new FarewellDeadRemindListItem());
                        deadRemindItem.setRemindId(remindItem.getId())
                                .setTaskName("入厅任务");
                        remindIdList.add(remindItem.getId());

                        // 更新状态
                        if (taskItem.getTaskFlag().intValue() < FarewellTaskFlag.RuTingTongZhiJieShou) {
                            farewellDao.updateFarewellTaskFlag(taskItem.getFarewellId(), taskItem.getOperationNo(), FarewellTaskFlag.RuTingTongZhiJieShou, loginItem);
                        }

                        loadItems.add(deadRemindItem);
                    }
                }
            }

            // 出厅提醒任务
            for (Map.Entry<Integer, TaskRemindItem> entry : chuTingMap.entrySet()) {
                TaskRemindItem remindItem = entry.getValue();
                if (taskMap.containsKey(remindItem.getBusinessId())) {
                    FarewellTaskItem taskItem = taskMap.get(remindItem.getBusinessId());
                    // 出厅提醒
                    if (DataUtil.equals(taskItem.getFarewellState(), Constants.MournState.Begin)) {
                        farewellService.resetFarewellTime(taskItem);
                        FarewellDeadRemindListItem deadRemindItem = BeanUtil.copy2Bean(taskItem, new FarewellDeadRemindListItem());
                        deadRemindItem.setRemindId(remindItem.getId())
                                .setTaskName("出厅任务");
                        remindIdList.add(remindItem.getId());

                        // 更新状态
                        if (taskItem.getTaskFlag().intValue() < FarewellTaskFlag.ChuTingTongZhiJieShou) {
                            farewellDao.updateFarewellTaskFlag(taskItem.getFarewellId(), taskItem.getOperationNo(), FarewellTaskFlag.ChuTingTongZhiJieShou, loginItem);
                        }

                        loadItems.add(deadRemindItem);
                    }
                }
            }

            if (DataUtil.valid(remindIdList)) {
                // 批量更新接收任务状态,2是接收
                taskRemindDao.updateReceiveStateByIds(remindIdList);
            }
        }

        // 按照发送提醒时间顺序排序
        Collections.sort(loadItems);

        return success(loadItems);
    }
}
