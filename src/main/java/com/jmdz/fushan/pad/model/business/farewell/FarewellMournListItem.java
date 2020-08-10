package com.jmdz.fushan.pad.model.business.farewell;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 业务洽谈告别任务信息
 *
 * @author LiCongLu
 * @date 2020-08-07 08:38
 */
@ApiModel(value = "业务洽谈告别任务信息", description = "业务洽谈告别任务信息")
public class FarewellMournListItem extends BaseBean {

    /**
     * 告别主键
     */
    @ApiModelProperty(value = "告别主键", name = "id", position = 1)
    private Integer id;

    /**
     * 占用告别任务，业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "hallName", position = 3)
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
     * 告别状态值
     */
    @ApiModelProperty(value = "告别状态值", name = "farewellState", position = 7)
    private Integer farewellState;

    /**
     * 告别状态文本
     */
    @ApiModelProperty(value = "告别状态文本", name = "farewellStateText", position = 8)
    private String farewellStateText;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 9)
    private String remark;

    public Integer getId() {
        return id;
    }

    public FarewellMournListItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FarewellMournListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public FarewellMournListItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FarewellMournListItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public FarewellMournListItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public FarewellMournListItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getFarewellState() {
        return farewellState;
    }

    public FarewellMournListItem setFarewellState(Integer farewellState) {
        this.farewellState = farewellState;
        return this;
    }

    public String getFarewellStateText() {
        return farewellStateText;
    }

    public FarewellMournListItem setFarewellStateText(String farewellStateText) {
        this.farewellStateText = farewellStateText;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FarewellMournListItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
