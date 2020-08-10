package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.VehicleProvider;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.leader.LeaderAllLabelItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.vehicle.VehicleCarListItem;
import com.jmdz.fushan.pad.model.vehicle.VehicleTaskListItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * 车辆接运Dao
 *
 * @author LiCongLu
 * @date 2020-07-09 10:47
 */
public interface VehicleDao {

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
    @SelectProvider(type = VehicleProvider.class, method = "listVehicleTaskList")
    ArrayList<VehicleTaskListItem> listVehicleTaskList(@Param("userId") String userId);

    /**
     * 按照接运管理表主键获取接运任务信息
     *
     * @param vehicleId 接运管理表主键
     * @return
     * @author LiCongLu
     * @date 2020-07-10 09:14
     */
    @SelectProvider(type = VehicleProvider.class, method = "getVehicleTaskByVehicleId")
    VehicleTaskListItem getVehicleTaskByVehicleId(@Param("vehicleId") String vehicleId);

    /**
     * 加载所有车辆信息
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-10 09:00
     */
    @Select(" SELECT v.id as id,VehicleNO as vehicleNo,sItem.id as vehicleTypeId,sItem.Name as vehicleType " +
            "  FROM " + TableNames.Vehicle + " v join " + TableNames.ServiceItem + " sItem on v.ServiceItemID = sItem.id")
    ArrayList<VehicleCarListItem> listVehicleCarList();

    /**
     * 按照主键获取车辆信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-10 09:11
     */
    @Select(" SELECT v.id as id,VehicleNO as vehicleNo,sItem.id as vehicleTypeId,sItem.Name as vehicleType " +
            "  FROM " + TableNames.Vehicle + " v join " + TableNames.ServiceItem + " sItem on v.ServiceItemID = sItem.id " +
            "  where v.id = #{id}")
    VehicleCarListItem getVehicleCarById(@Param("id") Integer id);

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
    @UpdateProvider(type = VehicleProvider.class, method = "updateVehicleOut")
    void updateVehicleOut(@Param("loginItem") LoginItem loginItem, @Param("vehicleId") String vehicleId, @Param("carItem") VehicleCarListItem carItem);

    /**
     * 执行回车操作
     *
     * @param loginItem 当前账号
     * @param vehicleId 接运主键
     * @return
     * @author LiCongLu
     * @date 2020-07-10 09:30
     */
    @UpdateProvider(type = VehicleProvider.class, method = "updateVehicleBack")
    void updateVehicleBack(@Param("loginItem") LoginItem loginItem, @Param("vehicleId") String vehicleId);

    /**
     * 按照查询日期查询车辆信息
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 10:52
     */
    @SelectProvider(type = VehicleProvider.class, method = "countVehicleForLeader")
    ArrayList<LeaderAllLabelItem> countVehicleForLeader(@Param("data") LeaderAllData data);

    /**
     * 按照账号获取最近出车的业务编号
     *
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-21 09:07
     */
    @Select(" select top 1 OperationNO as operationNo from FMIS_VehicleManage where DriverUserId = #{userId} " +
            " and CarryState = " + Constants.CarryState.YiChuChe +
            " and Convert(varchar(10),VehicleTime,120) >= CONVERT(varchar(10),dateadd(DAY,-2,GETDATE()) ,120)" +
            " order by CarryTime desc ")
    @ResultType(String.class)
    String getLastOperationNoByUserId(@Param("userId") String userId);
}
