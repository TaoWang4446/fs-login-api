package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.vehicle.AppLocation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * AppProvider
 *
 * @author LiCongLu
 * @date 2020-07-09 16:07
 */
public class AppProvider {

    /**
     * 新增定位信息
     *
     * @param item 坐标信息
     * @return
     * @author LiCongLu
     * @date 2020-07-09 16:07
     */
    public String insertAppLocation(@Param("item") AppLocation item) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.AppLocation);
                VALUES("loginId", "#{item.loginId}");
                VALUES("userId", "#{item.userId}");
                VALUES("locationTime", "getdate()");
                VALUES("longitude", "#{item.longitude}");
                VALUES("latitude", "#{item.latitude}");
                VALUES("locationCode", item.getLocationCode() == null ? "''" : "#{item.locationCode}");
                VALUES("locationFlag", "#{item.locationFlag}");
                VALUES("remark", "#{item.remark}");
                VALUES("updateTime", "getdate()");
                VALUES("operationNo", "#{item.operationNo}");
            }
        }.toString();
    }

}
