package com.jmdz.fushan.dao.provider;

import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.task.TaskNoticeData;
import com.jmdz.fushan.pad.model.task.TaskRemindItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * TaskRemindProvider
 *
 * @author LiCongLu
 * @date 2020-07-15 14:06
 */
public class TaskRemindProvider {

    /**
     * 新增提醒任务信息列表
     *
     * @param item 提醒任务
     * @return
     * @author LiCongLu
     * @date 2020-07-15 14:11
     */
    public String insertTaskRemindItem(@Param("item") TaskRemindItem item) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.TaskRemind);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("SourceModule", "#{item.sourceModule}");
                VALUES("TargetModule", "#{item.targetModule}");
                VALUES("Describe", "#{item.describe}");
                VALUES("SendTime", "getdate()");
                VALUES("State", "1");
                VALUES("TableName", "#{item.tableName}");
                VALUES("BusinessID", "#{item.businessId}");
            }
        }.toString();
    }

    /**
     * 按照业务类型查询任务提醒内容
     *
     * @param tableName 业务表
     * @return
     * @author LiCongLu
     * @date 2020-07-15 15:44
     */
    public String listTaskRemindByTableName(@Param("tableName") String tableName) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  id,OperationNo as operationNo,SourceModule as sourceModule,TargetModule as targetModule,Describe as describe ");
                builder.append(" ,Convert(varchar(19),SendTime,120) as sendTime,Convert(varchar(19),ReceiveTime,120) as receiveTime");
                builder.append(" ,State as state,TableName as tableName,BusinessID as businessId ");
                SELECT(builder.toString());
                FROM(TableNames.TaskRemind);
                WHERE(" TableName = #{tableName} ");
                // 当天发送或者未接收任务
                WHERE(" ( Convert(varchar(10),SendTime,120) = Convert(varchar(10),GETDATE(),120) or State = 1 ) ");
                ORDER_BY(" id desc ");
            }
        }.toString();
    }

    /**
     * 批量更新接收任务提醒信息
     *
     * @param idList 主键集合
     * @return
     * @author LiCongLu
     * @date 2020-07-15 16:59
     */
    public String updateReceiveStateByIds(@Param("idList") ArrayList<Integer> idList) {
        return new SQL() {
            {
                UPDATE(TableNames.TaskRemind);
                SET(" State = 2");
                SET(" ReceiveTime = getdate()");
                String ids = StringUtils.arrayToDelimitedString(idList.toArray(new Integer[]{}), ",");
                WHERE(" id in (" + ids + ") ");
                WHERE(" State = 1 ");
            }
        }.toString();
    }

    /**
     * 按照任务类别查询提醒任务
     *
     * @param sourceList 任务类别
     * @param loginItem  当前账号
     * @param viewTime   查询时间
     * @return
     * @author LiCongLu
     * @date 2020-07-29 14:42
     */
    public String listTaskNoticeForView(@Param("sourceList") ArrayList<String> sourceList, @Param("loginItem") LoginItem loginItem, @Param("viewTime") Integer viewTime) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  task.id as id,SourceModule as module,SourceModule as title,Describe as content ");
                SELECT(builder.toString());
                FROM(TableNames.TaskRemind + " task ");
                LEFT_OUTER_JOIN(TableNames.TaskRemindView + " taskView on taskView.remindId = task.id and taskView.userId = #{loginItem.userId} and taskView.loginId = #{loginItem.loginId} ");
                WHERE(" taskView.id is null ");

                String sources = StringUtils.arrayToDelimitedString(sourceList.toArray(new String[]{}), "','");
                WHERE(" SourceModule in ('" + sources + "') ");

                // 当天发送或者未接收任务
                WHERE(" CONVERT(varchar(19),SendTime,120)>= CONVERT(varchar(19),DATEADD(SS,-" + viewTime + ",GETDATE()),120) ");
                ORDER_BY(" task.id desc ");
            }
        }.toString();
    }

    /**
     * 插入任务提醒查看
     *
     * @param remindId  任务提醒主键
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-29 15:52
     */
    public String insertTaskRemindView(@Param("remindId") Integer remindId, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.TaskRemindView);
                VALUES("remindId", "#{remindId}");
                VALUES("userId", "#{loginItem.userId}");
                VALUES("loginId", "#{loginItem.loginId}");
                VALUES("viewTime", "getdate()");
            }
        }.toString();
    }
}
