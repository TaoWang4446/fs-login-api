package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.service.ServiceSuitData;
import com.jmdz.fushan.pad.model.service.ServiceListItem;
import com.jmdz.fushan.pad.model.service.ServiceSuitItem;
import com.jmdz.fushan.pad.model.service.ServiceSuitSetItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * BusinessSuitService
 *
 * @author LiCongLu
 * @date 2020-08-10 09:32
 */
@Service("businessSuitService")
public class BusinessSuitService extends BaseService {

    @Resource
    private DeadDao deadDao;

    @Resource
    private ServiceSuitDao serviceSuitDao;

    @Resource
    private FuneralProductDao funeralProductDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private FaceLiftDao faceLiftDao;

    /**
     * 服务套餐编码
     */
    private String itemsCode = "YWZL_FWTC";

    /**
     * 业务洽谈服务套餐加载接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-10 09:38
     */
    public BaseResult<ArrayList<ServiceSuitItem>> loadBusinessServiceSuit(OperationNoData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ArrayList<ServiceSuitItem> loadItems = serviceSuitDao.listServiceSuitByItemsCode(itemsCode);
        for (ServiceSuitItem loadItem : loadItems) {
            ArrayList<ServiceSuitSetItem> suitSetItems = serviceSuitDao.listServiceSuitSetItemBySuitId(itemsCode, loadItem.getSuitId());
            loadItem.setServiceItems(BeanUtil.copy2List(suitSetItems, ServiceListItem.class));
        }
        return success(loadItems);
    }


