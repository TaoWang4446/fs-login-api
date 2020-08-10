package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新增接运任务请求数据
 *
 * @author LiCongLu
 * @date 2020-08-10 13:11
 */
@ApiModel(value = "新增接运任务请求数据", description = "新增接运任务请求数据")
public class VehicleTaskData extends UserData {
    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 4)
    private String deadName;

    /**
     * 逝者性别
     */
    @ApiModelProperty(value = "逝者性别", name = "deadSex", position = 5)
    private String deadSex;

    /**
     * 逝者年龄
     */
    @ApiModelProperty(value = "逝者年龄", name = "deadAge", position = 6)
    private String deadAge;

    /**
     * 死亡原因
     */
    @ApiModelProperty(value = "死亡原因", name = "deathCause", position = 7)
    private String deathCause;

    /**
     * 联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名", name = "relationName", position = 8)
    private String relationName;

    /**
     * 联系人电话
     */
    @AnValidate(value = "联系人电话", required = true)
    @ApiModelProperty(value = "联系人电话", name = "relationPhone", position = 9)
    private String relationPhone;

    /**
     * 与逝者关系
     */
    @ApiModelProperty(value = "与逝者关系", name = "deadRelation", position = 10)
    private String deadRelation;

    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址", name = "relationAddress", position = 11)
    private String relationAddress;

    /**
     * 预约车型主键
     */
    @AnValidate(value = "预约车型主键", required = true)
    @ApiModelProperty(value = "预约车型主键", name = "vehicleTypeId", position = 12)
    private Integer vehicleTypeId;

    /**
     * 车辆用途主键
     */
    @ApiModelProperty(value = "车辆用途主键", name = "cheLiangYongTu", position = 13)
    private String cheLiangYongTu;

    /**
     * 预约时间
     */
    @AnValidate(value = "预约时间", required = true)
    @ApiModelProperty(value = "预约时间", name = "carryTime", position = 14)
    private String carryTime;

    /**
     * 接尸地点
     */
    @AnValidate(value = "接尸地点", required = true)
    @ApiModelProperty(value = "接尸地点", name = "carryPlace", position = 15)
    private String carryPlace;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 16)
    private String remark;

    public String getDeadName() {
        return deadName;
    }

    public VehicleTaskData setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public VehicleTaskData setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public VehicleTaskData setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getDeathCause() {
        return deathCause;
    }

    public VehicleTaskData setDeathCause(String deathCause) {
        this.deathCause = deathCause;
        return this;
    }

    public String getRelationName() {
        return relationName;
    }

    public VehicleTaskData setRelationName(String relationName) {
        this.relationName = relationName;
        return this;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public VehicleTaskData setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
        return this;
    }

    public String getDeadRelation() {
        return deadRelation;
    }

    public VehicleTaskData setDeadRelation(String deadRelation) {
        this.deadRelation = deadRelation;
        return this;
    }

    public String getRelationAddress() {
        return relationAddress;
    }

    public VehicleTaskData setRelationAddress(String relationAddress) {
        this.relationAddress = relationAddress;
        return this;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public VehicleTaskData setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
        return this;
    }

    public String getCheLiangYongTu() {
        return cheLiangYongTu;
    }

    public VehicleTaskData setCheLiangYongTu(String cheLiangYongTu) {
        this.cheLiangYongTu = cheLiangYongTu;
        return this;
    }

    public String getCarryTime() {
        return carryTime;
    }

    public VehicleTaskData setCarryTime(String carryTime) {
        this.carryTime = carryTime;
        return this;
    }

    public String getCarryPlace() {
        return carryPlace;
    }

    public VehicleTaskData setCarryPlace(String carryPlace) {
        this.carryPlace = carryPlace;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public VehicleTaskData setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
