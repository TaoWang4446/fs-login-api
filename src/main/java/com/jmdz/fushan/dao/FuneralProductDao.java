package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.ChargeProvider;
import com.jmdz.fushan.dao.provider.FuneralProductProvider;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.business.FuneralProductItem;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * FuneralProductDao
 *
 * @author LiCongLu
 * @date 2020-08-04 11:16
 */
public interface FuneralProductDao {

    /**
     * 新增丧葬用品服务
     *
     * @param item   丧葬服务物品
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 11:18
     */
    @InsertProvider(type = FuneralProductProvider.class, method = "insertFuneralProductItem")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertFuneralProductItem(@Param("item") FuneralProductItem item, @Param("userId") String userId);
}
