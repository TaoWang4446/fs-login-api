package com.jmdz.fushan.helper;


import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.RecordImageDao;
import com.jmdz.fushan.dao.RecordListDao;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.model.entity.RecordList;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * RecordTool
 *
 * @author LiCongLu
 * @date 2020-06-12 09:57
 */
@Component("recordTool")
public class RecordTool extends BaseService {
    @Resource
    private FileHelper fileHelper;
    @Resource
    private RecordListDao recordListDao;

    @Resource
    private RecordImageDao recordImageDao;

    /**
     * 上传图片
     *
     * @param file        图片文件
     * @param userId      当前账号
     * @param operationNo 逝者编号
     * @param listCode    列表编码
     * @param imageCode   图片编码
     * @param imageName   图片名称
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:57
     */
    public void saveRecordImage(MultipartFile file, String userId, String operationNo, int listCode,
                                int imageCode, String imageName) throws ActionException {
        saveRecordImage(new MultipartFile[]{file}, userId, operationNo, listCode, imageCode, imageName);
    }

    /**
     * 上传图片
     *
     * @param files       图片文件
     * @param userId      当前账号
     * @param operationNo 逝者编号
     * @param listCode    列表编码
     * @param imageCode   图片编码
     * @param imageName   图片名称
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:57
     */
    public void saveRecordImage(MultipartFile[] files, String userId, String operationNo, int listCode, int imageCode, String imageName) throws ActionException {
        if (files == null || files.length == 0) {
            throw exception("不存在图片文件");
        }

        RecordList recordList = recordListDao.getRecordListByCode(listCode);
        if (recordList == null || DataUtil.invalid(recordList.getId())) {
            throw exception("不存在图片列表分类");
        }

        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                RecordImage recordImage = fileHelper.saveImageFile(file);
                recordImage.setListId(recordList.getId());
                recordImage.setImageName(imageName);
                recordImage.setRemark(imageName);
                recordImage.setImageCode(imageCode);
                recordImage.setOperationNo(operationNo);
                // 插入数据库
                recordImageDao.insertRecordImage(recordImage, userId);

                // 验证是否插入数据库
                if (DataUtil.invalid(recordImage.getId())) {
                    throw exception("保存图片错误");
                }
            }
        }
    }

    /**
     *
     * 获取或生成二维码
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 08:50
     */
    public RecordImage getOrCreateQRCode(String operationNo, int listCode, int imageCode, String userId, String remark) {
        RecordImage recordImage = recordImageDao.getRecordImageByOperationNo(operationNo, listCode, imageCode);
        if (recordImage == null) {
            createNewQRCode(operationNo, listCode, imageCode, userId, remark);
            recordImage = recordImageDao.getRecordImageByOperationNo(operationNo, listCode, imageCode);
        } else {
            /**判断二维码文件是否存在，如果不存在，重新生成一个*/
            boolean isFileExists = fileHelper.isFileExists(recordImage.getImagePath());
            if (!isFileExists) {
                createNewQRCode(operationNo, listCode, imageCode, userId, remark);
                recordImage = recordImageDao.getRecordImageByOperationNo(operationNo, listCode, imageCode);
            }
        }
        return recordImage;
    }

    /**
     * 生成新的二维码
     *
     * @param operationNo
     * @param listCode
     * @param imageCode
     * @param userId
     * @param remark
     */
    private void createNewQRCode(String operationNo, int listCode, int imageCode, String userId, String remark) {
        RecordImage recordImage = fileHelper.saveQRCodeImage(operationNo);
        RecordList recordList = recordListDao.getRecordListByCode(listCode);
        if (recordList != null && recordList.getId() > 0 && recordImage != null) {
            recordImage.setListId(recordList.getId());
            recordImage.setImageName(operationNo);
            recordImage.setRemark(remark);
            recordImage.setImageCode(imageCode);
            recordImage.setOperationNo(operationNo);
            recordImageDao.insertRecordImage(recordImage, userId);
        }
    }
}
