package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 费用详情
 *
 * @author Sunzhaoqi
 * @date 2020/7/14 10:34
 */
public class DeadChargeAllItem extends BaseBean {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 物品id
     */
    private Integer itemId;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 物品服务费用名称
     */
    private String itemName;

    /**
     * 数量
     */
    private BigDecimal number;

    /**
     * 总价
     */
    private BigDecimal charge;

    /**
     * 优惠减免
     */
    private BigDecimal benefitCharge;

    /**
     * 实收
     */
    private BigDecimal realCharge;

    /**
     * 是否卫生棺
     */
    private Integer asWeiShengGuan;

    public Integer getId() {
        return id;
    }

    public DeadChargeAllItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public DeadChargeAllItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public DeadChargeAllItem setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public DeadChargeAllItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public DeadChargeAllItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public DeadChargeAllItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public BigDecimal getBenefitCharge() {
        return benefitCharge;
    }

    public DeadChargeAllItem setBenefitCharge(BigDecimal benefitCharge) {
        this.benefitCharge = benefitCharge;
        return this;
    }

    public BigDecimal getRealCharge() {
        return realCharge;
    }

    public DeadChargeAllItem setRealCharge(BigDecimal realCharge) {
        this.realCharge = realCharge;
        return this;
    }

    public Integer getAsWeiShengGuan() {
        return asWeiShengGuan;
    }

    public DeadChargeAllItem setAsWeiShengGuan(Integer asWeiShengGuan) {
        this.asWeiShengGuan = asWeiShengGuan;
        return this;
    }
}
