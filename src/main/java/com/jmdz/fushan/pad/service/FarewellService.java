package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.helper.PdfFileHelper;
import com.jmdz.fushan.helper.RecordTool;
import com.jmdz.fushan.model.config.*;
import com.jmdz.fushan.model.entity.RecordFile;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.SignImageItem;
import com.jmdz.fushan.pad.model.farewell.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.task.TaskRecordListItem;
import com.jmdz.fushan.pad.model.task.TaskRemindItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * FarewellService
 *
 * @author LiCongLu
 * @date 2020-07-13 13:31
 */
@Service("farewellService")
public class FarewellService extends BaseService {

    @Resource
    private FarewellDao farewellDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private TaskRecordDao taskRecordDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private TaskRemindDao taskRemindDao;

    @Resource
    private RecordTool recordTool;

    @Resource
    private RecordImageDao recordImageDao;

    @Resource
    private PdfFileHelper pdfFileHelper;

    @Resource
    private RecordFileDao recordFileDao;

    /**
     * 加载告别厅列表
     *
     * @return
     * @author LiCongLu
     * @date 2020-07-13 15:24
     */
    public BaseResult<ArrayList<FarewellHallItem>> loadFarewellHallList() {
        ArrayList<FarewellHallItem> loadItems = farewellDao.listFarewellHall();
        return success(loadItems);
    }

    /**
     * 加载指定礼厅当天告别信息
     *
     * @param data 告别厅信息
     * @return
     * @author LiCongLu
     * @date 2020-07-13 15:35
     */
    public BaseResult<ArrayList<FarewellTaskListItem>> loadFarewellTaskList(FarewellHallData data) {
        ArrayList<FarewellTaskListItem> loadItems = new ArrayList<>();
        // 获取业务数据
        ArrayList<FarewellTaskListItem> farewellItems = farewellDao.listFarewellTaskListByHallId(data.getHallId());
        ArrayList<FarewellTaskListItem> appointmentItems = new ArrayList<>();
        ArrayList<FarewellTaskListItem> beginItems = new ArrayList<>();
        ArrayList<FarewellTaskListItem> endItems = new ArrayList<>();
        for (FarewellTaskListItem listItem : farewellItems) {
            // 设置告别时间段
            resetFarewellTime(listItem);

            // 判断默认状态值
            if (DataUtil.invalid(listItem.getTaskFlag())) {
                listItem.setTaskFlag(FarewellTaskFlag.WeiZhunBei);
            }

            listItem.setTaskFlagText(getTaskFlag(listItem.getTaskFlag()));

            // 判断礼厅状态
            if (DataUtil.equals(listItem.getFarewellState(), Constants.MournState.Begin)) {
                listItem.setFarewellStateText("已入厅");
                if (listItem.getTaskFlag().intValue() < FarewellTaskFlag.ChuTingTongZhi) {
                    listItem.setTaskFlagText("已入厅");
                }
                beginItems.add(listItem);
            } else if (listItem.getFarewellState() == Constants.MournState.End) {
                listItem.setFarewellStateText("已出厅");
                listItem.setTaskFlagText("已出厅");
                endItems.add(listItem);
            } else {
                listItem.setFarewellStateText("未入厅");
                appointmentItems.add(listItem);
            }
        }

        // 按照特定顺序设置返回值
        loadItems.addAll(beginItems);
        loadItems.addAll(appointmentItems);
        loadItems.addAll(endItems);
        return success(loadItems);
    }

    /**
     * 重新排列告别时间段
     *
     * @param listItem 告别任务列表信息
     * @return
     * @author LiCongLu
     * @date 2020-07-14 09:08
     */
    public void resetFarewellTime(FarewellTaskListItem listItem) {
        // 截取时间段
        String[] beginTimes = listItem.getBeginTime().split(" ");
        String farewellTime = beginTimes.length > 1 ? beginTimes[1] : "";
        if (DataUtil.valid(listItem.getEndTime())) {
            String[] endTimes = listItem.getEndTime().split(" ");
            farewellTime = farewellTime + (endTimes.length > 1 ? "-" + endTimes[1] : "");
        }
        listItem.setFarewellTime(farewellTime);
    }

