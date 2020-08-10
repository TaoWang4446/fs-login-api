package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.BusinessContactsInfoData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * BusinessContactsProvider
 *
 * @author Wangshengtao
 * @date 2020-07-09 14:15
 */
public class BusinessContactsProvider {

    /**
     * 新增联系人（家属、承办人）信息
     *
     * @param item      联系人信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-04 14:13
     */
    public String insertBusinessContactsInfo(@Param("item") BusinessContactsInfoData item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.Contacts);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("Name", "#{item.name}");
                VALUES("Sex", "#{item.sex}");
                VALUES("DierRelation", "#{item.dierRelation}");
                VALUES("Phone", "#{item.phone}");
                VALUES("CertificateType", "#{item.certificateType}");
                VALUES("CertificateNO", "#{item.certificateNO}");
                VALUES("Address", "#{item.address}");
                VALUES("FuneralDirector", "#{item.funeralDirector}");
                VALUES("FuneralDirectorSex", "#{item.funeralDirectorSex}");
                VALUES("FuneralDirectorDierRelation", "#{item.funeralDirectorDierRelation}");
                VALUES("FuneralDirectorPhone", "#{item.funeralDirectorPhone}");
                VALUES("FuneralDirectorCertificateType", "#{item.funeralDirectorCertificateType}");
                VALUES("FuneralDirectorCertificateNO", "#{item.funeralDirectorCertificateNO}");
                VALUES("Operator", "#{loginItem.userCode}");
                VALUES("OperateTime", "getdate()");
            }
        }.toString();
    }

    /**
     * 更新联系人（家属、承办人）信息
     *
     * @param item      联系人信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-04 14:13
     */
    public String updateBusinessContactsInfo(@Param("item") BusinessContactsInfoData item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Contacts);
                SET("Name=#{item.name} ");
                SET("Sex=#{item.sex} ");
                SET("DierRelation=#{item.dierRelation} ");
                SET("Phone=#{item.phone} ");
                SET("CertificateType=#{item.certificateType} ");
                SET("CertificateNO=#{item.certificateNO} ");
                SET("Address=#{item.address} ");
                SET("FuneralDirector=#{item.funeralDirector} ");
                SET("FuneralDirectorSex=#{item.funeralDirectorSex} ");
                SET("FuneralDirectorDierRelation=#{item.funeralDirectorDierRelation} ");
                SET("FuneralDirectorPhone=#{item.funeralDirectorPhone} ");
                SET("FuneralDirectorCertificateType=#{item.funeralDirectorCertificateType} ");
                SET("FuneralDirectorCertificateNO=#{item.funeralDirectorCertificateNO} ");
                SET("Operator=#{loginItem.userCode} ");
                SET("OperateTime=getdate()");
                WHERE(" OperationNO = #{item.operationNo} ");
            }
        }.toString();
    }

    /**
     * 按照业务编号加载联系人信息
     *
     * @param operationNo 业务编码
     * @return
     * @author Wangshengtao
     * @date 2020-08-04 15:51
     */
    public String getBusinessContactsInfoByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 ID as id,Name as name , Sex as sex , DierRelation as dierRelation , Phone as phone , CertificateType as certificateType");
                builder.append(" ,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = certificateType) certificateTypeText ");
                builder.append(" ,CertificateNO as certificateNO , Address as address , FuneralDirector as funeralDirector ");
                builder.append(" ,FuneralDirectorSex as funeralDirectorSex , FuneralDirectorDierRelation as funeralDirectorDierRelation ");
                builder.append(" ,FuneralDirectorPhone as funeralDirectorPhone , FuneralDirectorCertificateType as funeralDirectorCertificateType ");
                builder.append(" ,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = funeralDirectorCertificateType) funeralDirectorCertificateTypeText ");
                builder.append(" ,FuneralDirectorCertificateNO as funeralDirectorCertificateNO , OperationNo as operationNo ");
                SELECT(builder.toString());
                FROM(TableNames.Contacts);
                WHERE(" OperationNO = #{operationNo} ");
            }
        }.toString();
    }
}
