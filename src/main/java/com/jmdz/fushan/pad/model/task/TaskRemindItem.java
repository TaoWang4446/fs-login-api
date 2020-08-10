package com.jmdz.fushan.pad.model.task;

import com.jmdz.common.base.BaseBean;

/**
 * 任务提醒任务信息
 *
 * @author LiCongLu
 * @date 2020-07-15 14:00
 */
public class TaskRemindItem extends BaseBean {
    /**
     * 提醒主键
     */
    private int id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 任务名称
     */
    private String sourceModule;

    /**
     * 任务接收部门
     */
    private String targetModule;

    /**
     * 任务说明
     */
    private String describe;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 接收时间
     */
    private String receiveTime;

    /**
     * 接收状态 1已发送 2 已接收
     */
    private String state;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 业务id
     */
    private String businessId;

    public int getId() {
        return id;
    }

    public TaskRemindItem setId(int id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public TaskRemindItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getSourceModule() {
        return sourceModule;
    }

    public TaskRemindItem setSourceModule(String sourceModule) {
        this.sourceModule = sourceModule;
        return this;
    }

    public String getTargetModule() {
        return targetModule;
    }

    public TaskRemindItem setTargetModule(String targetModule) {
        this.targetModule = targetModule;
        return this;
    }

    public String getDescribe() {
        return describe;
    }

    public TaskRemindItem setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public TaskRemindItem setSendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public TaskRemindItem setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
        return this;
    }

    public String getState() {
        return state;
    }

    public TaskRemindItem setState(String state) {
        this.state = state;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public TaskRemindItem setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getBusinessId() {
        return businessId;
    }

    public TaskRemindItem setBusinessId(String businessId) {
        this.businessId = businessId;
        return this;
    }
}
