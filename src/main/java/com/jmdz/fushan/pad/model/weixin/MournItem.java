package com.jmdz.fushan.pad.model.weixin;

import com.jmdz.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 预约告别信息
 *
 * @author LiCongLu
 * @date 2020-07-21 15:37
 */
public class MournItem extends BaseBean {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 告别厅类型主键
     */
    private Integer mournHallTypeId;

    /**
     * 告别厅主键
     */
    private Integer mournHallId;

    /**
     * 预约开始时间
     */
    private String beginTime;

    /**
     * 预约结束时间
     */
    private String endTime;

    /**
     * 告别费用
     */
    private BigDecimal charge;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务随机码
     */
    private String randomId;

    /**
     * 价格
     */
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public MournItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public MournItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getMournHallTypeId() {
        return mournHallTypeId;
    }

    public MournItem setMournHallTypeId(Integer mournHallTypeId) {
        this.mournHallTypeId = mournHallTypeId;
        return this;
    }

    public Integer getMournHallId() {
        return mournHallId;
    }

    public MournItem setMournHallId(Integer mournHallId) {
        this.mournHallId = mournHallId;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public MournItem setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public MournItem setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public MournItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public MournItem setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public MournItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MournItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
