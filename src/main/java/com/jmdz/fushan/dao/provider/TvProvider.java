package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * TvProvider
 *
 * @author LiCongLu
 * @date 2020-08-07 15:16
 */
public class TvProvider {

    /**
     * 获取冷藏整容电视左侧信息集合
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-07 15:20
     */
    public String listColdFaceTvLeft() {
        // 出藏任务
        String chuCangSql = new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP (100) PERCENT task.id as taskId,dead.OperationNO as operationNo,DierName as deadName ");
                builder.append(" ,equipment.No as noName ");
                SELECT(builder.toString());
                FROM(TableNames.TaskRemind + " task ");
                LEFT_OUTER_JOIN(TableNames.Dead + " dead on dead.OperationNO = task.OperationNo ");
                LEFT_OUTER_JOIN(TableNames.ColdStorage + " cold on cold.OperationNO = dead.OperationNO and cold.id = task.BusinessID ");
                LEFT_OUTER_JOIN(TableNames.ColdStorageEquipment + " equipment on equipment.id = cold.ColdStorageNO ");
                WHERE(" dead.IsDead = 1");
                WHERE(" cold.RemainsState = " + Constants.ColdStorageState.In);
                WHERE(" SourceModule ='出藏任务' and TableName = 'f_ColdStorage' ");
                WHERE(" CONVERT(varchar(10),SendTime,120) = CONVERT(varchar(10),GETDATE(),120) ");
                ORDER_BY(" task.id ");
            }
        }.toString();

        // 出厅任务
        String chuTingSql = new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  TOP (100) PERCENT task.id as taskId,dead.OperationNO as operationNo,DierName as deadName ");
                builder.append(" ,hall.MournName as noName ");
                SELECT(builder.toString());
                FROM(TableNames.TaskRemind + " task ");
                LEFT_OUTER_JOIN(TableNames.Dead + " dead on dead.OperationNO = task.OperationNo ");
                LEFT_OUTER_JOIN(TableNames.Mourn + " mourn on mourn.OperationNO = dead.OperationNO and mourn.id = task.BusinessID ");
                LEFT_OUTER_JOIN(TableNames.MournHall + " hall on hall.MournHallId = mourn.MournHallID ");
                WHERE(" dead.IsDead = 1");
                WHERE(" mourn.ExecuteCircs = " + Constants.MournState.Begin);
                WHERE(" SourceModule ='出厅任务' and TableName = 'f_Mourn' ");
                WHERE(" CONVERT(varchar(10),SendTime,120) = CONVERT(varchar(10),GETDATE(),120) ");

                ORDER_BY(" task.id ");
            }
        }.toString();

        // 拼接脚本
        StringBuilder builder = new StringBuilder();
        builder.append(" select * from ( " + chuCangSql + " ) a");
        builder.append(" union all ");
        builder.append(" select * from ( " + chuTingSql + " ) b");
        return " select distinct taskId,operationNo,deadName,noName from ( " + builder.toString() + " ) c order by c.taskId ";
    }

    /**
     * 获取冷藏整容电视右侧信息集合
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-07 16:58
     */
    public String listColdFaceTvRight() {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" face.OperationNO as operationNo,DierName as deadName,sItem.Name as itemName ");
                builder.append(" ,CONVERT(varchar(16),face.ExcuteTime,120) as excuteTime ");
                SELECT(builder.toString());
                FROM(TableNames.FaceLift + " face ");
                LEFT_OUTER_JOIN(TableNames.Dead + " dead on dead.OperationNO = face.OperationNO ");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + "  sItem on sItem.id = face.ServiceItemID ");
                WHERE(" dead.IsDead = 1");
                WHERE(" CONVERT(varchar(10),face.ExcuteTime,120) = CONVERT(varchar(10),GETDATE(),120) ");
                ORDER_BY(" CONVERT(varchar(16),face.ExcuteTime,120),face.OperationNO ");
            }
        }.toString();
    }
}
