package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.*;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.service.ServiceSuitSetItem;
import com.jmdz.fushan.pad.model.task.TaskRemindItem;
import com.jmdz.fushan.pad.model.weixin.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * WxMournService
 *
 * @author LiCongLu
 * @date 2020-07-21 10:13
 */
@Service("wxMournService")
public class WxMournService extends BaseService {

    @Resource
    private WxMournDao wxMournDao;

    @Resource
    private ConfigData configData;

    @Resource
    private TaskRemindDao taskRemindDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private DeadDao deadDao;

    @Resource
    private FarewellDao farewellDao;

    @Resource
    private ServiceDao serviceDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private ServiceSuitDao serviceSuitDao;

    /**
     * 加载微信告别预约审核列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-21 10:14
     */
    public BaseResult loadWxMournExamine() {
        ArrayList<WxMournExamineItem> loadItems = wxMournDao.listWxMournExamineList();

        ArrayList<Integer> idList = new ArrayList<>();
        // 设置审核状态
        for (WxMournExamineItem loadItem : loadItems) {
            loadItem.setExamineStateText("未识别");
            if (DataUtil.equals(loadItem.getExamineState(), ExamineState.WeiShenHe)) {
                loadItem.setExamineStateText("未审核");
            } else if (DataUtil.equals(loadItem.getExamineState(), ExamineState.TongGuo)) {
                loadItem.setExamineStateText("通过");
            } else if (DataUtil.equals(loadItem.getExamineState(), ExamineState.BuTongGuo)) {
                loadItem.setExamineStateText("不通过");
            } else if (DataUtil.equals(loadItem.getExamineState(), ExamineState.YiZuoFei)) {
                loadItem.setExamineStateText("已作废");
            }
            idList.add(loadItem.getId());
        }

        // 处理单点服务
        if (DataUtil.valid(idList)) {
            // 查询单点物品
            ArrayList<WxMournServiceItem> serviceItems = wxMournDao.listWxMournServiceForIdList(idList, configData.getPadFarewellServiceParent(), configData.getPadFarewellServiceBelong());
            if (DataUtil.valid(serviceItems)) {
                for (WxMournExamineItem loadItem : loadItems) {
                    ArrayList<String> serviceList = new ArrayList<>();
                    for (WxMournServiceItem serviceItem : serviceItems) {
                        if (DataUtil.equals(serviceItem.getWxMournId(), loadItem.getId())) {
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
     * 保存审核告别预约信息通过
     *
     * @param loginItem 当前账号
     * @param data      告别主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:27
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveWxMournExaminePass(LoginItem loginItem, IdData data) throws ActionException {
        WxMournItem loadItem = wxMournDao.getWxMournById(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getId())) {
            return failure("预约告别主键错误，未找到预约告别信息");
        }

        // 判断是否已审核
        if (DataUtil.valid(loadItem.getMournId())
                || DataUtil.valid(loadItem.getExamineState())) {
            return failure("预约告别信息已进行审核，无法继续审核");
        }

        // 判断预约礼厅
        if (DataUtil.invalid(loadItem.getHallId())) {
            return failure("没有预约礼厅，不能够通过");
        }

        // 判断业务编号
        DeadBasicItem deadItem = deadDao.getDeadBasicByOperationNo(loadItem.getOperationNo());
        if (deadItem == null || DataUtil.invalid(deadItem.getOperationNo())) {
            return failure("业务编号错误，不存在此逝者");
        }

        // 判断预约时间
        if (DataUtil.invalid(loadItem.getBeginTime(), loadItem.getEndTime())) {
            return failure("预约开始时间与预约结束时间不能为空，不能通过");
        }

        // 保存逝者告别信息
        saveMourn(loginItem, loadItem);

        // 审核通过
        wxMournDao.updateExaminePass(data.getId(), loadItem.getOperationNo(), loadItem.getMournId(), loginItem);

        String logContent = StringUtil.format("微信告别预约审核,预约主键【{0}】，业务编号【{1}】，家属姓名【{2}】，家属电话【{3}】，审核通过", loadItem.getId().toString()
                , loadItem.getOperationNo(), loadItem.getContactName(), loadItem.getContactPhone());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiWeiHu, "预约告别审核通过", BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        return loadWxMournExamine();
    }

    /**
     * 保存逝者及告别信息
     *
     * @param loginItem 当前账号
     * @param loadItem  告别预约信息
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:30
     */
    private void saveMourn(LoginItem loginItem, WxMournItem loadItem) throws ActionException {

        // 告别费用信息
        ServiceItem typeItem = serviceDao.getServiceItemById(loadItem.getHallTypeId());
        if (typeItem == null || DataUtil.invalid(typeItem.getId())) {
            throw exception("预约礼厅类型错误，未找到此礼厅所对应的费用");
        }

        // 判断是否存在占用
        Integer timeMournId = farewellDao.getFarewellIdByTime(loadItem.getHallId(), loadItem.getBeginTime(), loadItem.getEndTime());
        if (DataUtil.valid(timeMournId)) {
            throw exception("此时间段已有告别，不能通过");
        }

        String randomId = DataUtil.getUUID();

        // 告别任务信息
        MournItem mournItem = new MournItem();
        mournItem.setOperationNo(loadItem.getOperationNo())
                .setMournHallTypeId(loadItem.getHallTypeId())
                .setMournHallId(loadItem.getHallId())
                .setBeginTime(loadItem.getBeginTime())
                .setEndTime(loadItem.getEndTime())
                .setRemark(loadItem.getRemark())
                .setCharge(typeItem.getPrice())
                .setPrice(typeItem.getPrice())
                .setRandomId(randomId);
        // 插入告别任务信息
        farewellDao.insertFarewellWxMourn(mournItem, loginItem);
        if (DataUtil.invalid(mournItem.getId())) {
            throw exception("新增告别任务信息失败");
        }

        loadItem.setMournId(mournItem.getId());

        // 更新逝者告别信息
        farewellDao.updateDeadFarewellWx(loadItem.getOperationNo(), typeItem, mournItem, loadItem, loginItem);

        // 告别费用
        ChargeItem chargeTypeItem = new ChargeItem();
        chargeTypeItem.setOperationNo(loadItem.getOperationNo());
        chargeTypeItem.setServiceCode(20);
        chargeTypeItem.setRandomId(randomId);
        chargeTypeItem.setItemId(typeItem.getId());
        chargeTypeItem.setNumber(new BigDecimal(1));
        chargeTypeItem.setPrice(typeItem.getPrice());
        chargeTypeItem.setCharge(typeItem.getPrice());
        chargeTypeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                .setProductFrom(0);
        // 插入告别费用
        chargeDao.insertChargeItem(chargeTypeItem, loginItem.getUserId());
        if (DataUtil.invalid(chargeTypeItem.getId())) {
            throw exception("添加告别费用失败");
        }

        // 查询告别物品
        ArrayListExt<Integer> idList = new ArrayListExt<>();
        idList.add(loadItem.getId());
        // 查询单点物品
        ArrayList<WxMournServiceItem> serviceItems = wxMournDao.listWxMournServiceForIdList(idList, configData.getPadFarewellServiceParent(), configData.getPadFarewellServiceBelong());
        if (DataUtil.valid(serviceItems)) {
            insertChargeItem(loginItem, loadItem, randomId, serviceItems, "");
        }

        // 插入套餐费用信息
        insertChargeForSuit(loginItem, loadItem, randomId);

        // 发送任务提醒
        String describe = StringUtil.format("告别任务：业务编号为{0}的告别已发送，请注意查收！", loadItem.getOperationNo());
        TaskRemindItem item = new TaskRemindItem();
        item.setOperationNo(loadItem.getOperationNo())
                .setSourceModule(TaskSourceModule.GaoBieRenWu)
                .setTargetModule("告别厅App")
                .setTableName(TableNames.Mourn)
                .setBusinessId(String.valueOf(loadItem.getMournId()))
                .setDescribe(describe);
        taskRemindDao.insertTaskRemindItem(item);
        if (DataUtil.invalid(item.getId())) {
            throw exception("添加任务提醒错误");
        }
    }

    /**
     * 批量插入费用信息
     *
     * @param loginItem    当前账号
     * @param loadItem     微信预约告别信息
     * @param randomId     业务随机码
     * @param serviceItems 物品服务
     * @param remark       备注
     * @return
     * @author LiCongLu
     * @date 2020-07-21 16:04
     */
    private void insertChargeItem(LoginItem loginItem, WxMournItem loadItem, String randomId, ArrayList<WxMournServiceItem> serviceItems, String remark) throws ActionException {


        // 服务物品名称
        ArrayList<String> namesList = new ArrayList<>();

        for (WxMournServiceItem serviceItem : serviceItems) {
            // 单点物品服务
            ChargeItem chargeItem = new ChargeItem();
            chargeItem.setOperationNo(loadItem.getOperationNo());
            chargeItem.setServiceCode(20);
            chargeItem.setRandomId(randomId);
            chargeItem.setItemId(serviceItem.getItemId());
            chargeItem.setNumber(serviceItem.getNumber());
            chargeItem.setPrice(serviceItem.getPrice());
            chargeItem.setCharge(serviceItem.getPrice().multiply(serviceItem.getNumber()));
            chargeItem.setChargeDate(DateUtil.formatPattern16(new Date()));
            chargeItem.setRemark(remark)
                    .setProductFrom(0);
            // 插入单点物品服务
            chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());

            if (DataUtil.invalid(chargeItem.getId())) {
                throw exception("添加物品服务失败");
            }

            // 插入关联详情
            chargeDao.insertChargeServiceItemDetails(chargeItem.getId(), chargeItem.getItemId(), chargeItem.getNumber());

            // 记录费用名称
            namesList.add(serviceItem.getItemName());
        }

        // 套餐物品服务名称
        String nameStr = StringUtils.arrayToDelimitedString(namesList.toArray(new String[]{}), ",");

        String logContent = String.format("告别厅办理，业务编号【%s】，告别厅名称【%s】，" +
                        "，操作人【%s】，单点物品[%s]，添加单点物品"
                , loadItem.getOperationNo(), loadItem.getHallName(), loginItem.getRealName(), nameStr);

        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), "告别厅", "费用",
                "物品服务", logContent, loadItem.getOperationNo());
    }

    /**
     * 插入套餐费用信息
     *
     * @param loginItem 当前账号
     * @param loadItem  预约信息
     * @param randomId  随机码
     * @return
     * @author LiCongLu
     * @date 2020-07-21 16:22
     */
    private void insertChargeForSuit(LoginItem loginItem, WxMournItem loadItem, String randomId) throws ActionException {
        if (DataUtil.invalid(loadItem.getItemDetailsId())) {
            return;
        }

        // 获取套餐服务
        ArrayList<ServiceSuitSetItem> serviceSetItems = serviceSuitDao.listTaoCanItemMournByItemTypeName(loadItem.getItemDetailsId());
        if (DataUtil.invalid(serviceSetItems)) {
            // throw exception("套餐错误，不包含任何物品服务");
            return;
        }

        // 服务物品名称
        ArrayList<String> namesList = new ArrayList<>();

        // 遍历添加费用信息
        for (ServiceSuitSetItem serviceItem : serviceSetItems) {
            if (DataUtil.isNull(serviceItem.getItemNumber())) {
                serviceItem.setItemNumber(new BigDecimal(1));
            }

            ChargeItem chargeItem = new ChargeItem();
            chargeItem.setOperationNo(loadItem.getOperationNo())
                    .setItemId(serviceItem.getItemId())
                    .setNumber(serviceItem.getItemNumber())
                    .setPrice(serviceItem.getItemPrice())
                    .setCharge(serviceItem.getItemPrice().multiply(serviceItem.getItemNumber()))
                    .setParentId(serviceItem.getParentId())
                    .setServiceCode(Constants.ServiceConst.GaoBie)
                    .setRandomId(randomId)
                    .setRemark(Constants.PI_LIANG_YUDING)
                    .setProductFrom(0);

            // 插入费用
            chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());

            // 判断插入结果
            if (DataUtil.invalid(chargeItem.getId())) {
                throw exception("添加套餐失败");
            }

            // 插入关联详情
            chargeDao.insertChargeServiceItemDetails(chargeItem.getId(), chargeItem.getItemId(), chargeItem.getNumber());

            // 记录费用名称
            namesList.add(serviceItem.getItemName());
        }

        // 套餐物品服务名称
        String nameStr = StringUtils.arrayToDelimitedString(namesList.toArray(new String[]{}), ",");

        String logContent = String.format("告别厅办理，业务编号【%s】，告别厅名称【%s】，" +
                        "，操作人【%s】，套餐物品[%s]，添加套餐操作"
                , loadItem.getOperationNo(), loadItem.getHallName(), loginItem.getRealName(), nameStr);

        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), "告别厅", "费用",
                "套餐", logContent, loadItem.getOperationNo());
    }

    /**
     * 保存审核告别预约信息不通过
     *
     * @param loginItem 当前账号
     * @param data      告别主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-21 13:57
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveWxMournExamineFail(LoginItem loginItem, IdData data) throws ActionException {
        WxMournItem loadItem = wxMournDao.getWxMournById(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getId())) {
            return failure("预约告别主键错误，未找到预约告别信息");
        }

        // 判断是否已审核
        if (DataUtil.valid(loadItem.getMournId())
                || DataUtil.valid(loadItem.getExamineState())) {
            return failure("预约告别信息已进行审核，无法继续审核");
        }

        // 审核不通过
        wxMournDao.updateExamineFail(data.getId(), loginItem);

        String logContent = StringUtil.format("微信告别预约审核,预约主键【{0}】，业务编号【{1}】，家属姓名【{2}】，家属电话【{3}】，审核不通过", loadItem.getId().toString()
                , loadItem.getOperationNo(), loadItem.getContactName(), loadItem.getContactPhone());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiWeiHu, "预约告别审核不通过", BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        return loadWxMournExamine();
    }
}
