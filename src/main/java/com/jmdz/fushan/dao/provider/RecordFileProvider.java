package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.entity.RecordFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


/**
 * RecordFileProvider
 *
 * @author LiCongLu
 * @date 2020-06-12 15:28
 */
public class RecordFileProvider {

    /**
     * 新增文件
     *
     * @param item   文件记录
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-16 14:29
     */
    public String insertRecordFile(@Param("item") RecordFile item, @Param("userId") String userId) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.RecordFile);
                VALUES("operationNo", "#{item.operationNo}");
                VALUES("fileName", "#{item.fileName}");
                VALUES("filePath", "#{item.filePath}");
                VALUES("fileLength", "#{item.fileLength}");
                VALUES("fileType", "#{item.fileType}");
                VALUES("fileCode", "#{item.fileCode}");
                VALUES("remark", "#{item.remark}");
                VALUES("userId", "#{userId}");
                VALUES("updateTime", "getdate()");
            }
        }.toString();
    }
}