    /**
     * 加载告别任务详情
     *
     * @param data 业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-14 08:42
     */
    public BaseResult<FarewellTaskServiceItem> loadFarewellTaskWithId(OperationNoIdData data) {
        FarewellTaskItem farewellTaskItem = farewellDao.getFarewellTaskById(data.getId());
        if (DataUtil.isNull(farewellTaskItem) || DataUtil.invalid(farewellTaskItem.getOperationNo())) {
            return failure("告别任务主键错误，未找到此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(farewellTaskItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与告别任务不匹配");
        }

        // 重新设置告别任务时间段
        resetFarewellTime(farewellTaskItem);

        // 判断遗体转运状态
        resetFarewellTaskFlag(farewellTaskItem);

        // 查询物品费用
        ArrayList<FarewellChargeItem> chargeItems = chargeDao.listFarewellChargeByOperationNo(data.getOperationNo());
        // 获取整容随机码
        ArrayList<String> faceLiftList = farewellDao.listFaceLiftRandomIdForFarewell(data.getOperationNo());
        // 礼仪服务
        StringBuilderExt riteBuilder = new StringBuilderExt();
        // 鲜花服务
        StringBuilderExt flowerBuilder = new StringBuilderExt();
        // 整容服务
        StringBuilderExt faceLiftBuilder = new StringBuilderExt();
        // 物品服务
        StringBuilderExt productBuilder = new StringBuilderExt();

        // 遍历费用，筛选各服务
        for (FarewellChargeItem chargeItem : chargeItems) {
            // 当前告别厅任务
            if (DataUtil.equals(farewellTaskItem.getRandomId(), chargeItem.getRandomId())) {
                if (DataUtil.valid(chargeItem.getAsRiteService())) {
                    appendChargeItem(riteBuilder, chargeItem);
                } else if (DataUtil.valid(chargeItem.getAsFlower())) {
                    appendChargeItem(flowerBuilder, chargeItem);
                } else if (DataUtil.valid(chargeItem.getAsStockProduct())) {
                    appendChargeItem(productBuilder, chargeItem);
                }
            } else if (faceLiftList.contains(chargeItem.getRandomId())) {
                appendChargeItem(faceLiftBuilder, chargeItem);
            }
        }

        // 设置各服务返回信息
        FarewellTaskServiceItem loadItem = BeanUtil.copy2Bean(farewellTaskItem, new FarewellTaskServiceItem());
        loadItem.setRiteService(riteBuilder.toString())
                .setFlowerService(flowerBuilder.toString())
                .setFaceLiftService(faceLiftBuilder.toString())
                .setProductService(productBuilder.toString());

        // 变更记录
        ArrayList<TaskRecordListItem> recordListItems = taskRecordDao.listTaskRecordByOperationNo(data.getOperationNo());
        // 后续处理

        // 签字图片信息
        RecordImage recordImage = recordImageDao.getRecordImageByOperationNo(loadItem.getOperationNo(), Constants.RecordCode.RecordListCode, Constants.RecordCode.FarewellSignCode);
        if (recordImage != null) {
            loadItem.setSignImageItem(BeanUtil.copy2Bean(recordImage, new SignImageItem()));
        }

        return success(loadItem);
    }

    /**
     * 追加物品服务列表
     *
     * @param builder    字符串追加
     * @param chargeItem 服务费用
     * @return
     * @author LiCongLu
     * @date 2020-07-14 13:18
     */
    private void appendChargeItem(StringBuilderExt builder, FarewellChargeItem chargeItem) {
        if (chargeItem != null) {
            builder.format("{0}*{1} {2}元  ", chargeItem.getItemName()
                    , DataUtil.getStripZerosString(chargeItem.getNumber())
                    , DataUtil.getStripZerosString(chargeItem.getCharge()));
        }
    }

    /**
     * 判断遗体转运状态
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-14 11:22
     */
    private void resetFarewellTaskFlag(FarewellTaskItem loadItem) {
        // 判断默认状态值
        if (DataUtil.invalid(loadItem.getTaskFlag())) {
            loadItem.setTaskFlag(FarewellTaskFlag.WeiZhunBei);
        }

        loadItem.setTaskFlagText(getTaskFlag(loadItem.getTaskFlag()));

        // 判断礼厅状态
        if (DataUtil.equals(loadItem.getFarewellState(), Constants.MournState.Begin)) {
            loadItem.setFarewellStateText("已入厅");
            if (loadItem.getTaskFlag().intValue() < FarewellTaskFlag.ChuTingTongZhi) {
                loadItem.setTaskFlagText("已入厅");
            }
        } else if (loadItem.getFarewellState() == Constants.MournState.End) {
            loadItem.setFarewellStateText("已出厅");
            loadItem.setTaskFlagText("已出厅");
        } else {
            loadItem.setFarewellStateText("未入厅");
        }
    }

