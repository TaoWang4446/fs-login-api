package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务洽谈逝者列表信息
 *
 * @author LiCongLu
 * @date 2020-07-28 13:24
 */
@ApiModel(value = "业务洽谈逝者列表信息", description = "业务洽谈逝者列表信息")
public class BusinessDeadListItem extends BaseBean {
    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 1)
    private String operationNo;

    /**
     * 审核状态值
     */
    @ApiModelProperty(value = "审核状态", name = "documentState", position = 2)
    private Integer documentState;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态", name = "documentStateText", position = 3)
    private String documentStateText;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 4)
    private String deadName;

    /**
     * 逝者性别
     */
    @ApiModelProperty(value = "逝者性别", name = "deadSex", position = 6)
    private String deadSex;

    /**
     * 逝者年龄
     */
    @ApiModelProperty(value = "逝者年龄", name = "deadAge", position = 7)
    private String deadAge;

    /**
     * 接运地点
     */
    @ApiModelProperty(value = "接运地点", name = "deathAddress", position = 8)
    private String deathAddress;

    /**
     * 守灵厅名称
     */
    @ApiModelProperty(value = "守灵厅名称", name = "wakeHallName", position = 9)
    private String wakeHallName;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "farewellHallName", position = 10)
    private String farewellHallName;

    /**
     * 火化炉类型名称
     */
    @ApiModelProperty(value = "火化炉类型名称", name = "machineTypeName", position = 11)
    private String machineTypeName;

    public String getOperationNo() {
        return operationNo;
    }

    public BusinessDeadListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getDocumentState() {
        return documentState;
    }

    public BusinessDeadListItem setDocumentState(Integer documentState) {
        this.documentState = documentState;
        return this;
    }

    public String getDocumentStateText() {
        return documentStateText;
    }

    public BusinessDeadListItem setDocumentStateText(String documentStateText) {
        this.documentStateText = documentStateText;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public BusinessDeadListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public BusinessDeadListItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public BusinessDeadListItem setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getDeathAddress() {
        return deathAddress;
    }

    public BusinessDeadListItem setDeathAddress(String deathAddress) {
        this.deathAddress = deathAddress;
        return this;
    }

    public String getWakeHallName() {
        return wakeHallName;
    }

    public BusinessDeadListItem setWakeHallName(String wakeHallName) {
        this.wakeHallName = wakeHallName;
        return this;
    }

    public String getFarewellHallName() {
        return farewellHallName;
    }

    public BusinessDeadListItem setFarewellHallName(String farewellHallName) {
        this.farewellHallName = farewellHallName;
        return this;
    }

    public String getMachineTypeName() {
        return machineTypeName;
    }

    public BusinessDeadListItem setMachineTypeName(String machineTypeName) {
        this.machineTypeName = machineTypeName;
        return this;
    }
}
