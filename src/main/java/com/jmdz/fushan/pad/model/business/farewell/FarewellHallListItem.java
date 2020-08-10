package com.jmdz.fushan.pad.model.business.farewell;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 业务洽谈告别厅信息
 *
 * @author LiCongLu
 * @date 2020-08-07 08:43
 */
@ApiModel(value = "业务洽谈告别厅信息", description = "业务洽谈告别厅信息")
public class FarewellHallListItem extends BaseBean {

    /**
     * 告别厅主键
     */
    @ApiModelProperty(value = "告别厅主键", name = "hallId", position = 1)
    private Integer hallId;

    /**
     * 告别厅编号
     */
    @ApiModelProperty(value = "告别厅编号", name = "hallNo", position = 2)
    private String hallNo;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "hallName", position = 3)
    private String hallName;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用,0已停用1可用", name = "asUse", position = 4)
    private Integer asUse;

    /**
     * 告别厅收费项目主键
     */
    @ApiModelProperty(value = "告别厅收费项目主键", name = "itemId", position = 5)
    private Integer itemId;

    /**
     * 告别厅收费项目名称
     */
    @ApiModelProperty(value = "告别厅收费项目名称", name = "itemName", position = 6)
    private String itemName;

    /**
     * 告别厅收费项目价格
     */
    @ApiModelProperty(value = "告别厅收费项目价格", name = "itemPrice", position = 7)
    private BigDecimal itemPrice;

    /**
     * 占用告别任务主键
     */
    @ApiModelProperty(value = "占用告别任务主键", name = "mournId", position = 8)
    private Integer mournId;

    /**
     * 占用告别任务，业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 9)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 10)
    private String deadName;

    /**
     * 告别状态值
     */
    @ApiModelProperty(value = "告别状态值", name = "farewellState", position = 11)
    private Integer farewellState;

    /**
     * 告别状态文本
     */
    @ApiModelProperty(value = "告别状态文本", name = "farewellStateText", position = 12)
    private String farewellStateText;

    /**
     * 当天预约告别任务信息列表
     */
    @ApiModelProperty(value = "当天预约告别任务信息列表", name = "farewellList", position = 13)
    private ArrayList<String> farewellList;

    public Integer getHallId() {
        return hallId;
    }

    public FarewellHallListItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getHallNo() {
        return hallNo;
    }

    public FarewellHallListItem setHallNo(String hallNo) {
        this.hallNo = hallNo;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public FarewellHallListItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public Integer getAsUse() {
        return asUse;
    }

    public FarewellHallListItem setAsUse(Integer asUse) {
        this.asUse = asUse;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public FarewellHallListItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public FarewellHallListItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public FarewellHallListItem setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public Integer getMournId() {
        return mournId;
    }

    public FarewellHallListItem setMournId(Integer mournId) {
        this.mournId = mournId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FarewellHallListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public FarewellHallListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public Integer getFarewellState() {
        return farewellState;
    }

    public FarewellHallListItem setFarewellState(Integer farewellState) {
        this.farewellState = farewellState;
        return this;
    }

    public String getFarewellStateText() {
        return farewellStateText;
    }

    public FarewellHallListItem setFarewellStateText(String farewellStateText) {
        this.farewellStateText = farewellStateText;
        return this;
    }

    public ArrayList<String> getFarewellList() {
        return farewellList;
    }

    public FarewellHallListItem setFarewellList(ArrayList<String> farewellList) {
        this.farewellList = farewellList;
        return this;
    }
}
