package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 逝者任务列表信息
 *
 * @author LiCongLu
 * @date 2020-07-10 13:55
 */
@ApiModel(value = "逝者任务列表信息", description = "逝者任务列表信息")
public class DeadAllListItem extends BaseBean {
    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 1)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 2)
    private String deadName;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "hallName", position = 3)
    private String hallName;

    /**
     * 告别开始时间
     */
    @ApiModelProperty(value = "告别开始时间", name = "beginTime", position = 4)
    private String beginTime;

    /**
     * 火化炉类型
     */
    @ApiModelProperty(value = "火化炉类型", name = "machineType", position = 5)
    private String machineType;

    public String getOperationNo() {
        return operationNo;
    }

    public DeadAllListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public DeadAllListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public DeadAllListItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public DeadAllListItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getMachineType() {
        return machineType;
    }

    public DeadAllListItem setMachineType(String machineType) {
        this.machineType = machineType;
        return this;
    }
}
