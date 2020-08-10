package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.BusinessServiceType;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.business.wake.*;
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
 * BusinessWakeService
 *
 * @author LiCongLu
 * @date 2020-08-05 11:20
 */
@Service("businessWakeService")
public class BusinessWakeService extends BaseService {

    @Resource
    private DeadDao deadDao;

    @Resource
    private BusinessWakeDao businessWakeDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private BusinessService businessService;

    /**
     * 按照业务编号加载守灵任务列表信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-05 15:07
     */
    public BaseResult<ArrayList<WakeMournListItem>> loadWakeMournList(OperationNoData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ArrayList<WakeMournListItem> loadItems = businessWakeDao.listWakeMournListByOperationNo(data.getOperationNo());
        for (WakeMournListItem loadItem : loadItems) {
            if (DataUtil.equals(loadItem.getWakeState(), Constants.MournState.Normal)) {
                loadItem.setWakeStateText("未入厅");
            } else if (DataUtil.equals(loadItem.getWakeState(), Constants.MournState.Begin)) {
                loadItem.setWakeStateText("已入厅");
            } else if (DataUtil.equals(loadItem.getWakeState(), Constants.MournState.End)) {
                loadItem.setWakeStateText("已出厅");
            }
        }
        return success(loadItems);
    }


    /**
     * 加载选择守灵厅信息接口
     *
     * @param data 选择时间
     * @return
     * @author LiCongLu
     * @date 2020-08-05 11:44
     */
    public BaseResult<ArrayList<WakeHallListItem>> loadWakeHallList(WakeHallListData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载守灵厅基本信息
        ArrayList<WakeHallListItem> loadItems = businessWakeDao.listWakeHallList();
        // 获取在厅逝者信息
        ArrayList<WakeHallDeadItem> wakeDeadItems = businessWakeDao.listWakeHallDeadByTime(data.getBeginTime(), data.getEndTime());

        // 获取当天开始守灵任务信息
        ArrayList<WakeHallDeadItem> wakeBeginItems = businessWakeDao.listWakeHallDeadForBeginTime(data.getBeginTime());

        for (WakeHallListItem loadItem : loadItems) {
            if (DataUtil.valid(loadItem.getAsUse())) {
                resetWakeHallDeadItem(loadItem, wakeDeadItems);
                resetWakeList(loadItem, wakeBeginItems);
            }
        }
        return success(loadItems);
    }

    /**
     * 设置在厅逝者信息
     *
     * @param hallItem      守灵厅信息
     * @param wakeDeadItems 守灵厅逝者信息
     * @return
     * @author LiCongLu
     * @date 2020-08-05 13:53
     */
    private void resetWakeHallDeadItem(WakeHallListItem hallItem, ArrayList<WakeHallDeadItem> wakeDeadItems) {
        if (DataUtil.invalid(wakeDeadItems)) {
            return;
        }
        for (WakeHallDeadItem deadItem : wakeDeadItems) {
            if (DataUtil.equals(deadItem.getHallId(), hallItem.getHallId())) {
                if (DataUtil.equals(deadItem.getWakeState(), Constants.MournState.Normal)) {
                    BeanUtil.copy2Bean(deadItem, hallItem);
                    hallItem.setWakeStateText("未入厅");
                } else if (DataUtil.equals(deadItem.getWakeState(), Constants.MournState.Begin)) {
                    BeanUtil.copy2Bean(deadItem, hallItem);
                    hallItem.setWakeStateText("已入厅");
                }
                break;
            }
        }
    }

    /**
     * 设置当前守灵厅信息
     *
     * @param hallItem       守灵厅信息
     * @param wakeBeginItems 当前开始守灵信息
     * @return
     * @author LiCongLu
     * @date 2020-08-05 14:24
     */
    private void resetWakeList(WakeHallListItem hallItem, ArrayList<WakeHallDeadItem> wakeBeginItems) {
        if (DataUtil.invalid(wakeBeginItems)) {
            return;
        }
        ArrayList<String> wakeList = new ArrayList<>();
        for (WakeHallDeadItem deadItem : wakeBeginItems) {
            if (DataUtil.equals(deadItem.getHallId(), hallItem.getHallId())) {
                wakeList.add(StringUtil.format("{0}～{1} 逝者【{2}】年龄【{3}】"
                        , deadItem.getBeginTime(), DateUtil.formatDateStr(deadItem.getEndTime(), "HH:mm"), deadItem.getDeadName(), deadItem.getDeadAge()));
            }
        }
        hallItem.setWakeList(wakeList);
    }

