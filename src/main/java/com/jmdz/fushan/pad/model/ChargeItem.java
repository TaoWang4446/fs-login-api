package com.jmdz.fushan.pad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 物品服务
 *
 * @author LiCongLu
 * @date 2020-06-10 13:42
 */
@ApiModel(value = "物品服务费用信息", description = "物品服务费用信息")
public class ChargeItem extends BaseBean {

    /**
     * 业务费用主键，f_Charge.id
     */
    @ApiModelProperty(value = "业务费用主键", name = "id", position = 1)
    private Integer id;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 物品服务费用父主键，f_ServiceItem.ParentId
     */
    @ApiModelProperty(value = "物品服务费用父主键", name = "parentId", position = 3)
    private Integer parentId;

    /**
     * 物品服务费用主键,f_ServiceItem.id
     */
    @AnValidate(name = "物品服务费用主键", required = true)
    @ApiModelProperty(value = "物品服务费用主键", name = "itemId", position = 4)
    private Integer itemId;

    /**
     * 物品服务费用名称
     */
    @ApiModelProperty(value = "物品服务费用名称", name = "itemName", position = 5)
    private String itemName;

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
     * 费用时间
     */
    @ApiModelProperty(value = "费用时间", name = "chargeDate", position = 9)
    private String chargeDate;

    /**
     * 是否结算完成
     */
    @ApiModelProperty(value = "是否结算完成", name = "asFinished", position = 10)
    private Integer asFinished;

    /**
     * 是否锁住
     */
    @ApiModelProperty(value = "是否锁住", name = "asLock", position = 11)
    private Integer asLock;

    /**
     * 物品服务业务值
     */
    @ApiModelProperty(value = "物品服务业务值", name = "serviceCode", position = 12)
    private Integer serviceCode;

    /**
     * 接运任务与服务关联随机值
     */
    @ApiModelProperty(value = "接运任务与服务关联随机值", name = "randomId", position = 13)
    private String randomId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 14)
    private String remark;

    /**
     * 是否物品服务标记
     */
    @JsonIgnore
    private Integer productFrom;

    /**
     * 优惠减免金额
     */
    @ApiModelProperty(value = "优惠减免金额", name = "preferentialCharge", position = 15)
    private BigDecimal preferentialCharge;

    /**
     * 惠民金额
     */
    @ApiModelProperty(value = "惠民金额", name = "huiminCharge", position = 16)
    private BigDecimal huiminCharge;

    /**
     * 挂账金额
     */
    @ApiModelProperty(value = "挂账金额", name = "guaZhangCharge", position = 17)
    private BigDecimal guaZhangCharge;

    /**
     * 挂账剩余金额
     */
    @ApiModelProperty(value = "挂账剩余金额", name = "guaZhangLeftCharge", position = 18)
    private BigDecimal guaZhangLeftCharge;

    /**
     * 套餐金额
     */
    @ApiModelProperty(value = "套餐金额", name = "taoCanCharge", position = 19)
    private BigDecimal taoCanCharge;

    /**
     * 总减免惠民金额
     */
    @ApiModelProperty(value = "总减免惠民金额", name = "benefitCharge", position = 20)
    private BigDecimal benefitCharge;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额", name = "realCharge", position = 21)
    private BigDecimal realCharge;

    /**
     *丧葬用品主键
     */
    @ApiModelProperty(value = "丧葬用品主键", name = "productId", position = 22)
    private Integer productId;

    public Integer getId() {
        return id;
    }

    public ChargeItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public ChargeItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public ChargeItem setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public ChargeItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public ChargeItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public ChargeItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ChargeItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public ChargeItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public ChargeItem setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public Integer getAsFinished() {
        return asFinished;
    }

    public ChargeItem setAsFinished(Integer asFinished) {
        this.asFinished = asFinished;
        return this;
    }

    public Integer getAsLock() {
        return asLock;
    }

    public ChargeItem setAsLock(Integer asLock) {
        this.asLock = asLock;
        return this;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public ChargeItem setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public ChargeItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public ChargeItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getProductFrom() {
        return productFrom;
    }

    public ChargeItem setProductFrom(Integer productFrom) {
        this.productFrom = productFrom;
        return this;
    }

    public BigDecimal getPreferentialCharge() {
        return preferentialCharge;
    }

    public ChargeItem setPreferentialCharge(BigDecimal preferentialCharge) {
        this.preferentialCharge = preferentialCharge;
        return this;
    }

    public BigDecimal getHuiminCharge() {
        return huiminCharge;
    }

    public ChargeItem setHuiminCharge(BigDecimal huiminCharge) {
        this.huiminCharge = huiminCharge;
        return this;
    }

    public BigDecimal getGuaZhangCharge() {
        return guaZhangCharge;
    }

    public ChargeItem setGuaZhangCharge(BigDecimal guaZhangCharge) {
        this.guaZhangCharge = guaZhangCharge;
        return this;
    }

    public BigDecimal getGuaZhangLeftCharge() {
        return guaZhangLeftCharge;
    }

    public ChargeItem setGuaZhangLeftCharge(BigDecimal guaZhangLeftCharge) {
        this.guaZhangLeftCharge = guaZhangLeftCharge;
        return this;
    }

    public BigDecimal getTaoCanCharge() {
        return taoCanCharge;
    }

    public ChargeItem setTaoCanCharge(BigDecimal taoCanCharge) {
        this.taoCanCharge = taoCanCharge;
        return this;
    }

    public BigDecimal getBenefitCharge() {
        return benefitCharge;
    }

    public ChargeItem setBenefitCharge(BigDecimal benefitCharge) {
        this.benefitCharge = benefitCharge;
        return this;
    }

    public BigDecimal getRealCharge() {
        return realCharge;
    }

    public ChargeItem setRealCharge(BigDecimal realCharge) {
        this.realCharge = realCharge;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public ChargeItem setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }
}