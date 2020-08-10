package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.helper.RecordTool;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * BusinessBasicService
 *
 * @author LiCongLu
 * @date 2020-07-09 13:12
 */
@Service("businessDeadService")
public class BusinessDeadService extends BaseService {

    @Resource
    private BusinessDeadDao businessDeadDao;

    @Resource
    private DeadDao deadDao;

    @Resource
    private RelationDao relationDao;

    @Resource
    private RecordImageDao recordImageDao;

    @Resource
    private BusinessLogDao businessLogDao;



    /**
     * 业务洽谈逝者列表加载接口
     *
     * @param data 关键字
     * @return
     * @author LiCongLu
     * @date 2020-07-28 13:22
     */
    public BaseResult<ArrayList<BusinessDeadListItem>> loadBusinessDeadList(BusinessDeadData data) {
        ArrayList<BusinessDeadListItem> loadItems = businessDeadDao.listBusinessDeadByKeyword(data.getKeyword());
        for (BusinessDeadListItem loadItem : loadItems) {
            loadItem.setDocumentStateText(DataUtil.valid(loadItem.getDocumentState()) ? "已审核" : "未审核");
        }
        return success(loadItems);
    }

    /**
     * 业务洽谈逝者详情信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-28 13:35
     */
    public BaseResult<BusinessDeadAllItem> loadBusinessDeadAllWithOperationNo(OperationNoData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if(deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())){
            return failure("业务编号错误，未找到此逝者");
        }

        BusinessDeadAllItem loadItem = new BusinessDeadAllItem();
        loadItem.setDeadBasicItem(deadBasicItem);

        RelationItem relationItem = relationDao.getRelationItemByOperationNo(data.getOperationNo());
        AgentItem agentItem = relationDao.getAgentItemByOperationNo(data.getOperationNo());

        loadItem.setRelationItem(relationItem);
        loadItem.setAgentItem(agentItem);


        return success(loadItem);
    }

    /**
     * 业务洽谈逝者信息加载接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-03 11:15
     */
    public BaseResult<BusinessDeadInfo> loadBusinessDeadInfoByOperationNo(OperationNoData data) {

        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if(deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())){
            return failure("业务编号错误，未找到此逝者");
        }

        BusinessDeadInfo deadInfo = deadDao.getBusinessDeadInfoByOperationNo(data.getOperationNo());
        return success(deadInfo);
    }

    /**
     * 业务洽谈逝者信息更新接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-03 13:19
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveBusinessDeadInfo(LoginItem loginItem,BusinessDeadInfoData data) throws ActionException {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if(deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())){
            return failure("业务编号错误，未找到此逝者");
        }
        deadDao.updateBusinessDeadInfo(loginItem, data);
        String logContent = StringUtil.format("业务洽谈任务逝者,逝者姓名【{0}】【{1}】,逝者性别【{2}】，逝者年龄【{3}】，逝者出生日期【{4}】，死亡日期【{5}】，逝者身份证号【{6}】。"
                , data.getOperationNo(), data.getDierName(), data.getDierSex(), data.getDierAge(),
                data.getBirthDay(), data.getDeathTime(),data.getCertificateNO());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang,
                "业务洽谈", BusinessConst.OperateType.XiuGai, logContent, data.getOperationNo());

        return success("保存成功");
    }
}