    /**
     * 业务洽谈添加服务套餐接口
     *
     * @param loginItem 当前账号
     * @param data      服务套餐主键
     * @return
     * @author LiCongLu
     * @date 2020-08-10 10:10
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveBusinessServiceSuit(LoginItem loginItem, ServiceSuitData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 业务随机码
        String randomId = DataUtil.getUUID();

        // 套餐主键
        ArrayList<ServiceSuitSetItem> suitSetItems = serviceSuitDao.listServiceSuitSetItemBySuitId(itemsCode, data.getSuitId());
        for (ServiceSuitSetItem suitSetItem : suitSetItems) {
            if (DataUtil.equals(suitSetItem.getTableName(), "f_funeralproduct")) {
                insertFuneralProduct(loginItem, deadBasicItem, randomId, suitSetItem);
            } else if (DataUtil.equals(suitSetItem.getTableName(), "f_facelift")) {
                insertFaceLift(loginItem, deadBasicItem, randomId, suitSetItem);
            }
        }
        return success("添加完成");
    }

    /**
     * 批量添加套餐丧葬用品
     *
     * @param loginItem     当前账号
     * @param deadBasicItem 逝者信息
     * @param randomId      业务随机码
     * @param suitSetItem   当前套餐物品
     * @return
     * @author LiCongLu
     * @date 2020-08-10 10:40
     */
    private void insertFuneralProduct(LoginItem loginItem, DeadBasicItem deadBasicItem, String randomId, ServiceSuitSetItem suitSetItem) throws ActionException {
        // 添加物品费用
        FuneralProductItem productItem = new FuneralProductItem();
        productItem.setOperationNo(deadBasicItem.getOperationNo())
                .setServiceItemId(suitSetItem.getItemId())
                .setNumber(suitSetItem.getItemNumber())
                .setPrice(suitSetItem.getItemPrice())
                .setCharge(suitSetItem.getItemNumber().multiply(suitSetItem.getItemPrice()))
                .setRandomId(randomId)
                .setRemark(Constants.PI_LIANG_YUDING)
                .setBiaokey(suitSetItem.getSuitKey());
        funeralProductDao.insertFuneralProductItem(productItem, loginItem.getUserId());
        if (DataUtil.invalid(productItem.getId())) {
            throw exception("添加服务物品失败");
        }

        // 添加费用信息
        ChargeItem chargeItem = new ChargeItem();
        chargeItem.setOperationNo(productItem.getOperationNo())
                .setServiceCode(30)
                .setRandomId(randomId)
                .setItemId(productItem.getServiceItemId())
                .setNumber(productItem.getNumber())
                .setPrice(productItem.getPrice())
                .setCharge(productItem.getCharge())
                .setRemark(productItem.getRemark())
                .setProductFrom(1);
        chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
        if (DataUtil.invalid(chargeItem.getId())) {
            throw exception("添加服务物品费用失败");
        }

        String logContent = StringUtil.format("套餐预定丧葬用品及服务:业务编号【{0}】，逝者姓名【{1}】" +
                        "，服务项名称【{2}】，单价【{3}】，数量【{4}】，金额【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), suitSetItem.getItemName()
                , DataUtil.getPlainString(chargeItem.getPrice())
                , DataUtil.getPlainString(chargeItem.getNumber())
                , DataUtil.getPlainString(chargeItem.getCharge()));
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.SangZangYongPinJiFuWu, "添加丧葬用品及服务"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());
    }

    /**
     * 批量添加套餐整容项目
     *
     * @param loginItem     当前账号
     * @param deadBasicItem 逝者信息
     * @param randomId      业务随机码
     * @param suitSetItem   当前套餐物品
     * @return
     * @author LiCongLu
     * @date 2020-08-10 10:41
     */
    private void insertFaceLift(LoginItem loginItem, DeadBasicItem deadBasicItem, String randomId, ServiceSuitSetItem suitSetItem) throws ActionException {
        // 添加整容项目
        FaceLiftItem faceLiftItem = new FaceLiftItem();
        faceLiftItem.setOperationNo(deadBasicItem.getOperationNo())
                .setServiceItemId(suitSetItem.getItemId())
                .setPrice(suitSetItem.getItemPrice())
                .setFaceLiftNum(suitSetItem.getItemNumber())
                .setCharge(suitSetItem.getItemPrice().multiply(suitSetItem.getItemNumber()))
                .setRandomId(randomId)
                .setRemark(Constants.PI_LIANG_YUDING);
        faceLiftDao.insertFaceLiftItem(faceLiftItem, loginItem.getUserId());
        if (DataUtil.invalid(faceLiftItem.getId())) {
            throw exception("添加整容项失败");
        }

        // 添加费用信息
        ChargeItem chargeItem = new ChargeItem();
        chargeItem.setOperationNo(faceLiftItem.getOperationNo())
                .setServiceCode(10)
                .setRandomId(randomId)
                .setItemId(faceLiftItem.getServiceItemId())
                .setNumber(faceLiftItem.getFaceLiftNum())
                .setPrice(faceLiftItem.getPrice())
                .setCharge(faceLiftItem.getCharge())
                .setRemark(faceLiftItem.getRemark())
                .setProductFrom(0);
        chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
        if (DataUtil.invalid(chargeItem.getId())) {
            throw exception("添加整容费用失败");
        }

        String logContent = StringUtil.format("套餐预定整容项目:业务编号【{0}】，逝者姓名【{1}】，服务项名称【{2}】，单价【{3}】，数量【{4}】，金额【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), suitSetItem.getItemName()
                , DataUtil.getPlainString(chargeItem.getPrice())
                , DataUtil.getPlainString(chargeItem.getNumber())
                , DataUtil.getPlainString(chargeItem.getCharge()));
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.FangFuZhengRong, "预约整容项目"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());

    }

    /**
     * 删除业务洽谈服务套餐接口
     *
     * @param loginItem 当前账号
     * @param data      业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-10 11:16
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteBusinessServiceSuit(LoginItem loginItem, OperationNoData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 删除套餐服务
        chargeDao.deleteFuneralProductFaceLiftSuitCharge(data.getOperationNo());
        String logContent = StringUtil.format("取消套餐:业务编号【{0}】，逝者姓名【{1}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi, "取消套餐"
                , BusinessConst.OperateType.XiuGai, logContent, deadBasicItem.getOperationNo());

        return success("删除完成");
    }
}
