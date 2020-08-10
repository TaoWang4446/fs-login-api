package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 亲属信息
 *
 * @author LiCongLu
 * @date 2020-07-16 09:02
 */
@ApiModel(value = "亲属信息", description = "亲属信息")
public class RelationItem extends BaseBean {
    /**
     * 亲属表主键
     */
    @ApiModelProperty(value = "亲属表主键", name = "relationId", position = 1)
    private int relationId;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 亲属姓名
     */
    @ApiModelProperty(value = "亲属姓名", name = "relationName", position = 3)
    private String relationName;

    /**
     * 亲属性别
     */
    @ApiModelProperty(value = "亲属性别", name = "relationSex", position = 4)
    private String relationSex;

    /**
     * 亲属联系电话
     */
    @ApiModelProperty(value = "亲属联系电话", name = "relationPhone", position = 5)
    private String relationPhone;

    /**
     * 亲属住址
     */
    @ApiModelProperty(value = "亲属住址", name = "relationAddress", position = 6)
    private String relationAddress;

    /**
     * 与逝者关系
     */
    @ApiModelProperty(value = "与逝者关系", name = "deadRelation", position = 7)
    private String deadRelation;

    /**
     * 亲属身份证号
     */
    @ApiModelProperty(value = "亲属身份证号", name = "relationCertificateNo", position = 8)
    private String relationCertificateNo;

    /**
     * 身份证识别图片
     */
    @ApiModelProperty(value = "身份证识别图片", name = "imagePath", position = 9)
    private String imagePath;

    public int getRelationId() {
        return relationId;
    }

    public RelationItem setRelationId(int relationId) {
        this.relationId = relationId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public RelationItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getRelationName() {
        return relationName;
    }

    public RelationItem setRelationName(String relationName) {
        this.relationName = relationName;
        return this;
    }

    public String getRelationSex() {
        return relationSex;
    }

    public RelationItem setRelationSex(String relationSex) {
        this.relationSex = relationSex;
        return this;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public RelationItem setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
        return this;
    }

    public String getRelationAddress() {
        return relationAddress;
    }

    public RelationItem setRelationAddress(String relationAddress) {
        this.relationAddress = relationAddress;
        return this;
    }

    public String getDeadRelation() {
        return deadRelation;
    }

    public RelationItem setDeadRelation(String deadRelation) {
        this.deadRelation = deadRelation;
        return this;
    }

    public String getRelationCertificateNo() {
        return relationCertificateNo;
    }

    public RelationItem setRelationCertificateNo(String relationCertificateNo) {
        this.relationCertificateNo = relationCertificateNo;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public RelationItem setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }
}
