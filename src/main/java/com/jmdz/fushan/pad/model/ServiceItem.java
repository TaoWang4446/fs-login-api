package com.jmdz.fushan.pad.model;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 物品服务信息
 *
 * @author LiCongLu
 * @date 2020-07-17 10:01
 */
public class ServiceItem extends BaseBean {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 父主键
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否固定
     */
    private Integer asFixed;

    /**
     * 是否删除
     */
    private Integer asDeleted;

    /**
     * 所属服务业务
     */
    private String belongServiceItem;

    /**
     * 单位
     */
    private String prickle;

    /**
     * 是否鲜花
     */
    private Integer asFlower;

    /**
     * 是否物品包
     */
    private Integer asPackage;

    /**
     * 标记
     */
    private String biaoKey;

    public Integer getId() {
        return id;
    }

    public ServiceItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public ServiceItem setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ServiceItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ServiceItem setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ServiceItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getAsFixed() {
        return asFixed;
    }

    public ServiceItem setAsFixed(Integer asFixed) {
        this.asFixed = asFixed;
        return this;
    }

    public Integer getAsDeleted() {
        return asDeleted;
    }

    public ServiceItem setAsDeleted(Integer asDeleted) {
        this.asDeleted = asDeleted;
        return this;
    }

    public String getBelongServiceItem() {
        return belongServiceItem;
    }

    public ServiceItem setBelongServiceItem(String belongServiceItem) {
        this.belongServiceItem = belongServiceItem;
        return this;
    }

    public String getPrickle() {
        return prickle;
    }

    public ServiceItem setPrickle(String prickle) {
        this.prickle = prickle;
        return this;
    }

    public Integer getAsFlower() {
        return asFlower;
    }

    public ServiceItem setAsFlower(Integer asFlower) {
        this.asFlower = asFlower;
        return this;
    }

    public Integer getAsPackage() {
        return asPackage;
    }

    public ServiceItem setAsPackage(Integer asPackage) {
        this.asPackage = asPackage;
        return this;
    }

    public String getBiaoKey() {
        return biaoKey;
    }

    public ServiceItem setBiaoKey(String biaoKey) {
        this.biaoKey = biaoKey;
        return this;
    }
}
