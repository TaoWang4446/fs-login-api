package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.BusinessCrematingDao;
import com.jmdz.fushan.dao.BusinessLogDao;
import com.jmdz.fushan.dao.ChargeDao;
import com.jmdz.fushan.dao.DeadDao;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.BusinessCrematingData;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfo;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfoData;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.mchange.v2.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * BusinessCrematingService
 *
 * @author LiCongLu
 * @date 2020-08-05 11:26
 */
@Service("businessCrematingService")
public class BusinessCrematingService extends BaseService {

    @Resource
    private DeadDao deadDao;

    @Resource
    private BusinessCrematingDao businessCrematingDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private BusinessLogDao businessLogDao;

    /**
     * 查询火化任务详情信息
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-06 10:30
     */
    public BaseResult loadBusinessCrematingInfoByOperationNo(OperationNoData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }
        //Todo:即便是没有 服务项目 也要 显示 所有信息
        BusinessCrematingInfo crematingInfo = businessCrematingDao.getBusinessCrematingInfoByOperationNo(data.getOperationNo());
        return success(crematingInfo);
    }

    /**
     * 保存火化任务详情信息
     *
     * @param data 业务编号
     * @param loginItem 登录信息
     * @return
     * @author WangShengtao
     * @date 2020-08-06 09:30
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveBusinessCrematingInfo(BusinessCrematingData data, LoginItem loginItem) throws ActionException {
        BusinessCrematingInfo businessCrematingInfo = businessCrematingDao.
                getBusinessCrematingInfoByOperationNo(data.getBusinessCrematingInfoData().getOperationNo());
        //判断是 新增 or 更新
        //uuid 作为唯一主键值
        String randomId = UUID.randomUUID().toString();

        if (Objects.isNull(businessCrematingInfo)){
            // 新增
            data.getBusinessCrematingInfoData().setCharge(getCharge(data));
            data.getChargeItem().setCharge(getCharge(data));//费用
            data.getBusinessCrematingInfoData().setRandomId(randomId);
            data.getChargeItem().setRandomId(randomId);//随机号
            data.getChargeItem().setOperationNo(data.getBusinessCrematingInfoData().getOperationNo());
            businessCrematingDao.insertBusinessCrematingInfo(data.getBusinessCrematingInfoData(),loginItem);
            if(DataUtil.invalid(data.getBusinessCrematingInfoData().getId())){
                throw exception("添加火化任务失败");
            }
            chargeDao.insertChargeItem(data.getChargeItem(),loginItem.getUserId());
            if(DataUtil.invalid(data.getChargeItem().getId())){
                throw exception("添加火化任务的费用失败");
            }
        }else {
            //1.根据 业务编号 && randoId 查询出 主键值

            BusinessCrematingInfo businessCremating = businessCrematingDao.getBusinessCrematingInfoByCreating(data.getBusinessCrematingInfoData());
            if(Objects.isNull(businessCremating)){
                throw exception("业务编号 或者 randomId不对，请检查！");
            }

            //2.根据主键值去更新 数据
            businessCrematingDao.updateBusinessCrematingInfo(data.getBusinessCrematingInfoData(),loginItem);
            chargeDao.updateChargeItemByOperationNoAndRandomId(data.getChargeItem(),loginItem.getUserId());
        }

        //新增 和 更新 时 uui不一致的问题
        // 后台逻辑计算 费用

        // 根据主键 进行更新
        /*int cid = data.getBusinessCrematingInfoData().getId();
        if(null == businessCrematingInfo){
            data.getBusinessCrematingInfoData().setRandomId(randomId);
            data.getChargeItem().setRandomId(randomId);
            data.getChargeItem().setOperationNo(data.getBusinessCrematingInfoData().getOperationNo());

            businessCrematingDao.insertBusinessCrematingInfo(data.getBusinessCrematingInfoData(),loginItem);
            if(DataUtil.invalid(data.getBusinessCrematingInfoData().getId())){
                throw exception("添加火化任务失败");
            }
            chargeDao.insertChargeItem(data.getChargeItem(),loginItem.getUserId());
        }else {
            data.getBusinessCrematingInfoData().setRandomId(randomId);
            data.getChargeItem().setRandomId(randomId);
            data.getChargeItem().setOperationNo(data.getBusinessCrematingInfoData().getOperationNo());
            businessCrematingDao.
            businessCrematingDao.updateBusinessCrematingInfo(data.getBusinessCrematingInfoData(),loginItem);
            chargeDao.updateChargeItemByOperationNoAndRandomId(data.getChargeItem(),loginItem.getUserId());
        }*/

        String logContent = StringUtil.format("业务洽谈任务火化:,炉型【{0}】【{1}】,遗体类别【{2}】，骨灰处理方式【{3}】，是否留炉【{4}】，火化日期【{5}】，" +
                        " 火化单价【{6}】,费用【{7},charge表费用【{8}】。"
                , data.getBusinessCrematingInfoData().getOperationNo()
                , data.getBusinessCrematingInfoData().getCrematingMachineTypeID().toString()
                , data.getBusinessCrematingInfoData().getDeadType()
                , data.getBusinessCrematingInfoData().getAshesManageMode()
                , data.getBusinessCrematingInfoData().getIsBespeak().toString()
                , data.getBusinessCrematingInfoData().getCremationTime()
                , data.getBusinessCrematingInfoData().getCrematingPrice().toString()
                , data.getChargeItem().getCharge().toString());

        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi,
                "业务洽谈火化任务详情：",
                null == data.getBusinessCrematingInfoData().getId() ? BusinessConst.OperateType.TianJia:BusinessConst.OperateType.XiuGai,
                logContent, data.getBusinessCrematingInfoData().getOperationNo());

        return success("保存成功");
    }

    private BigDecimal getCharge(BusinessCrematingData data) {
        BigDecimal charge = null;
        if(678 == data.getBusinessCrematingInfoData().getCrematingMachineTypeID()){
            if("0150c26a-de31-41fc-ab79-faf0667413ef".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(580.00);
            }
            if("1ed972a1-ab88-422c-8a4b-8530af8eeb89".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(700.00);
            }
            if("e5ebc963-2fa2-4644-a9d3-5841aac58dfa".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(700.00);
            }
            if("ee3774ca-bdd3-436b-b959-773a93b723a3".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(290.00);
            }
        }

        if(780 == data.getBusinessCrematingInfoData().getCrematingMachineTypeID()){
            if("0150c26a-de31-41fc-ab79-faf0667413ef".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(580.00);
            }
            if("1ed972a1-ab88-422c-8a4b-8530af8eeb89".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(380.00);
            }
            if("e5ebc963-2fa2-4644-a9d3-5841aac58dfa".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(380.00);
            }
            if("ee3774ca-bdd3-436b-b959-773a93b723a3".equals(data.getBusinessCrematingInfoData().getDeadType())){
                charge = new BigDecimal(290.00);
            }
        }
        return charge;
    }


    /**
     * 删除火化任务详情信息（根据业务编号）
     *
     * @param data 业务编号
     * @param loginItem 登录信息
     * @return
     * @author WangShengtao
     * @date 2020-08-06 15:30
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteBusinessCrematingRowWithOperationNo(LoginItem loginItem, OperationNoData data) throws ActionException {
        if(DataUtil.invalid(data.getOperationNo())){
            failure("请检查业务编号");
        }
        businessCrematingDao.deleteRecordCremating(data.getOperationNo());
        // 添加日志
        String logContent = StringUtil.format("删除:业务编号【{0}】对应的数据记录行", data.getOperationNo());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi,
                "删除火化任务记录" , BusinessConst.OperateType.ShanChu, logContent, data.getOperationNo());

        return success("删除完成");

    }



    private void setCharge(BusinessCrematingData data) {

    }
}
