package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.ChargeProvider;
import com.jmdz.fushan.dao.provider.FuneralProductProvider;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.business.FuneralProductItem;
import org.apache.ibatis.annotations.*;

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

    /**
     * 按照主键获取丧葬用品费用
     *
     * @param chargeId 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:34
     */
    @SelectProvider(type = FuneralProductProvider.class, method = "getFuneralProductChargeById")
    ChargeItem getFuneralProductChargeById(@Param("chargeId") Integer chargeId);

    /**
     * 更新丧葬用品费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:40
     */
    @UpdateProvider(type = FuneralProductProvider.class, method = "updateFuneralProductCharge")
    void updateFuneralProductCharge(@Param("item") ChargeItem item, @Param("userId") String userId);

    /**
     * 删除丧葬用品费用
     *
     * @param operationNo 业务编号
     * @param chargeId    费用主键
     * @param productId   丧葬用品主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 14:46
     */
    @Delete(" delete from " + TableNames.FuneralProduct + " where OperationNO = #{operationNo} and id = #{productId} " +
            "  and Random_Id in (select Random_Id from f_Charge where ServiceItemID = ServiceItem and id = #{chargeId} and IsFinished = 0 ) ; " +
            " delete from " + TableNames.Charge + " where OperationNO = #{operationNo} and id = #{chargeId} and IsFinished = 0 ;")
    void deleteFuneralProductCharge(@Param("operationNo") String operationNo, @Param("chargeId") Integer chargeId, @Param("productId") Integer productId);
}
