package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.helper.GlobalTool;
import com.jmdz.fushan.model.config.*;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.business.AgentItem;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.business.RelationItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.task.TaskNoticeVo;
import com.jmdz.fushan.pad.model.task.TaskRemindItem;
import com.jmdz.fushan.pad.model.weixin.VehicleManageItem;
import com.jmdz.fushan.pad.model.weixin.WxRecInfoExamineItem;
import com.jmdz.fushan.pad.model.weixin.WxRecInfoItem;
import com.jmdz.fushan.pad.model.weixin.WxRecServiceItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * WxRecService
 *
 * @author LiCongLu
 * @date 2020-07-21 10:12
 */
@Service("wxRecService")
public class WxRecService extends BaseService {

    @Resource
    private WxRecInfoDao wxRecInfoDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private ConfigData configData;

    @Resource
    private GlobalTool globalTool;

    @Resource
    private ServiceDao serviceDao;

    @Resource
    private DeadDao deadDao;

    @Resource
    private RelationDao relationDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private VehicleManageDao vehicleManageDao;

    @Resource
    private TaskRemindDao taskRemindDao;

    /**
     * 加载微信接运预约审核列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:12
     */
    public BaseResult<ArrayList<WxRecInfoExamineItem>> loadWxRecInfoExamine() {
        ArrayList<WxRecInfoExamineItem> loadItems = wxRecInfoDao.listWxRecInfoExamineList();

        ArrayList<Integer> idList = new ArrayList<>();
        // 设置审核状态
        for (WxRecInfoExamineItem loadItem : loadItems) {
            if (DataUtil.equals(loadItem.getExamineState(), ExamineState.WeiShenHe)) {
                loadItem.setExamineStateText("未审核");
            } else if (DataUtil.equals(loadItem.getExamineState(), ExamineState.TongGuo)) {
                loadItem.setExamineStateText("通过");
            } else if (DataUtil.equals(loadItem.getExamineState(), ExamineState.BuTongGuo)) {
                loadItem.setExamineStateText("不通过");
            } else if (DataUtil.equals(loadItem.getExamineState(), ExamineState.YiZuoFei)) {
                loadItem.setExamineStateText("已作废");
            } else {
                loadItem.setExamineStateText("未识别");
            }
            idList.add(loadItem.getId());
        }

        if (DataUtil.valid(idList)) {
            // 查询随车物品
            ArrayList<WxRecServiceItem> serviceItems = wxRecInfoDao.listWxRecServiceForIdList(idList, configData.getVehicleServiceParent(), configData.getVehicleServiceBelong());
            if (DataUtil.valid(serviceItems)) {
                for (WxRecInfoExamineItem loadItem : loadItems) {
                    ArrayList<String> serviceList = new ArrayList<>();
                    for (WxRecServiceItem serviceItem : serviceItems) {
                        if (DataUtil.equals(serviceItem.getRecId(), loadItem.getId())) {
                            serviceList.add(serviceItem.getItemName() + "*" + DataUtil.getStripZerosString(serviceItem.getNumber()));
                        }
                    }
                    loadItem.setGoodsServices(StringUtils.arrayToDelimitedString(serviceList.toArray(new String[]{}), ","));
                }
            }
        }
        return success(loadItems);
    }

