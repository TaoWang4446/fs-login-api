package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 整容服务费用请求数据
 *
 * @author LiCongLu
 * @date 2020-08-05 09:17
 */
@ApiModel(value = "整容服务费用请求数据", description = "整容服务费用请求数据")
public class FaceLiftChargeData extends UserData {

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
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间", name = "excuteTime", position = 14)
    private String excuteTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 11)
    private String remark;

    public Integer getId() {
        return id;
    }

    public FaceLiftChargeData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FaceLiftChargeData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public FaceLiftChargeData setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FaceLiftChargeData setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FaceLiftChargeData setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getExcuteTime() {
        return excuteTime;
    }

    public FaceLiftChargeData setExcuteTime(String excuteTime) {
        this.excuteTime = excuteTime;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FaceLiftChargeData setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
