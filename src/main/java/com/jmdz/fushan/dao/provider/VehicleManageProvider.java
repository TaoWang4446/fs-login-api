package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.VehicleManageItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * VehicleManageProvider
 *
 * @author LiCongLu
 * @date 2020-07-17 10:21
 */
public class VehicleManageProvider {

    /**
     * 新增接运管理信息
     *
     * @param item      接运任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 10:20
     */
    public String insertVehicleManage(@Param("item") VehicleManageItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.VehicleManage);
                VALUES("Vehicle_Id", "#{item.vehicleId}");
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("LinkmanName", "#{item.linkmanName}");
                VALUES("LinkmanPhone", "#{item.linkmanPhone}");
                VALUES("DierRelation", "#{item.deadRelation}");
                VALUES("CarryPlace", "#{item.carryPlace,jdbcType=VARCHAR}");
                VALUES("CheliangYongTu", "#{item.cheLiangYongTu,jdbcType=VARCHAR}");
                VALUES("CarryTime", "Convert(varchar(16),#{item.carryTime},120)");
                VALUES("Remark", "#{item.remark}");
                VALUES("Random_Id", "#{item.randomId}");
                VALUES("ChargeStandard", "#{item.chargeStandard}");
                VALUES("FactCharge", "#{item.factStandard}");
                VALUES("BespeakVehicleType", "#{item.bespeakVehicleType,jdbcType=VARCHAR}");
                VALUES("CarryState", "#{item.carryState}");
                VALUES("CreateUserId", "#{loginItem.userId}");
                VALUES("CreateUserName", "#{loginItem.realName}");
            }
        }.toString();
    }
}
