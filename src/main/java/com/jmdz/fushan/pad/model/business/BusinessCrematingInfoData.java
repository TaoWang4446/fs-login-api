package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 *@ClassName BusinessCrematingInfoData
 *@Description TODO
 *@Author WangShengtao
 *@Date 2020-08-06 09:12
 */
@ApiModel(value = "业务洽谈火化任务详情信息", description = "业务洽谈火化任务详情信息")
public class BusinessCrematingInfoData extends UserData {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", position = 1)
    private Integer id;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    @AnValidate(name = "业务编号必填", required = true)
    private String operationNo;

    /**
     * 炉型
     */
    @ApiModelProperty(value = "炉型", name = "crematingMachineTypeID", position = 3)
    private Integer crematingMachineTypeID;

    /**
     * 遗体类别
     */
    @ApiModelProperty(value = "遗体类别", name = "deadType", position = 4)
    private String deadType;
    /**
     * 骨灰处理方式
     */
    @ApiModelProperty(value = "骨灰处理方式", name = "ashesManageMode", position = 5)
    private String ashesManageMode;


    /**
     * 是否留炉（预约）
     */
    @ApiModelProperty(value = "是否留炉（预约）", name = "isBespeak", position = 6)
    private Integer isBespeak;

    /**
     * 火化日期
     */
    @ApiModelProperty(value = "火化日期", name = "cremationTime", position = 7)
    private String cremationTime;

    /**
     * 火化单价
     */
    @ApiModelProperty(value = "火化单价", name = "crematingPrice", position = 8)
    private BigDecimal crematingPrice;


    /**
     * 火化费用
     */
    @ApiModelProperty(value = "火化费用", name = "crematingCharge", position = 9)
    private BigDecimal crematingCharge;

    /**
     * 费用
     */
    @ApiModelProperty(value = "费用", name = "charge", position = 10)
    private BigDecimal charge;

    /**
     * 随机业务号
     */
    @ApiModelProperty(value = "随机业务号", name = "randomId", position = 11)
    @AnValidate(name = "随机号必填", required = true)
    private String randomId;

    public String getRandomId() {
        return randomId;
    }

    public void setRandomId(String randomId) {
        this.randomId = randomId;
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

    public String getAshesManageMode() {
        return ashesManageMode;
    }

    public void setAshesManageMode(String ashesManageMode) {
        this.ashesManageMode = ashesManageMode;
    }

    public String getCremationTime() {
        return cremationTime;
    }

    public void setCremationTime(String cremationTime) {
        this.cremationTime = cremationTime;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
