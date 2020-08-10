package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.ChargeProvider;
import com.jmdz.fushan.dao.provider.FaceLiftProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.business.FaceLiftChargeListItem;
import com.jmdz.fushan.pad.model.business.FaceLiftItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * FaceLiftDao
 *
 * @author LiCongLu
 * @date 2020-08-04 15:58
 */
public interface FaceLiftDao {

    /**
     * 新增整容服务
     *
     * @param item   整容服务
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 11:18
     */
    @InsertProvider(type = FaceLiftProvider.class, method = "insertFaceLiftItem")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertFaceLiftItem(@Param("item") FaceLiftItem item, @Param("userId") String userId);

    /**
     * 按照业务编号查询整容费用列表
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 16:51
     */
    @SelectProvider(type = FaceLiftProvider.class, method = "listFaceLiftChargeListByOperationNo")
    ArrayList<FaceLiftChargeListItem> listFaceLiftChargeListByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 通过费用主键加载整容费用信息
     *
     * @param chargeId 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 17:11
     */
    @SelectProvider(type = FaceLiftProvider.class, method = "getFaceLiftChargeListByChargeId")
    FaceLiftChargeListItem getFaceLiftChargeListByChargeId(@Param("chargeId") Integer chargeId);

    /**
     * 删除丧葬用品费用
     *
     * @param operationNo 业务编号
     * @param chargeId 费用主键
     * @param productId 丧葬用品主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 14:46
     */

    /**
     * 删除整容费用
     *
     * @param operationNo 业务编号
     * @param chargeId 费用主键
     * @param faceLiftId 整容主键
     * @return
     * @author LiCongLu
     * @date 2020-08-05 09:06
     */
    @Delete(" delete from " + TableNames.FaceLift + " where OperationNO = #{operationNo} and id = #{faceLiftId} " +
            "  and Random_Id in (select Random_Id from f_Charge where ServiceItemID = ServiceItem and id = #{chargeId} and IsFinished = 0 ) ; " +
            " delete from " + TableNames.Charge + " where OperationNO = #{operationNo} and id = #{chargeId} and IsFinished = 0 ;")
    void deleteFaceLiftCharge(@Param("operationNo") String operationNo,@Param("chargeId") Integer chargeId, @Param("faceLiftId") Integer faceLiftId);

    /**
     * 更新整容费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-05 09:26
     */
    @UpdateProvider(type = FaceLiftProvider.class, method = "updateFaceLiftCharge")
    void updateFaceLiftCharge(@Param("item")  FaceLiftChargeListItem item, @Param("userId") String userId);
}
