package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfoData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * BusinessCrematingProvider
 *
 * @author LiCongLu
 * @date 2020-08-05 11:27
 */
public class BusinessCrematingProvider {


    /**
     * 按照业务编号加载 火化任务列表 信息
     *
     * @param operationNo 业务编码
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 09:41
     */
    public String getBusinessCrematingListInfoByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 cr.id,cr.OperationNO as operationNO");
                builder.append(" , CrematingMachineTypeID as crematingMachineTypeID ");
                builder.append(" , DeadType as deadType ");
                builder.append(" ,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = DeadType) as deadTypeText ");
                builder.append(" , AshesManageMode as ashesManageMode ");
                builder.append(" , CremationTime as cremationTime ");
                builder.append(" ,CrematingNumber * CrematingPrice  crematingCharge ");
                builder.append(" ,ch.Charge as charge ");
                SELECT(builder.toString());
                FROM(TableNames.Cremating +" cr,"+TableNames.Charge +" ch ");
                WHERE( " cr.Random_Id = ch.Random_Id");
                WHERE(" cr.OperationNO = #{operationNo} ");
            }
        }.toString();
    }


    /**
     * 按照业务编号加载火化任务详情信息
     *
     * @param operationNo 业务编码
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 09:41
     */
    public String getBusinessCrematingInfoByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 cr.id,cr.OperationNO as operationNO");
                builder.append(" , CrematingMachineTypeID as crematingMachineTypeID ");
                builder.append(" , DeadType as deadType ");
                builder.append(" ,(select top 1 ItemName from Fmis_ItemDetails where ItemDetailsId = DeadType) as deadTypeText ");
                builder.append(" , AshesManageMode as ashesManageMode ");
                builder.append(" ,IsBespeak as isBespeak ");
                builder.append(" , CremationTime as cremationTime ");
                builder.append(" , CrematingPrice crematingPrice ");
                builder.append(" ,CrematingNumber * CrematingPrice  crematingCharge ");
                builder.append(" ,ch.Charge as charge ");
                SELECT(builder.toString());
                FROM(TableNames.Cremating +" cr,"+TableNames.Charge +" ch ");
                WHERE( " cr.Random_Id = ch.Random_Id");
                WHERE(" cr.OperationNO = #{operationNo} ");
            }
        }.toString();
    }

    /**
     * 新增火化任务详情信息
     *
     * @param item      火化任务详情信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 14:13
     */
    public String insertBusinessCrematingInfo(@Param("item") BusinessCrematingInfoData item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.Cremating);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("CrematingMachineTypeID", "#{item.crematingMachineTypeID}");
                VALUES("DeadType", "#{item.deadType}");
                VALUES("AshesManageMode", "#{item.ashesManageMode}");
                VALUES("IsBespeak", "#{item.isBespeak}");
                VALUES("Random_Id", "#{item.randomId}");
                VALUES("CremationTime", "#{item.cremationTime}");
                VALUES("CrematingPrice", "#{item.crematingPrice,jdbcType=DECIMAL}");
                VALUES("Charge", "#{item.charge}");
                VALUES("Operator", "#{loginItem.userCode}");
            }
        }.toString();
    }

    /**
     * 更新火化任务详情信息
     *
     * @param item      火化任务详情信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 14:13
     */
    public String updateBusinessCrematingInfo(@Param("item") BusinessCrematingInfoData item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Cremating);
                SET("CrematingMachineTypeID=#{item.crematingMachineTypeID}");
                SET("DeadType=#{item.deadType}");
                SET("AshesManageMode=#{item.ashesManageMode}");
                SET("IsBespeak=#{item.isBespeak}");
                SET("CremationTime=#{item.cremationTime}");
                SET("CrematingPrice=#{item.crematingPrice}");
                SET("Charge=#{item.charge}");
                SET("Operator=#{loginItem.userCode}");
                WHERE(" OperationNO = #{item.operationNo} ");
            }
        }.toString();
    }

}
