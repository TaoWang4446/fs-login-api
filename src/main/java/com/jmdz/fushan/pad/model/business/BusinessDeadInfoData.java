package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *@ClassName BusinessDeadInfoData
 *@Description TODO
 *@Author WangShengtao
 *@Date 2020-08-03 16:12
 *@Version 1.0
 */
@ApiModel(value = "业务洽谈逝者信息", description = "业务洽谈逝者信息")
public class BusinessDeadInfoData extends UserData {
    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 1)
    private String operationNo;

    /**
     * 姓名
     */
    @AnValidate(value = "姓名", required = true)
    @ApiModelProperty(value = "姓名",name = "dierName",position = 1)
    private String dierName;

    /**
     *年龄
     */
    @ApiModelProperty(value = "年龄",name = "dierAge",position = 2)
    private String dierAge;

    /**
     *头像
     */
    @ApiModelProperty(value = "头像",name = "photo",position = 3)
    private String photo;

    /**
     * 性别
     */
    @AnValidate(value = "性别", required = true)
    @ApiModelProperty(value = "性别",name = "dierSex",position = 4)
    private String dierSex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期",name = "birthDay",position = 5)
    private String birthDay;

    /**
     * 死亡日期
     */
    @ApiModelProperty(value = "死亡日期",name = "deathTime",position = 6)
    private String deathTime;

    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型",name = "certificateType",position = 7)
    private String certificateType;

    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码",name = "certificateNO",position = 8)
    private String certificateNO;

    /**
     * 遗体类型
     */
    @ApiModelProperty(value = "遗体类型",name = "boneType",position = 9)
    private String boneType;

    /**
     * 遗体状态
     */
    @ApiModelProperty(value = "遗体状态",name = "remainsState",position = 10)
    private String remainsState;

    /**
     * 国籍
     */
    @ApiModelProperty(value = "国籍",name = "nationality",position = 11)
    private String nationality;

    /**
     * 死亡原因
     */
    @AnValidate(value = "死亡原因", required = true)
    @ApiModelProperty(value = "死亡原因",name = "deathCause",position = 12)
    private String deathCause;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族",name = "folk",position = 13)
    private String folk;

    /**
     * 生前住址
     */
    @ApiModelProperty(value = "生前住址",name = "beforeDeathAddress",position = 14)
    private String beforeDeathAddress;

    /**
     * 减免类型
     */
    @ApiModelProperty(value = "减免类型",name = "peopleType",position = 15)
    private String peopleType;

    /**
     *
     */
    @ApiModelProperty(value = "省",name = "dierProvince",position = 16)
    private String dierProvince;

    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称",name = "dierProvinceName",position = 17)
    private String dierProvinceName;

    /**
     * 市
     */
    @ApiModelProperty(value = "市",name = "dierCity",position = 18)
    private String dierCity;

    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称",name = "dierCityName",position = 19)
    private String dierCityName;

    /**
     * 街道办
     */
    @ApiModelProperty(value = "区、县",name = "dierDistrict",position = 20)
    private String dierDistrict;

    /**
     * 街道办名称
     */
    @ApiModelProperty(value = "区、县名称",name = "dierDistrictName",position = 21)
    private String dierDistrictName;

    /**
     * 街道办
     */
    @ApiModelProperty(value = "街道办",name = "dierStreetOffice",position = 22)
    private String dierStreetOffice;

    /**
     * 街道办名称
     */
    @ApiModelProperty(value = "街道办名称",name = "dierStreetOfficeName",position = 23)
    private String dierStreetOfficeName;

    /**
     * 行政村
     */
    @ApiModelProperty(value = "行政村",name = "dierVillage",position = 24)
    private String dierVillage;

    /**
     * 政村名称
     */
    @ApiModelProperty(value = "行政村名称",name = "dierVillageName",position = 25)
    private String dierVillageName;

    /**
     * 户籍
     */
    @ApiModelProperty(value = "户籍",name = "censusRegisterAddress",position = 26)
    private String censusRegisterAddress;

    /**
     * 现居住地
     */
    @ApiModelProperty(value = "现居住地",name = "presentLiveAddress",position = 27)
    private String presentLiveAddress;

    /**
     * 是否到馆
     */
    @ApiModelProperty(value = "是否到馆",name = "isArrived",position = 28)
    private Integer isArrived;

    /**
     * 到馆日期
     */
    @ApiModelProperty(value = "到馆日期",name = "arriveTime",position = 29)
    private String arriveTime;

    /**
     * 死亡证明出具单位
     */
    @ApiModelProperty(value = "死亡证明出具单位",name = "deathProofUnit",position = 30)
    private String deathProofUnit;

    /**
     * 殡葬证明出具单位
     */
    @ApiModelProperty(value = "殡葬证明出具单位",name = "funeralProofUnit",position = 31)
    private String funeralProofUnit;

    /**
     * 接尸地点
     */
    @ApiModelProperty(value = "接尸地点",name = "deathAddress",position = 32)
    private String deathAddress;

    /**
     * 洽谈员
     */
    @ApiModelProperty(value = "洽谈员",name = "qiaTanYuan",position = 33)
    private String qiaTanYuan;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name = "remark",position = 34)
    private String remark;

    /**
     * 送尸联系人
     */
    @ApiModelProperty(value = "送尸联系人",name = "cadaverLinkman",position = 34)
    private String cadaverLinkman;

    public String getCadaverLinkman() {
        return cadaverLinkman;
    }

    public void setCadaverLinkman(String cadaverLinkman) {
        this.cadaverLinkman = cadaverLinkman;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public void setOperationNo(String operationNo) {
        this.operationNo = operationNo;
    }

    public String getDierName() {
        return dierName;
    }

    public void setDierName(String dierName) {
        this.dierName = dierName;
    }

    public String getDierAge() {
        return dierAge;
    }

    public void setDierAge(String dierAge) {
        this.dierAge = dierAge;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDierSex() {
        return dierSex;
    }

    public void setDierSex(String dierSex) {
        this.dierSex = dierSex;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(String deathTime) {
        this.deathTime = deathTime;
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

    public String getBoneType() {
        return boneType;
    }

    public void setBoneType(String boneType) {
        this.boneType = boneType;
    }

    public String getRemainsState() {
        return remainsState;
    }

    public void setRemainsState(String remainsState) {
        this.remainsState = remainsState;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDeathCause() {
        return deathCause;
    }

    public void setDeathCause(String deathCause) {
        this.deathCause = deathCause;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getBeforeDeathAddress() {
        return beforeDeathAddress;
    }

    public void setBeforeDeathAddress(String beforeDeathAddress) {
        this.beforeDeathAddress = beforeDeathAddress;
    }

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public String getDierProvince() {
        return dierProvince;
    }

    public void setDierProvince(String dierProvince) {
        this.dierProvince = dierProvince;
    }

    public String getDierProvinceName() {
        return dierProvinceName;
    }

    public void setDierProvinceName(String dierProvinceName) {
        this.dierProvinceName = dierProvinceName;
    }

    public String getDierCity() {
        return dierCity;
    }

    public void setDierCity(String dierCity) {
        this.dierCity = dierCity;
    }

    public String getDierCityName() {
        return dierCityName;
    }

    public void setDierCityName(String dierCityName) {
        this.dierCityName = dierCityName;
    }

    public String getDierDistrict() {
        return dierDistrict;
    }

    public void setDierDistrict(String dierDistrict) {
        this.dierDistrict = dierDistrict;
    }

    public String getDierDistrictName() {
        return dierDistrictName;
    }

    public void setDierDistrictName(String dierDistrictName) {
        this.dierDistrictName = dierDistrictName;
    }

    public String getDierStreetOffice() {
        return dierStreetOffice;
    }

    public void setDierStreetOffice(String dierStreetOffice) {
        this.dierStreetOffice = dierStreetOffice;
    }

    public String getDierStreetOfficeName() {
        return dierStreetOfficeName;
    }

    public void setDierStreetOfficeName(String dierStreetOfficeName) {
        this.dierStreetOfficeName = dierStreetOfficeName;
    }

    public String getDierVillage() {
        return dierVillage;
    }

    public void setDierVillage(String dierVillage) {
        this.dierVillage = dierVillage;
    }

    public String getDierVillageName() {
        return dierVillageName;
    }

    public void setDierVillageName(String dierVillageName) {
        this.dierVillageName = dierVillageName;
    }

    public String getCensusRegisterAddress() {
        return censusRegisterAddress;
    }

    public void setCensusRegisterAddress(String censusRegisterAddress) {
        this.censusRegisterAddress = censusRegisterAddress;
    }

    public String getPresentLiveAddress() {
        return presentLiveAddress;
    }

    public void setPresentLiveAddress(String presentLiveAddress) {
        this.presentLiveAddress = presentLiveAddress;
    }

    public Integer getIsArrived() {
        return isArrived;
    }

    public void setIsArrived(Integer isArrived) {
        this.isArrived = isArrived;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDeathProofUnit() {
        return deathProofUnit;
    }

    public void setDeathProofUnit(String deathProofUnit) {
        this.deathProofUnit = deathProofUnit;
    }

    public String getFuneralProofUnit() {
        return funeralProofUnit;
    }

    public void setFuneralProofUnit(String funeralProofUnit) {
        this.funeralProofUnit = funeralProofUnit;
    }

    public String getDeathAddress() {
        return deathAddress;
    }

    public void setDeathAddress(String deathAddress) {
        this.deathAddress = deathAddress;
    }

    public String getQiaTanYuan() {
        return qiaTanYuan;
    }

    public void setQiaTanYuan(String qiaTanYuan) {
        this.qiaTanYuan = qiaTanYuan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
