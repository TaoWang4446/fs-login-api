package com.jmdz.fushan.pad.model.business.wake;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 业务洽谈守灵任务详情信息
 *
 * @author LiCongLu
 * @date 2020-08-06 10:34
 */
@ApiModel(value = "业务洽谈守灵任务详情信息", description = "业务洽谈守灵任务详情信息")
public class WakeMournItem extends BaseBean {
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
     * 守灵厅主键
     */
    @ApiModelProperty(value = "守灵厅主键", name = "hallId", position = 3)
    private Integer hallId;

    /**
     * 守灵厅名称
     */
    @ApiModelProperty(value = "守灵厅名称", name = "hallName", position = 4)
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
     * 守灵状态值
     */
    @ApiModelProperty(value = "守灵状态值", name = "wakeState", position = 7)
    private Integer wakeState;

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
     * 守灵厅收费项目主键
     */
    @ApiModelProperty(value = "守灵厅收费项目主键", name = "itemId", position = 13)
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

    public WakeMournItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WakeMournItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getHallId() {
        return hallId;
    }

    public WakeMournItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public WakeMournItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public WakeMournItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public WakeMournItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getWakeState() {
        return wakeState;
    }

    public WakeMournItem setWakeState(Integer wakeState) {
        this.wakeState = wakeState;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public WakeMournItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WakeMournItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getTaskFlag() {
        return taskFlag;
    }

    public WakeMournItem setTaskFlag(Integer taskFlag) {
        this.taskFlag = taskFlag;
        return this;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public WakeMournItem setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public WakeMournItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WakeMournItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public WakeMournItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public WakeMournItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public Integer getAsFinished() {
        return asFinished;
    }

    public WakeMournItem setAsFinished(Integer asFinished) {
        this.asFinished = asFinished;
        return this;
    }
}