    /**
     * 保存审核接运预约信息通过
     *
     * @param loginItem 当前账号
     * @param data      接运主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:37
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<ArrayList<WxRecInfoExamineItem>> saveWxRecInfoExaminePass(LoginItem loginItem, IdData data) throws ActionException {
        WxRecInfoItem loadItem = wxRecInfoDao.getWxRecInfoById(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getId())) {
            return failure("预约接运主键错误，未找到预约接运信息");
        }

        // 判断是否已审核
        if (DataUtil.valid(loadItem.getOperationNo())
                || DataUtil.valid(loadItem.getExamineState())) {
            return failure("预约接运信息已进行审核，无法继续审核");
        }

        // 判断预约车型
        if (DataUtil.invalid(loadItem.getCarTypeCode())) {
            return failure("没有预约车型，不能够通过");
        }

        // 判断微信唯一键
        if (DataUtil.invalid(loadItem.getOpenId())) {
            return failure("预约微信未识别，不能够通过");
        }

        // 保存逝者接运信息
        saveDeadVehicle(loginItem, loadItem);

        // 审核通过
        wxRecInfoDao.updateExaminePass(data.getId(), loadItem.getOperationNo(), loadItem.getVehicleId(), loginItem);

        // 设置关联数据
        wxRecInfoDao.insertWxBusinessBinding(loadItem.getOperationNo(), loadItem.getOpenId(), loginItem);

        String logContent = StringUtil.format("微信接运预约审核,预约主键【{0}】，逝者姓名【{1}】，家属姓名【{2}】，家属电话【{3}】，审核通过", loadItem.getId().toString()
                , loadItem.getDeadName(), loadItem.getContactName(), loadItem.getContactPhone());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiWeiHu, "预约接运审核通过", BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        return loadWxRecInfoExamine();
    }

    /**
     * 保存逝者及接运信息
     *
     * @param loginItem 当前账号
     * @param loadItem  接运预约信息
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:32
     */
    private void saveDeadVehicle(LoginItem loginItem, WxRecInfoItem loadItem) throws ActionException {
        // 生成业务编号
        String operationNo = globalTool.createOperationNo();
        String randomId = DataUtil.getUUID();
        DeadBasicItem deadItem = new DeadBasicItem();
        deadItem.setOperationNo(operationNo);
        if (DataUtil.invalid(deadItem.getOperationNo())) {
            throw exception("生成新业务编号错误");
        }
        deadItem.setDeadName(loadItem.getDeadName());
        deadItem.setDeadAge(loadItem.getDeadAge());
        deadItem.setDeadSex(loadItem.getDeadSex());
        deadItem.setDeathAddress(loadItem.getAddress());
        // 新增逝者
        deadDao.insertDeadBasicItem(deadItem, loginItem);

        // 家属信息
        RelationItem relationItem = new RelationItem();
        relationItem.setOperationNo(deadItem.getOperationNo());
        relationItem.setRelationName(loadItem.getContactName());
        relationItem.setRelationPhone(loadItem.getContactPhone());
        relationDao.insertRelationItem(relationItem, loginItem);
        if (DataUtil.invalid(relationItem.getRelationId())) {
            throw exception("保存家属信息失败");
        }

        // 接运费用信息
        ServiceItem typeItem = serviceDao.getServiceItemById(loadItem.getCarTypeCode());
        if (typeItem == null || DataUtil.invalid(typeItem.getId())) {
            throw exception("预约车型错误，未找到此车型");
        }

        // 接运信息
        VehicleManageItem vehicleItem = getVehicleManageItem(loadItem, operationNo, randomId, typeItem);
        // 插入接运信息
        vehicleManageDao.insertVehicleManage(vehicleItem, loginItem);

        // 接运费用
        ChargeItem chargeTypeItem = new ChargeItem();
        chargeTypeItem.setOperationNo(operationNo);
        chargeTypeItem.setServiceCode(40);
        chargeTypeItem.setRandomId(randomId);
        chargeTypeItem.setItemId(typeItem.getId());
        chargeTypeItem.setNumber(new BigDecimal(1));
        chargeTypeItem.setPrice(typeItem.getPrice());
        chargeTypeItem.setCharge(typeItem.getPrice());
        chargeTypeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                .setProductFrom(0);
        // 插入接运费用
        chargeDao.insertChargeItem(chargeTypeItem, loginItem.getUserId());
        if (DataUtil.invalid(chargeTypeItem.getId())) {
            throw exception("添加接运费用失败");
        }

        // 查询随车物品
        ArrayListExt<Integer> idList = new ArrayListExt<>();
        idList.add(loadItem.getId());
        ArrayList<WxRecServiceItem> serviceItems = wxRecInfoDao.listWxRecServiceForIdList(idList, configData.getVehicleServiceParent(), configData.getVehicleServiceBelong());
        if (DataUtil.valid(serviceItems)) {
            for (WxRecServiceItem serviceItem : serviceItems) {
                // 随车物品费用
                ChargeItem chargeItem = new ChargeItem();
                chargeItem.setOperationNo(operationNo);
                chargeItem.setServiceCode(40);
                chargeItem.setRandomId(randomId);
                chargeItem.setItemId(serviceItem.getItemId());
                chargeItem.setNumber(serviceItem.getNumber());
                chargeItem.setPrice(serviceItem.getPrice());
                chargeItem.setCharge(serviceItem.getPrice().multiply(serviceItem.getNumber()));
                chargeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                        .setProductFrom(0);
                // 插入随车物品费用
                chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
                // 插入关联详情
                chargeDao.insertChargeServiceItemDetails(chargeItem.getId(), chargeItem.getItemId(), chargeItem.getNumber());

                if (DataUtil.invalid(chargeItem.getId())) {
                    throw exception("添加接运随车物品失败");
                }
            }
        }

        // 添加日志
        String logContent = StringUtil.format("预约接运通过车辆信息登记,操作人员【{0}】,逝者【{1}】【{2}】,联系人姓名【{3}】，联系电话【{4}】，预约车型【{5}】，预约时间【{6}】，接尸地点【{7}】。"
                , loginItem.getRealName(), deadItem.getOperationNo(), deadItem.getDeadName(), relationItem.getRelationName(), relationItem.getRelationPhone(), typeItem.getName(), vehicleItem.getCarryTime(), vehicleItem.getCarryPlace());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "接运办理", BusinessConst.OperateType.TianJia, logContent, deadItem.getOperationNo());


