package com.jmdz.fushan.pad.dead.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 逝者信息
 * </p>
 *
 * @author Wangshengtao
 * @since 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("f_Dead")
@ApiModel(value="Dead对象", description="逝者信息")
public class Dead implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "OperationNO", type = IdType.AUTO)
    private String OperationNO;

    @TableField("DierName")
    private String DierName;

    @TableField("DierSex")
    private String DierSex;

    @TableField("DierAge")
    private String DierAge;

    @TableField("BirthDay")
    private String BirthDay;

    private String nationality;

    @TableField("CertificateType")
    private String CertificateType;

    @TableField("CertificateNO")
    private String CertificateNO;

    @TableField("Folk")
    private String Folk;

    @TableField("CensusRegisterAddress")
    private String CensusRegisterAddress;

    @TableField("BeforeDeathAddress")
    private String BeforeDeathAddress;

    @TableField("BeforeDeathUnit")
    private String BeforeDeathUnit;

    @TableField("Business")
    private String Business;

    @TableField("DeathTime")
    private String DeathTime;

    @TableField("DeathAddress")
    private String DeathAddress;

    @TableField("DeathCause")
    private String DeathCause;

    @TableField("DeathProofUnit")
    private String DeathProofUnit;

    @TableField("FuneralProofUnit")
    private String FuneralProofUnit;

    @TableField("RemainsState")
    private String RemainsState;

    @TableField("PaymentMode")
    private String PaymentMode;

    @TableField("IsFinished")
    private Integer IsFinished;

    @TableField("IsAshes")
    private String IsAshes;

    @TableField("AshesManageMode")
    private String AshesManageMode;

    @TableField("Operator")
    private String Operator;

    @TableField("OperateTime")
    private Date OperateTime;

    @TableField("Remark")
    private String Remark;

    @TableField("Pinyin")
    private String Pinyin;

    @TableField("Clear_salesman")
    private Integer clearSalesman;

    @ApiModelProperty(value = "押金")
    @TableField("Deposit")
    private Double Deposit;

    @ApiModelProperty(value = "是否优惠")
    @TableField("IsPreferential")
    private Integer IsPreferential;

    @TableField("Hobbies")
    private String Hobbies;

    @TableField("Family")
    private String Family;

    @TableField("Position")
    private String Position;

    @TableField("WorkingTime")
    private String WorkingTime;

    @TableField("Religion")
    private String Religion;

    @TableField("CadaverDelivering")
    private String CadaverDelivering;

    @TableField("CadaverLinkman")
    private String CadaverLinkman;

    @TableField("CadaverPhone")
    private String CadaverPhone;

    @ApiModelProperty(value = "接运人员身份证号")
    @TableField("CadaverIndentityNo")
    private String CadaverIndentityNo;

    @TableField("Photo")
    private String Photo;

    @TableField("DeathProof")
    private Integer DeathProof;

    @TableField("IsPrint")
    private Integer IsPrint;

    @TableField("LoadTime")
    private String LoadTime;

    @TableField("DeadType")
    private String DeadType;

    @TableField("IsExternalBody")
    private Integer IsExternalBody;

    @ApiModelProperty(value = "是否自送逝者")
    @TableField("IsWilling")
    private Integer IsWilling;

    @ApiModelProperty(value = "自送车牌号")
    @TableField("PlateNumber")
    private String PlateNumber;

    @ApiModelProperty(value = "到馆时间 yyyy-MM-dd HH:mm")
    @TableField("ArriveTime")
    private String ArriveTime;

    @ApiModelProperty(value = "是否到馆 1到达 0未到")
    @TableField("IsArrived")
    private Integer IsArrived;

    @TableField("DeathProofNo")
    private String DeathProofNo;

    @TableField("CrematingProofNo")
    private String CrematingProofNo;

    @TableField("DierProvince")
    private String DierProvince;

    @TableField("DierProvinceName")
    private String DierProvinceName;

    @TableField("DierCity")
    private String DierCity;

    @TableField("DierCityName")
    private String DierCityName;

    @TableField("DierDistrict")
    private String DierDistrict;

    @TableField("DierDistrictName")
    private String DierDistrictName;

    @ApiModelProperty(value = "逝者的乡镇")
    @TableField("DierTown")
    private String DierTown;

    @TableField("DierStreetOffice")
    private String DierStreetOffice;

    @TableField("DierStreetOfficeName")
    private String DierStreetOfficeName;

    @TableField("DierVillage")
    private String DierVillage;

    @TableField("DierVillageName")
    private String DierVillageName;

    @TableField("AreaCode")
    private String AreaCode;

    @TableField("AreaName")
    private String AreaName;

    @TableField("CrematingPrintNumber")
    private Integer CrematingPrintNumber;

    @ApiModelProperty(value = "是否是逝者 1逝者 0零售物品购买人员")
    @TableField("IsDead")
    private Integer IsDead;

    @TableField("BoneType")
    private String BoneType;

    @TableField("IsSinotrans")
    private Integer IsSinotrans;

    @TableField("SinotransDate")
    private String SinotransDate;

    @TableField("IsViewCremation")
    private String IsViewCremation;

    @TableField("CurrentPosition")
    private String CurrentPosition;

    @ApiModelProperty(value = "人员类别，数据字典值")
    @TableField("PeopleType")
    private String PeopleType;

    @TableField("IsUploadPhoto")
    private Integer IsUploadPhoto;

    @TableField("ServiceGuider")
    private String ServiceGuider;

    @TableField("IsChargeReduce")
    private Integer IsChargeReduce;

    @TableField("AddUser")
    private String AddUser;

    @TableField("AddTime")
    private Date AddTime;

    @TableField("DocumentState")
    private Integer DocumentState;

    @TableField("ColdID")
    private Integer ColdID;

    @TableField("ColdNo")
    private String ColdNo;

    @TableField("ColdStatus")
    private String ColdStatus;

    @TableField("ColdBeginTime")
    private String ColdBeginTime;

    @TableField("ColdEndTime")
    private String ColdEndTime;

    @TableField("ColdTypeID")
    private Integer ColdTypeID;

    @TableField("ColdTypeName")
    private String ColdTypeName;

    @TableField("ChargePay")
    private Double ChargePay;

    @TableField("ChargeReceivable")
    private Double ChargeReceivable;

    @TableField("ChargeReal")
    private Double ChargeReal;

    @TableField("ChargeDate")
    private String ChargeDate;

    @TableField("MournID")
    private Integer MournID;

    @TableField("MournName")
    private String MournName;

    @TableField("MournStatus")
    private String MournStatus;

    @TableField("MournBeginTime")
    private String MournBeginTime;

    @TableField("MournEndTime")
    private String MournEndTime;

    @TableField("MournTypeID")
    private Integer MournTypeID;

    @TableField("MournTypeName")
    private String MournTypeName;

    @TableField("FarewellID")
    private Integer FarewellID;

    @TableField("FarewellName")
    private String FarewellName;

    @TableField("FarewellStatus")
    private String FarewellStatus;

    @TableField("FarewellBeginTime")
    private String FarewellBeginTime;

    @TableField("FarewellEndTime")
    private String FarewellEndTime;

    @TableField("FarewellTypeID")
    private Integer FarewellTypeID;

    @TableField("FarewellTypeName")
    private String FarewellTypeName;

    @TableField("CrematingID")
    private Integer CrematingID;

    @TableField("CrematingNo")
    private String CrematingNo;

    @TableField("CrematingTypeID")
    private Integer CrematingTypeID;

    @TableField("CrematingTypeName")
    private String CrematingTypeName;

    @TableField("CrematingDate")
    private String CrematingDate;

    @ApiModelProperty(value = "购墓合同号")
    @TableField("GouMuHeTongHao")
    private String GouMuHeTongHao;

    @ApiModelProperty(value = "套餐Id")
    @TableField("TaocanId")
    private String TaocanId;

    @ApiModelProperty(value = "套餐名称")
    @TableField("TaocanName")
    private String TaocanName;

    @ApiModelProperty(value = "套餐数量")
    @TableField("TaocanShuliang")
    private Integer TaocanShuliang;

    @TableField("GouMuHeTongUrl")
    private String GouMuHeTongUrl;

    @TableField("LianXiDanWei")
    private String LianXiDanWei;

    @TableField("IsFarmer")
    private String IsFarmer;

    @TableField("IsLocal")
    private String IsLocal;

    @TableField("IsDiBao")
    private String IsDiBao;

    @TableField("NoName")
    private String NoName;

    @TableField("BuBan")
    private String BuBan;

    @TableField("IsSign")
    private String IsSign;

    @TableField("SignTime")
    private Date SignTime;

    @ApiModelProperty(value = "自送人（自送司机）")
    @TableField("PlateUser")
    private String PlateUser;

    @TableField("QiaTanYuan")
    private String QiaTanYuan;

    @TableField("ZhengRongBeiZhu")
    private String ZhengRongBeiZhu;

    @TableField("HuiMinQuYu")
    private String HuiMinQuYu;

    @TableField("PresentLiveAddress")
    private String PresentLiveAddress;

    @TableField("SynDeadId")
    private String SynDeadId;

    @TableField("Push_State")
    private Integer pushState;

    @TableField("OperationType")
    private Integer OperationType;

    @TableField("DataPushDate")
    private Date DataPushDate;

    @TableField("SinotransAddress")
    private String SinotransAddress;


    public static final String OPERATIONNO = "OperationNO";

    public static final String DIERNAME = "DierName";

    public static final String DIERSEX = "DierSex";

    public static final String DIERAGE = "DierAge";

    public static final String BIRTHDAY = "BirthDay";

    public static final String NATIONALITY = "nationality";

    public static final String CERTIFICATETYPE = "CertificateType";

    public static final String CERTIFICATENO = "CertificateNO";

    public static final String FOLK = "Folk";

    public static final String CENSUSREGISTERADDRESS = "CensusRegisterAddress";

    public static final String BEFOREDEATHADDRESS = "BeforeDeathAddress";

    public static final String BEFOREDEATHUNIT = "BeforeDeathUnit";

    public static final String BUSINESS = "Business";

    public static final String DEATHTIME = "DeathTime";

    public static final String DEATHADDRESS = "DeathAddress";

    public static final String DEATHCAUSE = "DeathCause";

    public static final String DEATHPROOFUNIT = "DeathProofUnit";

    public static final String FUNERALPROOFUNIT = "FuneralProofUnit";

    public static final String REMAINSSTATE = "RemainsState";

    public static final String PAYMENTMODE = "PaymentMode";

    public static final String ISFINISHED = "IsFinished";

    public static final String ISASHES = "IsAshes";

    public static final String ASHESMANAGEMODE = "AshesManageMode";

    public static final String OPERATOR = "Operator";

    public static final String OPERATETIME = "OperateTime";

    public static final String REMARK = "Remark";

    public static final String PINYIN = "Pinyin";

    public static final String CLEAR_SALESMAN = "Clear_salesman";

    public static final String DEPOSIT = "Deposit";

    public static final String ISPREFERENTIAL = "IsPreferential";

    public static final String HOBBIES = "Hobbies";

    public static final String FAMILY = "Family";

    public static final String POSITION = "Position";

    public static final String WORKINGTIME = "WorkingTime";

    public static final String RELIGION = "Religion";

    public static final String CADAVERDELIVERING = "CadaverDelivering";

    public static final String CADAVERLINKMAN = "CadaverLinkman";

    public static final String CADAVERPHONE = "CadaverPhone";

    public static final String CADAVERINDENTITYNO = "CadaverIndentityNo";

    public static final String PHOTO = "Photo";

    public static final String DEATHPROOF = "DeathProof";

    public static final String ISPRINT = "IsPrint";

    public static final String LOADTIME = "LoadTime";

    public static final String DEADTYPE = "DeadType";

    public static final String ISEXTERNALBODY = "IsExternalBody";

    public static final String ISWILLING = "IsWilling";

    public static final String PLATENUMBER = "PlateNumber";

    public static final String ARRIVETIME = "ArriveTime";

    public static final String ISARRIVED = "IsArrived";

    public static final String DEATHPROOFNO = "DeathProofNo";

    public static final String CREMATINGPROOFNO = "CrematingProofNo";

    public static final String DIERPROVINCE = "DierProvince";

    public static final String DIERPROVINCENAME = "DierProvinceName";

    public static final String DIERCITY = "DierCity";

    public static final String DIERCITYNAME = "DierCityName";

    public static final String DIERDISTRICT = "DierDistrict";

    public static final String DIERDISTRICTNAME = "DierDistrictName";

    public static final String DIERTOWN = "DierTown";

    public static final String DIERSTREETOFFICE = "DierStreetOffice";

    public static final String DIERSTREETOFFICENAME = "DierStreetOfficeName";

    public static final String DIERVILLAGE = "DierVillage";

    public static final String DIERVILLAGENAME = "DierVillageName";

    public static final String AREACODE = "AreaCode";

    public static final String AREANAME = "AreaName";

    public static final String CREMATINGPRINTNUMBER = "CrematingPrintNumber";

    public static final String ISDEAD = "IsDead";

    public static final String BONETYPE = "BoneType";

    public static final String ISSINOTRANS = "IsSinotrans";

    public static final String SINOTRANSDATE = "SinotransDate";

    public static final String ISVIEWCREMATION = "IsViewCremation";

    public static final String CURRENTPOSITION = "CurrentPosition";

    public static final String PEOPLETYPE = "PeopleType";

    public static final String ISUPLOADPHOTO = "IsUploadPhoto";

    public static final String SERVICEGUIDER = "ServiceGuider";

    public static final String ISCHARGEREDUCE = "IsChargeReduce";

    public static final String ADDUSER = "AddUser";

    public static final String ADDTIME = "AddTime";

    public static final String DOCUMENTSTATE = "DocumentState";

    public static final String COLDID = "ColdID";

    public static final String COLDNO = "ColdNo";

    public static final String COLDSTATUS = "ColdStatus";

    public static final String COLDBEGINTIME = "ColdBeginTime";

    public static final String COLDENDTIME = "ColdEndTime";

    public static final String COLDTYPEID = "ColdTypeID";

    public static final String COLDTYPENAME = "ColdTypeName";

    public static final String CHARGEPAY = "ChargePay";

    public static final String CHARGERECEIVABLE = "ChargeReceivable";

    public static final String CHARGEREAL = "ChargeReal";

    public static final String CHARGEDATE = "ChargeDate";

    public static final String MOURNID = "MournID";

    public static final String MOURNNAME = "MournName";

    public static final String MOURNSTATUS = "MournStatus";

    public static final String MOURNBEGINTIME = "MournBeginTime";

    public static final String MOURNENDTIME = "MournEndTime";

    public static final String MOURNTYPEID = "MournTypeID";

    public static final String MOURNTYPENAME = "MournTypeName";

    public static final String FAREWELLID = "FarewellID";

    public static final String FAREWELLNAME = "FarewellName";

    public static final String FAREWELLSTATUS = "FarewellStatus";

    public static final String FAREWELLBEGINTIME = "FarewellBeginTime";

    public static final String FAREWELLENDTIME = "FarewellEndTime";

    public static final String FAREWELLTYPEID = "FarewellTypeID";

    public static final String FAREWELLTYPENAME = "FarewellTypeName";

    public static final String CREMATINGID = "CrematingID";

    public static final String CREMATINGNO = "CrematingNo";

    public static final String CREMATINGTYPEID = "CrematingTypeID";

    public static final String CREMATINGTYPENAME = "CrematingTypeName";

    public static final String CREMATINGDATE = "CrematingDate";

    public static final String GOUMUHETONGHAO = "GouMuHeTongHao";

    public static final String TAOCANID = "TaocanId";

    public static final String TAOCANNAME = "TaocanName";

    public static final String TAOCANSHULIANG = "TaocanShuliang";

    public static final String GOUMUHETONGURL = "GouMuHeTongUrl";

    public static final String LIANXIDANWEI = "LianXiDanWei";

    public static final String ISFARMER = "IsFarmer";

    public static final String ISLOCAL = "IsLocal";

    public static final String ISDIBAO = "IsDiBao";

    public static final String NONAME = "NoName";

    public static final String BUBAN = "BuBan";

    public static final String ISSIGN = "IsSign";

    public static final String SIGNTIME = "SignTime";

    public static final String PLATEUSER = "PlateUser";

    public static final String QIATANYUAN = "QiaTanYuan";

    public static final String ZHENGRONGBEIZHU = "ZhengRongBeiZhu";

    public static final String HUIMINQUYU = "HuiMinQuYu";

    public static final String PRESENTLIVEADDRESS = "PresentLiveAddress";

    public static final String SYNDEADID = "SynDeadId";

    public static final String PUSH_STATE = "Push_State";

    public static final String OPERATIONTYPE = "OperationType";

    public static final String DATAPUSHDATE = "DataPushDate";

    public static final String SINOTRANSADDRESS = "SinotransAddress";

}
