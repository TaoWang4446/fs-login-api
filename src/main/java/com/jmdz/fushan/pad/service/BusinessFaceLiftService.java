package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.BusinessServiceType;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * BusinessFaceLiftService
 *
 * @author LiCongLu
 * @date 2020-08-04 15:12
 */
@Service("businessFaceLiftService")
public class BusinessFaceLiftService extends BaseService {

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private DeadDao deadDao;

    @Resource
    private BusinessService businessService;

    @Resource
    private FaceLiftDao faceLiftDao;

    @Resource
    private BusinessLogDao businessLogDao;

    /**
     * 加载整容服务项目费用列表接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 16:48
     */
    public BaseResult<ArrayList<FaceLiftChargeListItem>> loadFaceLiftChargeList(OperationNoData data) {
        ArrayList<FaceLiftChargeListItem> loadItems = faceLiftDao.listFaceLiftChargeListByOperationNo(data.getOperationNo());
        return success(loadItems);
    }

    /**
     * 保存整容服务项目接口
     *
     * @param data 整容项目
     * @return
     * @author LiCongLu
     * @date 2020-08-04 15:18
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveFaceLiftService(LoginItem loginItem, FaceLiftServiceData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 判断是否存在物品服务
        if (DataUtil.invalid(data.getServiceItems())) {
            return failure("必须选择丧葬用品及服务");
        }

        String operationNo = data.getOperationNo();
        StringBuilderExt builder = new StringBuilderExt();

        // 执行时间，按照数据库表里字段名定义
        String excuteTime = null;
        if (DataUtil.valid(data.getExcuteTime())) {
            excuteTime = DateUtil.formatDateStr(data.getExcuteTime(), BaseBean.yyyy_MM_dd_HH_mm);
        }

        String randomId = DataUtil.getUUID();

        // 查询物品服务费用信息
        HashMap<Integer, BusinessServiceItem> serviceMap = businessService.getBusinessServiceMap(BusinessServiceType.ZhengRong);
        // 开始遍历插入费用
        for (BusinessServiceData serviceData : data.getServiceItems()) {
            if (!serviceMap.containsKey(serviceData.getItemId())) {
                throw exception("存在与业务不相符的服务物品主键，保存失败");
            }

            BusinessServiceItem serviceItem = serviceMap.get(serviceData.getItemId());

            // 添加整容项目
            FaceLiftItem faceLiftItem = new FaceLiftItem();
            faceLiftItem.setOperationNo(operationNo)
                    .setServiceItemId(serviceData.getItemId())
                    .setPrice(serviceData.getPrice())
                    .setFaceLiftNum(serviceData.getNumber())
                    .setCharge(serviceData.getPrice().multiply(serviceData.getNumber()))
                    .setRandomId(randomId)
                    .setRemark(serviceData.getRemark())
                    .setExcuteTime(excuteTime);
            faceLiftDao.insertFaceLiftItem(faceLiftItem, loginItem.getUserId());
            if (DataUtil.invalid(faceLiftItem.getId())) {
                throw exception("添加整容项失败");
            }

            // 添加费用信息
            ChargeItem chargeItem = new ChargeItem();
            chargeItem.setOperationNo(operationNo)
                    .setServiceCode(10)
                    .setRandomId(randomId)
                    .setItemId(serviceData.getItemId())
                    .setNumber(serviceData.getNumber())
                    .setPrice(serviceData.getPrice())
                    .setCharge(serviceData.getNumber().multiply(serviceData.getPrice()))
                    .setRemark(serviceData.getRemark())
                    .setProductFrom(0);
            chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
            if (DataUtil.invalid(chargeItem.getId())) {
                throw exception("添加整容费用失败");
            }

            builder.format("整容项目名称【{0}】，单价【{1}】，数量【{2}】，费用【{3}】;", serviceItem.getName(), DataUtil.getPlainString(serviceData.getPrice())
                    , DataUtil.getPlainString(serviceData.getNumber()), DataUtil.getPlainString(chargeItem.getCharge()));
        }

        String logContent = StringUtil.format("预定整容项目:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.FangFuZhengRong, "预约整容项目"
                , BusinessConst.OperateType.XiuGai, logContent, operationNo);

        return success("添加完成");
    }

    /**
     * 按照费用主键加载整容服务项目费用接口
     *
     * @param data 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 17:07
     */
    public BaseResult<FaceLiftChargeListItem> loadFaceLiftChargeWithId(OperationNoIdData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载费用信息
        FaceLiftChargeListItem loadItem = faceLiftDao.getFaceLiftChargeListByChargeId(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getFaceLiftId())) {
            return failure("费用主键错误，不存在此整容费用");
        }