        // 设置返回值
        loadItem.setOperationNo(vehicleItem.getOperationNo())
                .setVehicleId(vehicleItem.getVehicleId());

        // 发送任务提醒
        String describe = StringUtil.format("接运任务：业务编号为{0}的接运任务已发送，请注意查收！", loadItem.getOperationNo());
        TaskRemindItem item = new TaskRemindItem();
        item.setOperationNo(loadItem.getOperationNo())
                .setSourceModule(TaskSourceModule.JieYunRenWu)
                .setTargetModule("接运任务App")
                .setTableName(TableNames.VehicleManage)
                .setBusinessId(loadItem.getVehicleId())
                .setDescribe(describe);
        taskRemindDao.insertTaskRemindItem(item);
        if (DataUtil.invalid(item.getId())) {
            throw exception("添加任务提醒错误");
        }
    }

    /**
     * 得到接运信息
     *
     * @param loadItem    接运预约信息
     * @param operationNo 业务编号
     * @param randomId    随机码
     * @param typeItem    接运类型服务
     * @return
     * @author LiCongLu
     * @date 2020-07-17 10:43
     */
    private VehicleManageItem getVehicleManageItem(WxRecInfoItem loadItem, String operationNo, String randomId, ServiceItem typeItem) throws ActionException {
        VehicleManageItem vehicleItem = new VehicleManageItem();
        vehicleItem.setVehicleId(randomId);
        vehicleItem.setOperationNo(operationNo);
        vehicleItem.setLinkmanName(loadItem.getContactName());
        vehicleItem.setLinkmanPhone(loadItem.getContactPhone());
        vehicleItem.setCarryPlace(loadItem.getAddress());
        vehicleItem.setCarryTime(loadItem.getArrivalTime());
        vehicleItem.setRemark(loadItem.getRemark());
        vehicleItem.setRandomId(randomId);
        vehicleItem.setChargeStandard(typeItem.getPrice());
        vehicleItem.setFactStandard(typeItem.getPrice());
        vehicleItem.setBespeakVehicleType(loadItem.getCarTypeCode());
        vehicleItem.setCarryState(Constants.CarryState.ShangWeiChuChe);
        return vehicleItem;
    }

    /**
     * 保存审核接运预约信息不通过
     *
     * @param loginItem 当前账号
     * @param data      接运主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:37
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<ArrayList<WxRecInfoExamineItem>> saveWxRecInfoExamineFail(LoginItem loginItem, IdData data) throws ActionException {
        WxRecInfoItem loadItem = wxRecInfoDao.getWxRecInfoById(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getId())) {
            return failure("预约接运主键错误，未找到预约接运信息");
        }

        // 判断是否已审核
        if (DataUtil.valid(loadItem.getOperationNo())
                || DataUtil.valid(loadItem.getExamineState())) {
            return failure("预约接运信息已进行审核，无法继续审核");
        }

        // 审核不通过
        wxRecInfoDao.updateExamineFail(data.getId(), loginItem);

        String logContent = StringUtil.format("微信接运预约审核,预约主键【{0}】，逝者姓名【{1}】，联系人姓名【{2}】，联系电话【{3}】，审核不通过", loadItem.getId().toString()
                , loadItem.getDeadName(), loadItem.getContactName(), loadItem.getContactPhone());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiWeiHu, "预约接运审核不通过", BusinessConst.OperateType.XiuGai, logContent, "");

        return loadWxRecInfoExamine();
    }
}
