package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.common.util.*;
import com.jmdz.common.util.third.GsonUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.BusinessServiceType;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.business.farewell.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.MournItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * BusinessFarewellService
 *
 * @author LiCongLu
 * @date 2020-08-06 11:24
 */
@Service("businessFarewellService")
public class BusinessFarewellService extends BaseService {

    @Resource
    private DeadDao deadDao;

    @Resource
    private BusinessFarewellDao businessFarewellDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private BusinessService businessService;

    @Resource
    private ItemsDao itemsDao;

    /**
     * 按照业务编号加载告别任务列表信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 09:42
     */
    public BaseResult<ArrayList<FarewellMournListItem>> loadFarewellMournList(OperationNoData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ArrayList<FarewellMournListItem> loadItems = businessFarewellDao.listFarewellMournListByOperationNo(data.getOperationNo());
        for (FarewellMournListItem loadItem : loadItems) {
            if (DataUtil.equals(loadItem.getFarewellState(), Constants.MournState.Normal)) {
                loadItem.setFarewellStateText("未入厅");
            } else if (DataUtil.equals(loadItem.getFarewellState(), Constants.MournState.Begin)) {
                loadItem.setFarewellStateText("已入厅");
            } else if (DataUtil.equals(loadItem.getFarewellState(), Constants.MournState.End)) {
                loadItem.setFarewellStateText("已出厅");
            }
        }
        return success(loadItems);
    }

    /**
     * 加载选择告别厅信息接口
     *
     * @param data 选择时间
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:00
     */
    public BaseResult<ArrayList<FarewellHallListItem>> loadFarewellHallList(FarewellHallListData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载告别厅基本信息
        ArrayList<FarewellHallListItem> loadItems = businessFarewellDao.listFarewellHallList();
        // 获取在厅逝者信息
        ArrayList<FarewellHallDeadItem> farewellDeadItems = businessFarewellDao.listFarewellHallDeadByTime(data.getBeginTime(), data.getEndTime());

        // 获取当天开始告别任务信息
        ArrayList<FarewellHallDeadItem> farewellBeginItems = businessFarewellDao.listFarewellHallDeadForBeginTime(data.getBeginTime());

        for (FarewellHallListItem loadItem : loadItems) {
            if (DataUtil.valid(loadItem.getAsUse())) {
                resetFarewellHallDeadItem(loadItem, farewellDeadItems);
                resetFarewellList(loadItem, farewellBeginItems);
            }
        }
        return success(loadItems);
    }

    /**
     * 设置在厅逝者信息
     *
     * @param hallItem          告别厅信息
     * @param farewellDeadItems 告别厅逝者信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:53
     */
    private void resetFarewellHallDeadItem(FarewellHallListItem hallItem, ArrayList<FarewellHallDeadItem> farewellDeadItems) {
        if (DataUtil.invalid(farewellDeadItems)) {
            return;
        }
        for (FarewellHallDeadItem deadItem : farewellDeadItems) {
            if (DataUtil.equals(deadItem.getHallId(), hallItem.getHallId())) {
                if (DataUtil.equals(deadItem.getFarewellState(), Constants.MournState.Normal)) {
                    BeanUtil.copy2Bean(deadItem, hallItem);
                    hallItem.setFarewellStateText("未入厅");
                } else if (DataUtil.equals(deadItem.getFarewellState(), Constants.MournState.Begin)) {
                    BeanUtil.copy2Bean(deadItem, hallItem);
                    hallItem.setFarewellStateText("已入厅");
                }
                break;
            }
        }
    }

    /**
     * 设置当前告别厅信息
     *
     * @param hallItem           告别厅信息
     * @param farewellBeginItems 当前开始告别信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 14:24
     */
    private void resetFarewellList(FarewellHallListItem hallItem, ArrayList<FarewellHallDeadItem> farewellBeginItems) {
        if (DataUtil.invalid(farewellBeginItems)) {
            return;
        }
        ArrayList<String> farewellList = new ArrayList<>();
        for (FarewellHallDeadItem deadItem : farewellBeginItems) {
            if (DataUtil.equals(deadItem.getHallId(), hallItem.getHallId())) {
                farewellList.add(StringUtil.format("{0}～{1} 逝者【{2}】年龄【{3}】"
                        , deadItem.getBeginTime(), DateUtil.formatDateStr(deadItem.getEndTime(), "HH:mm"), deadItem.getDeadName(), deadItem.getDeadAge()));
            }
        }
        hallItem.setFarewellList(farewellList);
    }

