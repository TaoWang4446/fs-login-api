package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.RecordImageDao;
import com.jmdz.fushan.helper.FileHelper;
import com.jmdz.fushan.helper.RecordTool;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author LiCongLu
 * @date 2020-07-09 15:14
 */
@Service("recordService")
public class RecordService extends BaseService {

    @Resource
    private RecordImageDao recordImageDao;

    @Resource
    private FileHelper fileHelper;

    @Resource
    private RecordTool recordTool;

    /**
     * 加载图片列表接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:13
     */
    public BaseResult loadRecordImageList(OperationNoData data) {
        ArrayList<RecordImage> loadImages = recordImageDao.listRecordImageByListCode(data.getOperationNo(), Constants.RecordCode.RecordListCode);
        return success(loadImages);
    }

    /**
     * 上传图片文件接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:26
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult uploadRecordImage(LoginItem loginItem, OperationNoData data, MultipartFile file) throws ActionException {
        recordTool.saveRecordImage(file, loginItem.getUserId(), data.getOperationNo(), Constants.RecordCode.RecordListCode, Constants.RecordCode.RecordImageCode, "资料图片上传");
        return success("上传成功");
    }

    /**
     * 删除图片文件接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:29
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteRecordImage(LoginItem loginItem, OperationNoIdData data) throws ActionException {
        RecordImage item = recordImageDao.getRecordImageById(data.getId());
        if (item == null || DataUtil.invalid(item.getId())) {
            throw exception("图片文件主键错误，为找图片");
        }
        // 判断业务编号
        if (!DataUtil.equals(data.getOperationNo(), item.getOperationNo())) {
            throw exception("业务编号与图片不匹配");
        }
        recordImageDao.deleteRecordImage(data.getId());
        fileHelper.deleteImageFile(item);
        return success("删除成功");
    }
}
