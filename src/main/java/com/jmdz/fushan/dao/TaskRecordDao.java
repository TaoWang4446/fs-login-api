package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.TaskRecordProvider;
import com.jmdz.fushan.pad.model.task.TaskRecordListItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

/**
 * TaskRecordDao
 *
 * @author LiCongLu
 * @date 2020-07-15 08:57
 */
public interface TaskRecordDao {

    /**
     * 按照业务编号查询费用任务记录
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-15 09:00
     */
    @SelectProvider(type = TaskRecordProvider.class, method = "listTaskRecordByOperationNo")
    ArrayList<TaskRecordListItem> listTaskRecordByOperationNo(@Param("operationNo") String operationNo);
}
