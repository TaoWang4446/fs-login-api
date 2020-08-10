package com.jmdz.fushan.pad.model.weixin;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 微信预约接运信息
 *
 * @author LiCongLu
 * @date 2020-07-16 14:07
 */
@ApiModel(value = "微信预约接运信息", description = "微信预约接运信息")
public class WxRecInfoExamineItem extends BaseBean {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", position = 1)
    private Integer id;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 2)
    private String deadName;

    /**
     * 逝者年龄
     */
    @ApiModelProperty(value = "逝者年龄", name = "deadAge", position = 3)
    private String deadAge;

    /**
     * 逝者性别
     */
    @ApiModelProperty(value = "逝者性别", name = "deadSex", position = 4)
    private String deadSex;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", name = "contactName", position = 5)
    private String contactName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", name = "contactPhone", position = 6)
    private String contactPhone;

    /**
     * 预约车型
     */
    @ApiModelProperty(value = "预约车型", name = "carType", position = 7)
    private String carType;

    /**
     * 接运地址
     */
    @ApiModelProperty(value = "接运地址", name = "address", position = 8)
    private String address;

    /**
     * 预约接运时间
     */
    @ApiModelProperty(value = "预约接运时间", name = "arrivalTime", position = 9)
    private String arrivalTime;

    /**
     * 预约操作时间
     */
    @ApiModelProperty(value = "预约操作时间", name = "changeDate", position = 10)
    private String changeDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 11)
    private String remark;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 13)
    private String operationNo;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态", name = "examineState", position = 14)
    private Integer examineState;

    /**
     * 审核状态文本
     */
    @ApiModelProperty(value = "审核状态文本", name = "examineStateText", position = 15)
    private String examineStateText;

    /**
     * 物品服务
     */
    @ApiModelProperty(value = "物品服务", name = "goodsServices", position = 16)
    private String goodsServices;

    public Integer getId() {
        return id;
    }

    public WxRecInfoExamineItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public WxRecInfoExamineItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public WxRecInfoExamineItem setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public WxRecInfoExamineItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    public WxRecInfoExamineItem setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public WxRecInfoExamineItem setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public String getCarType() {
        return carType;
    }

    public WxRecInfoExamineItem setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public WxRecInfoExamineItem setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public WxRecInfoExamineItem setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public WxRecInfoExamineItem setChangeDate(String changeDate) {
        this.changeDate = changeDate;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WxRecInfoExamineItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WxRecInfoExamineItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getExamineState() {
        return examineState;
    }

    public WxRecInfoExamineItem setExamineState(Integer examineState) {
        this.examineState = examineState;
        return this;
    }

    public String getExamineStateText() {
        return examineStateText;
    }

    public WxRecInfoExamineItem setExamineStateText(String examineStateText) {
        this.examineStateText = examineStateText;
        return this;
    }

    public String getGoodsServices() {
        return goodsServices;
    }

    public WxRecInfoExamineItem setGoodsServices(String goodsServices) {
        this.goodsServices = goodsServices;
        return this;
    }
}
