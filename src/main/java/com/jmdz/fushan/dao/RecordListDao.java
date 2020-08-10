package com.jmdz.fushan.dao;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.entity.RecordList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * RecordListDao
 *
 * @author LiCongLu
 * @date 2020-06-12 09:55
 */
public interface RecordListDao {

    /**
     * 依照目录类型之获取某个资料备案目录 主要用于获取默认接运任务目录
     *
     * @param listCode 列表编码
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:56
     */
    @Select("select top 1 id,listName,listCode,listOrder,userId,Convert(varchar,updateTime,120) as updateTime from " + TableNames.RecordList +
            " where listCode=#{listCode} order by listOrder")
    RecordList getRecordListByCode(@Param("listCode") int listCode);

    /**
     * 获取最新列表
     *
     * @param listCode 列表编码
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:56
     */
    @Select("select id,hideFlag,listName,listCode,listOrder,userId,Convert(varchar,updateTime,120) as updateTime from " + TableNames.RecordList +
            " where hideFlag=0 and listCode=#{listCode} order by listOrder")
    ArrayList<RecordList> listRecordList(@Param("listCode") int listCode);
}
