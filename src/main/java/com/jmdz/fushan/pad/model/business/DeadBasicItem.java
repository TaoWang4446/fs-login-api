package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 逝者基本信息
 *
 * @author LiCongLu
 * @date 2020-07-09 13:13
 */
@ApiModel(value = "逝者基本信息", description = "逝者基本信息")
public class DeadBasicItem extends BaseBean {

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 1)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 2)
    private String deadName;

    /**
     * 逝者性别
     */
    @ApiModelProperty(value = "逝者性别", name = "deadSex", position = 3)
    private String deadSex;

    /**
     * 逝者年龄
     */
    @ApiModelProperty(value = "逝者年龄", name = "deadAge", position = 4)
    private String deadAge;

    /**
     * 逝者出生日期
     */
    @ApiModelProperty(value = "逝者出生日期", name = "deadBirthday", position = 5)
    private String deadBirthday;

    /**
     * 逝者住址
     */
    @ApiModelProperty(value = "逝者住址", name = "deathAddress", position = 6)
    private String deathAddress;

    /**
     * 死亡日期
     */
    @ApiModelProperty(value = "死亡日期", name = "deathTime", position = 7)
    private String deathTime;

    /**
     * 逝者身份证号
     */
    @ApiModelProperty(value = "逝者身份证号", name = "deadCertificateNo", position = 8)
    private String deadCertificateNo;

    /**
     * 死亡原因
     */
    @ApiModelProperty(value = "死亡原因", name = "deathCause", position = 9)
    private String deathCause;

    /**
     * 身份证识别图片
     */
    @ApiModelProperty(value = "身份证识别图片", name = "imagePath", position = 10)
    private String imagePath;

    public String getOperationNo() {
        return operationNo;
    }

    public DeadBasicItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public DeadBasicItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public DeadBasicItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public DeadBasicItem setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getDeadBirthday() {
        return deadBirthday;
    }

    public DeadBasicItem setDeadBirthday(String deadBirthday) {
        this.deadBirthday = deadBirthday;
        return this;
    }

    public String getDeathAddress() {
        return deathAddress;
    }

    public DeadBasicItem setDeathAddress(String deathAddress) {
        this.deathAddress = deathAddress;
        return this;
    }

    public String getDeathTime() {
        return deathTime;
    }

    public DeadBasicItem setDeathTime(String deathTime) {
        this.deathTime = deathTime;
        return this;
    }

    public String getDeadCertificateNo() {
        return deadCertificateNo;
    }

    public DeadBasicItem setDeadCertificateNo(String deadCertificateNo) {
        this.deadCertificateNo = deadCertificateNo;
        return this;
    }

    public String getDeathCause() {
        return deathCause;
    }

    public DeadBasicItem setDeathCause(String deathCause) {
        this.deathCause = deathCause;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public DeadBasicItem setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }
}
