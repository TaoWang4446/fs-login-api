package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.BusinessCrematingDao;
import com.jmdz.fushan.dao.BusinessLogDao;
import com.jmdz.fushan.dao.ChargeDao;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.BusinessCrematingData;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfo;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    private BusinessCrematingDao businessCrematingDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private BusinessLogDao businessLogDao;


    /**
     * 查询火化 任务列表 信息
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-06 10:30
     */
    public BaseResult loadBusinessCrematingListInfoByOperationNo(OperationNoData data) {
        BusinessCrematingInfo crematingInfo = businessCrematingDao.getBusinessCrematingInfoByOperationNo(data.getOperationNo());
        if(null == crematingInfo){
            return failure("请检查业务编号，未找到火化任务列表信息");
        }
        return success(crematingInfo);
    }

    /**
     * 查询火化任务详情信息
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-06 10:30
     */
    public BaseResult loadBusinessCrematingInfoByOperationNo(OperationNoData data) {
        BusinessCrematingInfo crematingInfo = businessCrematingDao.getBusinessCrematingInfoByOperationNo(data.getOperationNo());
        if(null == crematingInfo){
            return failure("请检查业务编号，未找到火化任务详情信息");
        }
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
    public BaseResult saveBusinessCrematingInfo(BusinessCrematingData data, LoginItem loginItem) {
        BusinessCrematingInfo businessCrematingInfo = businessCrematingDao.
                getBusinessCrematingInfoByOperationNo(data.getBusinessCrematingInfoData().getOperationNo());

        String randomId = UUID.randomUUID().toString();
        data.getBusinessCrematingInfoData().setRandomId(randomId);
        data.getChargeItem().setRandomId(randomId);
        data.getChargeItem().setOperationNo(data.getBusinessCrematingInfoData().getOperationNo());
        data.getChargeItem().setCharge(data.getBusinessCrematingInfoData().getCharge());
        if(null == businessCrematingInfo){
            businessCrematingDao.insertBusinessCrematingInfo(data.getBusinessCrematingInfoData(),loginItem);
            chargeDao.insertChargeItem(data.getChargeItem(),loginItem.getUserId());
        }else {
            businessCrematingDao.updateBusinessCrematingInfo(data.getBusinessCrematingInfoData(),loginItem);
            chargeDao.updateChargeItemByOperationNoAndRandomId(data.getChargeItem(),loginItem.getUserId());
        }

        String logContent = StringUtil.format("业务洽谈任务火化:,炉型【{0}】【{1}】,遗体类别【{2}】，骨灰处理方式【{3}】，是否留炉【{4}】，火化日期【{5}】，" +
                        " 火化单价【{6}】,费用【{7},charge表费用【{8}】。"
                , data.getBusinessCrematingInfoData().getOperationNo()
                , data.getBusinessCrematingInfoData().getCrematingMachineTypeID().toString()
                , data.getBusinessCrematingInfoData().getDeadType()
                , data.getBusinessCrematingInfoData().getAshesManageMode()
                , data.getBusinessCrematingInfoData().getIsBespeak().toString()
                , data.getBusinessCrematingInfoData().getCremationTime()
                , data.getBusinessCrematingInfoData().getCrematingPrice().toString()
                , data.getBusinessCrematingInfoData().getCharge().toPlainString()
                , data.getChargeItem().getCharge().toString());

        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi,
                "业务洽谈火化任务详情：",
                null == businessCrematingInfo ? BusinessConst.OperateType.TianJia:BusinessConst.OperateType.XiuGai,
                logContent, data.getBusinessCrematingInfoData().getOperationNo());

        return success("保存成功");
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
}
