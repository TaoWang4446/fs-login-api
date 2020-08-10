package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.BusinessServiceType;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * BusinessProductService
 *
 * @author LiCongLu
 * @date 2020-08-03 16:14
 */
@Service("businessProductService")
public class BusinessProductService extends BaseService {

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private DeadDao deadDao;

    @Resource
    private BusinessService businessService;

    @Resource
    private FuneralProductDao funeralProductDao;

    @Resource
    private BusinessLogDao businessLogDao;

    /**
     * 按照类型加载丧葬用品费用列表接口
     *
     * @param data 服务和物品类型
     * @return
     * @author LiCongLu
     * @date 2020-08-03 15:57
     */
    public BaseResult loadFuneralProductChargeWithType(ServiceTypeData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ArrayList<ChargeItem> loadItems = new ArrayList<>();
        // 忽略字段
        ArrayListExt<Integer> ignoreList = new ArrayListExt<>();

        Integer biaokey = getBiaokey(ignoreList, data.getType());

        if (biaokey == null) {
            return failure("业务类型错误，未识别出来");
        }

        // 当查询条件存在时，查询丧葬用品费用列表
        ArrayList<ChargeItem> loadAllItems = chargeDao.listProductChargeByBiaokey(data.getOperationNo(), biaokey);
        for (ChargeItem loadItem : loadAllItems) {
            if (!ignoreList.contains(loadItem.getId())) {
                loadItems.add(loadItem);
            }
        }
        return success(loadItems);
    }

    private Integer getBiaokey(ArrayListExt<Integer> ignoreList, String type) {
        Integer biaokey = null;
        switch (type) {
            case BusinessServiceType.SangZangYongPin:
                // 丧葬用品
                biaokey = 0;
                break;
            case BusinessServiceType.GuHuiHe:
                // 骨灰盒
                biaokey = 1;
                break;
            case BusinessServiceType.ShouYi:
                // 寿衣
                biaokey = 4;
                break;
            case BusinessServiceType.ZhiGuan:
                // 纸棺
                biaokey = 5;
                break;
            case BusinessServiceType.XianHua:
                // 鲜花
                biaokey = 6;
                break;
            case BusinessServiceType.FuWuXiangMu:
                // 服务项目
                biaokey = 2;
                ignoreList.add(2460);
                break;
        }
        return biaokey;
    }

