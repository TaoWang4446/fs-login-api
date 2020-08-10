package com.jmdz.fushan.pad.model.farewell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 告别任务列表信息
 *
 * @author LiCongLu
 * @date 2020-07-13 15:33
 */
@ApiModel(value = "告别任务列表信息", description = "告别任务列表信息")
public class FarewellTaskListItem extends BaseBean {
    /**
     * 告别任务主键
     */
    @ApiModelProperty(value = "告别任务主键", name = "farewellId", position = 1)
    private Integer farewellId;

    /**
     * 逝者编号
     */
    @ApiModelProperty(value = "逝者编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "deadName", position = 3)
    private String deadName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "deadSex", position = 4)
    private String deadSex;

    /**
     * 入厅时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "入厅时间", name = "beginTime", position = 5)
    private String beginTime;

    /**
     * 出厅时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "出厅时间", name = "endTime", position = 6)
    private String endTime;

    /**
     * 告别时间
     */
    @ApiModelProperty(value = "告别时间", name = "farewellTime", position = 7)
    private String farewellTime;

    /**
     * 告别状态值
     */
    @ApiModelProperty(value = "告别状态值", name = "farewellState", position = 8)
    private Integer farewellState;

    /**
     * 告别状态文本
     */
    @ApiModelProperty(value = "告别状态文本", name = "farewellStateText", position = 9)
    private String farewellStateText;

    /**
     * 任务标记
     */
    @ApiModelProperty(value = "任务标记", name = "taskFlag", position = 10)
    private Integer taskFlag;

    /**
     * 任务标记文本
     */
    @ApiModelProperty(value = "任务标记文本", name = "taskFlagText", position = 11)
    private String taskFlagText;

    public Integer getFarewellId() {
        return farewellId;
    }

    public FarewellTaskListItem setFarewellId(Integer farewellId) {
        this.farewellId = farewellId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FarewellTaskListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public FarewellTaskListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public FarewellTaskListItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public FarewellTaskListItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public FarewellTaskListItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getFarewellTime() {
        return farewellTime;
    }

    public FarewellTaskListItem setFarewellTime(String farewellTime) {
        this.farewellTime = farewellTime;
        return this;
    }

    public Integer getFarewellState() {
        return farewellState;
    }

    public FarewellTaskListItem setFarewellState(Integer farewellState) {
        this.farewellState = farewellState;
        return this;
    }

    public String getFarewellStateText() {
        return farewellStateText;
    }

    public FarewellTaskListItem setFarewellStateText(String farewellStateText) {
        this.farewellStateText = farewellStateText;
        return this;
    }

    public Integer getTaskFlag() {
        return taskFlag;
    }

    public FarewellTaskListItem setTaskFlag(Integer taskFlag) {
        this.taskFlag = taskFlag;
        return this;
    }

    public String getTaskFlagText() {
        return taskFlagText;
    }

    public FarewellTaskListItem setTaskFlagText(String taskFlagText) {
        this.taskFlagText = taskFlagText;
        return this;
    }
}
