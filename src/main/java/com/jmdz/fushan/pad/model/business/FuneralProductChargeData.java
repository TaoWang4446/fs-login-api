package com.jmdz.fushan.pad.model.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseBean;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 丧葬用品费用请求数据
 *
 * @author LiCongLu
 * @date 2020-08-04 13:12
 */
@ApiModel(value = "丧葬用品费用请求数据", description = "丧葬用品费用请求数据")
public class FuneralProductChargeData extends UserData {

    /**
     * 费用主键，f_Charge.id
     */
    @AnValidate(name = "费用主键", required = true)
    @ApiModelProperty(value = "费用主键", name = "id", position = 4)
    private Integer id;

    /**
     * 业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 5)
    private String operationNo;

    /**
     * 数量
     */
    @AnValidate(name = "数量", required = true)
    @ApiModelProperty(value = "数量", name = "number", position = 6)
    private BigDecimal number;

    /**
     * 价格
     */
    @AnValidate(name = "价格", required = true)
    @ApiModelProperty(value = "价格", name = "price", position = 7)
    private BigDecimal price;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额", name = "charge", position = 8)
    private BigDecimal charge;

    /**
     * 优惠减免金额
     */
    @ApiModelProperty(value = "优惠减免金额", name = "preferentialCharge", position = 9)
    private BigDecimal preferentialCharge;

    /**
     * 费用时间
     */
    @ApiModelProperty(value = "费用时间", name = "chargeDate", position = 10)
    private String chargeDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 11)
    private String remark;

    public Integer getId() {
        return id;
    }

    public FuneralProductChargeData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FuneralProductChargeData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public FuneralProductChargeData setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FuneralProductChargeData setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FuneralProductChargeData setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public BigDecimal getPreferentialCharge() {
        return preferentialCharge;
    }

    public FuneralProductChargeData setPreferentialCharge(BigDecimal preferentialCharge) {
        this.preferentialCharge = preferentialCharge;
        return this;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public FuneralProductChargeData setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FuneralProductChargeData setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
