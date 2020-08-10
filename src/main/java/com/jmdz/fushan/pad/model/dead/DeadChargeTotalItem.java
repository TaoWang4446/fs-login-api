package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 费用总和
 *
 * @author Sunzhaoqi
 * @date 2020/7/14 10:29
 */
@ApiModel(value = "费用总和", description = "费用总和")
public class DeadChargeTotalItem extends BaseBean {
    /**
     * 应收
     */
    @ApiModelProperty(value = "应收", name = "charge", position = 1)
    private BigDecimal charge;

    /**
     * 惠民
     */
    @ApiModelProperty(value = "惠民", name = "benefitCharge", position = 2)
    private BigDecimal benefitCharge;

    /**
     * 实收
     */
    @ApiModelProperty(value = "实收", name = "realCharge", position = 3)
    private BigDecimal realCharge;

    public BigDecimal getCharge() {
        return charge;
    }

    public DeadChargeTotalItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public BigDecimal getBenefitCharge() {
        return benefitCharge;
    }

    public DeadChargeTotalItem setBenefitCharge(BigDecimal benefitCharge) {
        this.benefitCharge = benefitCharge;
        return this;
    }

    public BigDecimal getRealCharge() {
        return realCharge;
    }

    public DeadChargeTotalItem setRealCharge(BigDecimal realCharge) {
        this.realCharge = realCharge;
        return this;
    }
}
