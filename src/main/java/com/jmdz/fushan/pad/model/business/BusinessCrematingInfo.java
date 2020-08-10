package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @ClassName BusinessDeadInfoData
 * @Description TODO
 * @Author WangShengtao
 * @Date 2020-08-06 09:12
 */
@ApiModel(value = "业务洽谈火化任务详情展示信息", description = "业务洽谈火化任务详情展示信息")
public class BusinessCrematingInfo extends BaseBean {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", position = 1)
    private Integer id;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 炉型
     */
    @ApiModelProperty(value = "炉型", name = "crematingMachineTypeID", position = 3)
    private Integer crematingMachineTypeID;

    /**
     * 炉型文本
     */
    @ApiModelProperty(value = "炉型文本", name = "crematingMachineTypeText", position = 5)
    private String crematingMachineTypeText;

    /**
     * 遗体类别
     */
    @ApiModelProperty(value = "遗体类别", name = "deadType", position = 5)
    private String deadType;

    /**
     * 遗体类别文本
     */
    @ApiModelProperty(value = "遗体类别文本", name = "deadTypeText", position = 6)
    private String deadTypeText;

    /**
     * 骨灰处理方式
     */
    @ApiModelProperty(value = "骨灰处理方式", name = "ashesManageMode", position = 7)
    private String ashesManageMode;


    /**
     * 是否留炉（预约）
     */
    @ApiModelProperty(value = "是否留炉（预约）", name = "isBespeak", position = 8)
    private Integer isBespeak;

    /**
     * 入炉火化时间
     */
    @ApiModelProperty(value = "入炉火化时间", name = "orderCremationTime", position = 9)
    private String orderCremationTime;

    /**
     * 火化单价
     */
    @ApiModelProperty(value = "火化单价", name = "crematingPrice", position = 10)
    private BigDecimal crematingPrice;


    /**
     * 火化费用
     */
    @ApiModelProperty(value = "火化费用", name = "crematingCharge", position = 11)
    private BigDecimal crematingCharge;

    /**
     * 费用
     */
    @ApiModelProperty(value = "费用", name = "charge", position = 12)
    private BigDecimal charge;


    public String getCrematingMachineTypeText() {
        return crematingMachineTypeText;
    }

    public void setCrematingMachineTypeText(String crematingMachineTypeText) {
        this.crematingMachineTypeText = crematingMachineTypeText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public void setOperationNo(String operationNo) {
        this.operationNo = operationNo;
    }


    public String getDeadType() {
        return deadType;
    }

    public void setDeadType(String deadType) {
        this.deadType = deadType;
    }

    public String getDeadTypeText() {
        return deadTypeText;
    }

    public void setDeadTypeText(String deadTypeText) {
        this.deadTypeText = deadTypeText;
    }

    public String getAshesManageMode() {
        return ashesManageMode;
    }

    public void setAshesManageMode(String ashesManageMode) {
        this.ashesManageMode = ashesManageMode;
    }

    public String getOrderCremationTime() {
        return orderCremationTime;
    }

    public void setOrderCremationTime(String orderCremationTime) {
        this.orderCremationTime = orderCremationTime;
    }

    public BigDecimal getCrematingPrice() {
        return crematingPrice;
    }

    public void setCrematingPrice(BigDecimal crematingPrice) {
        this.crematingPrice = crematingPrice;
    }

    public BigDecimal getCrematingCharge() {
        return crematingCharge;
    }

    public void setCrematingCharge(BigDecimal crematingCharge) {
        this.crematingCharge = crematingCharge;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Integer getCrematingMachineTypeID() {
        return crematingMachineTypeID;
    }

    public void setCrematingMachineTypeID(Integer crematingMachineTypeID) {
        this.crematingMachineTypeID = crematingMachineTypeID;
    }

    public Integer getIsBespeak() {
        return isBespeak;
    }

    public void setIsBespeak(Integer isBespeak) {
        this.isBespeak = isBespeak;
    }
}
