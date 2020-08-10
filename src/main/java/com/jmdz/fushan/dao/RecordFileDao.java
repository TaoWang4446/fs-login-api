package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.RecordFileProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.entity.RecordFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;


/**
 * RecordFileDao
 *
 * @author LiCongLu
 * @date 2020-06-12 15:32
 */
public interface RecordFileDao {

    /**
     * 新增备案文件
     *
     * @param item   记录文件
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-06-12 15:32
     */
    @InsertProvider(type = RecordFileProvider.class, method = "insertRecordFile")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertRecordFile(@Param("item") RecordFile item, @Param("userId") String userId);


    /**
     * 删除备案文件
     *
     * @param operationNo 逝者编号
     * @param fileCode    文件编码
     * @return
     * @author LiCongLu
     * @date 2020-06-12 15:33
     */
    @Delete("delete from " + TableNames.RecordFile + " where operationNo=#{operationNo} and fileCode=#{fileCode}")
    void deleteRecordFileByOperationNo(@Param("operationNo") String operationNo, @Param("fileCode") int fileCode);
}