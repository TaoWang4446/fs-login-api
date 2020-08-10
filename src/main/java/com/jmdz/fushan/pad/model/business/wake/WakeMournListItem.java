package com.jmdz.fushan.pad.model.business.wake;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 业务洽谈守灵任务信息
 *
 * @author LiCongLu
 * @date 2020-08-05 15:06
 */
@ApiModel(value = "业务洽谈守灵任务信息", description = "业务洽谈守灵任务信息")
public class WakeMournListItem extends BaseBean {

    /**
     * 守灵主键
     */
    @ApiModelProperty(value = "守灵主键", name = "id", position = 1)
    private Integer id;

    /**
     * 占用守灵任务，业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 守灵厅名称
     */
    @ApiModelProperty(value = "守灵厅名称", name = "hallName", position = 3)
    private String hallName;

    /**
     * 费用
     */
    @ApiModelProperty(value = "费用", name = "charge", position = 4)
    private BigDecimal charge;

    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间", name = "beginTime", position = 5)
    private String beginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间", name = "endTime", position = 6)
    private String endTime;

    /**
     * 守灵状态值
     */
    @ApiModelProperty(value = "守灵状态值", name = "wakeState", position = 7)
    private Integer wakeState;

    /**
     * 守灵状态文本
     */
    @ApiModelProperty(value = "守灵状态文本", name = "wakeStateText", position = 8)
    private String wakeStateText;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 9)
    private String remark;

    public Integer getId() {
        return id;
    }

    public WakeMournListItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WakeMournListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public WakeMournListItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public WakeMournListItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public WakeMournListItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public WakeMournListItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getWakeState() {
        return wakeState;
    }

    public WakeMournListItem setWakeState(Integer wakeState) {
        this.wakeState = wakeState;
        return this;
    }

    public String getWakeStateText() {
        return wakeStateText;
    }

    public WakeMournListItem setWakeStateText(String wakeStateText) {
        this.wakeStateText = wakeStateText;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WakeMournListItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
