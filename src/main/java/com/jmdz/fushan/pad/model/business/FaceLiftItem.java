package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 整容项目信息
 *
 * @author LiCongLu
 * @date 2020-08-04 15:46
 */
public class FaceLiftItem extends BaseBean {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 服务项目主键
     */
    private Integer serviceItemId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 整容数量
     */
    private BigDecimal faceLiftNum;

    /**
     * 金额
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
     * 执行时间
     */
    private String excuteTime;

    public Integer getId() {
        return id;
    }

    public FaceLiftItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public FaceLiftItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getServiceItemId() {
        return serviceItemId;
    }

    public FaceLiftItem setServiceItemId(Integer serviceItemId) {
        this.serviceItemId = serviceItemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FaceLiftItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getFaceLiftNum() {
        return faceLiftNum;
    }

    public FaceLiftItem setFaceLiftNum(BigDecimal faceLiftNum) {
        this.faceLiftNum = faceLiftNum;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public FaceLiftItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public FaceLiftItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FaceLiftItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getExcuteTime() {
        return excuteTime;
    }

    public FaceLiftItem setExcuteTime(String excuteTime) {
        this.excuteTime = excuteTime;
        return this;
    }
}
