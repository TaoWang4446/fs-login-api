package com.jmdz.fushan.pad.model.business.wake;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 业务洽谈守灵厅任务逝者信息
 *
 * @author LiCongLu
 * @date 2020-08-05 13:31
 */
@ApiModel(value = "业务洽谈守灵厅任务逝者信息", description = "业务洽谈守灵厅任务逝者信息")
public class WakeHallDeadItem extends BaseBean {

    /**
     * 占用守灵任务主键
     */
    @ApiModelProperty(value = "占用守灵任务主键", name = "mournId", position = 1)
    private Integer mournId;

    /**
     * 守灵厅主键
     */
    @ApiModelProperty(value = "守灵厅主键", name = "hallId", position = 2)
    private Integer hallId;

    /**
     * 占用守灵任务，业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 3)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 4)
    private String deadName;

    /**
     * 逝者年龄
     */
    @ApiModelProperty(value = "逝者年龄", name = "deadAge", position = 5)
    private String deadAge;

    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间", name = "beginTime", position = 6)
    private String beginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间", name = "endTime", position = 7)
    private String endTime;

    /**
     * 守灵状态值
     */
    @ApiModelProperty(value = "守灵状态值", name = "wakeState", position = 8)
    private Integer wakeState;

    public Integer getMournId() {
        return mournId;
    }

    public WakeHallDeadItem setMournId(Integer mournId) {
        this.mournId = mournId;
        return this;
    }

    public Integer getHallId() {
        return hallId;
    }

    public WakeHallDeadItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WakeHallDeadItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public WakeHallDeadItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public WakeHallDeadItem setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public WakeHallDeadItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public WakeHallDeadItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getWakeState() {
        return wakeState;
    }

    public WakeHallDeadItem setWakeState(Integer wakeState) {
        this.wakeState = wakeState;
        return this;
    }
}
