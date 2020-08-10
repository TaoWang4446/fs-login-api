package com.jmdz.fushan.dao.provider;

import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.business.wake.WakeHallListItem;
import com.jmdz.fushan.pad.model.business.wake.WakeMournItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.MournItem;
import com.jmdz.fushan.pad.model.weixin.WxMournItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * BusinessWakeProvider
 *
 * @author LiCongLu
 * @date 2020-08-05 11:21
 */
public class BusinessWakeProvider {

    /**
     * 按照业务编号查询守灵信息
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-05 15:09
     */
    public String listWakeMournListByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" mourn.id as id,mourn.OperationNO as operationNo,hall.MournName as hallName ");
                builder.append(" ,BeginTime as beginTime,EndTime endTime,ExecuteCircs as wakeState,mourn.Remark as remark ");
                builder.append(" ,charge.Charge as charge ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO ");
                JOIN(TableNames.MournHall + " hall on hall.MournHallId = mourn.MournHallID ");
                JOIN(TableNames.Charge + "  charge on charge.OperationNO = mourn.OperationNO  " +
                        "  and charge.ServiceItem = hall.ServiceItemID and charge.Random_Id = mourn.Random_Id" +
                        "  and charge.IsDelete = 0 ");
                WHERE("  mourn.OperationNO = #{operationNo} and mourn.IsMourn = 0 ");
                ORDER_BY(" BeginTime ");
            }
        }.toString();
    }

    /**
     * 加载守灵厅信息
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-05 13:13
     */
    public String listWakeHallList() {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" MournHallId as hallId,MournNo as hallNo,MournName as hallName,hall.IsUse as asUse ");
                builder.append(" ,sItem.id as itemId,sItem.Name as itemName,sItem.Price as itemPrice ");
                SELECT(builder.toString());
                FROM(TableNames.MournHall + " hall ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = ServiceItemID");
                WHERE("  hall.IsDeleted = 0 and IsMourn = 0 ");
                ORDER_BY(" MournHallId ");
            }
        }.toString();
    }

    /**
     * 按照主键,加载守灵厅信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:19
     */
    public String getWakeHallListById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" MournHallId as hallId,MournNo as hallNo,MournName as hallName,hall.IsUse as asUse ");
                builder.append(" ,sItem.id as itemId,sItem.Name as itemName,sItem.Price as itemPrice ");
                SELECT(builder.toString());
                FROM(TableNames.MournHall + " hall ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = ServiceItemID");
                WHERE("  hall.IsDeleted = 0 and IsMourn = 0 ");
                WHERE(" hall.MournHallId = #{id} ");
            }
        }.toString();
    }

    /**
     * 利用时间查询守灵任务的逝者信息
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-05 13:35
     */
    public String listWakeHallDeadByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" id as mournId,MournHallID as hallId,ExecuteCircs as wakeState ");
                builder.append(" ,mourn.OperationNO as operationNo,DierName as deadName,DierAge as deadAge");
                builder.append(" ,BeginTime as beginTime,EndTime as endTime ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO ");
                WHERE(" ExecuteCircs in(0,1) and IsMourn= 0 ");

                // 预约时间查询条件
                StringBuilderExt whereExt = new StringBuilderExt();
                whereExt.line("(((ISNULL(#{beginTime},'') >= BeginTime and ISNULL(#{beginTime},'')<EndTime)")
                        .line(" or (ISNULL(#{endTime},'') > BeginTime and ISNULL(#{endTime},'')<=EndTime))")
                        .line(" or (ISNULL(#{beginTime},'')<BeginTime and ISNULL(#{endTime},'')>EndTime))");
                WHERE(whereExt.toString());
            }
        }.toString();
    }

    /**
     * 按照守灵厅主键查询当前占用守灵任务主键
     *
     * @param mournId   守灵任务排除主键
     * @param hallId    守灵厅主键
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:25
     */
    public String getWakeMournIdByHallId(@Param("mournId") Integer mournId, @Param("hallId") Integer hallId, @Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 id ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn");
                WHERE(" ExecuteCircs in(0,1) and IsMourn= 0 ");

                // 预约时间查询条件
                StringBuilderExt whereExt = new StringBuilderExt();
                whereExt.line("(((ISNULL(#{beginTime},'') >= BeginTime and ISNULL(#{beginTime},'')<EndTime)")
                        .line(" or (ISNULL(#{endTime},'') > BeginTime and ISNULL(#{endTime},'')<=EndTime))")
                        .line(" or (ISNULL(#{beginTime},'')<BeginTime and ISNULL(#{endTime},'')>EndTime))");
                WHERE(whereExt.toString());

                WHERE(" id!=ISNULL(#{mournId},0) and MournHallID=#{hallId} ");
            }
        }.toString();
    }

    /**
     * 查询当天守灵逝者任务信息
     *
     * @param beginTime 开始时间
     * @return
     * @author LiCongLu
     * @date 2020-08-05 14:20
     */
    public String listWakeHallDeadForBeginTime(@Param("beginTime") String beginTime) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" id as mournId,MournHallID as hallId,ExecuteCircs as wakeState ");
                builder.append(" ,mourn.OperationNO as operationNo,DierName as deadName,DierAge as deadAge");
                builder.append(" ,BeginTime as beginTime,EndTime as endTime ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO ");
                WHERE(" IsMourn= 0 ");
                WHERE(" Convert(varchar(10),BeginTime,120) = Convert(varchar(10),#{beginTime},120)");
                ORDER_BY(" BeginTime ");
            }
        }.toString();
    }

    /**
     * 按照主键加载守灵任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 10:45
     */
    public String getWakeMournById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 mourn.id as id,mourn.OperationNO as operationNo,hall.MournHallId as hallId,hall.MournName as hallName ");
                builder.append(" ,BeginTime as beginTime,EndTime endTime,ExecuteCircs as wakeState,mourn.Random_Id as randomId,mourn.Remark as remark,ISNULL(TaskFlag,0) as taskFlag ");
                builder.append(" ,charge.id as chargeId, charge.ServiceItem as itemId,charge.Price as price,charge.Number as number,charge.Charge as charge ");
                // 同一业务存在已结算费用，即视为结算
                builder.append(" ,ISNULL((select top 1 IsFinished from " + TableNames.Charge + " c where c.Random_Id = mourn.Random_Id");
                builder.append(" and c.OperationNO = mourn.OperationNO and c.IsDelete = 0 and c.IsFinished = 1 ),0) as asFinished ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO ");
                JOIN(TableNames.MournHall + " hall on hall.MournHallId = mourn.MournHallID ");
                JOIN(TableNames.Charge + "  charge on charge.OperationNO = mourn.OperationNO  " +
                        "  and charge.ServiceItem = hall.ServiceItemID and charge.Random_Id = mourn.Random_Id" +
                        "  and charge.IsDelete = 0 ");
                WHERE("  mourn.id = #{id} and mourn.IsMourn = 0 ");
            }
        }.toString();
    }

    /**
     * 新增守灵任务信息
     *
     * @param item      守灵任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:13
     */
    public String insertBusinessWakeMourn(@Param("item") MournItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.Mourn);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("MournHallID", "#{item.mournHallId}");
                VALUES("BeginTime", "Convert(varchar(16),#{item.beginTime},120)");
                VALUES("EndTime", "Convert(varchar(16),#{item.endTime},120)");
                VALUES("Charge", "#{item.charge}");
                VALUES("Price", "#{item.price}");
                VALUES("Remark", "#{item.remark}");
                VALUES("Random_Id", "#{item.randomId}");
                VALUES("ExecuteCircs", String.valueOf(Constants.MournState.Normal));
                VALUES("Operator", "#{loginItem.userId}");
                VALUES("OperateTime", "Convert(varchar(16),GETDATE(),120)");

                VALUES("IsMourn", "0");
                VALUES("IsActive", "1");
                VALUES("IsFlag", "0");
                VALUES("CreateDate", "Convert(varchar(16),GETDATE(),120)");
                VALUES("ServiceState", "0");
            }
        }.toString();
    }

    /**
     * 更新逝者关联守灵厅信息
     *
     * @param operationNo 业务编号
     * @param beginTime   开始时间
     * @param endTime     结束时间
     * @param hallItem    守灵厅信息
     * @param mournStatus 守灵状态
     * @param loginItem   当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:54
     */
    public String updateDeadWakeMourn(@Param("operationNo") String operationNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("hallItem") WakeHallListItem hallItem
            , @Param("mournStatus") String mournStatus, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Dead);

                SET(" MournID = #{hallItem.hallId}");
                SET(" MournName = #{hallItem.hallName}");
                if (DataUtil.valid(mournStatus)) {
                    SET(" MournStatus =  #{mournStatus}");
                }
                SET(" MournBeginTime = #{beginTime}");
                SET(" MournEndTime = #{endTime}");
                SET(" MournTypeID = #{hallItem.itemId}");
                SET(" MournTypeName = #{hallItem.itemName}");

                SET("Operator = #{loginItem.userCode}");
                SET("OperateTime = getdate()");
                WHERE(" OperationNO = #{operationNo} ");
            }
        }.toString();
    }

    /**
     * 更新守灵任务信息
     *
     * @param item      守灵任务信息
     * @param loginItem 当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 15:30
     */
    public String updateBusinessWakeMourn(@Param("item") WakeMournItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Mourn);
                SET("MournHallID = #{item.hallId}");
                SET("BeginTime = Convert(varchar(16),#{item.beginTime},120)");
                SET("EndTime = Convert(varchar(16),#{item.endTime},120)");
                SET("Charge = #{item.charge}");
                SET("Price = #{item.price}");
                SET("Remark = #{item.remark}");
                SET("Operator = #{loginItem.userId}");
                SET("OperateTime = Convert(varchar(16),GETDATE(),120)");
                WHERE(" id = #{item.id} and OperationNO = #{item.operationNo} ");
            }
        }.toString();
    }

    /**
     * 删除逝者信息里的守灵信息
     *
     * @param operationNo 业务编号
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:46
     */
    public String deleteDeadWakeMourn(@Param("operationNo") String operationNo, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Dead);

                SET(" MournID = null ");
                SET(" MournName = null ");
                SET(" MournStatus =  null ");
                SET(" MournBeginTime = null ");
                SET(" MournEndTime = null ");
                SET(" MournTypeID = null ");
                SET(" MournTypeName = null ");

                SET("Operator = #{loginItem.userCode}");
                SET("OperateTime = getdate()");
                WHERE(" OperationNO = #{operationNo} ");
            }
        }.toString();
    }
}
