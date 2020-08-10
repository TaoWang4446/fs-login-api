package com.jmdz.fushan.pad.model.farewell;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 告别出入厅遗体接运任务提醒列表信息
 *
 * @author LiCongLu
 * @date 2020-07-15 15:35
 */
@ApiModel(value = "告别出入厅遗体接运任务提醒列表信息", description = "告别出入厅遗体接运任务提醒列表信息")
public class FarewellDeadRemindListItem extends BaseBean implements Comparable<FarewellDeadRemindListItem> {

    /**
     * 提醒任务主键
     */
    @ApiModelProperty(value = "提醒任务主键", name = "remindId", position = 1)
    private Integer remindId;

    /**
     * 告别任务主键
     */
    @ApiModelProperty(value = "告别任务主键", name = "farewellId", position = 2)
    private Integer farewellId;

    /**
     * 逝者编号
     */
    @ApiModelProperty(value = "逝者编号", name = "operationNo", position = 3)
    private String operationNo;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "deadName", position = 4)
    private String deadName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "deadSex", position = 5)
    private String deadSex;

    /**
     * 告别时间
     */
    @ApiModelProperty(value = "告别时间", name = "farewellTime", position = 6)
    private String farewellTime;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "hallName", position = 7)
    private String hallName;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称", name = "taskName", position = 8)
    private String taskName;

    public Integer getRemindId() {
        return remindId;
    }

    public FarewellDeadRemindListItem setRemindId(Integer remindId) {
        this.remindId = remindId;
        return this;
    }

    public Integer getFarewellId() {
        return farewellId;
    }

    public FarewellDeadRemindListItem setFarewellId(Integer farewellId) {
        this.farewellId = farewellId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FarewellDeadRemindListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public FarewellDeadRemindListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public FarewellDeadRemindListItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getFarewellTime() {
        return farewellTime;
    }

    public FarewellDeadRemindListItem setFarewellTime(String farewellTime) {
        this.farewellTime = farewellTime;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public FarewellDeadRemindListItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getTaskName() {
        return taskName;
    }

    public FarewellDeadRemindListItem setTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    @Override
    public int compareTo(FarewellDeadRemindListItem o) {
        try {
            if (o == null) {
                return 1;
            }
            if (o.remindId.intValue() > this.remindId.intValue()) {
                return 1;
            }
            if (o.remindId.intValue() == this.remindId.intValue()) {
                return 0;
            }
            if (o.remindId.intValue() < this.remindId.intValue()) {
                return -1;
            }
        } catch (Exception ex) {
            return -1;
        }
        return 0;
    }
}
