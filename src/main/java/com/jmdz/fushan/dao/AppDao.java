package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.AppProvider;
import com.jmdz.fushan.pad.model.vehicle.AppLocation;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author LiCongLu
 * @date 2020-07-09 16:06
 */
public interface AppDao {

    /**
     * 新增定位信息
     *
     * @param item 坐标信息
     * @return
     * @author LiCongLu
     * @date 2020-07-09 16:07
     */
    @InsertProvider(type = AppProvider.class, method = "insertAppLocation")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertAppLocation(@Param("item") AppLocation item);
}
