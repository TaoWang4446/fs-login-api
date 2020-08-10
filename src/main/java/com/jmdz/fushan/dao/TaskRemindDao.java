package com.jmdz.fushan.dao;

import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.fushan.dao.provider.TaskRemindProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.task.TaskNoticeVo;
import com.jmdz.fushan.pad.model.task.TaskRemindItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * 任务提醒表
 *
 * @author LiCongLu
 * @date 2020-07-15 13:59
 */
public interface TaskRemindDao {

    /**
     * 新增提醒任务信息列表
     *
     * @param item 提醒任务
     * @return
     * @author LiCongLu
     * @date 2020-07-15 14:11
     */
    @InsertProvider(type = TaskRemindProvider.class, method = "insertTaskRemindItem")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertTaskRemindItem(@Param("item") TaskRemindItem item);

    /**
     * 按照业务类型查询任务提醒内容
     *
     * @param tableName 业务表
     * @return
     * @author LiCongLu
     * @date 2020-07-15 15:44
     */
    @SelectProvider(type = TaskRemindProvider.class, method = "listTaskRemindByTableName")
    ArrayList<TaskRemindItem> listTaskRemindByTableName(@Param("tableName") String tableName);

    /**
     * 批量更新接收任务提醒信息
     *
     * @param idList 主键集合
     * @return
     * @author LiCongLu
     * @date 2020-07-15 16:59
     */
    @UpdateProvider(type = TaskRemindProvider.class, method = "updateReceiveStateByIds")
    void updateReceiveStateByIds(@Param("idList") ArrayList<Integer> idList);

    /**
     * 更新接收任务提醒信息
     *
     * @param sourceModule 信息模块
     * @param tableName    表名
     * @param businessId   业务主键
     * @return
     * @author LiCongLu
     * @date 2020-07-23 13:27
     */
    @Update(" update " + TableNames.TaskRemind + " set State = 2,ReceiveTime = getdate() " +
            "  where SourceModule = #{sourceModule} and TableName = #{tableName} and BusinessID = #{businessId} " +
            "  and ISNULL(State,0) = 1 ")
    void updateReceiveStateByBusinessId(@Param("sourceModule") String sourceModule, @Param("tableName") String tableName, @Param("businessId") String businessId);

    /**
     * 按照任务类别查询提醒任务
     *
     * @param sourceList 任务类别
     * @param loginItem 当前账号
     * @param viewTime 查询时间
     * @return
     * @author LiCongLu
     * @date 2020-07-29 14:42
     */
    @SelectProvider(type = TaskRemindProvider.class, method = "listTaskNoticeForView")
    ArrayList<TaskNoticeVo> listTaskNoticeForView(@Param("sourceList") ArrayList<String> sourceList,@Param("loginItem") LoginItem loginItem,@Param("viewTime") Integer viewTime);

    /**
     * 插入任务提醒查看
     * 
     * @param remindId 任务提醒主键
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-29 15:52
     */
    @InsertProvider(type = TaskRemindProvider.class, method = "insertTaskRemindView")
    void insertTaskRemindView(@Param("remindId") Integer remindId,@Param("loginItem") LoginItem loginItem);
}
