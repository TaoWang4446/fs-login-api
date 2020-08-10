package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;

/**
 * 接收数据库查询相关服务信息
 *
 * @author Sunzhaoqi
 * @date 2020/7/15 14:41
 */
public class DeadServiceAllItem extends BaseBean {

    /**
     * 接运类型
     */
    private String bespeakVehicleTypeName;

    /**
     * 冷藏设备名称
     */
    private String equipmentName;

    /**
     * 冷藏类型名称
     */
    private String equipmentTypeName;

    /**
     * 预约入藏时间
     */
    private String coldStorageInTime;

    /**
     * 预约出藏时间
     */
    private String coldStorageOutTime;

    /**
     *
     * 守灵厅名称
     */
    private String wakeHallName;

    /**
     * 守灵开始时间
     */
    private String wakeBeginTime;

    /**
     * 守灵结束时间
     */
    private String wakeEndTime;

    /**
     * 告别厅名称
     */
    private String farewellHallName;

    /**
     * 告别厅开始时间
     */
    private String farewellBeginTime;

    /**
     * 告别厅结束时间
     */
    private String farewellEndTime;

    /**
     * 火化炉类型名称
     */
    private String machineTypeName;

    /**
     * 火化开始时间
     */
    private String cremationBeginTime;

    public String getBespeakVehicleTypeName() {
        return bespeakVehicleTypeName;
    }

    public DeadServiceAllItem setBespeakVehicleTypeName(String bespeakVehicleTypeName) {
        this.bespeakVehicleTypeName = bespeakVehicleTypeName;
        return this;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public DeadServiceAllItem setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
        return this;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public DeadServiceAllItem setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
        return this;
    }

    public String getColdStorageInTime() {
        return coldStorageInTime;
    }

    public DeadServiceAllItem setColdStorageInTime(String coldStorageInTime) {
        this.coldStorageInTime = coldStorageInTime;
        return this;
    }

    public String getColdStorageOutTime() {
        return coldStorageOutTime;
    }

    public DeadServiceAllItem setColdStorageOutTime(String coldStorageOutTime) {
        this.coldStorageOutTime = coldStorageOutTime;
        return this;
    }

    public String getWakeHallName() {
        return wakeHallName;
    }

    public DeadServiceAllItem setWakeHallName(String wakeHallName) {
        this.wakeHallName = wakeHallName;
        return this;
    }

    public String getWakeBeginTime() {
        return wakeBeginTime;
    }

    public DeadServiceAllItem setWakeBeginTime(String wakeBeginTime) {
        this.wakeBeginTime = wakeBeginTime;
        return this;
    }

    public String getWakeEndTime() {
        return wakeEndTime;
    }

    public DeadServiceAllItem setWakeEndTime(String wakeEndTime) {
        this.wakeEndTime = wakeEndTime;
        return this;
    }

    public String getFarewellHallName() {
        return farewellHallName;
    }

    public DeadServiceAllItem setFarewellHallName(String farewellHallName) {
        this.farewellHallName = farewellHallName;
        return this;
    }

    public String getFarewellBeginTime() {
        return farewellBeginTime;
    }

    public DeadServiceAllItem setFarewellBeginTime(String farewellBeginTime) {
        this.farewellBeginTime = farewellBeginTime;
        return this;
    }

    public String getFarewellEndTime() {
        return farewellEndTime;
    }

    public DeadServiceAllItem setFarewellEndTime(String farewellEndTime) {
        this.farewellEndTime = farewellEndTime;
        return this;
    }

    public String getMachineTypeName() {
        return machineTypeName;
    }

    public DeadServiceAllItem setMachineTypeName(String machineTypeName) {
        this.machineTypeName = machineTypeName;
        return this;
    }

    public String getCremationBeginTime() {
        return cremationBeginTime;
    }

    public DeadServiceAllItem setCremationBeginTime(String cremationBeginTime) {
        this.cremationBeginTime = cremationBeginTime;
        return this;
    }
}
