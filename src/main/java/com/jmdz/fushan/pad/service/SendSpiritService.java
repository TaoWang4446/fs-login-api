package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.BusinessLogDao;
import com.jmdz.fushan.dao.CrematingDao;
import com.jmdz.fushan.dao.TaskRemindDao;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.config.TaskSourceModule;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.spirit.SendSpiritListItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * 送灵任务接口
 *
 * @author LiCongLu
 * @date 2020-07-22 16:35
 */
@Service("sendSpiritService")
public class SendSpiritService extends BaseService {

    @Resource
    private CrematingDao crematingDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private TaskRemindDao taskRemindDao;

    /**
     * 加载送灵任务列表接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-22 16:38
     */
    public BaseResult<ArrayList<SendSpiritListItem>> loadSendSpiritList() {
        // 加载所有数据
        ArrayList<SendSpiritListItem> loadAllItems = crematingDao.listSendSpirit();

        // 最终数据
        ArrayList<SendSpiritListItem> loadItems = new ArrayList<>();

        // 冷却中或火化完成未取灰数据
        ArrayList<SendSpiritListItem> loadHighlightItems = new ArrayList<>();

        // 取灰完成的数据
        ArrayList<SendSpiritListItem> loadAshItems = new ArrayList<>();

        // 取灰完成的数据
        ArrayList<SendSpiritListItem> loadMiddleItems = new ArrayList<>();

        // 设置状态
        for (SendSpiritListItem loadItem : loadAllItems) {
            loadItem.setAsHighlight(0);
            if (DataUtil.valid(loadItem.getAsGetAsh())) {
                // 已取灰的不需要确认
                loadItem.setCremationStateText("已取灰")
                        .setConfirmSendSpirit(1);
                loadAshItems.add(loadItem);
            } else {
                if (DataUtil.equals(loadItem.getCremationState(), Constants.CrematingState.Order)) {
                    loadMiddleItems.add(loadItem.setCremationStateText("未调度"));
                } else if (DataUtil.equals(loadItem.getCremationState(), Constants.CrematingState.Dispatch)) {
                    loadMiddleItems.add(loadItem.setCremationStateText("已调度"));
                } else if (DataUtil.equals(loadItem.getCremationState(), Constants.CrematingState.Begin)) {
                    loadMiddleItems.add(loadItem.setCremationStateText("火化中"));
                } else if (DataUtil.equals(loadItem.getCremationState(), Constants.CrematingState.Cooling)) {
                    loadHighlightItems.add(loadItem.setCremationStateText("冷却中").setAsHighlight(1));
                } else if (DataUtil.equals(loadItem.getCremationState(), Constants.CrematingState.End)) {
                    loadHighlightItems.add(loadItem.setCremationStateText("火化完成").setAsHighlight(1));
                } else {
                    loadMiddleItems.add(loadItem);
                }
            }
        }

        // 逐个排序
        Collections.sort(loadHighlightItems);
        Collections.sort(loadMiddleItems);
        Collections.sort(loadAshItems);

        // 加载返回数据
        loadItems.addAll(loadHighlightItems);
        loadItems.addAll(loadMiddleItems);
        loadItems.addAll(loadAshItems);
        return success(loadItems);
    }

    /**
     * 保存确认送灵任务接口
     *
     * @param loginItem 当前账号
     * @param data      主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-23 09:46
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveConfirmSendSpirit(LoginItem loginItem, IdData data) throws ActionException {
        SendSpiritListItem loadItem = crematingDao.getSendSpiritById(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getOperationNo())) {
            return failure("业务主键错误，不存在此送灵任务");
        }

        // 判断是否取灰
        if (DataUtil.valid(loadItem.getAsGetAsh())) {
            return failure("当前送灵任务已取灰，无法继续确认");
        }

        // 判断是否已确认
        if (DataUtil.valid(loadItem.getConfirmSendSpirit())) {
            return failure("当前送灵任务已确认，无法再次确认");
        }

        // 进行送灵确认
        crematingDao.updateConfirmSendSpirit(loadItem.getId(), loginItem);
        //  更新提醒任务
        taskRemindDao.updateReceiveStateByBusinessId(TaskSourceModule.SongLingRenWu, TableNames.Cremating, String.valueOf(loadItem.getId()));

        String logContent = StringUtil.format("火化送灵任务,逝者【{0}】【{1}】,送灵任务确认。", loadItem.getOperationNo(), loadItem.getDeadName());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.HuoHua, "送灵任务", BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        return loadSendSpiritList();
    }
}
