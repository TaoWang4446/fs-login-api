package com.jmdz.fushan.pad.model.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 物品服务信息
 *
 * @author LiCongLu
 * @date 2020-06-10 09:06
 */
@ApiModel(value = "物品服务信息", description = "物品服务信息")
public class ServiceListItem extends BaseBean {

    /**
     * 服务主键
     */
    @ApiModelProperty(value = "服务主键", name = "itemId", position = 1)
    private Integer itemId;

    /**
     * 服务父类主键
     */
    @ApiModelProperty(value = "服务父类主键", name = "parentId", position = 2)
    private Integer parentId;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称", name = "itemName", position = 3)
    private String itemName;

    /**
     * 服务价格
     */
    @ApiModelProperty(value = "服务价格", name = "itemPrice", position = 4)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal itemPrice;

    /**
     * 服务数量
     */
    @ApiModelProperty(value = "服务数量", name = "itemNumber", position = 5)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal itemNumber;

    /**
     * 服务单位
     */
    @ApiModelProperty(value = "服务单位", name = "itemUnit", position = 6)
    private String itemUnit;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "sort", position = 7)
    private Integer sort;

    public Integer getItemId() {
        return itemId;
    }

    public ServiceListItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public ServiceListItem setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public ServiceListItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public ServiceListItem setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public BigDecimal getItemNumber() {
        return itemNumber;
    }

    public ServiceListItem setItemNumber(BigDecimal itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public ServiceListItem setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ServiceListItem setSort(Integer sort) {
        this.sort = sort;
        return this;
    }
}