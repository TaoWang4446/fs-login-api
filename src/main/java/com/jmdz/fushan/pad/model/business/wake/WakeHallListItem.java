package com.jmdz.fushan.pad.model.business.wake;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 业务洽谈守灵厅信息
 *
 * @author LiCongLu
 * @date 2020-08-05 11:39
 */
@ApiModel(value = "业务洽谈守灵厅信息", description = "业务洽谈守灵厅信息")
public class WakeHallListItem extends BaseBean {

    /**
     * 守灵厅主键
     */
    @ApiModelProperty(value = "守灵厅主键", name = "hallId", position = 1)
    private Integer hallId;

    /**
     * 守灵厅编号
     */
    @ApiModelProperty(value = "守灵厅编号", name = "hallNo", position = 2)
    private String hallNo;

    /**
     * 守灵厅名称
     */
    @ApiModelProperty(value = "守灵厅名称", name = "hallName", position = 3)
    private String hallName;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用,0已停用1可用", name = "asUse", position = 4)
    private Integer asUse;

    /**
     * 守灵厅收费项目主键
     */
    @ApiModelProperty(value = "守灵厅收费项目主键", name = "itemId", position = 5)
    private Integer itemId;

    /**
     * 守灵厅收费项目名称
     */
    @ApiModelProperty(value = "守灵厅收费项目名称", name = "itemName", position = 6)
    private String itemName;

    /**
     * 守灵厅收费项目价格
     */
    @ApiModelProperty(value = "守灵厅收费项目价格", name = "itemPrice", position = 7)
    private BigDecimal itemPrice;

    /**
     * 占用守灵任务主键
     */
    @ApiModelProperty(value = "占用守灵任务主键", name = "mournId", position = 8)
    private Integer mournId;

    /**
     * 占用守灵任务，业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 9)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 10)
    private String deadName;

    /**
     * 守灵状态值
     */
    @ApiModelProperty(value = "守灵状态值", name = "wakeState", position = 11)
    private Integer wakeState;

    /**
     * 守灵状态文本
     */
    @ApiModelProperty(value = "守灵状态文本", name = "wakeStateText", position = 12)
    private String wakeStateText;

    /**
     * 当天预约守灵任务信息列表
     */
    @ApiModelProperty(value = "当天预约守灵任务信息列表", name = "wakeList", position = 13)
    private ArrayList<String> wakeList;

    public Integer getHallId() {
        return hallId;
    }

    public WakeHallListItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getHallNo() {
        return hallNo;
    }

    public WakeHallListItem setHallNo(String hallNo) {
        this.hallNo = hallNo;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public WakeHallListItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public Integer getAsUse() {
        return asUse;
    }

    public WakeHallListItem setAsUse(Integer asUse) {
        this.asUse = asUse;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public WakeHallListItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public WakeHallListItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public WakeHallListItem setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public Integer getMournId() {
        return mournId;
    }

    public WakeHallListItem setMournId(Integer mournId) {
        this.mournId = mournId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WakeHallListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public WakeHallListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public Integer getWakeState() {
        return wakeState;
    }

    public WakeHallListItem setWakeState(Integer wakeState) {
        this.wakeState = wakeState;
        return this;
    }

    public String getWakeStateText() {
        return wakeStateText;
    }

    public WakeHallListItem setWakeStateText(String wakeStateText) {
        this.wakeStateText = wakeStateText;
        return this;
    }

    public ArrayList<String> getWakeList() {
        return wakeList;
    }

    public WakeHallListItem setWakeList(ArrayList<String> wakeList) {
        this.wakeList = wakeList;
        return this;
    }
}
