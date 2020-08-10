package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * AppDeviceProvider
 *
 * @author LiCongLu
 * @date 2020-07-09 09:16
 */
public class AppDeviceProvider {

    /**
     * 插入设备信息
     *
     * @param deviceId 设备信息
     * @return
     * @author LiCongLu
     * @date 2020-07-09 09:16
     */
    public String insertDeviceId(@Param("deviceId") String deviceId) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.AppDevice);
                VALUES("deviceId", "#{deviceId}");
                VALUES("createTime", "getdate()");
            }
        }.toString();
    }
}
