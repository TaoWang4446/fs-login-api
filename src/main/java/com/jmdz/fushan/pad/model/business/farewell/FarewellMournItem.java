package com.jmdz.fushan.pad.model.business.farewell;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * 业务洽谈告别任务详情信息
 * 
 * @author LiCongLu
 * @date 2020-08-07 08:49
 */
@ApiModel(value = "业务洽谈告别任务详情信息", description = "业务洽谈告别任务详情信息")
public class FarewellMournItem extends BaseBean {
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
     * 告别厅主键
     */
    @ApiModelProperty(value = "告别厅主键", name = "hallId", position = 3)
    private Integer hallId;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "hallName", position = 4)
    private String hallName;

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
     * 业务随机码
     */
    @ApiModelProperty(value = "业务随机码", name = "randomId", position = 9)
    private String randomId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 10)
    private String remark;

    /**
     * 任务标记
     */
    @ApiModelProperty(value = "任务标记", name = "taskFlag", position = 11)
    private Integer taskFlag;

    /**
     * 费用主键
     */
    @ApiModelProperty(value = "费用主键", name = "chargeId", position = 12)
    private Integer chargeId;

    /**
     * 告别厅收费项目主键
     */
    @ApiModelProperty(value = "告别厅收费项目主键", name = "itemId", position = 13)
    private Integer itemId;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", name = "price", position = 14)
    private BigDecimal price;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", name = "number", position = 15)
    private BigDecimal number;

    /**
     * 费用
     */
    @ApiModelProperty(value = "费用", name = "charge", position = 16)
    private BigDecimal charge;

    /**
     * 是否结算
     */
    @ApiModelProperty(value = "是否结算", name = "asFinished", position = 17)
    private Integer asFinished;

    public Integer getId() {
        return id;
    }

    public FarewellMournItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FarewellMournItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getHallId() {
        return hallId;
    }

    public FarewellMournItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public FarewellMournItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public FarewellMournItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public FarewellMournItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getFarewellState() {
        return farewellState;
    }

    public FarewellMournItem setFarewellState(Integer farewellState) {
        this.farewellState = farewellState;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public FarewellMournItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FarewellMournItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getTaskFlag() {
        return taskFlag;
    }

    public FarewellMournItem setTaskFlag(Integer taskFlag) {
        this.taskFlag = taskFlag;
        return this;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public FarewellMournItem setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public FarewellMournItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FarewellMournItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public FarewellMournItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FarewellMournItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public Integer getAsFinished() {
        return asFinished;
    }

    public FarewellMournItem setAsFinished(Integer asFinished) {
        this.asFinished = asFinished;
        return this;
    }
}
