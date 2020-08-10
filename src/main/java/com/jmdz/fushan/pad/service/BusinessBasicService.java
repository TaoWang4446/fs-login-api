package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.BusinessLogDao;
import com.jmdz.fushan.dao.DeadDao;
import com.jmdz.fushan.dao.RecordImageDao;
import com.jmdz.fushan.helper.RecordTool;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.DeadBasicData;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * BusinessBasicService
 *
 * @author LiCongLu
 * @date 2020-07-09 13:12
 */
@Service("businessBasicService")
public class BusinessBasicService extends BaseService {

    @Resource
    private DeadDao deadDao;

    @Resource
    private RecordImageDao recordImageDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private RecordTool recordTool;

    /**
     * 加载逝者身份信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 13:19
     */
    public BaseResult<DeadBasicItem> loadDeadBasicItem(OperationNoData data) {
        DeadBasicItem loadItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        RecordImage recordImage = recordImageDao.getRecordImageByOperationNo(data.getOperationNo(), Constants.RecordCode.RecordListCode, Constants.RecordCode.DeadBasicCode);
        if (loadItem != null && recordImage != null) {
            loadItem.setImagePath(recordImage.getImagePath());
        }
        return success(loadItem);
    }

    /**
     * 逝者详情保存接口，带有图片文件
     *
     * @param loginItem 当前账号
     * @param data      图片数据
     * @return
     * @author LiCongLu
     * @date 2020-07-09 13:59
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveDeadBasicItemFile(LoginItem loginItem, DeadBasicData data, MultipartFile file) throws ActionException {

        data.setDeadBirthday(DateUtil.format10DateStr(data.getDeadBirthday()));
        data.setDeathTime(DateUtil.format10DateStr(data.getDeathTime()));

        DeadBasicItem deadItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadItem == null) {
            throw exception("业务编号错误，未找到此逝者");
        }

        // 更新逝者信息
        BeanUtil.copy2Bean(data, deadItem);
        if (DataUtil.invalid(deadItem.getDeadName())) {
            deadItem.setDeadName("");
        }
        deadDao.updateDeadBasicItem(deadItem, loginItem);

        String logContent = StringUtil.format("接运任务逝者,逝者姓名【{0}】【{1}】,逝者性别【{2}】，逝者年龄【{3}】，逝者出生日期【{4}】，死亡日期【{5}】，逝者身份证号【{6}】。"
                , deadItem.getOperationNo(), deadItem.getDeadName(), deadItem.getDeadSex(), deadItem.getDeadAge(), deadItem.getDeadBirthday(), deadItem.getDeathTime(), deadItem.getDeadCertificateNo());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "接运任务", BusinessConst.OperateType.XiuGai, logContent, deadItem.getOperationNo());

        recordTool.saveRecordImage(file, loginItem.getUserId(), data.getOperationNo(), Constants.RecordCode.RecordListCode, Constants.RecordCode.DeadBasicCode, "识别逝者身份证图片");


        return success("保存成功");
    }
}