    private String getTaskFlag(Integer taskFlag) {
        String taskFlagText = "未准备";
        // 判断任务标记
        switch (taskFlag) {
            case FarewellTaskFlag.WeiZhunBei:
                taskFlagText = "未准备";
                break;
            case FarewellTaskFlag.ZhunBeiWanCheng:
                taskFlagText = "准备完成";
                break;
            case FarewellTaskFlag.RuTingTongZhi:
                taskFlagText = "入厅任务已发送";
                break;
            case FarewellTaskFlag.RuTingTongZhiJieShou:
                taskFlagText = "入厅任务已接收";
                break;
            case FarewellTaskFlag.ChuTingTongZhi:
                taskFlagText = "出厅任务已发送";
                break;
            case FarewellTaskFlag.ChuTingTongZhiJieShou:
                taskFlagText = "出厅任务已接收";
                break;
            default:
                taskFlagText = "";
                break;
        }
        return taskFlagText;
    }

    /**
     * 告别任务准备完成
     *
     * @param loginItem 当前账号
     * @param data      业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-15 10:57
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskFlagReady(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        FarewellTaskItem loadItem = farewellDao.getFarewellTaskById(data.getId());
        if (DataUtil.isNull(loadItem) || DataUtil.invalid(loadItem.getOperationNo())) {
            return failure("告别任务主键错误，未找到此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(loadItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与告别任务不匹配");
        }

        // 判断礼厅状态
        if (!DataUtil.equals(loadItem.getFarewellState(), Constants.MournState.Normal)) {
            return failure("当前告别任务非未入厅状态，无法进行准备完成工作");
        }

        // 判断状态标记
        if (!DataUtil.equals(loadItem.getTaskFlag(), FarewellTaskFlag.WeiZhunBei)) {
            return failure("当前告别任务非未准备状态，无法进行准备完成工作");
        }

        // 判断占用执行状态
        if (DataUtil.valid(loadItem.getExecuteId())) {
            return failure("当前告别厅存在正在进行告别任务，不能同时进行");
        }

        // 更新状态
        farewellDao.updateFarewellTaskFlag(data.getId(), data.getOperationNo(), FarewellTaskFlag.ZhunBeiWanCheng, loginItem);

        String logContent = StringUtil.format("告别任务准备完成,逝者【{0}】【{1}】,准备完成", loadItem.getOperationNo(), loadItem.getDeadName());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "告别任务"
                , BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        return loadFarewellTaskWithId(data);
    }


    /**
     * 告别任务遗体接运入厅
     *
     * @param loginItem 当前账号
     * @param data      业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-15 10:57
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskFlagBegin(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        FarewellTaskItem loadItem = farewellDao.getFarewellTaskById(data.getId());
        if (DataUtil.isNull(loadItem) || DataUtil.invalid(loadItem.getOperationNo())) {
            return failure("告别任务主键错误，未找到此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(loadItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与告别任务不匹配");
        }

        // 判断礼厅状态
        if (!DataUtil.equals(loadItem.getFarewellState(), Constants.MournState.Normal)) {
            return failure("当前告别任务非未入厅状态，无法进行遗体入厅接运工作");
        }

        // 判断状态标记
        if (!DataUtil.equals(loadItem.getTaskFlag(), FarewellTaskFlag.ZhunBeiWanCheng)) {
            return failure("当前告别任务非准备完成状态，无法进行遗体入厅接运工作");
        }

        // 判断占用执行状态
        if (DataUtil.valid(loadItem.getExecuteId())) {
            return failure("当前告别厅存在正在进行告别任务，不能同时进行");
        }

        // 更新状态
        farewellDao.updateFarewellTaskFlag(data.getId(), data.getOperationNo(), FarewellTaskFlag.RuTingTongZhi, loginItem);

        String logContent = StringUtil.format("告别任务遗体入厅接运,逝者【{0}】【{1}】,遗体入厅接运", loadItem.getOperationNo(), loadItem.getDeadName());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "告别任务"
                , BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        // 发送任务提醒
        String describe = StringUtil.format("告别任务：业务编号为{0}的入厅遗体接运任务已发送，请注意查收！", loadItem.getOperationNo());
        saveTaskRemind(loadItem, TaskSourceModule.RuTingJieYun, describe);

        return loadFarewellTaskWithId(data);
    }

    /**
     * 新增提醒任务
     *
     * @param loadItem 告别任务
     * @return
     * @author LiCongLu
     * @date 2020-07-15 14:16
     */
    private void saveTaskRemind(FarewellTaskItem loadItem, String sourceModule, String describe) throws ActionException {
        TaskRemindItem item = new TaskRemindItem();
        item.setOperationNo(loadItem.getOperationNo())
                .setSourceModule(sourceModule)
                .setTargetModule("出入厅任务App")
                .setTableName(TableNames.Mourn)
                .setBusinessId(loadItem.getFarewellId().toString())
                .setDescribe(describe);
        taskRemindDao.insertTaskRemindItem(item);
        if (DataUtil.invalid(item.getId())) {
            throw exception("添加任务提醒错误");
        }
    }

