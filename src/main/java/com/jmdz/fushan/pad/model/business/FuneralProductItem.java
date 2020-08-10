package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 殡葬服务物品信息
 *
 * @author LiCongLu
 * @date 2020-08-04 10:52
 */
public class FuneralProductItem extends BaseBean {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 费用主键
     */
    private Integer serviceItemId;

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
     * 随机码
     */
    private String randomId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标记
     */
    private Integer biaokey;

    public Integer getId() {
        return id;
    }

    public FuneralProductItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FuneralProductItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getServiceItemId() {
        return serviceItemId;
    }

    public FuneralProductItem setServiceItemId(Integer serviceItemId) {
        this.serviceItemId = serviceItemId;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public FuneralProductItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FuneralProductItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FuneralProductItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public FuneralProductItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FuneralProductItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getBiaokey() {
        return biaokey;
    }

    public FuneralProductItem setBiaokey(Integer biaokey) {
        this.biaokey = biaokey;
        return this;
    }
}
