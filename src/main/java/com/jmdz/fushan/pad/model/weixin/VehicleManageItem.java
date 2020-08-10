package com.jmdz.fushan.pad.model.weixin;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 新增接运管理信息
 *
 * @author LiCongLu
 * @date 2020-07-17 09:51
 */
public class VehicleManageItem extends BaseBean {
    /**
     * 接运管理主键
     */
    private String vehicleId;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 联系人
     */
    private String linkmanName;

    /**
     * 联系人电话
     */
    private String linkmanPhone;

    /**
     * 接尸地点
     */
    private String carryPlace;

    /**
     * 接运时间
     */
    private String carryTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 关联字符串
     */
    private String randomId;

    /**
     * 收费标准
     */
    private BigDecimal chargeStandard;

    /**
     * 收费金额
     */
    private BigDecimal factStandard;

    /**
     * 车辆类型
     */
    private Integer bespeakVehicleType;

    /**
     *  车辆用途
     */
    private String cheLiangYongTu;

    /**
     * 接运状态
     */
    private Integer carryState;

    /**
     * 与逝者关系
     */
    private String deadRelation;

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleManageItem setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public VehicleManageItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public VehicleManageItem setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
        return this;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public VehicleManageItem setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
        return this;
    }

    public String getCarryPlace() {
        return carryPlace;
    }

    public VehicleManageItem setCarryPlace(String carryPlace) {
        this.carryPlace = carryPlace;
        return this;
    }

    public String getCarryTime() {
        return carryTime;
    }

    public VehicleManageItem setCarryTime(String carryTime) {
        this.carryTime = carryTime;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public VehicleManageItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public VehicleManageItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public BigDecimal getChargeStandard() {
        return chargeStandard;
    }

    public VehicleManageItem setChargeStandard(BigDecimal chargeStandard) {
        this.chargeStandard = chargeStandard;
        return this;
    }

    public BigDecimal getFactStandard() {
        return factStandard;
    }

    public VehicleManageItem setFactStandard(BigDecimal factStandard) {
        this.factStandard = factStandard;
        return this;
    }

    public Integer getBespeakVehicleType() {
        return bespeakVehicleType;
    }

    public VehicleManageItem setBespeakVehicleType(Integer bespeakVehicleType) {
        this.bespeakVehicleType = bespeakVehicleType;
        return this;
    }

    public String getCheLiangYongTu() {
        return cheLiangYongTu;
    }

    public VehicleManageItem setCheLiangYongTu(String cheLiangYongTu) {
        this.cheLiangYongTu = cheLiangYongTu;
        return this;
    }

    public Integer getCarryState() {
        return carryState;
    }

    public VehicleManageItem setCarryState(Integer carryState) {
        this.carryState = carryState;
        return this;
    }

    public String getDeadRelation() {
        return deadRelation;
    }

    public VehicleManageItem setDeadRelation(String deadRelation) {
        this.deadRelation = deadRelation;
        return this;
    }
}