    /**
     * 按照主键加载守灵任务详情信息接口
     *
     * @param data 业务主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 10:32
     */
    public BaseResult<WakeMournAllItem> loadWakeMournAllWithId(OperationNoIdData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载守灵任务信息
        WakeMournItem wakeMournItem = businessWakeDao.getWakeMournById(data.getId());
        if (wakeMournItem == null || DataUtil.invalid(wakeMournItem.getBeginTime(), wakeMournItem.getRandomId())) {
            return failure("主键错误，不存在此守灵任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(wakeMournItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与守灵任务不匹配");
        }

        //综合返回实体
        WakeMournAllItem loadItem = new WakeMournAllItem();
        loadItem.setWakeMournItem(wakeMournItem);
        // 加载服务和物品
        loadItem.setChargeItems(chargeDao.listChargeByRandomId(wakeMournItem.getOperationNo(), wakeMournItem.getRandomId(), wakeMournItem.getItemId()));

        return success(loadItem);
    }

    /**
     * 重新计算守灵费用，已与BS端进行比对了
     * <p>
     * 守灵厅结算方式
     * 1.当开始时间，结束时间，价格全都不为空时，才进行计算，否则，数量默认为1，金额为0
     * 2.计算规则，按小时计算
     * a.不足一小时，按照一小时计算；不足12小时，按12小时计算；
     * b.金额，所得小时与价格相乘。
     *
     * @param beginTime  开始时间
     * @param endTime    结束时间
     * @param chargeItem 费用实体
     * @return
     * @author LiCongLu
     * @date 2020-08-05 17:26
     */
    public void resetWakeCharge(String beginTime, String endTime, ChargeItem chargeItem) throws ActionException {
        if (chargeItem == null) {
            return;
        }

        // 计算条件不全
        if (DataUtil.invalid(beginTime, endTime)
                || chargeItem.getPrice() == null
                || beginTime.length() < 16
                || endTime.length() < 16) {
            chargeItem.setNumber(new BigDecimal(1));
            chargeItem.setCharge(new BigDecimal(0));
            return;
        }

        // 日期格式不对
        Date beginDate = DateUtil.parsePattern(beginTime);
        Date endDate = DateUtil.parsePattern(endTime);
        if (beginDate == null || endDate == null) {
            chargeItem.setNumber(new BigDecimal(1));
            chargeItem.setCharge(new BigDecimal(0));
            return;
        }

        // 日期时间大小不对
        if (beginDate.after(endDate)) {
            throw exception("预约入藏时间晚于预约出藏时间");
        }

        // 分钟
        long minutes = Math.abs(endDate.getTime() - beginDate.getTime()) / (1000 * 60);
        // 不足一小时部分，按一小时算
        long hours = minutes / 60 + (minutes % 60 > 0 ? 1 : 0);
        // 总数时长，不足12小时，按照12小时计算
        BigDecimal number = new BigDecimal(hours < 12 ? 12 : hours);
        // 计算费用
        chargeItem.setNumber(number);
        chargeItem.setCharge(chargeItem.getNumber().multiply(chargeItem.getPrice()));
    }

