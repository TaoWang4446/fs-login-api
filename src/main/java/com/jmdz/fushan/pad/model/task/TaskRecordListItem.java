package com.jmdz.fushan.pad.model.task;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 任务记录信息
 *
 * @author LiCongLu
 * @date 2020-07-15 08:59
 */
public class TaskRecordListItem extends BaseBean {
    /**
     * 记录主键
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 服务项目主键
     */
    private String itemId;

    /**
     * 服务项目名称
     */
    private String itemName;

    /**
     * 服务项目父类主键
     */
    private Integer parentId;

    /**
     * 记录类型 0告别 1为守灵 3为整容 4为火化
     * 0告别1守灵2待化室3整容4火化
     */
    private Integer recordType;

    /**
     * 业务主键
     */
    private Integer mainServiceId;

    /**
     * 设备(礼厅)主键
     */
    private Integer equipmentId;

    /**
     * 数量
     */
    private BigDecimal number;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal charge;

    /**
     * 是否为主服务
     */
    private Integer asPid;

    /**
     * 变更描述
     */
    private String changeDescribe;

    /**
     * 发送状态0：未发送 1：已发送
     */
    private Integer sendState;

    /**
     * 发送时间
     */
    private String sendTime;

    public Integer getId() {
        return id;
    }

    public TaskRecordListItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public TaskRecordListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public TaskRecordListItem setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public TaskRecordListItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public TaskRecordListItem setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public TaskRecordListItem setRecordType(Integer recordType) {
        this.recordType = recordType;
        return this;
    }

    public Integer getMainServiceId() {
        return mainServiceId;
    }

    public TaskRecordListItem setMainServiceId(Integer mainServiceId) {
        this.mainServiceId = mainServiceId;
        return this;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public TaskRecordListItem setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public TaskRecordListItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TaskRecordListItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public TaskRecordListItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public Integer getAsPid() {
        return asPid;
    }

    public TaskRecordListItem setAsPid(Integer asPid) {
        this.asPid = asPid;
        return this;
    }

    public String getChangeDescribe() {
        return changeDescribe;
    }

    public TaskRecordListItem setChangeDescribe(String changeDescribe) {
        this.changeDescribe = changeDescribe;
        return this;
    }

    public Integer getSendState() {
        return sendState;
    }

    public TaskRecordListItem setSendState(Integer sendState) {
        this.sendState = sendState;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public TaskRecordListItem setSendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }
}
