package com.jmdz.fushan.pad.model.weixin;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

/**
 * 告别信息表
 *
 * @author LiCongLu
 * @date 2020-07-21 13:59
 */
public class WxMournItem extends BaseBean {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 联系人主键
     */
    private String contactId;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 预约开始时间
     */
    private String beginTime;

    /**
     * 预约结束时间
     */
    private String endTime;

    /**
     * 审核状态
     */
    private Integer examineState;

    /**
     * 审核状态文本
     */
    private String examineStateText;

    /**
     * 备注
     */
    private String remark;

    /**
     * 礼厅主键
     */
    private Integer hallId;

    /**
     * 类型主键，即费用主键
     */
    private Integer hallTypeId;

    /**
     * 礼厅名称
     */
    private String hallName;

    /**
     * 套餐主键
     */
    private String itemDetailsId;

    /**
     * 套餐名称
     */
    private String itemDetailsName;

    /**
     * 告别任务主键
     */
    private Integer mournId;

    public Integer getId() {
        return id;
    }

    public WxMournItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WxMournItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getContactId() {
        return contactId;
    }

    public WxMournItem setContactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    public WxMournItem setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public WxMournItem setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public WxMournItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public WxMournItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getExamineState() {
        return examineState;
    }

    public WxMournItem setExamineState(Integer examineState) {
        this.examineState = examineState;
        return this;
    }

    public String getExamineStateText() {
        return examineStateText;
    }

    public WxMournItem setExamineStateText(String examineStateText) {
        this.examineStateText = examineStateText;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WxMournItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getHallId() {
        return hallId;
    }

    public WxMournItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public Integer getHallTypeId() {
        return hallTypeId;
    }

    public WxMournItem setHallTypeId(Integer hallTypeId) {
        this.hallTypeId = hallTypeId;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public WxMournItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getItemDetailsId() {
        return itemDetailsId;
    }

    public WxMournItem setItemDetailsId(String itemDetailsId) {
        this.itemDetailsId = itemDetailsId;
        return this;
    }

    public String getItemDetailsName() {
        return itemDetailsName;
    }

    public WxMournItem setItemDetailsName(String itemDetailsName) {
        this.itemDetailsName = itemDetailsName;
        return this;
    }

    public Integer getMournId() {
        return mournId;
    }

    public WxMournItem setMournId(Integer mournId) {
        this.mournId = mournId;
        return this;
    }
}
