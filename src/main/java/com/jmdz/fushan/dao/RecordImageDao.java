package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.RecordImageProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.entity.RecordImage;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * RecordImageDao
 *
 * @author LiCongLu
 * @date 2020-06-12 09:51
 */
public interface RecordImageDao {
    /**
     * 通过业务编号获取备案资料图片
     *
     * @param operationNo 业务编号
     * @param listCode    列表编号
     * @param imageCode   图片编号
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:51
     */
    @Select("select TOP 1 image.id,operationNo,listId,imageName,imagePath,imageLength,thumbPath,imageCode,remark,Convert(varchar,image.updateTime,120) as updateTime" +
            " from " + TableNames.RecordImage + " image join " + TableNames.RecordList + " list on image.listId = list.id" +
            " where list.listCode=#{listCode} and operationNo=#{operationNo} and imageCode=#{imageCode}" +
            " order by image.id desc")
    RecordImage getRecordImageByOperationNo(@Param("operationNo") String operationNo, @Param("listCode") int listCode, @Param("imageCode") int imageCode);

    /**
     * 通过业务编号获取备案资料图片集合
     *
     * @param operationNo 业务编号
     * @param listCode    列表编号
     * @param imageCode   图片编号
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:52
     */
    @Select("select image.id,operationNo,listId,imageName,imagePath,imageLength,thumbPath,imageCode,remark,Convert(varchar,image.updateTime,120) as updateTime" +
            " from " + TableNames.RecordImage + " image join " + TableNames.RecordList + " list on image.listId = list.id" +
            " where list.listCode=#{listCode} and operationNo=#{operationNo} and imageCode=#{imageCode}" +
            " order by image.id")
    ArrayList<RecordImage> listRecordImageByOperationNo(@Param("operationNo") String operationNo, @Param("listCode") int listCode,
                                                        @Param("imageCode") int imageCode);

    /**
     * 通过业务编号获取备案资料图片集合
     *
     * @param operationNo 业务编号
     * @param listCode    列表编号
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:52
     */
    @Select("select image.id,operationNo,listId,imageName,imagePath,imageLength,thumbPath,imageCode,remark,Convert(varchar,image.updateTime,120) as updateTime" +
            " from " + TableNames.RecordImage + " image join " + TableNames.RecordList + " list on image.listId = list.id" +
            " where list.listCode=#{listCode} and operationNo=#{operationNo} " +
            " order by image.id")
    ArrayList<RecordImage> listRecordImageByListCode(@Param("operationNo") String operationNo, @Param("listCode") int listCode);

    /**
     * 获取最新列表
     *
     * @param operationNo 逝者编号
     * @param listId      列表主键
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:52
     */
    @Select("select id,operationNo,listId,imageName,imagePath,imageLength,thumbPath,Convert(varchar,updateTime,120) as updateTime from " + TableNames.RecordImage +
            " where operationNo=#{operationNo} and listId=#{listId} order by id desc")
    ArrayList<RecordImage> listRecordImageByListId(@Param("operationNo") String operationNo, @Param("listId") int listId);

    /**
     * 新增备案资料图片
     *
     * @param item   图片记录
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:53
     */
    @InsertProvider(type = RecordImageProvider.class, method = "insertRecordImage")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertRecordImage(@Param("item") RecordImage item, @Param("userId") String userId);

    /**
     * 通过id获取备案资料图片
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:54
     */
    @Select("select id,operationNo,listId,imageName,imagePath,imageLength,thumbPath,imageCode,remark" +
            " from " + TableNames.RecordImage + " where id=#{id}")
    RecordImage getRecordImageById(@Param("id") int id);

    /**
     * 删除图片文件
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:55
     */
    @Delete("delete from " + TableNames.RecordImage + " where id=#{id}")
    void deleteRecordImage(@Param("id") int id);

    /**
     * 删除某个值的文件
     *
     * @param operationNo 逝者编号
     * @param imageCode   图片编码
     * @return
     * @author LiCongLu
     * @date 2020-06-12 09:55
     */
    @Delete("delete from " + TableNames.RecordImage + " where operationNo=#{operationNo} and imageCode=#{imageCode}")
    void deleteRecordImageByOperationNo(@Param("operationNo") String operationNo, @Param("imageCode") int imageCode);
}