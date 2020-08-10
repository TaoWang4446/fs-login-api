package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.business.FaceLiftChargeListItem;
import com.jmdz.fushan.pad.model.business.FaceLiftItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * FaceLiftProvider
 *
 * @author LiCongLu
 * @date 2020-08-04 15:58
 */
public class FaceLiftProvider {

    /**
     * 新增丧葬用品服务
     *
     * @param item   丧葬服务物品
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 11:18
     */
    public String insertFaceLiftItem(@Param("item") FaceLiftItem item, @Param("userId") String userId) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.FaceLift);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("ServiceItemID", "#{item.serviceItemId}");
                VALUES("Price", "#{item.price}");
                VALUES("FaceLiftNum", "#{item.faceLiftNum}");
                VALUES("Charge", "#{item.charge}");
                VALUES("Random_Id", "#{item.randomId}");
                VALUES("Remark", "#{item.remark}");
                VALUES("IsFinished", "0");
                VALUES("ChargeingPerson", "#{userId}");
                if (DataUtil.valid(item.getExcuteTime())) {
                    VALUES("ExcuteTime", "#{item.excuteTime}");
                }
                VALUES("Operator", "#{userId}");
                VALUES("OperateTime", "getdate()");
            }
        }.toString();
    }

    /**
     * 按照业务编号查询整容费用列表
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 16:51
     */
    public String listFaceLiftChargeListByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" charge.id,charge.OperationNO as operationNo ");
                builder.append(" ,sItem.id as itemId,sItem.Name as itemName");
                builder.append(" ,charge.Number as number,charge.Price as price,charge.Charge as charge ");
                builder.append(" ,charge.IsFinished as asFinished");

                builder.append(" ,faceLift.IsFinished as asFaceLiftFinished,faceLift.Remark as remark");
                builder.append(" ,faceLift.ExcuteTime as excuteTime ,faceLift.id as faceLiftId ");

                SELECT(builder.toString());
                FROM(TableNames.FaceLift + " faceLift ");
                JOIN(TableNames.Charge + " charge on charge.ServiceItem = faceLift.ServiceItemID and charge.Random_Id = faceLift.Random_Id ");
                JOIN(TableNames.ServiceItem + " sItem on charge.ServiceItem = sItem.id ");
                WHERE(" charge.OperationNO = #{operationNo} ");
                WHERE(" charge.IsDelete = 0 ");
            }
        }.toString();
    }

    /**
     * 通过费用主键加载整容费用信息
     *
     * @param chargeId 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 17:11
     */
    public String getFaceLiftChargeListByChargeId(@Param("chargeId") Integer chargeId) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 charge.id,charge.OperationNO as operationNo ");
                builder.append(" ,sItem.id as itemId,sItem.Name as itemName");
                builder.append(" ,charge.Number as number,charge.Price as price,charge.Charge as charge ");
                builder.append(" ,charge.IsFinished as asFinished");

                builder.append(" ,faceLift.IsFinished as asFaceLiftFinished,faceLift.Remark as remark");
                builder.append(" ,faceLift.ExcuteTime as excuteTime ,faceLift.id as faceLiftId ");

                SELECT(builder.toString());
                FROM(TableNames.FaceLift + " faceLift ");
                JOIN(TableNames.Charge + " charge on charge.ServiceItem = faceLift.ServiceItemID and charge.Random_Id = faceLift.Random_Id ");
                JOIN(TableNames.ServiceItem + " sItem on charge.ServiceItem = sItem.id ");
                WHERE(" charge.id = #{chargeId} ");
                WHERE(" charge.IsDelete = 0 ");
            }
        }.toString();
    }

    /**
     * 更新整容费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-05 09:26
     */
    public String updateFaceLiftCharge(@Param("item") FaceLiftChargeListItem item, @Param("userId") String userId) {
        // 更新费用
        String updateChargeStr =  new SQL() {
            {
                UPDATE(TableNames.Charge);
                SET(" Number = #{item.number}");
                SET(" Price = #{item.price}");
                SET(" Charge = #{item.charge}");
                SET(" Operator = #{userId}");
                WHERE(" IsFinished=0 and IsLock=0 and IsDelete=0");
                WHERE(" id = #{item.id} ");
            }
        }.toString();

        // 更新整容费用
        String updateProductStr = new SQL() {
            {
                UPDATE(TableNames.FaceLift);
                SET("FaceLiftNum = #{item.number}");
                SET("Price = #{item.price}");
                SET("Charge = #{item.charge}");
                SET("Remark = #{item.remark}");
                SET("ExcuteTime = #{item.excuteTime}");
                SET("Operator = #{userId}");
                SET("OperateTime = getdate()");
                WHERE("id = #{item.faceLiftId}");
            }
        }.toString();

        return new StringBuilder().append(updateChargeStr).append(";").append(updateProductStr).append(";").toString();
    }
}
