package com.jmdz.fushan.pad.model.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmdz.common.base.BaseBean;
import com.jmdz.fushan.pad.model.ChargeItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 接运任务列表实体
 *
 * @author LiCongLu
 * @date 2020-07-09 10:07
 */
@ApiModel(value = "接运任务列表实体", description = "接运任务列表实体")
public class VehicleTaskListItem extends BaseBean {

    /**
     * 接运管理表主键
     */
    @ApiModelProperty(value = "接运管理表主键", name = "vehicleId", position = 1)
    private String vehicleId;

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
     * 联系人
     */
    @ApiModelProperty(value = "联系人", name = "linkman", position = 4)
    private String linkman;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", name = "contactNumber", position = 5)
    private String contactNumber;

    /**
     * 预约接运时间
     */
    @ApiModelProperty(value = "预约接运时间", name = "carryTime", position = 6)
    private String carryTime;

    /**
     * 接运地址
     */
    @ApiModelProperty(value = "接运地址", name = "carryPlace", position = 7)
    private String carryPlace;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间", name = "bookInTime", position = 8)
    private String bookInTime;

    /**
     * 预约车型主键
     */
    @ApiModelProperty(value = "预约车型主键", name = "vehicleTypeId", position = 10)
    private int vehicleTypeId;

    /**
     * 预约车型
     */
    @ApiModelProperty(value = "预约车型", name = "vehicleType", position = 11)
    private String vehicleType;

    /**
     * 接运任务状态值
     */
    @JsonIgnore
    @ApiModelProperty(value = "接运任务状态值", name = "carryState", position = 12)
    private int carryState;

    /**
     * 接运任务状态文本
     */
    @ApiModelProperty(value = "接运任务状态文本", name = "carryStateText", position = 13)
    private String carryStateText;

    /**
     * 业务随机码
     */
    @ApiModelProperty(value = "接运任务随机码", name = "randomId", position = 14)
    private String randomId;

    /**
     * 是否结算
     */
    @ApiModelProperty(value = "是否结算", name = "asFinished", position = 15)
    private Integer asFinished;

    /**
     * 随车物品
     */
    @ApiModelProperty(value = "随车物品", name = "chargeItems", position = 16)
    private ArrayList<ChargeItem> chargeItems;

    /**
     * 接运车辆集合
     */
    @ApiModelProperty(value = "接运车辆集合", name = "vehicleCarItems", position = 17)
    ArrayList<VehicleCarListItem> vehicleCarItems;

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleTaskListItem setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public VehicleTaskListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public VehicleTaskListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getLinkman() {
        return linkman;
    }

    public VehicleTaskListItem setLinkman(String linkman) {
        this.linkman = linkman;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public VehicleTaskListItem setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getCarryTime() {
        return carryTime;
    }

    public VehicleTaskListItem setCarryTime(String carryTime) {
        this.carryTime = carryTime;
        return this;
    }

    public String getCarryPlace() {
        return carryPlace;
    }

    public VehicleTaskListItem setCarryPlace(String carryPlace) {
        this.carryPlace = carryPlace;
        return this;
    }

    public String getBookInTime() {
        return bookInTime;
    }

    public VehicleTaskListItem setBookInTime(String bookInTime) {
        this.bookInTime = bookInTime;
        return this;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public VehicleTaskListItem setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public VehicleTaskListItem setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public int getCarryState() {
        return carryState;
    }

    public VehicleTaskListItem setCarryState(int carryState) {
        this.carryState = carryState;
        return this;
    }

    public String getCarryStateText() {
        return carryStateText;
    }

    public VehicleTaskListItem setCarryStateText(String carryStateText) {
        this.carryStateText = carryStateText;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public VehicleTaskListItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public Integer getAsFinished() {
        return asFinished;
    }

    public VehicleTaskListItem setAsFinished(Integer asFinished) {
        this.asFinished = asFinished;
        return this;
    }

    public ArrayList<ChargeItem> getChargeItems() {
        return chargeItems;
    }

    public VehicleTaskListItem setChargeItems(ArrayList<ChargeItem> chargeItems) {
        this.chargeItems = chargeItems;
        return this;
    }

    public ArrayList<VehicleCarListItem> getVehicleCarItems() {
        return vehicleCarItems;
    }

    public VehicleTaskListItem setVehicleCarItems(ArrayList<VehicleCarListItem> vehicleCarItems) {
        this.vehicleCarItems = vehicleCarItems;
        return this;
    }
}
