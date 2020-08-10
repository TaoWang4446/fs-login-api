package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.BusinessDeadInfoData;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import java.util.Date;

/**
 * DeadProvider
 *
 * @author LiCongLu
 * @date 2020-07-09 14:15
 */
public class DeadProvider {

    /**
     * 新增逝者基本信息
     *
     * @param item      逝者信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 14:13
     */
    public String insertDeadBasicItem(@Param("item") DeadBasicItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.Dead);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("DierName", "#{item.deadName}");
                VALUES("DierSex", "#{item.deadSex}");
                VALUES("DierAge", "#{item.deadAge}");
                VALUES("BirthDay", "#{item.deadBirthday}");
                VALUES("DeathAddress", "#{item.deathAddress}");
                VALUES("DeathTime", "#{item.deathTime}");
                VALUES("CertificateNO", "#{item.deadCertificateNo}");
                VALUES("IsDead", "1");
                VALUES("Operator", "#{loginItem.userCode}");
                VALUES("OperateTime", "getdate()");
                VALUES("AddUser", "#{loginItem.realName}");
                VALUES("AddTime", "getdate()");
            }
        }.toString();
    }

    /**
     * 更新逝者基本信息
     *
     * @param item      逝者信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 14:13
     */
    public String updateDeadBasicItem(@Param("item") DeadBasicItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Dead);
                SET("DierName = #{item.deadName}");
                SET("DierSex = #{item.deadSex}");
                SET("DierAge = #{item.deadAge}");
                SET("BirthDay = #{item.deadBirthday}");
                SET("DeathAddress = #{item.deathAddress}");
                SET("DeathTime = #{item.deathTime}");
                SET("CertificateNO = #{item.deadCertificateNo}");
                SET("Operator = #{loginItem.userCode}");
                SET("OperateTime = getdate()");
                WHERE(" OperationNO = #{item.operationNo} ");
            }
        }.toString();
    }

    /**
     * 查询指定日期的逝者任务列表
     *
     * @param queryDate 查询日期
     * @return
     * @author LiCongLu
     * @date 2020-07-10 14:12
     */
    public String listDeadAllListByDate(@Param("queryDate") Date queryDate) {

        // 当天有告别
        String gaoBieSql = new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP (100) PERCENT dead.OperationNO as operationNo,dead.DierName as deadName ");
                builder.append(" ,hall.MournName as hallName,mourn.BeginTime as beginTime,sItem.Name as machineType,cremating.OrderCremationTime ");
                SELECT(builder.toString());
                FROM(TableNames.Dead + " dead ");
                LEFT_OUTER_JOIN(TableNames.Mourn + " mourn on mourn.OperationNO= dead.OperationNO  and IsMourn = 1 ");
                LEFT_OUTER_JOIN(TableNames.MournHall + "  hall on hall.MournHallId = mourn.MournHallID ");
                LEFT_OUTER_JOIN(TableNames.Cremating + " cremating on cremating.OperationNO = dead.OperationNO ");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = cremating.CrematingMachineTypeID ");
                WHERE(" dead.IsDead = 1");

                if (DataUtil.invalid(queryDate)) {
                    WHERE(" CONVERT(varchar(10),mourn.BeginTime,120) = CONVERT(varchar(10),GETDATE(),120) ");
                } else {
                    WHERE(" CONVERT(varchar(10),mourn.BeginTime,120) = CONVERT(varchar(10),#{queryDate},120) ");
                }

                ORDER_BY(" dead.OperationNO ");
            }
        }.toString();

        // 当天有火化
        String huoHuaSql = new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP (100) PERCENT dead.OperationNO as operationNo,dead.DierName as deadName ");
                builder.append(" ,hall.MournName as hallName,mourn.BeginTime as beginTime,sItem.Name as machineType,cremating.OrderCremationTime ");
                SELECT(builder.toString());
                FROM(TableNames.Dead + " dead ");
                LEFT_OUTER_JOIN(TableNames.Mourn + " mourn on mourn.OperationNO= dead.OperationNO  and IsMourn = 1 ");
                LEFT_OUTER_JOIN(TableNames.MournHall + "  hall on hall.MournHallId = mourn.MournHallID ");
                LEFT_OUTER_JOIN(TableNames.Cremating + " cremating on cremating.OperationNO = dead.OperationNO ");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = cremating.CrematingMachineTypeID ");
                WHERE(" dead.IsDead = 1");

                if (DataUtil.invalid(queryDate)) {
                    WHERE(" CONVERT(varchar(10),cremating.OrderCremationTime,120) = CONVERT(varchar(10),GETDATE(),120) ");
                } else {
                    WHERE(" CONVERT(varchar(10),cremating.OrderCremationTime,120) =CONVERT(varchar(10),#{queryDate},120) ");
                }

                ORDER_BY(" dead.OperationNO ");
            }
        }.toString();

        // 拼接脚本
        StringBuilder builder = new StringBuilder();
        builder.append(" select * from ( " + gaoBieSql + " ) a");
        builder.append(" union all ");
        builder.append(" select * from ( " + huoHuaSql + " ) b");
        return " select distinct operationNo,deadName,hallName,beginTime,machineType,OrderCremationTime from ( " + builder.toString() + " ) c order by c.beginTime,c.OrderCremationTime ";
    }

    /**
     * 查询指定业务编码逝者基本详细信息
     *
     * @param operationNo 业务编码
     * @return
     * @author Sunzhaoqi
     * @date 2020-07-13 10:51
     */
    public String getDeadDetailsByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 dead.DierName as deadName,dead.OperationNO as operationNo ");
                builder.append(" ,dead.DierAge as deadAge,dead.DierSex as deadSex,dead.CertificateNO as certificateNO,dead.BeforeDeathAddress as beforeDeathAddress");
                builder.append(" ,c.Name as relationName,c.Phone as relationPhone ");
                SELECT(builder.toString());
                FROM(TableNames.Dead + " dead ");
                LEFT_OUTER_JOIN(TableNames.Contacts + " c on c.OperationNO = dead.OperationNO");
                WHERE("  dead.OperationNO = #{operationNo} ");
                WHERE(" dead.IsDead = 1 ");
            }
        }.toString();
    }

    /**
     * 按照业务编号加载逝者费用明细信息
     *
     * @param operationNo 业务编码
     * @return
     * @author Sunzhaoqi
     * @date 2020-07-13 10:51
     */
    public String listDeadChargeAllByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  charge.id,sItem.id as itemId, sItem.ParentId as parentId, sItem.Name as itemName ");
                builder.append(" ,charge.Number as number,charge.Charge as charge, (charge.HuiminCharge + charge.PreferentialCharge) as benefitCharge ");
                builder.append(" , charge.RealCharge as realCharge,IsWeiShengGuan as asWeiShengGuan ");
                SELECT(builder.toString());
                FROM(TableNames.Charge + " charge ");
                JOIN(TableNames.ServiceItem + " sItem on charge.ServiceItem = sItem.id  ");
                WHERE(" charge.IsDelete = 0");
                WHERE(" charge.OperationNO = #{operationNo} ");
            }
        }.toString();
    }


    /**
     * 按照业务编号加载逝者服务信息
     *
     * @param operationNo 业务编码
     * @return
     * @author Sunzhaoqi
     * @date 2020-07-13 10:51
     */
    public String getDeadServiceAllByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 fvm.Name as bespeakVehicleTypeName ");
                builder.append(" ,cse.No as equipmentName,coldTypeItem.Name as equipmentTypeName,cold.ColdStorageInTime as coldStorageInTime,cold.ColdStorageOutTime as coldStorageOutTime ");
                builder.append(" ,wakeHall.MournName as wakeHallName,wake.BeginTime as wakeBeginTime,wake.EndTime as wakeEndTime");
                builder.append(" ,farewellHall.MournName as farewellHallName,farewell.BeginTime as farewellBeginTime,farewell.EndTime as farewellEndTime");
                builder.append(" ,crematingItem.Name as machineTypeName,cremating.BeginTime as cremationBeginTime");

                SELECT(builder.toString());
                FROM(TableNames.Dead + " dead ");
                LEFT_OUTER_JOIN(TableNames.VehicleManage + " fm on fm.OperationNO = dead.OperationNO ");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " fvm on fm.BespeakVehicleType = fvm.id ");
                LEFT_OUTER_JOIN(TableNames.ColdStorage + " cold  on cold.OperationNO = dead.OperationNO");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " coldTypeItem  on coldTypeItem.id =  cold.ColdStorageType");
                LEFT_OUTER_JOIN(TableNames.ColdStorageEquipment + " cse  on cse.id =  cold.ColdStorageNO");
                LEFT_OUTER_JOIN(TableNames.Mourn + " wake on wake.OperationNO= dead.OperationNO and wake.IsMourn = 0");
                LEFT_OUTER_JOIN(TableNames.MournHall + " wakeHall on wakeHall.MournHallId = wake.MournHallID");
                LEFT_OUTER_JOIN(TableNames.Mourn + " farewell on farewell.OperationNO= dead.OperationNO and farewell.IsMourn = 1");
                LEFT_OUTER_JOIN(TableNames.MournHall + " farewellHall on farewellHall.MournHallId = farewell.MournHallID");
                LEFT_OUTER_JOIN(TableNames.Cremating + " cremating on cremating.OperationNO = dead.OperationNO");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " crematingItem on crematingItem.id = cremating.CrematingMachineTypeID");
                WHERE(" dead.OperationNO = #{operationNo} ");
            }
        }.toString();
    }


    /**
     * 按照业务编号加载逝者服务信息
     *
     * @param operationNo 业务编码
     * @return
     * @author Wangshengtao
     * @date 2020-08-03 15:51
     */
    public String getBusinessDeadInfoByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();

                builder.append(" top 1 OperationNO as operationNo,DierName as dierName ,DierAge as dierAge, Photo as photo,CertificateNO as certificateNO" );
                builder.append(",DierSex as dierSex, BirthDay as birthDay,DeathTime as deathTime,CertificateType as certificateType ");
                builder.append(" ,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = certificateType) as certificateTypeText ");
                builder.append(" ,BoneType as boneType,(select ItemName from Fmis_ItemDetails where ItemDetailsId = boneType) as boneTypeText ");
                builder.append(" ,RemainsState as remainsState,nationality as nationality ");
                builder.append(" ,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = nationality) as nationalityText ");
                builder.append(" ,DeathCause as deathCause,CadaverLinkman as cadaverLinkman ");
                builder.append(" ,Folk as folk,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = folk) as folkText ");
                builder.append(" ,BeforeDeathAddress as beforeDeathAddress ");
                builder.append(" ,PeopleType as peopleType,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = peopleType) as peopleTypeText ");
                builder.append(" ,DierProvince as dierProvince, DierProvinceName as dierProvinceName, DierCity as dierCity ");
                builder.append(" ,DierCityName as dierCityName, DierDistrict as dierDistrict, DierDistrictName as dierDistrictName" );
                builder.append(" ,DierStreetOffice as dierStreetOffice, DierStreetOfficeName as dierStreetOfficeName, DierVillage as dierVillage ");
                builder.append(" ,DierVillageName as dierVillageName, CensusRegisterAddress as censusRegisterAddress, PresentLiveAddress as presentLiveAddress ");
                builder.append(" ,IsArrived as isArrived, ArriveTime as arriveTime, DeathProofUnit as deathProofUnit, FuneralProofUnit as funeralProofUnit ");
                builder.append(" ,DeathAddress as deathAddress, QiaTanYuan as qiaTanYuan, Remark as remark ");
                builder.append(" ,(select top 1 RealName from Fmis_User where UserId = qiaTanYuan) qiaTanYuanText ");
                SELECT(builder.toString());
                FROM(TableNames.Dead);
                WHERE(" OperationNO = #{operationNo} ");
                WHERE(" IsDead = 1 ");
            }
        }.toString();
    }

    /**
     * 更新逝者基本信息
     *
     * @param item      逝者信息
     * @param loginItem 当前账号
     * @return
     * @author WangShengtao
     * @date 2020-08-09 14:33
     */
    public String updateBusinessDeadInfo(@Param("loginItem") LoginItem loginItem, @Param("item") BusinessDeadInfoData item) {
        return new SQL() {
            {
                UPDATE(TableNames.Dead);
                SET("DierName=#{item.dierName}");
                SET("DierSex=#{item.dierSex}");
                SET("DierAge=#{item.dierAge}");
                SET("Photo=#{item.photo}");
                SET("BirthDay=#{item.birthDay}");
                SET("DeathTime=#{item.deathTime}");
                SET("CertificateType=#{item.certificateType}");
                SET("CertificateNO=#{item.certificateNO}");
                SET("RemainsState=#{item.remainsState}");
                SET("nationality=#{item.nationality}");
                SET("DeathCause=#{item.deathCause}");
                SET("Folk=#{item.folk}");
                SET("BeforeDeathAddress=#{item.beforeDeathAddress}");
                SET("PeopleType=#{item.peopleType}");
                SET("DierProvince=#{item.dierProvince}");
                SET("DierProvinceName=#{item.dierProvinceName}");
                SET("DierCity=#{item.dierCity}");
                SET("DierCityName=#{item.dierCityName}");
                SET("DierDistrict=#{item.dierDistrict}");
                SET("DierDistrictName=#{item.dierDistrictName}");
                SET("DierStreetOffice=#{item.dierStreetOffice}");
                SET("DierStreetOfficeName=#{item.dierStreetOfficeName}");
                SET("DierVillage=#{item.dierVillage}");
                SET("DierVillageName=#{item.dierVillageName}");
                SET("CensusRegisterAddress=#{item.censusRegisterAddress}");
                SET("PresentLiveAddress=#{item.presentLiveAddress}");
                SET("IsArrived=#{item.isArrived}");
                SET("ArriveTime=#{item.arriveTime}");
                SET("DeathProofUnit=#{item.deathProofUnit}");
                SET("FuneralProofUnit=#{item.funeralProofUnit}");
                SET("DeathAddress=#{item.deathAddress}");
                SET("QiaTanYuan=#{item.qiaTanYuan}");
                SET("Remark=#{item.remark}");
                SET("CadaverLinkman=#{item.cadaverLinkman}");
                SET("OperateTime=getdate()");
                SET("AddUser=#{loginItem.realName}");
                SET("AddTime=getdate()");
                SET("Operator = #{loginItem.userCode}");
                WHERE(" OperationNO = #{item.operationNo} ");
            }
        }.toString();
    }
}
