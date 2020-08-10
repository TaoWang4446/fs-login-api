package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseBean;
import com.jmdz.common.constant.EValidateCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 物品服务费用请求数据
 *
 * @author LiCongLu
 * @date 2020-08-04 10:40
 */
@ApiModel(value = "物品服务费用请求数据", description = "物品服务费用请求数据")
public class BusinessServiceData extends BaseBean {

    /**
     * 物品服务费用主键,f_ServiceItem.id
     */
    @AnValidate(name = "物品服务费用主键", required = true, code = EValidateCode.GREATER_ZERO)
    @ApiModelProperty(value = "物品服务费用主键", name = "itemId", position = 1)
    private Integer itemId;

    /**
     * 数量
     */
    @AnValidate(name = "数量", required = true, code = EValidateCode.GREATER_ZERO)
    @ApiModelProperty(value = "数量", name = "number", position = 2)
    private BigDecimal number;

    /**
     * 价格
     */
    @AnValidate(name = "价格", required = true, code = EValidateCode.NO_LESS_ZERO)
    @ApiModelProperty(value = "价格", name = "price", position = 3)
    private BigDecimal price;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额", name = "charge", position = 4)
    private BigDecimal charge;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 5)
    private String remark;

    public Integer getItemId() {
        return itemId;
    }

    public BusinessServiceData setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public BusinessServiceData setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BusinessServiceData setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public BusinessServiceData setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public BusinessServiceData setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}