    /**
     * 告别任务遗体接运出厅
     *
     * @param loginItem 当前账号
     * @param data      业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-15 10:57
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskFlagEnd(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        FarewellTaskItem loadItem = farewellDao.getFarewellTaskById(data.getId());
        if (DataUtil.isNull(loadItem) || DataUtil.invalid(loadItem.getOperationNo())) {
            return failure("告别任务主键错误，未找到此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(loadItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与告别任务不匹配");
        }

        // 判断礼厅状态
        if (!DataUtil.equals(loadItem.getFarewellState(), Constants.MournState.Begin)) {
            return failure("当前告别任务非已入厅状态，无法进行遗体出厅接运工作");
        }

        // 判断状态标记
        if (loadItem.getTaskFlag().intValue() >= FarewellTaskFlag.ChuTingTongZhi) {
            return failure("当前告别任务已完成过遗体出厅通知工作，无法再次进行遗体出厅接运工作");
        }

        // 更新状态
        farewellDao.updateFarewellTaskFlag(data.getId(), data.getOperationNo(), FarewellTaskFlag.ChuTingTongZhi, loginItem);

        String logContent = StringUtil.format("告别任务遗体出厅接运,逝者【{0}】【{1}】,遗体出厅接运", loadItem.getOperationNo(), loadItem.getDeadName());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "告别任务"
                , BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        // 发送任务提醒
        String describe = StringUtil.format("告别任务：业务编号为{0}的出厅遗体接运任务已发送，请注意查收！", loadItem.getOperationNo());
        saveTaskRemind(loadItem, TaskSourceModule.ChuTingJieYun, describe);

        return loadFarewellTaskWithId(data);
    }

    /**
     * 告别任务家属签字
     *
     * @param data 告别业务
     * @return
     * @author LiCongLu
     * @date 2020-07-15 14:31
     */
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskSign(LoginItem loginItem, OperationNoIdData data, MultipartFile file) {
        FarewellTaskItem loadItem = farewellDao.getFarewellTaskById(data.getId());
        if (DataUtil.isNull(loadItem) || DataUtil.invalid(loadItem.getOperationNo())) {
            return failure("告别任务主键错误，未找到此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(loadItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与告别任务不匹配");
        }

        // 记录签字图片
        recordTool.saveRecordImage(file, loginItem.getUserId(), data.getOperationNo(),
                Constants.RecordCode.RecordListCode, Constants.RecordCode.FarewellSignCode, "告别厅确认单签字图片");

        // 获得告别物品服务
        FarewellTaskServiceItem farewellTaskItem = loadFarewellTaskWithId(data).getData();

        RecordFile recordFile = pdfFileHelper.saveFarewellToPdf(farewellTaskItem);
        if (recordFile == null) {
            throw exception("签字PDF文件生成失败");
        }
        if (recordFile != null) {
            recordFile.setOperationNo(loadItem.getOperationNo());
            recordFile.setFileName(loadItem.getOperationNo());
            recordFile.setRemark("告别服务签字确认表");
            recordFile.setFileType(Constants.RecordCode.RecordFilePDFCode);
            recordFile.setFileCode(Constants.RecordCode.FarewellPDFCode);
            recordFileDao.insertRecordFile(recordFile, loginItem.getUserId());
            // 判断是否保存完成
            if (DataUtil.invalid(recordFile.getId())) {
                throw exception("保存签字PDF文件失败");
            }
        }

        String logContent = String.format("告别厅办理，业务编号【%s】，逝者姓名【%s】，告别厅名称【%s】，" +
                        "，操作人【%s】，告别厅签字"
                , data.getOperationNo(), loadItem.getDeadName(), loadItem.getHallName(), loginItem.getRealName());

        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), "告别厅", "签字",
                "签字", logContent, data.getOperationNo());

        return success(farewellTaskItem);
    }
}