    /**
     * 加载告别厅物品服务套餐接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 11:13
     */
    public BaseResult<ArrayList<BusinessServiceSuitItem>> loadFarewellServiceSuit(OperationNoData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ArrayList<BusinessServiceSuitItem> loadItems = new ArrayList<>();
        ArrayList<PickerItem> pickerItems = itemsDao.getItemDetailsIdByCode("", "YWZL_GBTTC");

        // 查询物品服务费用信息
        HashMap<Integer, BusinessServiceItem> serviceMap = businessService.getBusinessServiceMap(BusinessServiceType.GaoBie);
        ArrayList<BusinessServiceItem> serviceAllItems = businessFarewellDao.listFarewellSuitServiceByCode("YWZL_GBTTC");

        for (PickerItem pickerItem : pickerItems) {
            BusinessServiceSuitItem loadItem = new BusinessServiceSuitItem();
            loadItem.setSuitId(pickerItem.getValue())
                    .setSuitName(pickerItem.getText());

            // 部署代码
            ArrayList<BusinessServiceItem> serviceItems = new ArrayList<>();
            for (BusinessServiceItem serviceItem : serviceAllItems) {
                if (DataUtil.equals(loadItem.getSuitId(), serviceItem.getSuitId())) {
                    serviceItems.add(serviceItem);
                }
            }

            if (DataUtil.valid(serviceItems)) {
                loadItem.setServiceItems(serviceItems);
                loadItems.add(loadItem);
            }
        }

        return success(loadItems);
    }

    /**
     * 按照主键加载告别任务详情信息接口
     *
     * @param data 业务主键
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:07
     */
    public BaseResult<FarewellMournAllItem> loadFarewellMournAllWithId(OperationNoIdData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载告别任务信息
        FarewellMournItem farewellMournItem = businessFarewellDao.getFarewellMournById(data.getId());
        if (farewellMournItem == null || DataUtil.invalid(farewellMournItem.getBeginTime(), farewellMournItem.getRandomId())) {
            return failure("主键错误，不存在此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(farewellMournItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与告别任务不匹配");
        }

        //综合返回实体
        FarewellMournAllItem loadItem = new FarewellMournAllItem();
        loadItem.setFarewellMournItem(farewellMournItem);
        // 加载服务和物品
        loadItem.setChargeItems(chargeDao.listChargeByRandomId(farewellMournItem.getOperationNo(), farewellMournItem.getRandomId(), farewellMournItem.getItemId()));

        return success(loadItem);
    }

