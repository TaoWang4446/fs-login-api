package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.business.FuneralProductItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * FuneralProductProvider
 *
 * @author LiCongLu
 * @date 2020-08-04 11:16
 */
public class FuneralProductProvider {

    /**
     * 新增丧葬用品服务
     *
     * @param item   丧葬服务物品
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 11:18
     */
    public String insertFuneralProductItem(@Param("item") FuneralProductItem item, @Param("userId") String userId) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.FuneralProduct);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("ServiceItemId", "#{item.serviceItemId}");
                VALUES("OperateTime", "convert(varchar(16),getdate(),120)");
                VALUES("Number", "#{item.number}");
                VALUES("Price", "#{item.price}");
                VALUES("Charge", "#{item.charge}");
                VALUES("Random_Id", "#{item.randomId}");
                VALUES("Remark", "#{item.remark}");
                VALUES("Biaokey", "#{item.biaokey}");
                VALUES("Operator", "#{userId}");
            }
        }.toString();
    }

    /**
     * 按照主键获取丧葬用品费用
     *
     * @param chargeId 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:34
     */
    public String getFuneralProductChargeById(@Param("chargeId") Integer chargeId) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 charge.id,charge.OperationNO as operationNo ");
                builder.append(" ,sItem.ParentId as parentId,sItem.id as itemId,sItem.Name as itemName");
                builder.append(" ,charge.Number as number,charge.Price as price,charge.Charge as charge");
                builder.append(" ,charge.ChargeDate as chargeDate,charge.IsFinished as asFinished,IsLock as asLock");
                builder.append(" ,Service as serviceCode,charge.Random_Id as randomId,charge.Remark as remark");
                builder.append(" ,PreferentialCharge as preferentialCharge,HuiminCharge as huiminCharge ");
                builder.append(" ,GuaZhangCharge as guaZhangCharge,GuaZhangLeftCharge as guaZhangLeftCharge,TaoCanCharge as taoCanCharge");
                builder.append(" ,(HuiminCharge + PreferentialCharge + TaoCanCharge) as benefitCharge,RealCharge as realCharge ");
                builder.append(" ,product.id as productId ");
                SELECT(builder.toString());
                FROM(TableNames.FuneralProduct + " product ");
                JOIN(TableNames.Charge + " charge on charge.ServiceItem = product.ServiceItemId and charge.Random_Id = product.Random_Id ");
                JOIN(TableNames.ServiceItem + " sItem on charge.ServiceItem = sItem.id ");
                WHERE(" charge.id = #{chargeId} ");
                WHERE(" charge.IsDelete = 0 ");
            }
        }.toString();
    }

    /**
     * 更新丧葬用品费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:40
     */
    public String updateFuneralProductCharge(@Param("item") ChargeItem item, @Param("userId") String userId) {
        // 更新费用
        String updateChargeStr =  new SQL() {
            {
                UPDATE(TableNames.Charge);
                SET(" Number = #{item.number}");
                SET(" Price = #{item.price}");
                SET(" Charge = #{item.charge}");
                SET(" PreferentialCharge = #{item.preferentialCharge}");
                if(DataUtil.valid(item.getChargeDate())) {
                    SET(" ChargeDate = #{item.chargeDate}");
                }else{
                    SET(" ChargeDate= convert(varchar(16),getdate(),120)");
                }
                SET(" Operator = #{userId}");
                WHERE(" IsFinished=0 and IsLock=0 and IsDelete=0");
                WHERE(" id = #{item.id} ");
            }
        }.toString();

        // 更新丧葬产品费用
        String updateProductStr = new SQL() {
            {
                UPDATE(TableNames.FuneralProduct);
                SET("OperateTime = convert(varchar(16),getdate(),120)");
                SET("Number = #{item.number}");
                SET("Price = #{item.price}");
                SET("Charge = #{item.charge}");
                SET("Remark = #{item.remark}");
                SET("Operator = #{userId}");
                WHERE("id = #{item.productId}");
            }
        }.toString();

        return new StringBuilder().append(updateChargeStr).append(";").append(updateProductStr).append(";").toString();
    }
}
