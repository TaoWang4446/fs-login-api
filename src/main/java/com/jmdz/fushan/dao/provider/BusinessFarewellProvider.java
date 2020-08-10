package com.jmdz.fushan.dao.provider;

import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.farewell.FarewellHallListItem;
import com.jmdz.fushan.pad.model.business.farewell.FarewellMournItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.MournItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * BusinessFarewellProvider
 *
 * @author LiCongLu
 * @date 2020-08-06 11:25
 */
public class BusinessFarewellProvider {

    /**
     * 按照业务编号查询告别信息
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 15:09
     */
    public String listFarewellMournListByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" mourn.id as id,mourn.OperationNO as operationNo,hall.MournName as hallName ");
                builder.append(" ,BeginTime as beginTime,EndTime endTime,ExecuteCircs as farewellState,mourn.Remark as remark ");
                builder.append(" ,charge.Charge as charge ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO ");
                JOIN(TableNames.MournHall + " hall on hall.MournHallId = mourn.MournHallID ");
                JOIN(TableNames.Charge + "  charge on charge.OperationNO = mourn.OperationNO  " +
                        "  and charge.ServiceItem = hall.ServiceItemID and charge.Random_Id = mourn.Random_Id" +
                        "  and charge.IsDelete = 0 ");
                WHERE("  mourn.OperationNO = #{operationNo} and mourn.IsMourn = 1 ");
                ORDER_BY(" BeginTime ");
            }
        }.toString();
    }

    /**
     * 加载告别厅信息
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:13
     */
    public String listFarewellHallList() {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" MournHallId as hallId,MournNo as hallNo,MournName as hallName,hall.IsUse as asUse ");
                builder.append(" ,sItem.id as itemId,sItem.Name as itemName,sItem.Price as itemPrice ");
                SELECT(builder.toString());
                FROM(TableNames.MournHall + " hall ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = ServiceItemID");
                WHERE("  hall.IsDeleted = 0 and IsMourn = 1 ");
                ORDER_BY(" MournHallId ");
            }
        }.toString();
    }

    /**
     * 按照主键,加载告别厅信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:19
     */
    public String getFarewellHallListById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" MournHallId as hallId,MournNo as hallNo,MournName as hallName,hall.IsUse as asUse ");
                builder.append(" ,sItem.id as itemId,sItem.Name as itemName,sItem.Price as itemPrice ");
                SELECT(builder.toString());
                FROM(TableNames.MournHall + " hall ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = ServiceItemID");
                WHERE("  hall.IsDeleted = 0 and IsMourn = 1 ");
                WHERE(" hall.MournHallId = #{id} ");
            }
        }.toString();
    }

    /**
     * 利用时间查询告别任务的逝者信息
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:35
     */
    public String listFarewellHallDeadByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" id as mournId,MournHallID as hallId,ExecuteCircs as farewellState ");
                builder.append(" ,mourn.OperationNO as operationNo,DierName as deadName,DierAge as deadAge");
                builder.append(" ,BeginTime as beginTime,EndTime as endTime ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO ");
                WHERE(" ExecuteCircs in(0,1) and IsMourn= 1 ");

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
     * 按照告别厅主键查询当前占用告别任务主键
     *
     * @param mournId   告别任务排除主键
     * @param hallId    告别厅主键
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:25
     */
    public String getFarewellMournIdByHallId(@Param("mournId") Integer mournId, @Param("hallId") Integer hallId, @Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 id ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn");
                WHERE(" ExecuteCircs in(0,1) and IsMourn= 1 ");

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
     * 查询当天告别逝者任务信息
     *
     * @param beginTime 开始时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 14:20
     */
    public String listFarewellHallDeadForBeginTime(@Param("beginTime") String beginTime) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" id as mournId,MournHallID as hallId,ExecuteCircs as farewellState ");
                builder.append(" ,mourn.OperationNO as operationNo,DierName as deadName,DierAge as deadAge");
                builder.append(" ,BeginTime as beginTime,EndTime as endTime ");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO ");
                WHERE(" IsMourn= 1 ");
                WHERE(" Convert(varchar(10),BeginTime,120) = Convert(varchar(10),#{beginTime},120)");
                ORDER_BY(" BeginTime ");
            }
        }.toString();
    }

    /**
     * 按照主键加载告别任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 10:45
     */
    public String getFarewellMournById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 mourn.id as id,mourn.OperationNO as operationNo,hall.MournHallId as hallId,hall.MournName as hallName ");
                builder.append(" ,BeginTime as beginTime,EndTime endTime,ExecuteCircs as farewellState,mourn.Random_Id as randomId,mourn.Remark as remark,ISNULL(TaskFlag,0) as taskFlag ");
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
                WHERE("  mourn.id = #{id} and mourn.IsMourn = 1 ");
            }
        }.toString();
    }

    /**
     * 新增告别任务信息
     *
     * @param item      告别任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:13
     */
    public String insertBusinessFarewellMourn(@Param("item") MournItem item, @Param("loginItem") LoginItem loginItem) {
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

                VALUES("IsMourn", "1");
                VALUES("IsActive", "1");
                VALUES("IsFlag", "0");
                VALUES("CreateDate", "Convert(varchar(16),GETDATE(),120)");
                VALUES("ServiceState", "0");
            }
        }.toString();
    }

    /**
     * 更新逝者关联告别厅信息
     *
     * @param operationNo    业务编号
     * @param beginTime      开始时间
     * @param endTime        结束时间
     * @param hallItem       告别厅信息
     * @param farewellStatus 告别状态
     * @param loginItem      当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:54
     */
    public String updateDeadFarewellMourn(@Param("operationNo") String operationNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("hallItem") FarewellHallListItem hallItem
            , @Param("farewellStatus") String farewellStatus, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Dead);

                SET(" FarewellID = #{hallItem.hallId}");
                SET(" FarewellName = #{hallItem.hallName}");
                if (DataUtil.valid(farewellStatus)) {
                    SET(" FarewellStatus =  #{farewellStatus}");
                }
                SET(" FarewellBeginTime = #{beginTime}");
                SET(" FarewellEndTime = #{endTime}");
                SET(" FarewellTypeID = #{hallItem.itemId}");
                SET(" FarewellTypeName = #{hallItem.itemName}");

                SET("Operator = #{loginItem.userCode}");
                SET("OperateTime = getdate()");
                WHERE(" OperationNO = #{operationNo} ");
            }
        }.toString();
    }

    /**
     * 更新告别任务信息
     *
     * @param item      告别任务信息
     * @param loginItem 当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 15:30
     */
    public String updateBusinessFarewellMourn(@Param("item") FarewellMournItem item, @Param("loginItem") LoginItem loginItem) {
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
     * 清除逝者告别信息
     *
     * @param operationNo 业务编号
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:43
     */
    public String deleteDeadFarewellMourn(@Param("operationNo") String operationNo, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Dead);

                SET(" FarewellID = null");
                SET(" FarewellName = null");
                SET(" FarewellStatus =  null");
                SET(" FarewellBeginTime = null");
                SET(" FarewellEndTime =null");
                SET(" FarewellTypeID = null");
                SET(" FarewellTypeName = null");

                SET("Operator = #{loginItem.userCode}");
                SET("OperateTime = getdate()");
                WHERE(" OperationNO = #{operationNo} ");
            }
        }.toString();
    }

    /**
     * 获取告别套餐服务信息
     *
     * @param code 套餐编码
     * @return
     * @author LiCongLu
     * @date 2020-08-07 11:25
     */
    public String listFarewellSuitServiceByCode(@Param("code") String code) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" sItem.id as id,sItem.Name as name,sItem.DisplayName as displayName,sItem.Price as price,sItem.prickle as unit ");
                builder.append(" ,details.ItemDetailsId as suitId ");
                SELECT(builder.toString());
                FROM(TableNames.TaoCanItemMourn +" taoCan ");
                JOIN(TableNames.ServiceItem + "  sItem on sItem.id = taoCan.ServiceItemId ");
                JOIN(TableNames.ItemDetails + " details on details.ItemDetailsId = taoCan.ItemTypeName ");
                JOIN(TableNames.Items +" items on items.ItemsId = details.ItemsId ");
                WHERE(" sItem.IsDeleted = 0");
                WHERE(" items.Code = #{code} ");
                ORDER_BY(" taoCan.id ");
            }
        }.toString();
    }
}
