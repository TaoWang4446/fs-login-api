package com.jmdz.fushan.pad.model.farewell;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 告别任务费用信息
 *
 * @author LiCongLu
 * @date 2020-07-14 10:50
 */
public class FarewellChargeItem extends BaseBean {

    /**
     * 业务费用主键，f_Charge.id
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 物品服务费用父主键，f_ServiceItem.ParentId
     */
    private Integer parentId;

    /**
     * 物品服务费用主键,f_ServiceItem.id
     */
    private Integer itemId;

    /**
     * 物品服务费用名称
     */
    private String itemName;

    /**
     * 数量
     */
    private BigDecimal number;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 应收金额
     */
    private BigDecimal charge;

    /**
     * 费用时间
     */
    private String chargeDate;

    /**
     * 是否结算完成
     */
    private Integer asFinished;

    /**
     * 物品服务业务值
     */
    private Integer serviceCode;

    /**
     * 接运任务与服务关联随机值
     */
    private String randomId;

    /**
     * 是否鲜花
     */
    private Integer asFlower;

    /**
     * 是否物品
     */
    private Integer asStockProduct;

    /**
     * 是否殡仪服务
     */
    private Integer asRiteService;

    public Integer getId() {
        return id;
    }

    public FarewellChargeItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FarewellChargeItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public FarewellChargeItem setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public FarewellChargeItem setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public FarewellChargeItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public FarewellChargeItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FarewellChargeItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FarewellChargeItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public FarewellChargeItem setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public Integer getAsFinished() {
        return asFinished;
    }

    public FarewellChargeItem setAsFinished(Integer asFinished) {
        this.asFinished = asFinished;
        return this;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public FarewellChargeItem setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public FarewellChargeItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public Integer getAsFlower() {
        return asFlower;
    }

    public FarewellChargeItem setAsFlower(Integer asFlower) {
        this.asFlower = asFlower;
        return this;
    }

    public Integer getAsStockProduct() {
        return asStockProduct;
    }

    public FarewellChargeItem setAsStockProduct(Integer asStockProduct) {
        this.asStockProduct = asStockProduct;
        return this;
    }

    public Integer getAsRiteService() {
        return asRiteService;
    }

    public FarewellChargeItem setAsRiteService(Integer asRiteService) {
        this.asRiteService = asRiteService;
        return this;
    }
}