    /**
     * 保存守灵任务详情信息接口
     *
     * @param loginItem 当前账号
     * @param data      守灵任务信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 11:48
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveWakeMourn(LoginItem loginItem, WakeMournSaveData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 判断是新增还是更新
        if (DataUtil.invalid(data.getId())) {
            // 新增
            insertWakeMourn(loginItem, deadBasicItem, data);
        } else {
            updateWakeMourn(loginItem, deadBasicItem, data);
        }

        return success("保存成功");
    }

    /**
     * 新增守灵信息
     *
     * @param loginItem     当前账号
     * @param deadBasicItem 逝者基础信息
     * @param data          请求数据
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:11
     */
    private void insertWakeMourn(LoginItem loginItem, DeadBasicItem deadBasicItem, WakeMournSaveData data) throws ActionException {
        // 获取守灵厅信息
        WakeHallListItem hallItem = businessWakeDao.getWakeHallListById(data.getHallId());
        if (hallItem == null || DataUtil.invalid(hallItem.getItemId())) {
            throw exception("预约守灵厅主键错误，未找到此守灵厅");
        }

        // 判断是否停用
        if (DataUtil.invalid(hallItem.getAsUse())) {
            throw exception("守灵厅已经停用");
        }

        // 判断是否存在占用
        Integer timeMournId = businessWakeDao.getWakeMournIdByHallId(data.getId(), data.getHallId(), data.getBeginTime(), data.getEndTime());
        if (DataUtil.valid(timeMournId)) {
            throw exception("此时间段已有守灵任务，不能保存");
        }

        // 计算费用
        ChargeItem chargeItem = new ChargeItem();
        chargeItem.setItemId(hallItem.getItemId())
                .setPrice(hallItem.getItemPrice());
        resetWakeCharge(data.getBeginTime(), data.getEndTime(), chargeItem);

        // 业务随机码
        String randomId = DataUtil.getUUID();

        // 守灵信息
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
        // 插入守灵任务信息
        businessWakeDao.insertBusinessWakeMourn(mournItem, loginItem);
        if (DataUtil.invalid(mournItem.getId())) {
            throw exception("新增守灵任务信息失败");
        }

        // 更新逝者守灵信息
        businessWakeDao.updateDeadWakeMourn(data.getOperationNo(), data.getBeginTime(), data.getEndTime(), hallItem, "未入厅", loginItem);

        // 守灵费用
        chargeItem.setOperationNo(data.getOperationNo());
        chargeItem.setServiceCode(Constants.ServiceConst.ShouLing);
        chargeItem.setRandomId(randomId);
        chargeItem.setItemId(hallItem.getItemId());
        chargeItem.setNumber(chargeItem.getNumber());
        chargeItem.setPrice(chargeItem.getPrice());
        chargeItem.setCharge(chargeItem.getCharge());
        chargeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                .setProductFrom(0);
        // 插入守灵费用
        chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
        if (DataUtil.invalid(chargeItem.getId())) {
            throw exception("添加守灵费用失败");
        }

        // 插入物品和服务
        insertWakeMournServices(loginItem, deadBasicItem, randomId, data.getServiceItems());

        // 添加日志
        String logContent = StringUtil.format("添加守灵厅 业务编号【{0}】， 逝者姓名【{1}】" +
                        "，守灵厅名称【{2}】，守灵厅费用【{3}】，开始时间【{4}】，结束时间【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), hallItem.getHallName()
                , DataUtil.getPlainString(chargeItem.getCharge())
                , data.getBeginTime(), DataUtil.invalid(data.getEndTime()) ? "未选择结束时间" : data.getEndTime());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.ShouLingTing, "添加守灵厅", BusinessConst.OperateType.TianJia, logContent, data.getOperationNo());

    }

    /**
     * 添加守灵服务和物品
     *
     * @param loginItem    当前登录信息
     * @param randomId     随机码
     * @param serviceItems 服务和物品
     * @return
     * @author LiCongLu
     * @date 2020-08-06 14:30
     */
    private void insertWakeMournServices(LoginItem loginItem, DeadBasicItem deadBasicItem, String randomId, ArrayList<BusinessServiceData> serviceItems) throws ActionException {
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
        HashMap<Integer, BusinessServiceItem> serviceMap = businessService.getBusinessServiceMap(BusinessServiceType.ShouLing);
        // 开始遍历插入费用
        for (BusinessServiceData serviceData : serviceItems) {
            if (!serviceMap.containsKey(serviceData.getItemId())) {
                throw exception("存在与业务不相符的服务物品主键，保存失败");
            }

            BusinessServiceItem serviceItem = serviceMap.get(serviceData.getItemId());

            // 添加费用信息
            ChargeItem chargeItem = new ChargeItem();
            chargeItem.setOperationNo(deadBasicItem.getOperationNo())
                    .setServiceCode(Constants.ServiceConst.ShouLing)
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

        String logContent = StringUtil.format("守灵添加物品服务:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.ShouLingTing, "添加服务和物品"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());
    }

    /**
     * 新增守灵信息
     *
     * @param loginItem     当前账号
     * @param deadBasicItem 逝者基础信息
     * @param data          请求数据
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:11
     */
    private void updateWakeMourn(LoginItem loginItem, DeadBasicItem deadBasicItem, WakeMournSaveData data) throws ActionException {
        // 加载守灵任务信息
        WakeMournItem wakeMournItem = businessWakeDao.getWakeMournById(data.getId());
        if (wakeMournItem == null || DataUtil.invalid(wakeMournItem.getBeginTime(), wakeMournItem.getRandomId())) {
            throw exception("主键错误，不存在此守灵任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(wakeMournItem.getOperationNo(), data.getOperationNo())) {
            throw exception("业务编号错误，与守灵任务不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(wakeMournItem.getAsFinished())) {
            throw exception("已结算，无法修改");
        }

        // 判断状态
        if (!DataUtil.equals(wakeMournItem.getWakeState(), Constants.MournState.Normal)) {
            throw exception("非未入厅状态，不可修改");
        }

        // 任务执行状态
        if (!DataUtil.invalid(wakeMournItem.getTaskFlag())) {
            throw exception("任务已经开始执行，无法进行修改");
        }

        // 获取守灵厅信息
        WakeHallListItem hallItem = businessWakeDao.getWakeHallListById(data.getHallId());
        if (hallItem == null || DataUtil.invalid(hallItem.getItemId())) {
            throw exception("预约守灵厅主键错误，未找到此守灵厅");
        }

        // 判断是否停用
        if (DataUtil.invalid(hallItem.getAsUse())) {
            throw exception("守灵厅已经停用");
        }

        // 判断是否存在占用
        Integer timeMournId = businessWakeDao.getWakeMournIdByHallId(data.getId(), data.getHallId(), data.getBeginTime(), data.getEndTime());
        if (DataUtil.valid(timeMournId)) {
            throw exception("此时间段已有守灵任务，不能保存");
        }

        // 计算费用
        ChargeItem chargeItem = chargeDao.getChargeById(wakeMournItem.getChargeId());
        // 判断是否结算
        if (DataUtil.valid(chargeItem.getAsFinished())) {
            throw exception("已结算，无法修改");
        }

        chargeItem.setItemId(hallItem.getItemId())
                .setPrice(hallItem.getItemPrice());
        resetWakeCharge(data.getBeginTime(), data.getEndTime(), chargeItem);

        // 更新服务信息
        wakeMournItem.setItemId(hallItem.getItemId())
                .setHallId(hallItem.getHallId())
                .setBeginTime(data.getBeginTime())
                .setEndTime(data.getEndTime())
                .setRemark(data.getRemark())
                .setCharge(chargeItem.getCharge())
                .setPrice(chargeItem.getPrice());
        businessWakeDao.updateBusinessWakeMourn(wakeMournItem, loginItem);

        // 更新逝者守灵信息
        businessWakeDao.updateDeadWakeMourn(data.getOperationNo(), data.getBeginTime(), data.getEndTime(), hallItem, "", loginItem);

        // 守灵费用
        chargeItem.setOperationNo(data.getOperationNo());
        chargeItem.setServiceCode(Constants.ServiceConst.ShouLing);
        chargeItem.setItemId(hallItem.getItemId());
        chargeItem.setNumber(chargeItem.getNumber());
        chargeItem.setPrice(chargeItem.getPrice());
        chargeItem.setCharge(chargeItem.getCharge());
        chargeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                .setProductFrom(0);
        // 更新守灵费用
        chargeDao.updateChargeItem(chargeItem, loginItem.getUserId());

        // 插入物品和服务
        insertWakeMournServices(loginItem, deadBasicItem, wakeMournItem.getRandomId(), data.getServiceItems());

        // 删除物品和服务
        deleteWakeMournCharges(loginItem, deadBasicItem, wakeMournItem, data.getDeleteIds());

        // 添加日志
        String logContent = StringUtil.format("更新守灵厅 业务编号【{0}】， 逝者姓名【{1}】" +
                        "，守灵厅名称【{2}】，守灵厅费用【{3}】，开始时间【{4}】，结束时间【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), hallItem.getHallName()
                , DataUtil.getPlainString(chargeItem.getCharge())
                , data.getBeginTime(), DataUtil.invalid(data.getEndTime()) ? "未选择结束时间" : data.getEndTime());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.ShouLingTing, "更新守灵厅", BusinessConst.OperateType.XiuGai, logContent, data.getOperationNo());
    }

    /**
     * 删除物品和服务
     *
     * @param loginItem     当前登录账号
     * @param deadBasicItem 逝者信息
     * @param wakeMournItem 守灵信息
     * @param deleteIds     删除信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 15:50
     */
    private void deleteWakeMournCharges(LoginItem loginItem, DeadBasicItem deadBasicItem, WakeMournItem wakeMournItem, ArrayList<Integer> deleteIds) throws ActionException {
        if (DataUtil.invalid(deleteIds)) {
            return;
        }

        StringBuilderExt builder = new StringBuilderExt();
        // 加载服务和物品
        ArrayList<ChargeItem> chargeItems = chargeDao.listChargeByRandomId(wakeMournItem.getOperationNo(), wakeMournItem.getRandomId(), wakeMournItem.getItemId());

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

        String logContent = StringUtil.format("守灵删除物品服务:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.ShouLingTing, "删除服务和物品"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());
    }

    /**
     * 删除守灵任务详情信息接口
     *
     * @param data 主键信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 16:36
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteWakeMournWithId(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载守灵任务信息
        WakeMournItem wakeMournItem = businessWakeDao.getWakeMournById(data.getId());
        if (wakeMournItem == null || DataUtil.invalid(wakeMournItem.getBeginTime(), wakeMournItem.getRandomId())) {
            throw exception("主键错误，不存在此守灵任务");
        }

        // 判断业务编号
        if (!DataUtil.equals(wakeMournItem.getOperationNo(), data.getOperationNo())) {
            throw exception("业务编号错误，与守灵任务不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(wakeMournItem.getAsFinished())) {
            throw exception("已结算，无法删除");
        }

        // 判断状态
        if (!DataUtil.equals(wakeMournItem.getWakeState(), Constants.MournState.Normal)) {
            throw exception("非未入厅状态，不可删除");
        }

        // 任务执行状态
        if (!DataUtil.invalid(wakeMournItem.getTaskFlag())) {
            throw exception("任务已经开始执行，无法进行删除");
        }

        // 删除守灵信息
        businessWakeDao.deleteWakeMournById(data.getId(), data.getOperationNo());
        // 删除逝者关联守灵信息
        businessWakeDao.deleteDeadWakeMourn(data.getOperationNo(), loginItem);

        String mournContent = StringUtil.format("删除守灵厅信息:业务编号【{0}】，逝者姓名【{1}】，告别厅名称【{2}】" +
                        "，费用【{3}】，开始时间【{4}】，结束时间【{5}】，备注【{6}】"
                , wakeMournItem.getOperationNo(), deadBasicItem.getDeadName(), wakeMournItem.getHallName()
                , DataUtil.getPlainString(wakeMournItem.getCharge())
                , wakeMournItem.getBeginTime(), wakeMournItem.getEndTime(), wakeMournItem.getRemark());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi, "删除守灵厅信息"
                , BusinessConst.OperateType.ShanChu, mournContent, deadBasicItem.getOperationNo());

        StringBuilderExt builder = new StringBuilderExt();
        // 加载服务和物品
        ArrayList<ChargeItem> chargeItems = chargeDao.listChargeByRandomId(wakeMournItem.getOperationNo(), wakeMournItem.getRandomId(), 0);

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

        String logContent = StringUtil.format("守灵删除物品服务:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.ShouLingTing, "删除服务和物品"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());


        return success("删除成功");
    }
}
