package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 二维码图片信息
 *
 * @author LiCongLu
 * @date 2020-07-16 08:47
 */
@ApiModel(value = "二维码图片信息", description = "二维码图片信息，业务编号")
public class QrCodeItem extends BaseBean {

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 3)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 3)
    private String deadName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "deadSex", position = 3)
    private String deadSex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", name = "deadAge", position = 3)
    private String deadAge;

    /**
     * 死亡原因
     */
    @ApiModelProperty(value = "死亡原因", name = "deathCause", position = 3)
    private String deathCause;

    /**
     * 亲属姓名
     */
    @ApiModelProperty(value = "亲属姓名", name = "relationName", position = 3)
    private String relationName;

    /**
     * 二维码图片地址
     */
    @ApiModelProperty(value = "二维码图片地址", name = "imagePath", position = 3)
    private String imagePath;

    public String getOperationNo() {
        return operationNo;
    }

    public QrCodeItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public QrCodeItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getDeadSex() {
        return deadSex;
    }

    public QrCodeItem setDeadSex(String deadSex) {
        this.deadSex = deadSex;
        return this;
    }

    public String getDeadAge() {
        return deadAge;
    }

    public QrCodeItem setDeadAge(String deadAge) {
        this.deadAge = deadAge;
        return this;
    }

    public String getDeathCause() {
        return deathCause;
    }

    public QrCodeItem setDeathCause(String deathCause) {
        this.deathCause = deathCause;
        return this;
    }

    public String getRelationName() {
        return relationName;
    }

    public QrCodeItem setRelationName(String relationName) {
        this.relationName = relationName;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public QrCodeItem setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }
}
