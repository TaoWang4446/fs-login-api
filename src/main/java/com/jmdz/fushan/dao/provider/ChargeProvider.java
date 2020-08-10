package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * ChargeProvider
 *
 * @author LiCongLu
 * @date 2020-06-10 13:44
 */
public class ChargeProvider {

    /**
     * 新增费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-06-10 13:39
     */
    public String insertChargeItem(@Param("item") ChargeItem item, @Param("userId") String userId) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.Charge);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("ServiceItem", "#{item.itemId}");
                VALUES("Number", "#{item.number}");
                VALUES("Price", "#{item.price}");
                VALUES("Charge", "#{item.charge}");
                VALUES("ChargeDate", "convert(varchar(16),getdate(),120)");
                VALUES("Service", "#{item.serviceCode}");
                VALUES("Random_Id", "#{item.randomId}");
                VALUES("Remark", "#{item.remark}");
                VALUES("ProductFrom", "#{item.productFrom}");
                VALUES("IsFinished", "0");
                VALUES("IsLock", "0");
                VALUES("IsDelete", "0");
                VALUES("IsYuanShiJiLu", "1");
                VALUES("Operator", "#{userId}");
            }
        }.toString();
    }

    /**
     * 更新业务费用
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-10 11:25
     */
    public String updateChargeItem(@Param("item") ChargeItem item, @Param("userId") String userId) {
        return new SQL() {
            {
                UPDATE(TableNames.Charge);
                SET(" OperationNO= #{item.operationNo}");
                SET(" ServiceItem= #{item.itemId}");
                SET(" Number= #{item.number}");
                SET(" Price= #{item.price}");
                SET(" Charge= #{item.charge}");
                SET(" ChargeDate= convert(varchar(16),getdate(),120)");
                SET(" Service= #{item.serviceCode}");
                SET(" Random_Id= #{item.randomId}");
                SET(" Operator= #{userId}");
                WHERE(" IsFinished=0 and IsLock=0 and IsDelete=0");
                WHERE(" id = #{item.id} ");
            }
        }.toString();
    }

    /**
     * 更新业务费用 根据 业务编号和随机业务号
     *
     * @param item   费用
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-10 11:25
     */
    public String updateChargeItemByOperationNoAndRandomId(@Param("item") ChargeItem item,
                                                           @Param("userId") String userId) {
        return new SQL() {
            {
                UPDATE(TableNames.Charge);
                SET(" OperationNO= #{item.operationNo}");
                SET(" ServiceItem= #{item.itemId}");
                SET(" Number= #{item.number}");
                SET(" Price= #{item.price}");
                SET(" Charge= #{item.charge}");
                SET(" ChargeDate= convert(varchar(16),getdate(),120)");
                SET(" Service= #{item.serviceCode}");
                SET(" Random_Id= #{item.randomId}");
                SET(" Operator= #{userId}");
                WHERE(" IsFinished=0 and IsLock=0 and IsDelete=0");
                WHERE(" Random_Id = #{item.randomId} ");
                WHERE(" OperationNo = #{item.operationNo} ");
            }
        }.toString();
    }



    /**
     * 按照业务随机码查询费用集合
     *
     * @param operationNo 逝者编号
     * @param randomId    业务随机码
     * @param serviceId   业务费用服务主键
     * @return
     * @author LiCongLu
     * @date 2020-08-03 14:56
     */
    public String listChargeByRandomId(@Param("operationNo") String operationNo, @Param("randomId") String randomId, @Param("serviceId") Integer serviceId) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" charge.id,OperationNO as operationNo ");
                builder.append(" ,sItem.ParentId as parentId,sItem.id as itemId,sItem.Name as itemName");
                builder.append(" ,charge.Number as number,charge.Price as price,charge.Charge as charge");
                builder.append(" ,charge.ChargeDate as chargeDate,IsFinished as asFinished,IsLock as asLock");
                builder.append(" ,Service as serviceCode,Random_Id as randomId,charge.Remark as remark");
                builder.append(" ,PreferentialCharge as preferentialCharge,HuiminCharge as huiminCharge ");
                builder.append(" ,GuaZhangCharge as guaZhangCharge,GuaZhangLeftCharge as guaZhangLeftCharge,TaoCanCharge as taoCanCharge");
                builder.append(" ,(HuiminCharge + PreferentialCharge + TaoCanCharge) as benefitCharge,RealCharge as realCharge ");
                SELECT(builder.toString());
                FROM(TableNames.Charge + " charge ");
                JOIN(TableNames.ServiceItem + " sItem on charge.ServiceItem = sItem.id ");
                WHERE(" charge.IsDelete = 0 ");
                WHERE(" OperationNO = #{operationNo} ");

                // 存在随机码
                if(DataUtil.valid(randomId)) {
                    WHERE(" Random_Id = #{randomId} ");
                }

                // 存在业务费用主键，则过滤掉
                if (DataUtil.valid(serviceId)) {
                    WHERE(" sItem.id != #{serviceId} ");
                }
                ORDER_BY(" sItem.ParentId,sItem.OrderBy ");
            }
        }.toString();
    }

    /**
     * 按照标记查询殡葬产品费用集合
     *
     * @param operationNo 业务编号
     * @param biaokey     编号
     * @return
     * @author LiCongLu
     * @date 2020-08-03 15:38
     */
    public String listProductChargeByBiaokey(@Param("operationNo") String operationNo, @Param("biaokey") Integer biaokey) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" charge.id,charge.OperationNO as operationNo ");
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
                WHERE(" charge.IsDelete = 0 ");
                WHERE(" charge.OperationNO = #{operationNo} ");
                WHERE(" product.Biaokey = #{biaokey} ");
                ORDER_BY(" sItem.ParentId,sItem.OrderBy ");
            }
        }.toString();
    }

    /**
     * 按照主键获取物品服务
     *
     * @param chargeId 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-06-11 16:47
     */
    public String getChargeById(@Param("chargeId") Integer chargeId) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 charge.id,OperationNO as operationNo ");
                builder.append(" ,sItem.ParentId as parentId,sItem.id as itemId,sItem.Name as itemName");
                builder.append(" ,charge.Number as number,charge.Price as price,charge.Charge as charge");
                builder.append(" ,charge.ChargeDate as chargeDate,IsFinished as asFinished,IsLock as asLock");
                builder.append(" ,Service as serviceCode,Random_Id as randomId,charge.Remark as remark");
                builder.append(" ,PreferentialCharge as preferentialCharge,HuiminCharge as huiminCharge ");
                builder.append(" ,GuaZhangCharge as guaZhangCharge,GuaZhangLeftCharge as guaZhangLeftCharge,TaoCanCharge as taoCanCharge");
                builder.append(" ,(HuiminCharge + PreferentialCharge + TaoCanCharge) as benefitCharge,RealCharge as realCharge ");
                SELECT(builder.toString());
                FROM(TableNames.Charge + " charge ");
                JOIN(TableNames.ServiceItem + " sItem on charge.ServiceItem = sItem.id ");
                WHERE(" charge.id = #{chargeId} ");
                WHERE(" charge.IsDelete = 0 ");
            }
        }.toString();
    }

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
    public String queryChargeItemForBusinessRandomId(@Param("operationNo") String operationNo, @Param("randomId") String randomId, @Param("parentId") String parentId, @Param("belongService") String belongService) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" c.id as id,OperationNO as operationNo,sitem.id as itemId,sitem.Name as itemName,sitem.ParentId as parentId ");
                builder.append(" ,Number as number,ISNULL(c.Price,sitem.Price) as price,Charge as charge,ChargeDate as chargeDate");
                builder.append(" ,IsFinished as isFinished,IsLock as isLock,Service as serviceCode,Random_Id as randomId");
                builder.append(" ,PreferentialCharge as preferentialCharge,HuiminCharge as huiminCharge ");
                builder.append(" ,GuaZhangCharge as guaZhangCharge,GuaZhangLeftCharge as guaZhangLeftCharge,TaoCanCharge as taoCanCharge");
                builder.append(" ,(HuiminCharge + PreferentialCharge + TaoCanCharge) as benefitCharge,RealCharge as realCharge ");
                SELECT(builder.toString());
                FROM(TableNames.ServiceItem + " sitem ");
                String leftJoin = TableNames.Charge + " c on sitem.id=c.ServiceItem and OperationNO=#{operationNo} and c.IsDelete = 0";
                if (DataUtil.valid(randomId)) {
                    leftJoin += " and Random_Id=#{randomId}";
                }
                LEFT_OUTER_JOIN(leftJoin);
                WHERE(" sitem.IsDeleted=0 ");
                if (DataUtil.valid(parentId)) {
                    WHERE(" sitem.ParentId in (" + parentId + ")");
                }
                if (DataUtil.valid(belongService)) {
                    if (!belongService.contains(",")) {
                        WHERE(" sitem.BelongServiceItem like'%" + belongService + "%'");
                    } else {
                        String[] services = belongService.split(",");
                        String serviceLike = "";
                        for (int i = 0; i < services.length; i++) {
                            if (i == 0) {
                                serviceLike += " sitem.BelongServiceItem like'%" + services[i] + "%' ";
                            } else {
                                serviceLike += " or sitem.BelongServiceItem like'%" + services[i] + "%'";
                            }
                        }

                        WHERE(" (" + serviceLike + ") ");
                    }
                }
                ORDER_BY(" sitem.OrderBy ");
            }
        }.toString();
    }

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
    public String queryChargeItemForOperationNoList(@Param("operationNoList") ArrayList<String> operationNoList, @Param("parentId") String parentId, @Param("belongService") String belongService) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" c.id as id,OperationNO as operationNo,sitem.id as itemId,sitem.Name as itemName,sitem.ParentId as parentId ");
                builder.append(" ,Number as number,ISNULL(c.Price,sitem.Price) as price,Charge as charge,ChargeDate as chargeDate");
                builder.append(" ,IsFinished as isFinished,IsLock as isLock,Service as serviceCode,Random_Id as randomId");
                builder.append(" ,PreferentialCharge as preferentialCharge,HuiminCharge as huiminCharge ");
                builder.append(" ,GuaZhangCharge as guaZhangCharge,GuaZhangLeftCharge as guaZhangLeftCharge,TaoCanCharge as taoCanCharge");
                builder.append(" ,(HuiminCharge + PreferentialCharge + TaoCanCharge) as benefitCharge,RealCharge as realCharge ");
                SELECT(builder.toString());
                FROM(TableNames.ServiceItem + " sitem ");
                String operationNos = StringUtils.arrayToDelimitedString(operationNoList.toArray(new String[]{}), "','");
                JOIN(TableNames.Charge + " c on sitem.id=c.ServiceItem");
                WHERE(" sitem.IsDeleted=0 ");
                WHERE(" c.OperationNO in ('" + operationNos + "')");
                WHERE(" c.IsDelete = 0 ");
                if (DataUtil.valid(parentId)) {
                    WHERE(" sitem.ParentId in (" + parentId + ")");
                }
                if (DataUtil.valid(belongService)) {
                    if (!belongService.contains(",")) {
                        WHERE(" sitem.BelongServiceItem like'%" + belongService + "%'");
                    } else {
                        String[] services = belongService.split(",");
                        String serviceLike = "";
                        for (int i = 0; i < services.length; i++) {
                            if (i == 0) {
                                serviceLike += " sitem.BelongServiceItem like'%" + services[i] + "%' ";
                            } else {
                                serviceLike += " or sitem.BelongServiceItem like'%" + services[i] + "%'";
                            }
                        }

                        WHERE(" (" + serviceLike + ") ");
                    }
                }
                ORDER_BY(" sitem.OrderBy ");
            }
        }.toString();
    }

    /**
     * 按照查询时间及类型汇总费用
     *
     * @param data 查询时间
     * @return
     * @author LiCongLu
     * @date 2020-07-13 09:24
     */
    public String countChargeForLeaderByType(@Param("data") LeaderAllData data) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" pItem.id as parentId,pItem.Name as parentName,SUM(charge.Charge) as totalCharge");
                builder.append(" ,SUM(HuiminCharge + PreferentialCharge + TaoCanCharge) as benefitCharge,SUM(RealCharge) as realCharge ");

                SELECT(builder.toString());
                FROM(TableNames.Charge + " charge ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = charge.ServiceItem ");
                JOIN(TableNames.ServiceItem + " pItem on pItem.id = sItem.ParentId");

                WHERE(" charge.IsFinished = 1 and charge.IsDelete = 0");

                // 当无查询日期时默认为当前日期
                if (DataUtil.invalid(data.getQueryDate())) {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),charge.JieSuanDate,120) = Convert(varchar(10),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),charge.JieSuanDate,120) = Convert(varchar(7),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),charge.JieSuanDate,120) = Convert(varchar(4),GETDATE(),120) ");
                    }
                } else {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),charge.JieSuanDate,120) = Convert(varchar(10),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),charge.JieSuanDate,120) = Convert(varchar(7),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),charge.JieSuanDate,120) = Convert(varchar(4),#{data.queryDate},120) ");
                    }
                }


                GROUP_BY(" pItem.id,pItem.Name ");
                ORDER_BY(" pItem.id ");
            }
        }.toString();
    }

    /**
     * 按照业务编号查询告别任务所需费用集合
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-14 10:58
     */
    public String listFarewellChargeByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" c.id as id,OperationNO as operationNo,sitem.ParentId as parentId,sitem.id as itemId,sitem.Name as itemName ");
                builder.append(" ,Number as number,ISNULL(c.Price,sitem.Price) as price,Charge as charge,ChargeDate as chargeDate");
                builder.append(" ,IsFinished as isFinished,Service as serviceCode,Random_Id as randomId");
                builder.append(" ,IsFlower as asFlower,IsStockProduct as asStockProduct,IsRiteService as asRiteService");
                SELECT(builder.toString());
                FROM(TableNames.ServiceItem + " sitem ");
                JOIN(TableNames.Charge + " c on sitem.id=c.ServiceItem ");
                WHERE("  OperationNO=#{operationNo} ");
                WHERE("  c.IsDelete = 0 ");
                ORDER_BY(" sitem.OrderBy ");
            }
        }.toString();
    }
}
