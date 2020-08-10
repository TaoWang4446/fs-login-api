package com.jmdz.fushan.pad.model.weixin;

import com.jmdz.common.base.BaseBean;

/**
 * 微信预约接运信息
 *
 * @author LiCongLu
 * @date 2020-07-16 15:40
 */
public class WxRecInfoItem extends BaseBean {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 逝者姓名
     */
    private String deadName;

    /**
     * 逝者年龄
     */
    private String deadAge;

    /**
     * 逝者性别
     */
    private String deadSex;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 预约车型值
     */
    private int carTypeCode;

    /**
     * 预约车型
     */
    private String carType;

    /**
     * 接运地址
     */
    private String address;

    /**
     * 预约接运时间
     */
    private String arrivalTime;

    /**
     * 预约操作时间
     */
    private String changeDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 审核人员姓名
     */
    private String examineUserName;

    /**
     * 审核人员主键
     */
    private String examineUserId;

    /**
     * 审核状态
     */
    private Integer examineState;

    /**
     * 接运任务信息主键
     */
    private String vehicleId;

    /**
     * 微信唯一键
     */
    private String openId;

    public Integer getId() {
        return id;
    }

    public WxRecInfoItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public WxRecInfoItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public WxRecInfoItem setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public WxRecInfoItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    public WxRecInfoItem setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public WxRecInfoItem setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public int getCarTypeCode() {
        return carTypeCode;
    }

    public WxRecInfoItem setCarTypeCode(int carTypeCode) {
        this.carTypeCode = carTypeCode;
        return this;
    }

    public String getCarType() {
        return carType;
    }

    public WxRecInfoItem setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public WxRecInfoItem setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public WxRecInfoItem setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public WxRecInfoItem setChangeDate(String changeDate) {
        this.changeDate = changeDate;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WxRecInfoItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WxRecInfoItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getExamineUserName() {
        return examineUserName;
    }

    public WxRecInfoItem setExamineUserName(String examineUserName) {
        this.examineUserName = examineUserName;
        return this;
    }

    public String getExamineUserId() {
        return examineUserId;
    }

    public WxRecInfoItem setExamineUserId(String examineUserId) {
        this.examineUserId = examineUserId;
        return this;
    }

    public Integer getExamineState() {
        return examineState;
    }

    public WxRecInfoItem setExamineState(Integer examineState) {
        this.examineState = examineState;
        return this;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public WxRecInfoItem setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public WxRecInfoItem setOpenId(String openId) {
        this.openId = openId;
        return this;
    }
}
