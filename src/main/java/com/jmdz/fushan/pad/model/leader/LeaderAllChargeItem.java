package com.jmdz.fushan.pad.model.leader;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 领导加载所有信息里费用列表信息
 *
 * @author LiCongLu
 * @date 2020-07-13 09:21
 */
public class LeaderAllChargeItem extends BaseBean {

    /**
     * 父类主键
     */
    private Integer parentId;

    /**
     * 父类名称
     */
    private String parentName;

    /**
     * 应收
     */
    private BigDecimal totalCharge;

    /**
     * 惠民
     */
    private BigDecimal benefitCharge;

    /**
     * 实收
     */
    private BigDecimal realCharge;

    public Integer getParentId() {
        return parentId;
    }

    public LeaderAllChargeItem setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getParentName() {
        return parentName;
    }

    public LeaderAllChargeItem setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public LeaderAllChargeItem setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
        return this;
    }

    public BigDecimal getBenefitCharge() {
        return benefitCharge;
    }

    public LeaderAllChargeItem setBenefitCharge(BigDecimal benefitCharge) {
        this.benefitCharge = benefitCharge;
        return this;
    }

    public BigDecimal getRealCharge() {
        return realCharge;
    }

    public LeaderAllChargeItem setRealCharge(BigDecimal realCharge) {
        this.realCharge = realCharge;
        return this;
    }
}
