package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.VehicleManageProvider;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.VehicleManageItem;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * VehicleManageDao
 *
 * @author LiCongLu
 * @date 2020-07-17 10:20
 */
public interface VehicleManageDao {
    /**
     * 新增接运管理信息
     *
     * @param item      接运任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 10:20
     */
    @InsertProvider(type = VehicleManageProvider.class, method = "insertVehicleManage")
    void insertVehicleManage(@Param("item") VehicleManageItem item, @Param("loginItem") LoginItem loginItem);
}