        // 判断业务编号
        if (!DataUtil.equals(loadItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与费用所属业务编号不匹配");
        }

        return success(loadItem);
    }

    /**
     * 保存整容服务项目费用接口
     *
     * @param data 整容服务费用
     * @return
     * @author LiCongLu
     * @date 2020-08-05 09:18
     */
    public BaseResult saveFaceLiftCharge(LoginItem loginItem, FaceLiftChargeData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载费用信息
        FaceLiftChargeListItem loadItem = faceLiftDao.getFaceLiftChargeListByChargeId(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getFaceLiftId())) {
            return failure("费用主键错误，不存在此整容费用");
        }

        // 判断业务编号
        if (!DataUtil.equals(loadItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与费用所属业务编号不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(loadItem.getAsFinished())) {
            return failure("此条项目已经缴费，不允许修改");
        }

        // 赋值新费用
        loadItem.setNumber(data.getNumber())
                .setPrice(data.getPrice())
                .setCharge(data.getNumber().multiply(data.getPrice()))
                .setExcuteTime(data.getExcuteTime())
                .setRemark(data.getRemark());

        // 更新费用数据
        faceLiftDao.updateFaceLiftCharge(loadItem, loginItem.getUserId());

        String logContent = StringUtil.format("修改整容项目:业务编号【{0}】，逝者姓名【{1}】，服务项目【{2}】，价格【{3}】" +
                        "，数量【{4}】，费用【{5}】，备注【{6}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), loadItem.getItemName()
                , DataUtil.getPlainString(loadItem.getPrice()), DataUtil.getPlainString(loadItem.getNumber())
                , DataUtil.getPlainString(loadItem.getCharge()), data.getRemark());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.FangFuZhengRong, "修改整容项目"
                , BusinessConst.OperateType.XiuGai, logContent, data.getOperationNo());

        return success("修改完成");
    }

    /**
     * 删除整容服务项目费用接口
     *
     * @param data 主键信息
     * @return
     * @author LiCongLu
     * @date 2020-08-05 09:00
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteFaceLiftChargeWithId(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 加载费用信息
        FaceLiftChargeListItem loadItem = faceLiftDao.getFaceLiftChargeListByChargeId(data.getId());
        if (loadItem == null || DataUtil.invalid(loadItem.getFaceLiftId())) {
            return failure("费用主键错误，不存在此整容费用");
        }

        // 判断业务编号
        if (!DataUtil.equals(loadItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与费用所属业务编号不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(loadItem.getAsFinished())) {
            return failure("此条项目已经缴费，不允许删除");
        }

        // 更新费用数据
        faceLiftDao.deleteFaceLiftCharge(loadItem.getOperationNo(), loadItem.getId(), loadItem.getFaceLiftId());

        // 添加日志
        String logContent = StringUtil.format("删除整容项目:业务编号【{0}】，逝者姓名【{1}】，项目服务名称【{2}】，数量【{3}】，单价【{4}】，费用【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), loadItem.getItemName()
                , DataUtil.getPlainString(loadItem.getPrice()), DataUtil.getPlainString(loadItem.getNumber())
                , DataUtil.getPlainString(loadItem.getCharge()));
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi, "删除整容项目"
                , BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        return success("删除完成");
    }
}
