package com.jmdz.fushan.dao;


import com.jmdz.fushan.dao.provider.FarewellProvider;
import com.jmdz.fushan.dao.provider.VehicleManageProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.farewell.FarewellHallItem;
import com.jmdz.fushan.pad.model.farewell.FarewellTaskItem;
import com.jmdz.fushan.pad.model.farewell.FarewellTaskListItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.MournItem;
import com.jmdz.fushan.pad.model.weixin.VehicleManageItem;
import com.jmdz.fushan.pad.model.weixin.WxMournItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * 告别厅dao
 *
 * @author LiCongLu
 * @date 2020-06-08 10:39
 */
public interface FarewellDao {
    /**
     * 查询所有告别厅信息
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-06-08 10:42
     */
    @Select("select MournHallId as hallId,MournNo as hallNo,MournName as hallName,SortNum as sort " +
            " from " + TableNames.MournHall + " where IsDeleted = 0 and IsUse = 1 and IsMourn = 1 " +
            " order by SortNum")
    ArrayList<FarewellHallItem> listFarewellHall();

    /**
     * 查询当天告别厅任务信息
     *
     * @param hallId 告别厅主键
     * @return
     * @author LiCongLu
     * @date 2020-07-13 16:11
     */
    @SelectProvider(type = FarewellProvider.class, method = "listFarewellTaskListByHallId")
    ArrayList<FarewellTaskListItem> listFarewellTaskListByHallId(@Param("hallId") Integer hallId);

    /**
     * 按照主键获取告别任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-14 08:44
     */
    @SelectProvider(type = FarewellProvider.class, method = "getFarewellTaskById")
    FarewellTaskItem getFarewellTaskById(@Param("id") Integer id);

    /**
     * 按照主键查询告别任务信息集合
     *
     * @param idList 主键集合
     * @return
     * @author LiCongLu
     * @date 2020-07-15 16:34
     */
    @SelectProvider(type = FarewellProvider.class, method = "listFarewellTaskByIds")
    ArrayList<FarewellTaskItem> listFarewellTaskByIds(@Param("idList") ArrayList<Integer> idList);

    /**
     * 按照业务编号获取整容随机码
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-14 11:56
     */
    @Select("select distinct Random_Id from " + TableNames.FaceLift + " where OperationNO = #{operationNo}")
    @ResultType(String.class)
    ArrayList<String> listFaceLiftRandomIdForFarewell(@Param("operationNo") String operationNo);

    /**
     * 更新任务状态标记
     *
     * @param farewellId  业务信息主键
     * @param operationNo 业务编号
     * @param taskFlag    状态标记
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-15 11:21
     */
    @UpdateProvider(type = FarewellProvider.class, method = "updateFarewellTaskFlag")
    void updateFarewellTaskFlag(@Param("farewellId") Integer farewellId, @Param("operationNo") String operationNo, @Param("taskFlag") Integer taskFlag, @Param("loginItem") LoginItem loginItem);

    /**
     * 按照时间查询当前礼厅的告别厅任务主键
     *
     * @param hallId    礼厅主键
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-07-21 15:22
     */
    @Select(" select top 1 id from " + TableNames.Mourn +
            " where MournHallID = #{hallId} " +
            "  and ((#{beginTime} >= BeginTime and #{beginTime} <EndTime) or (#{endTime} > BeginTime and #{endTime} <=EndTime)) ")
    @ResultType(Integer.class)
    Integer getFarewellIdByTime(@Param("hallId") Integer hallId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 新增告别任务信息，通过微信告别审核
     *
     * @param item      告别任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-21 15:49
     */
    @InsertProvider(type = FarewellProvider.class, method = "insertFarewellWxMourn")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertFarewellWxMourn(@Param("item") MournItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 审核告别时，更新逝者相关告别信息
     *
     * @param operationNo 业务编号
     * @param typeItem    告别类型信息
     * @param mournItem   告别信息
     * @param loadItem    告别预约信息
     * @param loginItem   登录信息
     * @return
     * @author LiCongLu
     * @date 2020-07-30 13:43
     */
    @UpdateProvider(type = FarewellProvider.class, method = "updateDeadFarewellWx")
    void updateDeadFarewellWx(@Param("operationNo") String operationNo
            , @Param("typeItem") ServiceItem typeItem, @Param("mournItem") MournItem mournItem
            , @Param("loadItem") WxMournItem loadItem, @Param("loginItem") LoginItem loginItem);
}
