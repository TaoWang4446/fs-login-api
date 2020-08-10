package com.jmdz.fushan.pad.model.weixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 微信预约告别信息
 *
 * @author LiCongLu
 * @date 2020-07-21 10:14
 */
@ApiModel(value = "微信预约告别信息", description = "微信预约告别信息")
public class WxMournExamineItem extends BaseBean {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", position = 1)
    private Integer id;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 3)
    private String deadName;

    /**
     * 联系人主键
     */
    @JsonIgnore
    @ApiModelProperty(value = "联系人主键", name = "contactId", position = 4)
    private String contactId;

    /**
     * 联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名", name = "contactName", position = 5)
    private String contactName;

    /**
     * 联系人电话
     */
    @ApiModelProperty(value = "联系人电话", name = "contactPhone", position = 6)
    private String contactPhone;

    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间", name = "beginTime", position = 7)
    private String beginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间", name = "endTime", position = 8)
    private String endTime;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态", name = "examineState", position = 9)
    private Integer examineState;

    /**
     * 审核状态文本
     */
    @ApiModelProperty(value = "审核状态文本", name = "examineStateText", position = 10)
    private String examineStateText;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 11)
    private String remark;

    /**
     * 礼厅主键
     */
    @JsonIgnore
    @ApiModelProperty(value = "礼厅主键", name = "hallId", position = 12)
    private String hallId;

    /**
     * 礼厅名称
     */
    @ApiModelProperty(value = "礼厅名称", name = "hallName", position = 13)
    private String hallName;

    /**
     * 套餐主键
     */
    @JsonIgnore
    @ApiModelProperty(value = "套餐主键", name = "itemDetailsId", position = 14)
    private String itemDetailsId;

    /**
     * 套餐名称
     */
    @ApiModelProperty(value = "套餐名称", name = "itemDetailsName", position = 15)
    private String itemDetailsName;

    /**
     * 物品服务
     */
    @ApiModelProperty(value = "物品服务", name = "goodsServices", position = 16)
    private String goodsServices;

    public Integer getId() {
        return id;
    }

    public WxMournExamineItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WxMournExamineItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public WxMournExamineItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getContactId() {
        return contactId;
    }

    public WxMournExamineItem setContactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    public WxMournExamineItem setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public WxMournExamineItem setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public WxMournExamineItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public WxMournExamineItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getExamineState() {
        return examineState;
    }

    public WxMournExamineItem setExamineState(Integer examineState) {
        this.examineState = examineState;
        return this;
    }

    public String getExamineStateText() {
        return examineStateText;
    }

    public WxMournExamineItem setExamineStateText(String examineStateText) {
        this.examineStateText = examineStateText;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WxMournExamineItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getHallId() {
        return hallId;
    }

    public WxMournExamineItem setHallId(String hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public WxMournExamineItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getItemDetailsId() {
        return itemDetailsId;
    }

    public WxMournExamineItem setItemDetailsId(String itemDetailsId) {
        this.itemDetailsId = itemDetailsId;
        return this;
    }

    public String getItemDetailsName() {
        return itemDetailsName;
    }

    public WxMournExamineItem setItemDetailsName(String itemDetailsName) {
        this.itemDetailsName = itemDetailsName;
        return this;
    }

    public String getGoodsServices() {
        return goodsServices;
    }

    public WxMournExamineItem setGoodsServices(String goodsServices) {
        this.goodsServices = goodsServices;
        return this;
    }
}
