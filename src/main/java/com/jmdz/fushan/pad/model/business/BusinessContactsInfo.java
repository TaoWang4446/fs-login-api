package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *@ClassName BusinessDeadInfoData
 *@Description TODO
 *@Author WangShengtao
 *@Date 2020-08-04 11:12
 */
@ApiModel(value = "业务洽谈联系人展示信息", description = "业务洽谈联系人展示信息")
public class BusinessContactsInfo extends BaseBean {

    /**
     * 联系人主键
     */
    @ApiModelProperty(value = "联系人主键", name = "id", position = 1)
    private Integer id;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 亲属姓名
     */
    @ApiModelProperty(value = "亲属姓名",name = "name",position = 3)
    private String name;

    /**
     *亲属性别
     */
    @ApiModelProperty(value = "亲属性别",name = "sex",position = 4)
    private String sex;

    /**
     *亲属与逝者关系
     */
    @ApiModelProperty(value = "亲属与逝者关系",name = "dierRelation",position = 5)
    private String dierRelation;

    /**
     * 亲属联系电话
     */
    @ApiModelProperty(value = "亲属联系电话",name = "phone",position = 6)
    private String phone;

    /**
     * 亲属证件类型（主键）
     */
    @ApiModelProperty(value = "亲属证件类型代码",name = "certificateType",position = 7)
    private String certificateType;

    /**
     * 亲属证件类型(文本）
     */
    @ApiModelProperty(value = "亲属证件类型名称",name = "certificateTypeText",position = 8)
    private String certificateTypeText;

    /**
     * 亲属证件号码
     */
    @ApiModelProperty(value = "亲属证件号码",name = "certificateNO",position = 9)
    private String certificateNO;

    /**
     * 亲属家庭住址
     */
    @ApiModelProperty(value = "亲属家庭住址",name = "address",position = 10)
    private String address;

    /**
     * 丧事承办人
     */
    @ApiModelProperty(value = "丧事承办人",name = "funeralDirector",position = 11)
    private String funeralDirector;

    /**
     * 承办人性别
     */
    @ApiModelProperty(value = "承办人性别",name = "funeralDirectorSex",position = 12)
    private String funeralDirectorSex;

    /**
     * 承办人与逝者关系
     */
    @ApiModelProperty(value = "承办人与逝者关系",name = "funeralDirectorDierRelation",position = 13)
    private String funeralDirectorDierRelation;

    /**
     * 承办人联系电话
     */
    @ApiModelProperty(value = "承办人联系电话",name = "funeralDirectorPhone",position = 14)
    private String funeralDirectorPhone;

    /**
     * 承办人证件类型代码
     */
    @ApiModelProperty(value = "承办人证件类型代码",name = "funeralDirectorCertificateType",position = 15)
    private String funeralDirectorCertificateType;

    /**
     * 承办人证件类型名称
     */
    @ApiModelProperty(value = "承办人证件类型名称",name = "funeralDirectorCertificateTypeText",position = 16)
    private String funeralDirectorCertificateTypeText;

    /**
     * 承办人证件号码
     */
    @ApiModelProperty(value = "承办人证件号码",name = "funeralDirectorCertificateNO",position = 17)
    private String funeralDirectorCertificateNO;


    public String getCertificateTypeText() {
        return certificateTypeText;
    }

    public void setCertificateTypeText(String certificateTypeText) {
        this.certificateTypeText = certificateTypeText;
    }

    public String getFuneralDirectorCertificateTypeText() {
        return funeralDirectorCertificateTypeText;
    }

    public void setFuneralDirectorCertificateTypeText(String funeralDirectorCertificateTypeText) {
        this.funeralDirectorCertificateTypeText = funeralDirectorCertificateTypeText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDierRelation() {
        return dierRelation;
    }

    public void setDierRelation(String dierRelation) {
        this.dierRelation = dierRelation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNO() {
        return certificateNO;
    }

    public void setCertificateNO(String certificateNO) {
        this.certificateNO = certificateNO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFuneralDirector() {
        return funeralDirector;
    }

    public void setFuneralDirector(String funeralDirector) {
        this.funeralDirector = funeralDirector;
    }

    public String getFuneralDirectorSex() {
        return funeralDirectorSex;
    }

    public void setFuneralDirectorSex(String funeralDirectorSex) {
        this.funeralDirectorSex = funeralDirectorSex;
    }

    public String getFuneralDirectorDierRelation() {
        return funeralDirectorDierRelation;
    }

    public void setFuneralDirectorDierRelation(String funeralDirectorDierRelation) {
        this.funeralDirectorDierRelation = funeralDirectorDierRelation;
    }

    public String getFuneralDirectorPhone() {
        return funeralDirectorPhone;
    }

    public void setFuneralDirectorPhone(String funeralDirectorPhone) {
        this.funeralDirectorPhone = funeralDirectorPhone;
    }

    public String getFuneralDirectorCertificateType() {
        return funeralDirectorCertificateType;
    }

    public void setFuneralDirectorCertificateType(String funeralDirectorCertificateType) {
        this.funeralDirectorCertificateType = funeralDirectorCertificateType;
    }

    public String getFuneralDirectorCertificateNO() {
        return funeralDirectorCertificateNO;
    }

    public void setFuneralDirectorCertificateNO(String funeralDirectorCertificateNO) {
        this.funeralDirectorCertificateNO = funeralDirectorCertificateNO;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public void setOperationNo(String operationNo) {
        this.operationNo = operationNo;
    }
}
