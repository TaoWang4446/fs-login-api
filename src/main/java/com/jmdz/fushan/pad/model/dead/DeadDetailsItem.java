package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 逝者详细信息
 * @author Sunzhaoqi
 * @date 2020/7/13 9:30
 */
@ApiModel(value = "逝者详细信息", description = "逝者详细信息")
public class DeadDetailsItem extends BaseBean {
    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 1)
    private String deadName;

    /**
     * 逝者编号
     */
    @ApiModelProperty(value = "逝者编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "deadSex", position = 3)
    private String deadSex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", name = "deadSex", position = 4)
    private String deadAge;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", name = "certificateNo", position = 5)
    private String certificateNo;

    /**
     * 生前地址
     */
    @ApiModelProperty(value = "生前地址", name = "beforeDeathAddress", position = 6)
    private String beforeDeathAddress;

    /**
     * 亲属姓名
     */
    @ApiModelProperty(value = "亲属姓名", name = "relationName", position = 7)
    private String relationName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", name = "relationPhone", position = 8)
    private String relationPhone;

    public String getDeadName() {
        return deadName;
    }

    public DeadDetailsItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public DeadDetailsItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public DeadDetailsItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public DeadDetailsItem setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public DeadDetailsItem setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
        return this;
    }

    public String getBeforeDeathAddress() {
        return beforeDeathAddress;
    }

    public DeadDetailsItem setBeforeDeathAddress(String beforeDeathAddress) {
        this.beforeDeathAddress = beforeDeathAddress;
        return this;
    }

    public String getRelationName() {
        return relationName;
    }

    public DeadDetailsItem setRelationName(String relationName) {
        this.relationName = relationName;
        return this;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public DeadDetailsItem setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
        return this;
    }
}