    /**
     * 保存丧葬用品等费用
     *
     * @param data 保存物品费用
     * @return
     * @author LiCongLu
     * @date 2020-08-04 09:33
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveFuneralProductService(LoginItem loginItem, FuneralProductServiceData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        // 判断是否存在物品服务
        if (DataUtil.invalid(data.getServiceItems())) {
            return failure("必须选择丧葬用品及服务");
        }

        // 忽略字段
        ArrayListExt<Integer> ignoreList = new ArrayListExt<>();

        Integer biaokey = getBiaokey(ignoreList, data.getType());
        if (biaokey == null) {
            return failure("业务类型错误，未识别出来");
        }

        String operationNo = data.getOperationNo();

        StringBuilderExt builder = new StringBuilderExt();

        // 查询物品服务费用信息
        HashMap<Integer, BusinessServiceItem> serviceMap = businessService.getBusinessServiceMap(data.getType());
        // 开始遍历插入费用
        for (BusinessServiceData serviceData : data.getServiceItems()) {
            if (!serviceMap.containsKey(serviceData.getItemId())) {
                throw exception("存在与业务不相符的服务物品主键，保存失败");
            }

            BusinessServiceItem serviceItem = serviceMap.get(serviceData.getItemId());

            String randomId = DataUtil.getUUID();

            // 添加物品费用
            FuneralProductItem productItem = new FuneralProductItem();
            productItem.setOperationNo(operationNo)
                    .setServiceItemId(serviceData.getItemId())
                    .setNumber(serviceData.getNumber())
                    .setPrice(serviceData.getPrice())
                    .setCharge(serviceData.getNumber().multiply(serviceData.getPrice()))
                    .setRandomId(randomId)
                    .setRemark(serviceData.getRemark())
                    .setBiaokey(biaokey);
            funeralProductDao.insertFuneralProductItem(productItem, loginItem.getUserId());
            if (DataUtil.invalid(productItem.getId())) {
                throw exception("添加服务物品失败");
            }

            // 添加费用信息
            ChargeItem chargeItem = new ChargeItem();
            chargeItem.setOperationNo(operationNo)
                    .setServiceCode(30)
                    .setRandomId(randomId)
                    .setItemId(serviceData.getItemId())
                    .setNumber(productItem.getNumber())
                    .setPrice(productItem.getPrice())
                    .setCharge(productItem.getCharge())
                    .setRemark(productItem.getRemark())
                    .setProductFrom(1);
            chargeDao.insertChargeItem(chargeItem, loginItem.getUserId());
            if (DataUtil.invalid(chargeItem.getId())) {
                throw exception("添加服务物品费用失败");
            }

            builder.format("物品及服务名称【{0}】，单价【{1}】，数量【{2}】，费用【{3}】;", serviceItem.getName(), DataUtil.getPlainString(serviceData.getPrice())
                    , DataUtil.getPlainString(serviceData.getNumber()), DataUtil.getPlainString(productItem.getCharge()));
        }

        String logContent = StringUtil.format("预定丧葬用品及服务:业务编号【{0}】，逝者姓名【{1}】，{2}", deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), builder.toString());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.SangZangYongPinJiFuWu, "添加丧葬用品及服务"
                , BusinessConst.OperateType.XiuGai, logContent, operationNo);

        return success("添加完成");
    }

    /**
     * 按照主键加载丧葬用品费用接口
     *
     * @param data 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:07
     */
    public BaseResult<ChargeItem> loadFuneralProductChargeWithId(OperationNoIdData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ChargeItem chargeItem = funeralProductDao.getFuneralProductChargeById(data.getId());
        if (chargeItem == null || DataUtil.invalid(chargeItem.getProductId())) {
            return failure("费用主键错误，不存在此丧葬用品费用");
        }

        // 判断业务编号
        if (!DataUtil.equals(chargeItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与费用所属业务编号不匹配");
        }

        return success(chargeItem);
    }

    /**
     * 保存丧葬用品费用接口
     *
     * @param data 丧葬用品数据
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:15
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveFuneralProductCharge(LoginItem loginItem, FuneralProductChargeData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ChargeItem chargeItem = funeralProductDao.getFuneralProductChargeById(data.getId());
        if (chargeItem == null || DataUtil.invalid(chargeItem.getProductId())) {
            return failure("费用主键错误，不存在此丧葬用品费用");
        }

        // 判断业务编号
        if (!DataUtil.equals(chargeItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与费用所属业务编号不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(chargeItem.getAsFinished())) {
            return failure("此条项目已经缴费，不允许修改");
        }

        // 赋值新费用
        chargeItem.setNumber(data.getNumber())
                .setPrice(data.getPrice())
                .setCharge(data.getNumber().multiply(data.getPrice()))
                .setPreferentialCharge(data.getPreferentialCharge())
                .setChargeDate(data.getChargeDate())
                .setRemark(data.getRemark());

        // 得到总减免惠民金额
        BigDecimal benefitCharge = chargeItem.getPreferentialCharge().add(chargeItem.getHuiminCharge())
                .add(chargeItem.getGuaZhangLeftCharge()).add(chargeItem.getTaoCanCharge());

        // 判断应收是否出现负值
        if (benefitCharge.compareTo(chargeItem.getCharge()) > 0) {
            throw exception("优惠减免金额过大，实收金额已小于零");
        }

        // 更新费用数据
        funeralProductDao.updateFuneralProductCharge(chargeItem, loginItem.getUserId());

        // 添加日志
        String logContent = StringUtil.format("编辑收费项:业务编号【{0}】，逝者姓名【{1}】，服务项目【{2}】，价格【{3}】" +
                        "，数量【{4}】，费用【{5}】，优惠减免【{6}】，惠民减免【{7}】，备注【{8}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), chargeItem.getItemName()
                , DataUtil.getPlainString(chargeItem.getPrice()), DataUtil.getPlainString(chargeItem.getNumber())
                , DataUtil.getPlainString(chargeItem.getCharge())
                , DataUtil.getPlainString(chargeItem.getPreferentialCharge())
                , DataUtil.getPlainString(chargeItem.getHuiminCharge())
                , chargeItem.getRemark());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.FeiYongJieSuan, "编辑费用结算"
                , BusinessConst.OperateType.XiuGai, logContent, chargeItem.getOperationNo());

        return success("修改完毕");
    }

    /**
     * 按照删除丧葬用品费用接口
     *
     * @param data 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 14:17
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteFuneralProductChargeWithId(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ChargeItem chargeItem = funeralProductDao.getFuneralProductChargeById(data.getId());
        if (chargeItem == null || DataUtil.invalid(chargeItem.getProductId())) {
            return failure("费用主键错误，不存在此丧葬用品费用");
        }

        // 判断业务编号
        if (!DataUtil.equals(chargeItem.getOperationNo(), data.getOperationNo())) {
            return failure("业务编号错误，与费用所属业务编号不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(chargeItem.getAsFinished())) {
            return failure("此条项目已经缴费，不允许修改");
        }

        // 更新费用数据
        funeralProductDao.deleteFuneralProductCharge(chargeItem.getOperationNo(), chargeItem.getId(), chargeItem.getProductId());

        // 添加日志
        String logContent = StringUtil.format("删除丧葬用品:业务编号【{0}】，逝者姓名【{1}】，丧葬用品名称【{2}】，数量【{3}】，单价【{4}】，费用【{5}】"
                , deadBasicItem.getOperationNo(), deadBasicItem.getDeadName(), chargeItem.getItemName()
                , DataUtil.getPlainString(chargeItem.getPrice()), DataUtil.getPlainString(chargeItem.getNumber())
                , DataUtil.getPlainString(chargeItem.getCharge()));
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi, "删除丧葬用品"
                , BusinessConst.OperateType.XiuGai, logContent, chargeItem.getOperationNo());

        return success("删除完成");
    }
}
