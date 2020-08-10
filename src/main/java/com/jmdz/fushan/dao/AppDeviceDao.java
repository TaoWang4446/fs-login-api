package com.jmdz.fushan.dao;


import com.jmdz.fushan.dao.provider.AppDeviceProvider;
import com.jmdz.fushan.model.config.TableNames;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * AppDeviceDao
 *
 * @author LiCongLu
 * @date 2020-07-09 09:14
 */
public interface AppDeviceDao {

    /**
     * 查询注册主键
     *
     * @param deviceId 设备唯一码
     * @return
     * @author LiCongLu
     * @date 2020-07-09 09:19
     */
    @Select("select id from " + TableNames.AppDevice + " where deviceId=#{deviceId}")
    @ResultType(Integer.class)
    Integer getAppIdByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 插入设备信息
     *
     * @param deviceId 设备信息
     * @return
     * @author LiCongLu
     * @date 2020-07-09 09:16
     */
    @InsertProvider(type = AppDeviceProvider.class, method = "insertDeviceId")
    void insertDeviceId(@Param("deviceId") String deviceId);

}
