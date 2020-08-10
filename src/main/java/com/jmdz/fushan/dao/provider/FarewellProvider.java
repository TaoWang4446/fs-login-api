package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.dao.MournDao;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.MournItem;
import com.jmdz.fushan.pad.model.weixin.WxMournItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;


/**
 * FarewellProvider
 *
 * @author LiCongLu
 * @date 2020-06-08 11:00
 */
public class FarewellProvider {

    /**
     * 查询当天告别厅任务信息，包含完成的
     *
     * @param hallId 礼厅主键
     * @return
     * @author LiCongLu
     * @date 2020-06-08 11:00
     */
    public String listFarewellTaskListByHallId(@Param("hallId") Integer hallId) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  mourn.id as farewellId,dead.OperationNO as operationNo,DierName as deadName,DierSex as deadSex ");
                builder.append(" ,mourn.BeginTime as beginTime,mourn.EndTime as endTime");
                builder.append(" ,ISNULL(mourn.ExecuteCircs,0) as farewellState");
                builder.append(" ,ISNULL(mourn.TaskFlag,0) as taskFlag");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO");
                JOIN(TableNames.MournHall + "  hall on hall.MournHallID = mourn.MournHallID ");

                WHERE(" dead.IsDead = 1");
                WHERE(" hall.MournHallID = #{hallId} ");

                // 当天任务
                WHERE(" CONVERT(varchar(10),BeginTime,120) = CONVERT(varchar(10),GETDATE(),120) ");

                ORDER_BY(" mourn.BeginTime ");
            }
        }.toString();
    }

    /**
     * 按照主键获取告别任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-14 08:44
     */
    public String getFarewellTaskById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  top 1 mourn.id as farewellId,dead.OperationNO as operationNo,DierName as deadName,DierSex as deadSex ");
                builder.append(" ,mourn.BeginTime as beginTime,mourn.EndTime as endTime");
                builder.append(" ,ISNULL(mourn.ExecuteCircs,0) as farewellState");
                builder.append(" ,hall.MournHallID as hallId,hall.MournName as hallName");
                builder.append(" ,ISNULL(mourn.TaskFlag,0) as taskFlag");
                builder.append(" ,Random_Id as randomId");
                builder.append(" ,(select top 1 fMourn.id from " + TableNames.Mourn + " fMourn where fMourn.id != mourn.id ");
                builder.append("   and ExecuteCircs!=" + Constants.MournState.End + " and fMourn.MournHallID = mourn.MournHallID ");
                builder.append("   and CONVERT(varchar(10),fMourn.BeginTime,120) = CONVERT(varchar(10),GETDATE(),120) ");
                builder.append("   and ISNULL(TaskFlag,0)!=0 ) as executeId ");

                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO");
                JOIN(TableNames.MournHall + "  hall on hall.MournHallID = mourn.MournHallID ");

                WHERE(" dead.IsDead = 1");
                WHERE(" mourn.id = #{id} ");
            }
        }.toString();
    }

    /**
     * 按照主键查询告别任务信息集合
     *
     * @param idList 主键集合
     * @return
     * @author LiCongLu
     * @date 2020-07-15 16:34
     */
    public String listFarewellTaskByIds(@Param("idList") ArrayList<Integer> idList) {
        if (idList.size() == 0) {
            idList.add(0);
        }
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  mourn.id as farewellId,dead.OperationNO as operationNo,DierName as deadName,DierSex as deadSex ");
                builder.append(" ,mourn.BeginTime as beginTime,mourn.EndTime as endTime");
                builder.append(" ,ISNULL(mourn.ExecuteCircs,0) as farewellState");
                builder.append(" ,hall.MournHallID as hallId,hall.MournName as hallName");
                builder.append(" ,ISNULL(mourn.TaskFlag,0) as taskFlag");
                builder.append(" ,Random_Id as randomId");
                SELECT(builder.toString());
                FROM(TableNames.Mourn + " mourn ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNO");
                JOIN(TableNames.MournHall + "  hall on hall.MournHallID = mourn.MournHallID ");

                WHERE(" dead.IsDead = 1");

                // 当天任务
                WHERE(" CONVERT(varchar(10),BeginTime,120) = CONVERT(varchar(10),GETDATE(),120)");
                // 非已出厅任务
                WHERE(" ExecuteCircs != " + Constants.MournState.End);

                String ids = StringUtils.arrayToDelimitedString(idList.toArray(new Integer[]{}), ",");
                WHERE(" mourn.id in (" + ids + ") ");
            }
        }.toString();
    }


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
    public String updateFarewellTaskFlag(@Param("farewellId") Integer farewellId, @Param("operationNo") String operationNo, @Param("taskFlag") Integer taskFlag, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Mourn);
                SET(" TaskFlag = #{taskFlag}");
                SET(" Operator = #{loginItem.userId}");
                SET(" OperateTime= convert(varchar(16),getdate(),120)");
                WHERE(" id = #{farewellId} and OperationNO = #{operationNo} ");
            }
        }.toString();
    }

    /**
     * 新增告别任务信息，通过微信告别审核
     *
     * @param item      告别任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-21 15:49
     */
    public String insertFarewellWxMourn(@Param("item") MournItem item, @Param("loginItem") LoginItem loginItem) {
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
     * 审核告别时，更新逝者相关告别信息
     *
     * @param operationNo 业务编号
     * @param typeItem    告别类型信息
     * @param mournItem   告别信息
     * @param loginItem   登录信息
     * @return
     * @author LiCongLu
     * @date 2020-07-30 13:43
     */
    public String updateDeadFarewellWx(@Param("operationNo") String operationNo
            , @Param("typeItem") ServiceItem typeItem, @Param("mournItem") MournItem mournItem
            , @Param("loadItem") WxMournItem loadItem, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.Dead);

                SET(" FarewellID = #{mournItem.mournHallId}");
                SET(" FarewellName = #{loadItem.hallName}");
                SET(" FarewellStatus = '未入厅'");
                SET(" FarewellBeginTime = #{mournItem.beginTime}");
                SET(" FarewellEndTime = #{mournItem.endTime}");
                SET(" FarewellTypeID = #{typeItem.id}");
                SET(" FarewellTypeName = #{typeItem.name}");

                SET("Operator = #{loginItem.userCode}");
                SET("OperateTime = getdate()");
                WHERE(" OperationNO = #{operationNo} ");
            }
        }.toString();
    }
}
