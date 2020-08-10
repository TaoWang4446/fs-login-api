package com.jmdz.fushan.model.entity;

import com.jmdz.common.base.BaseBean;

/**
 * RecordImage
 *
 * @author LiCongLu
 * @date 2020-06-12 10:52
 */
public class RecordImage extends BaseBean {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 资料档案目录主键
     */
    private Integer listId;

    /**
     * 图片文件名称
     */
    private String imageName;

    /**
     * 图片保存路径
     */
    private String imagePath;

    /**
     * 图片长度
     */
    private Long imageLength;

    /**
     * 缩略图文件路径
     */
    private String thumbPath;

    /**
     * 图片标记
     */
    private Integer imageCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新帐号UserId
     */
    private String userId;

    /**
     * 更新时间
     */
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public RecordImage setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public RecordImage setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getListId() {
        return listId;
    }

    public RecordImage setListId(Integer listId) {
        this.listId = listId;
        return this;
    }

    public String getImageName() {
        return imageName;
    }

    public RecordImage setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public RecordImage setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public Long getImageLength() {
        return imageLength;
    }

    public RecordImage setImageLength(Long imageLength) {
        this.imageLength = imageLength;
        return this;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public RecordImage setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
        return this;
    }

    public Integer getImageCode() {
        return imageCode;
    }

    public RecordImage setImageCode(Integer imageCode) {
        this.imageCode = imageCode;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public RecordImage setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public RecordImage setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public RecordImage setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
