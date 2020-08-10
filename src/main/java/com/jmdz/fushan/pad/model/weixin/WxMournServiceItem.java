package com.jmdz.fushan.pad.model.weixin;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 告别预约服务物品
 *
 * @author LiCongLu
 * @date 2020-07-21 13:01
 */
public class WxMournServiceItem extends BaseBean {
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
     * 预约告别任务主键
     */
    private Integer wxMournId;

    public Integer getId() {
        return id;
    }

    public WxMournServiceItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public WxMournServiceItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public WxMournServiceItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public WxMournServiceItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public WxMournServiceItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public Integer getWxMournId() {
        return wxMournId;
    }

    public WxMournServiceItem setWxMournId(Integer wxMournId) {
        this.wxMournId = wxMournId;
        return this;
    }
}
