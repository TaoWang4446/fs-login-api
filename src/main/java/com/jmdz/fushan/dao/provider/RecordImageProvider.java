package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.entity.RecordImage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * RecordImageProvider
 *
 * @author LiCongLu
 * @date 2020-06-12 09:54
 */
public class RecordImageProvider {

    /**
     * 新增备案资料图片
     *
     * @param item   图片记录
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:53
     */
    public String insertRecordImage(@Param("item") RecordImage item, @Param("userId") String userId) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.RecordImage);
                VALUES("operationNo", "#{item.operationNo}");
                VALUES("listId", "#{item.listId}");
                VALUES("imageName", "#{item.imageName}");
                VALUES("imagePath", "#{item.imagePath}");
                VALUES("imageLength", "#{item.imageLength}");
                VALUES("thumbPath", "#{item.thumbPath}");
                VALUES("imageCode", "#{item.imageCode}");
                VALUES("remark", "#{item.remark}");
                VALUES("userId", "#{userId}");
                VALUES("updateTime", "getdate()");
            }
        }.toString();
    }
}
