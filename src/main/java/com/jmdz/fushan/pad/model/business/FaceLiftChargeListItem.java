package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseBean;
import com.jmdz.common.constant.EValidateCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 整容项目费用列表信息
 *
 * @author LiCongLu
 * @date 2020-08-04 16:39
 */
@ApiModel(value = "整容项目费用列表信息", description = "整容项目费用列表信息")
public class FaceLiftChargeListItem extends BaseBean {

    /**
     * 费用主键，f_Charge.id
     */
    @ApiModelProperty(value = "费用主键", name = "id", position = 4)
    private Integer id;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 5)
    private String operationNo;

    /**
     * 整容项目主键
     */
    @ApiModelProperty(value = "整容项目主键", name = "itemId", position = 6)
    private Integer itemId;

    /**
     * 整容项目名称
     */
    @ApiModelProperty(value = "整容项目名称", name = "itemName", position = 7)
    private String itemName;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", name = "number", position = 8)
    private BigDecimal number;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", name = "price", position = 9)
    private BigDecimal price;

    /**
     * 应收金额-费用
     */
    @ApiModelProperty(value = "应收金额-费用", name = "charge", position = 10)
    private BigDecimal charge;

    /**
     * 确认是否完成
     */
    @ApiModelProperty(value = "确认是否完成", name = "asFaceLiftFinished", position = 11)
    private Integer asFaceLiftFinished;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 12)
    private String remark;

    /**
     * 是否结算
     */
    @ApiModelProperty(value = "是否结算", name = "asFinished", position = 13)
    private Integer asFinished;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间", name = "excuteTime", position = 14)
    private String excuteTime;

    /**
     * 整容主键，faceLift.id
     */
    @ApiModelProperty(value = "整容主键", name = "faceLiftId", position = 15)
    private Integer faceLiftId;

    public Integer getId() {
        return id;
    }

    public FaceLiftChargeListItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FaceLiftChargeListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public FaceLiftChargeListItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public FaceLiftChargeListItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public FaceLiftChargeListItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FaceLiftChargeListItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FaceLiftChargeListItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public Integer getAsFaceLiftFinished() {
        return asFaceLiftFinished;
    }

    public FaceLiftChargeListItem setAsFaceLiftFinished(Integer asFaceLiftFinished) {
        this.asFaceLiftFinished = asFaceLiftFinished;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FaceLiftChargeListItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getAsFinished() {
        return asFinished;
    }

    public FaceLiftChargeListItem setAsFinished(Integer asFinished) {
        this.asFinished = asFinished;
        return this;
    }

    public String getExcuteTime() {
        return excuteTime;
    }

    public FaceLiftChargeListItem setExcuteTime(String excuteTime) {
        this.excuteTime = excuteTime;
        return this;
    }

    public Integer getFaceLiftId() {
        return faceLiftId;
    }

    public FaceLiftChargeListItem setFaceLiftId(Integer faceLiftId) {
        this.faceLiftId = faceLiftId;
        return this;
    }
}
