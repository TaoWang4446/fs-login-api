package com.jmdz.fushan.pad.model.weixin;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 车辆接运随车服务物品
 *
 * @author LiCongLu
 * @date 2020-07-17 08:50
 */
public class WxRecServiceItem extends BaseBean {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 随车项目主键
     */
    private Integer itemId;

    /**
     * 随车物品名称
     */
    private String itemName;

    /**
     * 随车物品价格
     */
    private BigDecimal price;

    /**
     * 随车物品数量
     */
    private BigDecimal number;

    /**
     * 预约接运任务主键
     */
    private Integer recId;

    public Integer getId() {
        return id;
    }

    public WxRecServiceItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public WxRecServiceItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public WxRecServiceItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WxRecServiceItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public WxRecServiceItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public Integer getRecId() {
        return recId;
    }

    public WxRecServiceItem setRecId(Integer recId) {
        this.recId = recId;
        return this;
    }
}