    /**
     * 保存告别任务详情信息接口
     *
     * @param loginItem 当前账号
     * @param data      告别任务信息
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:12
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveFarewellMourn(LoginItem loginItem, FarewellMournSaveData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 判断是新增还是更新
        if (DataUtil.invalid(data.getId())) {
            // 新增
            insertFarewellMourn(loginItem, deadBasicItem, data);
        } else {
            updateFarewellMourn(loginItem, deadBasicItem, data);
        }

        return success("保存成功");
    }

    /**
     * 新增告别信息
     *
     * @param loginItem     当前账号
     * @param deadBasicItem 逝者基础信息
     * @param data          请求数据
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:12
     */
    private void insertFarewellMourn(LoginItem loginItem, DeadBasicItem deadBasicItem, FarewellMournSaveData data) throws ActionException {
        // 获取告别厅信息
        FarewellHallListItem hallItem = businessFarewellDao.getFarewellHallListById(data.getHallId());
        if (hallItem == null || DataUtil.invalid(hallItem.getItemId())) {
            throw exception("预约告别厅主键错误，未找到此告别厅");
        }

        // 判断是否停用
        if (DataUtil.invalid(hallItem.getAsUse())) {
            throw exception("告别厅已经停用");
        }

        // 判断是否存在占用
        Integer timeMournId = businessFarewellDao.getFarewellMournIdByHallId(data.getId(), data.getHallId(), data.getBeginTime(), data.getEndTime());
        if (DataUtil.valid(timeMournId)) {
            throw exception("此时间段已有告别任务，不能保存");
        }

        // 告别按场次
        ChargeItem chargeItem = new ChargeItem();
        chargeItem.setItemId(hallItem.getItemId())
                .setPrice(hallItem.getItemPrice())
                .setCharge(hallItem.getItemPrice())
                .setNumber(new BigDecimal(1));

        // 业务随机码
        String randomId = DataUtil.getUUID();

        // 告别信息
        MournItem mournItem = new MournItem();
        mournItem.setOperationNo(data.getOperationNo())
                .setMournHallTypeId(hallItem.getItemId())
                .setMournHallId(hallItem.getHallId())
                .setBeginTime(data.getBeginTime())
                .setEndTime(data.getEndTime())
                .setRemark(data.getRemark())
                .setCharge(chargeItem.getCharge())
                .setPrice(chargeItem.getPrice())
                .setRandomId(randomId);
        // 插入告别任务信息
        businessFarewellDao.insertBusinessFarewellMourn(mournItem, loginItem);
        if (DataUtil.invalid(mournItem.getId())) {
            throw exception("新增告别任务信息失败");
        }

        // 更新逝者告别信息
        businessFarewellDao.updateDeadFarewellMourn(data.getOperationNo(), data.getBeginTime(), data.getEndTime(), hallItem, "未入厅", loginItem);

        // 告别费用
        chargeItem.setOperationNo(data.getOperationNo());
        chargeItem.setServiceCode(Constants.ServiceConst.GaoBie);
        chargeItem.setRandomId(randomId);
        chargeItem.setItemId(hallItem.getItemId());
        chargeItem.setNumber(chargeItem.getNumber());
        chargeItem.setPrice(chargeItem.getPrice());
        chargeItem.setCharge(chargeItem.getCharge());
        chargeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                .setProductFrom(0);
        // 插入告别费用
        chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
        if (DataUtil.invalid(chargeItem.getId())) {
            throw exception("添加告别费用失败");
        }

        // 插入物品和服务
        insertFarewellMournServices(loginItem, deadBasicItem, randomId, data.getServiceItems());

        // 添加日志
        String logContent = StringUtil.format("添加告别厅 业务编号【{0}】， 逝者姓名【{1}】" +
                        "，告别厅名称【{2}】，告别厅费用【{3}】，开始时间【{4}】，结束时间【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), hallItem.getHallName()
                , DataUtil.getPlainString(chargeItem.getCharge())
                , data.getBeginTime(), DataUtil.invalid(data.getEndTime()) ? "未选择结束时间" : data.getEndTime());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.GaoBieTing, "添加告别厅", BusinessConst.OperateType.TianJia, logContent, data.getOperationNo());

    }

    /**
     * 添加告别服务和物品
     *
     * @param loginItem    当前登录信息
     * @param randomId     随机码
     * @param serviceItems 服务和物品
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:12
     */
    private void insertFarewellMournServices(LoginItem loginItem, DeadBasicItem deadBasicItem, String randomId, ArrayList<BusinessServiceData> serviceItems) throws ActionException {
        // 判断关键业务信息
        if (DataUtil.invalid(deadBasicItem.getOperationNo(), randomId)) {
            return;
        }

        // 判断服务和物品
        if (DataUtil.invalid(serviceItems)) {
            return;
        }

        StringBuilderExt builder = new StringBuilderExt();
        // 查询物品服务费用信息
        HashMap<Integer, BusinessServiceItem> serviceMap = businessService.getBusinessServiceMap(BusinessServiceType.GaoBie);

        HashMap<Integer, BusinessServiceItem> serviceTaoCanMap = getTaoCanMap();

        // 开始遍历插入费用
        for (BusinessServiceData serviceData : serviceItems) {
            BusinessServiceItem serviceItem = null;
            // 套餐服务
            if (DataUtil.equals(serviceData.getRemark(), Constants.PI_LIANG_YUDING)
                    && serviceTaoCanMap.containsKey(serviceData.getItemId())) {
                serviceItem = serviceTaoCanMap.get(serviceData.getItemId());
            } else if (serviceMap.containsKey(serviceData.getItemId())) {
                serviceItem = serviceMap.get(serviceData.getItemId());
            }

            if (serviceItem == null) {
                throw exception("存在与业务不相符的服务物品主键，保存失败");
            }

            // 添加费用信息
            ChargeItem chargeItem = new ChargeItem();
            chargeItem.setOperationNo(deadBasicItem.getOperationNo())
                    .setServiceCode(Constants.ServiceConst.GaoBie)
                    .setRandomId(randomId)
                    .setItemId(serviceItem.getId())
                    .setNumber(serviceData.getNumber())
                    .setPrice(serviceData.getPrice())
                    .setCharge(serviceData.getNumber().multiply(serviceData.getPrice()))
                    .setRemark(serviceData.getRemark())
                    .setProductFrom(0);
            chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
            if (DataUtil.invalid(chargeItem.getId())) {
                throw exception("添加服务物品费用失败");
            }
            // 更新业务费用详情关联表
            chargeDao.insertChargeServiceItemDetails(chargeItem.getId(), chargeItem.getItemId(), chargeItem.getNumber());

            builder.format("物品及服务名称【{0}】，单价【{1}】，数量【{2}】，费用【{3}】;", serviceItem.getName(), DataUtil.getPlainString(serviceData.getPrice())
                    , DataUtil.getPlainString(serviceData.getNumber()), DataUtil.getPlainString(chargeItem.getCharge()));
        }

        String logContent = StringUtil.format("告别添加物品服务:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.GaoBieTing, "添加服务和物品"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());
    }

    /**
     * 获取套餐物品主键映射
     *
     * @return
     */
    private HashMap<Integer, BusinessServiceItem> getTaoCanMap() {
        ArrayList<BusinessServiceItem> serviceAllItems = businessFarewellDao.listFarewellSuitServiceByCode("YWZL_GBTTC");
        HashMap<Integer, BusinessServiceItem> loadMap = new HashMap<>(16);
        for (BusinessServiceItem loadItem : serviceAllItems) {
            loadMap.put(loadItem.getId(), loadItem);
        }
        return loadMap;
    }

    /**
     * 新增告别信息
     *
     * @param loginItem     当前账号
     * @param deadBasicItem 逝者基础信息
     * @param data          请求数据
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:12
     */
    private void updateFarewellMourn(LoginItem loginItem, DeadBasicItem deadBasicItem, FarewellMournSaveData data) throws ActionException {
        // 加载告别任务信息
        FarewellMournItem farewellMournItem = businessFarewellDao.getFarewellMournById(data.getId());
        if (farewellMournItem == null || DataUtil.invalid(farewellMournItem.getBeginTime(), farewellMournItem.getRandomId())) {
            throw exception("主键错误，不存在此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(farewellMournItem.getOperationNo(), data.getOperationNo())) {
            throw exception("业务编号错误，与告别任务不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(farewellMournItem.getAsFinished())) {
            throw exception("已结算，无法修改");
        }

        // 判断状态
        if (!DataUtil.equals(farewellMournItem.getFarewellState(), Constants.MournState.Normal)) {
            throw exception("非未入厅状态，不可修改");
        }

        // 任务执行状态
        if (!DataUtil.invalid(farewellMournItem.getTaskFlag())) {
            throw exception("任务已经开始执行，无法进行修改");
        }

        // 获取告别厅信息
        FarewellHallListItem hallItem = businessFarewellDao.getFarewellHallListById(data.getHallId());
        if (hallItem == null || DataUtil.invalid(hallItem.getItemId())) {
            throw exception("预约告别厅主键错误，未找到此告别厅");
        }

        // 判断是否停用
        if (DataUtil.invalid(hallItem.getAsUse())) {
            throw exception("告别厅已经停用");
        }

        // 判断是否存在占用
        Integer timeMournId = businessFarewellDao.getFarewellMournIdByHallId(data.getId(), data.getHallId(), data.getBeginTime(), data.getEndTime());
        if (DataUtil.valid(timeMournId)) {
            throw exception("此时间段已有告别任务，不能保存");
        }

        // 计算费用
        ChargeItem chargeItem = chargeDao.getChargeById(farewellMournItem.getChargeId());
        // 判断是否结算
        if (DataUtil.valid(chargeItem.getAsFinished())) {
            throw exception("已结算，无法修改");
        }

        // 费用按场次
        chargeItem.setItemId(hallItem.getItemId())
                .setPrice(hallItem.getItemPrice())
                .setCharge(hallItem.getItemPrice())
                .setNumber(new BigDecimal(1));

        // 更新服务信息
        farewellMournItem.setItemId(hallItem.getItemId())
                .setHallId(hallItem.getHallId())
                .setBeginTime(data.getBeginTime())
                .setEndTime(data.getEndTime())
                .setRemark(data.getRemark())
                .setCharge(chargeItem.getCharge())
                .setPrice(chargeItem.getPrice());
        businessFarewellDao.updateBusinessFarewellMourn(farewellMournItem, loginItem);

        // 更新逝者告别信息
        businessFarewellDao.updateDeadFarewellMourn(data.getOperationNo(), data.getBeginTime(), data.getEndTime(), hallItem, "", loginItem);

        // 告别费用
        chargeItem.setOperationNo(data.getOperationNo());
        chargeItem.setServiceCode(Constants.ServiceConst.GaoBie);
        chargeItem.setItemId(hallItem.getItemId());
        chargeItem.setNumber(chargeItem.getNumber());
        chargeItem.setPrice(chargeItem.getPrice());
        chargeItem.setCharge(chargeItem.getCharge());
        chargeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                .setProductFrom(0);
        // 更新告别费用
        chargeDao.updateChargeItem(chargeItem, loginItem.getUserId());

        // 插入物品和服务
        insertFarewellMournServices(loginItem, deadBasicItem, farewellMournItem.getRandomId(), data.getServiceItems());

        // 删除物品和服务
        deleteFarewellMournCharges(loginItem, deadBasicItem, farewellMournItem, data.getDeleteIds());

        // 添加日志
        String logContent = StringUtil.format("更新告别厅 业务编号【{0}】， 逝者姓名【{1}】" +
                        "，告别厅名称【{2}】，告别厅费用【{3}】，开始时间【{4}】，结束时间【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), hallItem.getHallName()
                , DataUtil.getPlainString(chargeItem.getCharge())
                , data.getBeginTime(), DataUtil.invalid(data.getEndTime()) ? "未选择结束时间" : data.getEndTime());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.GaoBieTing, "更新告别厅", BusinessConst.OperateType.XiuGai, logContent, data.getOperationNo());
    }

    /**
     * 删除物品和服务
     *
     * @param loginItem         当前登录账号
     * @param deadBasicItem     逝者信息
     * @param farewellMournItem 告别信息
     * @param deleteIds         删除信息
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:12
     */
    private void deleteFarewellMournCharges(LoginItem loginItem, DeadBasicItem deadBasicItem, FarewellMournItem farewellMournItem, ArrayList<Integer> deleteIds) throws ActionException {
        if (DataUtil.invalid(deleteIds)) {
            return;
        }

        StringBuilderExt builder = new StringBuilderExt();
        // 加载服务和物品
        ArrayList<ChargeItem> chargeItems = chargeDao.listChargeByRandomId(farewellMournItem.getOperationNo(), farewellMournItem.getRandomId(), farewellMournItem.getItemId());

        // 开始遍历费用
        for (ChargeItem chargeItem : chargeItems) {
            if (deleteIds.contains(chargeItem.getId())) {
                // 判断是否结算
                if (DataUtil.valid(chargeItem.getAsFinished())) {
                    throw exception(chargeItem.getItemName() + "已结算，无法删除");
                }

                // 执行删除费用
                chargeDao.deleteChargeById(deadBasicItem.getOperationNo(), chargeItem.getId());

                builder.format("物品及服务名称【{0}】，单价【{1}】，数量【{2}】，费用【{3}】;", chargeItem.getItemName(), DataUtil.getPlainString(chargeItem.getPrice())
                        , DataUtil.getPlainString(chargeItem.getNumber()), DataUtil.getPlainString(chargeItem.getCharge()));

                // 清除已删除主键
                deleteIds.remove(chargeItem.getId());
            }
        }

        // 判断是否删除完毕
        if (DataUtil.valid(deleteIds)) {
            throw exception("存在错误的删除费用主键，无法进行删除");
        }

        String logContent = StringUtil.format("告别删除物品服务:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.GaoBieTing, "删除服务和物品"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());
    }

    /**
     * 删除告别任务详情信息接口
     *
     * @param loginItem 当前账号
     * @param data      主键信息
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:29
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteFarewellMournWithId(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载告别任务信息
        FarewellMournItem farewellMournItem = businessFarewellDao.getFarewellMournById(data.getId());
        if (farewellMournItem == null || DataUtil.invalid(farewellMournItem.getBeginTime(), farewellMournItem.getRandomId())) {
            throw exception("主键错误，不存在此告别任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(farewellMournItem.getOperationNo(), data.getOperationNo())) {
            throw exception("业务编号错误，与告别任务不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(farewellMournItem.getAsFinished())) {
            throw exception("已结算，无法删除");
        }

        // 判断状态
        if (!DataUtil.equals(farewellMournItem.getFarewellState(), Constants.MournState.Normal)) {
            throw exception("非未入厅状态，不可删除");
        }

        // 任务执行状态
        if (!DataUtil.invalid(farewellMournItem.getTaskFlag())) {
            throw exception("任务已经开始执行，无法进行删除");
        }

        // 删除告别信息
        businessFarewellDao.deleteFarewellMournById(data.getId(), data.getOperationNo());
        // 更新逝者告别信息
        businessFarewellDao.deleteDeadFarewellMourn(data.getOperationNo(), loginItem);

        String mournContent = StringUtil.format("删除告别厅信息:业务编号【{0}】，逝者姓名【{1}】，告别厅名称【{2}】" +
                        "，费用【{3}】，开始时间【{4}】，结束时间【{5}】，备注【{6}】"
                , farewellMournItem.getOperationNo(), deadBasicItem.getDeadName(), farewellMournItem.getHallName()
                , DataUtil.getPlainString(farewellMournItem.getCharge())
                , farewellMournItem.getBeginTime(), farewellMournItem.getEndTime(), farewellMournItem.getRemark());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi, "删除告别厅信息"
                , BusinessConst.OperateType.ShanChu, mournContent, deadBasicItem.getOperationNo());

        StringBuilderExt builder = new StringBuilderExt();
        // 加载服务和物品
        ArrayList<ChargeItem> chargeItems = chargeDao.listChargeByRandomId(farewellMournItem.getOperationNo(), farewellMournItem.getRandomId(), 0);

        // 开始遍历费用
        for (ChargeItem chargeItem : chargeItems) {
            // 判断是否结算
            if (DataUtil.valid(chargeItem.getAsFinished())) {
                throw exception(chargeItem.getItemName() + "已结算，无法删除");
            }

            // 执行删除费用
            chargeDao.deleteChargeById(deadBasicItem.getOperationNo(), chargeItem.getId());

            builder.format("物品及服务名称【{0}】，单价【{1}】，数量【{2}】，费用【{3}】;", chargeItem.getItemName(), DataUtil.getPlainString(chargeItem.getPrice())
                    , DataUtil.getPlainString(chargeItem.getNumber()), DataUtil.getPlainString(chargeItem.getCharge()));
        }

        String logContent = StringUtil.format("告别删除物品服务:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.GaoBieTing, "删除服务和物品"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());


        return success("删除成功");
    }
}
