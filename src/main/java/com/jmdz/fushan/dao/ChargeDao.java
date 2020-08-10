package com.jmdz.fushan.dao;


import com.jmdz.fushan.dao.provider.ChargeProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.farewell.FarewellChargeItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllChargeItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * ChargeDao
 *
 * @author LiCongLu
 * @date 2020-06-10 13:38
 */
public interface ChargeDao {
    /**
     * 新增费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-06-10 13:39
     */
    @InsertProvider(type = ChargeProvider.class, method = "insertChargeItem")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertChargeItem(@Param("item") ChargeItem item, @Param("userId") String userId);

    /**
     * 更新业务费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-10 11:25
     */
    @UpdateProvider(type = ChargeProvider.class, method = "updateChargeItem")
    void updateChargeItem(@Param("item") ChargeItem item, @Param("userId") String userId);

    /**
     * 按照业务随机码查询费用集合，不包含业务费用
     *
     * @param operationNo 逝者编号
     * @param randomId    业务随机码
     * @param serviceId   业务费用服务主键
     * @return
     * @author LiCongLu
     * @date 2020-08-03 14:56
     */
    @SelectProvider(type = ChargeProvider.class, method = "listChargeByRandomId")
    ArrayList<ChargeItem> listChargeByRandomId(@Param("operationNo") String operationNo, @Param("randomId") String randomId, @Param("serviceId") Integer serviceId);

    /**
     * 按照标记查询殡葬产品费用集合
     *
     * @param operationNo 业务编号
     * @param biaokey     编号
     * @return
     * @author LiCongLu
     * @date 2020-08-03 15:38
     */
    @SelectProvider(type = ChargeProvider.class, method = "listProductChargeByBiaokey")
    ArrayList<ChargeItem> listProductChargeByBiaokey(@Param("operationNo") String operationNo, @Param("biaokey") Integer biaokey);

    /**
     * 按照主键获取物品服务
     *
     * @param chargeId 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-06-11 16:47
     */
    @SelectProvider(type = ChargeProvider.class, method = "getChargeById")
    ChargeItem getChargeById(@Param("chargeId") Integer chargeId);

    /**
     * 删除物品服务费用
     *
     * @param operationNo 逝者编号
     * @param chargeId    费用主键
     * @return
     * @author LiCongLu
     * @date 2020-06-11 17:01
     */
    @Delete(" delete from " + TableNames.ChargeServiceItemDetails + " where ChargeID in (select id from " + TableNames.Charge + " where OperationNO = #{operationNo} and id = #{chargeId} and IsFinished = 0);" +
            " delete from " + TableNames.Charge + " where OperationNO = #{operationNo} and id = #{chargeId} and IsFinished = 0;")
    void deleteChargeById(@Param("operationNo") String operationNo, @Param("chargeId") Integer chargeId);

    /**
     * 更新费用详情关联表
     *
     * @param chargeId 费用主键
     * @param itemId   主键
     * @param itemNum  数量
     * @return
     * @author LiCongLu
     * @date 2020-07-10 10:10
     */
    @Insert("delete from " + TableNames.ChargeServiceItemDetails + " where ChargeID=#{chargeId};" +
            "insert into " + TableNames.ChargeServiceItemDetails + " (ChargeID,ItemID,ItemNum) values(#{chargeId},#{itemId},#{itemNum});")
    void insertChargeServiceItemDetails(@Param("chargeId") int chargeId, @Param("itemId") Integer itemId, @Param("itemNum") BigDecimal itemNum);

    /**
     * 获取业务相关的费用列表
     *
     * @param operationNo   业务编号
     * @param randomId      随机码
     * @param parentId      所属父类
     * @param belongService 所属属性
     * @return
     * @author LiCongLu
     * @date 2020-07-10 10:12
     */
    @SelectProvider(type = ChargeProvider.class, method = "queryChargeItemForBusinessRandomId")
    ArrayList<ChargeItem> queryChargeItemForBusinessRandomId(@Param("operationNo") String operationNo, @Param("randomId") String randomId, @Param("parentId") String parentId, @Param("belongService") String belongService);

    /**
     * 获取业务相关的费用列表
     *
     * @param operationNoList 业务编号集合
     * @param parentId        所属父类
     * @param belongService   所属属性
     * @return
     * @author LiCongLu
     * @date 2020-07-10 15:39
     */
    @SelectProvider(type = ChargeProvider.class, method = "queryChargeItemForOperationNoList")
    ArrayList<ChargeItem> queryChargeItemForOperationNoList(@Param("operationNoList") ArrayList<String> operationNoList, @Param("parentId") String parentId, @Param("belongService") String belongService);

    /**
     * 按照查询时间及类型汇总费用
     *
     * @param data 查询时间
     * @return
     * @author LiCongLu
     * @date 2020-07-13 09:24
     */
    @SelectProvider(type = ChargeProvider.class, method = "countChargeForLeaderByType")
    ArrayList<LeaderAllChargeItem> countChargeForLeaderByType(@Param("data") LeaderAllData data);

    /**
     * 按照业务编号查询告别任务所需费用集合
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-14 10:58
     */
    @SelectProvider(type = ChargeProvider.class, method = "listFarewellChargeByOperationNo")
    ArrayList<FarewellChargeItem> listFarewellChargeByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 按照业务编号查询是否结算结果
     *
     * @param operationNo 业务编号
     * @param itemId      费用主键
     * @param randomId    随机编码
     * @return
     * @author LiCongLu
     * @date 2020-07-21 17:07
     */
    @Select(" select top 1 IsFinished from " + TableNames.Charge + " " +
            " where OperationNo = #{operationNo} and Random_Id = #{randomId} and ServiceItem = #{itemId} " +
            " order by id desc ")
    Integer getAsFinishedByRandomId(@Param("operationNo") String operationNo, @Param("itemId") Integer itemId, @Param("randomId") String randomId);

    /**
     * 按照主键获取丧葬用品费用
     *
     * @param chargeId 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:34
     */
    @SelectProvider(type = ChargeProvider.class, method = "getFuneralProductChargeById")
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
    @UpdateProvider(type = ChargeProvider.class, method = "updateFuneralProductCharge")
    void updateFuneralProductCharge(@Param("item") ChargeItem item, @Param("userId") String userId);

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
    @Delete(" delete from " + TableNames.FuneralProduct + " where OperationNO = #{operationNo} and id = #{productId} " +
            "  and Random_Id in (select Random_Id from f_Charge where ServiceItemID = ServiceItem and id = #{chargeId} and IsFinished = 0 ) ; " +
            " delete from " + TableNames.Charge + " where OperationNO = #{operationNo} and id = #{chargeId} and IsFinished = 0 ;")
    void deleteFuneralProductCharge(@Param("operationNo") String operationNo,@Param("chargeId") Integer chargeId, @Param("productId") Integer productId);

    /**
     * 更新业务费用 By OperationNo And RandomId
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 17:25
     */
    @UpdateProvider(type = ChargeProvider.class, method = "updateChargeItemByOperationNoAndRandomId")
    void updateChargeItemByOperationNoAndRandomId(@Param("item") ChargeItem item, @Param("userId") String userId);
}
