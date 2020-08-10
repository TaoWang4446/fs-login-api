package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.vehicle.VehicleCarListItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


/**
 * VehicleProvider
 *
 * @author LiCongLu
 * @date 2020-06-02 17:15
 */
public class VehicleProvider {

    /**
     * 记载接运任务列表
     * <p>
     * 所有待出差的；自己已出车的；当天自己回车的
     * 按照状态，接运时间顺序
     *
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 10:49
     */
    public String listVehicleTaskList(@Param("userId") String userId) {
        // 尚未出车脚本
        String shangWeiChuCheSql = new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP (100) PERCENT manage.Vehicle_Id as vehicleId,manage.OperationNO as operationNo,dead.DierName as deadName ");
                builder.append(" ,manage.LinkmanName as linkman,manage.LinkmanPhone as contactNumber ");
                builder.append(" ,manage.CarryTime as carryTime,manage.CarryPlace as carryPlace ");
                builder.append(" ,CONVERT(varchar(16),manage.BookInTime,120) as bookInTime ");
                builder.append(" ,sItem.id as vehicleTypeId,sItem.Name as vehicleType ");
                builder.append(" ,manage.CarryState as carryState,'尚未出车' as carryStateText");
                builder.append(" ,manage.Random_Id as randomId");
                builder.append("  ,( select top 1 IsFinished from " + TableNames.Charge);
                builder.append("    where OperationNo = dead.OperationNO and Random_Id = manage.Random_Id ");
                builder.append("      and ServiceItem = sItem.id ");
                builder.append("     order by id desc  ) as asFinished ");

                SELECT(builder.toString());
                FROM(TableNames.VehicleManage + " manage");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = manage.OperationNO");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = manage.BespeakVehicleType");
                WHERE(" manage.CarryState = " + Constants.CarryState.ShangWeiChuChe);
                ORDER_BY(" manage.CarryTime");
            }
        }.toString();

        // 已出车脚本
        String yiChuCheSql = new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP (100) PERCENT manage.Vehicle_Id as vehicleId,manage.OperationNO as operationNo,dead.DierName as deadName ");
                builder.append(" ,manage.LinkmanName as linkman,manage.LinkmanPhone as contactNumber ");
                builder.append(" ,manage.CarryTime as carryTime,manage.CarryPlace as carryPlace ");
                builder.append(" ,CONVERT(varchar(16),manage.BookInTime,120) as bookInTime ");
                builder.append(" ,sItem.id as vehicleTypeId,sItem.Name as vehicleType ");
                builder.append(" ,manage.CarryState as carryState,'已出车' as carryStateText");
                builder.append(" ,manage.Random_Id as randomId");
                builder.append("  ,( select top 1 IsFinished from " + TableNames.Charge);
                builder.append("    where OperationNo = dead.OperationNO and Random_Id = manage.Random_Id ");
                builder.append("      and ServiceItem = sItem.id ");
                builder.append("     order by id desc  ) as asFinished ");

                SELECT(builder.toString());
                FROM(TableNames.VehicleManage + " manage");
                JOIN(TableNames.Dead + " dead on dead.OperationNO= manage.OperationNO");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = manage.BespeakVehicleType");
                WHERE(" manage.DriverUserId=#{userId}");
                WHERE(" manage.CarryState = " + Constants.CarryState.YiChuChe);
                ORDER_BY(" manage.CarryTime");
            }
        }.toString();

        // 任务完成脚本
        String renWuWanChengSql = new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP (100) PERCENT manage.Vehicle_Id as vehicleId,manage.OperationNO as operationNo,dead.DierName as deadName ");
                builder.append(" ,manage.LinkmanName as linkman,manage.LinkmanPhone as contactNumber ");
                builder.append(" ,manage.CarryTime as carryTime,manage.CarryPlace as carryPlace ");
                builder.append(" ,CONVERT(varchar(16),manage.BookInTime,120) as bookInTime ");
                builder.append(" ,sItem.id as vehicleTypeId,sItem.Name as vehicleType ");
                builder.append(" ,manage.CarryState as carryState,'任务完成' as carryStateText");
                builder.append(" ,manage.Random_Id as randomId");
                builder.append("  ,( select top 1 IsFinished from " + TableNames.Charge);
                builder.append("    where OperationNo = dead.OperationNO and Random_Id = manage.Random_Id ");
                builder.append("      and ServiceItem = sItem.id ");
                builder.append("     order by id desc  ) as asFinished ");

                SELECT(builder.toString());
                FROM(TableNames.VehicleManage + " manage");
                JOIN(TableNames.Dead + " dead on dead.OperationNO= manage.OperationNO");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = manage.BespeakVehicleType");
                WHERE(" manage.DriverUserId=#{userId}");
                WHERE(" CONVERT(varchar(10),manage.CarryTime,120) = CONVERT(varchar(10),GETDATE(),120)");
                WHERE(" manage.CarryState = " + Constants.CarryState.RenWuWanCheng);
                ORDER_BY(" manage.CarryTime");
            }
        }.toString();

        // 拼接脚本
        StringBuilder builder = new StringBuilder();
        builder.append(" select * from ( " + shangWeiChuCheSql + " ) a ");
        builder.append(" union all ");
        builder.append(" select * from ( " + yiChuCheSql + " ) b ");
        builder.append(" union all ");
        builder.append(" select * from ( " + renWuWanChengSql + " ) c ");
        return builder.toString();
    }

    /**
     * 按照接运管理表主键获取接运任务信息
     *
     * @param vehicleId 接运管理表主键
     * @return
     * @author LiCongLu
     * @date 2020-07-10 09:14
     */
    public String getVehicleTaskByVehicleId(@Param("vehicleId") String vehicleId) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP 1 manage.Vehicle_Id as vehicleId,manage.OperationNO as operationNo,dead.DierName as deadName ");
                builder.append(" ,manage.LinkmanName as linkman,manage.LinkmanPhone as contactNumber ");
                builder.append(" ,manage.CarryTime as carryTime,manage.CarryPlace as carryPlace ");
                builder.append(" ,CONVERT(varchar(16),manage.BookInTime,120) as bookInTime ");
                builder.append(" ,sItem.id as vehicleTypeId,sItem.Name as vehicleType ");
                builder.append(" ,manage.CarryState as carryState");
                builder.append(" ,manage.Random_Id as randomId");
                builder.append("  ,( select top 1 IsFinished from " + TableNames.Charge);
                builder.append("    where OperationNo = dead.OperationNO and Random_Id = manage.Random_Id ");
                builder.append("      and ServiceItem = sItem.id ");
                builder.append("     order by id desc  ) as asFinished ");

                SELECT(builder.toString());
                FROM(TableNames.VehicleManage + " manage");
                JOIN(TableNames.Dead + " dead on dead.OperationNO= manage.OperationNO");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = manage.BespeakVehicleType");
                WHERE(" manage.Vehicle_Id = #{vehicleId}");
            }
        }.toString();
    }

    /**
     * 执行出车操作
     *
     * @param loginItem 当前账号
     * @param vehicleId 接运主键
     * @param carItem   车辆信息
     * @return
     * @author LiCongLu
     * @date 2020-07-10 09:24
     */
    public String updateVehicleOut(@Param("loginItem") LoginItem loginItem, @Param("vehicleId") String vehicleId, @Param("carItem") VehicleCarListItem carItem) {
        return new SQL() {
            {
                UPDATE(TableNames.VehicleManage);
                SET("CarryState = " + Constants.CarryState.YiChuChe);
                // 出车时更新出车时间
                SET("VehicleNO = #{carItem.vehicleNo}");
                SET("CarryMotorman = #{loginItem.realName}");
                SET("DriverUserId = #{loginItem.userId}");
                SET("VehicleTime = convert(varchar(16),getdate(),120)");
                SET("ModifyUserId = #{loginItem.userCode}");
                SET("ModifyUserName = #{loginItem.realName}");
                WHERE(" Vehicle_Id=#{vehicleId}");
            }
        }.toString();
    }

    /**
     * 执行出车操作
     *
     * @param loginItem 当前账号
     * @param vehicleId 接运主键
     * @return
     * @author LiCongLu
     * @date 2020-07-10 09:24
     */
    public String updateVehicleBack(@Param("loginItem") LoginItem loginItem, @Param("vehicleId") String vehicleId) {
        return new SQL() {
            {
                UPDATE(TableNames.VehicleManage);
                SET("CarryState = " + Constants.CarryState.RenWuWanCheng);
                SET("BackTime = convert(varchar(16),getdate(),120)");
                SET("ModifyUserId = #{loginItem.userCode}");
                SET("ModifyUserName = #{loginItem.realName}");
                WHERE(" Vehicle_Id=#{vehicleId}");
            }
        }.toString();
    }

    /**
     * 按照查询日期查询车辆信息
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 10:52
     */
    public String countVehicleForLeader(@Param("data") LeaderAllData data) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" sItem.id as id,sItem.Name as name,COUNT(*) as number ");

                SELECT(builder.toString());
                FROM(TableNames.VehicleManage + " vehicle ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = vehicle.BespeakVehicleType ");

                // 当无查询日期时默认为当前日期
                if (DataUtil.invalid(data.getQueryDate())) {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),CarryTime,120) = Convert(varchar(10),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),CarryTime,120) = Convert(varchar(7),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),CarryTime,120) = Convert(varchar(4),GETDATE(),120) ");
                    }
                } else {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),CarryTime,120) = Convert(varchar(10),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),CarryTime,120) = Convert(varchar(7),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),CarryTime,120) = Convert(varchar(4),#{data.queryDate},120) ");
                    }
                }


                GROUP_BY(" sItem.id,sItem.Name ");
                ORDER_BY(" sItem.id ");
            }
        }.toString();
    }
}
