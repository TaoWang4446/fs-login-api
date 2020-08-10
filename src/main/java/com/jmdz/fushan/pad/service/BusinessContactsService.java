package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * BusinessContactsService
 *
 * @author Wangshengtao
 * @date 2020-08-04 11:42
 */
@Service("businessContactsService")
public class BusinessContactsService extends BaseService {

    @Resource
    private BusinessContactsDao businessContactsDao;

    @Resource
    private DeadDao deadDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private UserDao  userDao;

    /**
     * 业务洽谈联系人新增/更新接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-04 11:59
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveBusinessContactsInfo(BusinessContactsInfoData data,LoginItem loginItem) throws ActionException {

        BusinessContactsInfo businessContactsInfo = businessContactsDao.getBusinessContactsInfoByOperationNo(data.getOperationNo());

        if(null == businessContactsInfo){
            businessContactsDao.insertBusinessContactsInfo(data,loginItem);
        }else {
            businessContactsDao.updateBusinessContactsInfo(data,loginItem);
        }

        String logContent = StringUtil.format("业务洽谈任务联系人,亲属姓名【{0}】【{1}】,亲属性别【{2}】，亲属与逝者关系【{3}】，亲属电话【{4}】，亲属地址【{5}】，" +
                        " 承办人姓名【{6}】,承办人性别【{7}】，承办人与逝者关系【{8}】，承办人电话【{9}】】。"
                , data.getOperationNo(), data.getName(), data.getSex(), data.getDierRelation(),data.getPhone(), data.getAddress()
                , data.getFuneralDirector(),data.getFuneralDirectorSex(),data.getFuneralDirectorDierRelation(),data.getFuneralDirectorPhone());

        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.XinXiDengJi,
                "业务洽谈联系人信息：",
                null == businessContactsInfo ? BusinessConst.OperateType.TianJia:BusinessConst.OperateType.XiuGai,
                logContent, data.getOperationNo());

        return success("保存成功");
    }

    /**
     * 业务洽谈逝者信息加载接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-04 11:59
     */
    public BaseResult<BusinessContactsInfo> loadBusinessContactsInfoByOperationNo(OperationNoData data) {

        BusinessContactsInfo contactsInfo = businessContactsDao.getBusinessContactsInfoByOperationNo(data.getOperationNo());
        if(null == contactsInfo){
           return failure("请检查业务编号，未找到联系人信息");
        }
        return success(contactsInfo);
    }


}
