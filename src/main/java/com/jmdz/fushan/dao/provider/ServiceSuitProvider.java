package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * 服务套餐Provider
 *
 * @author LiCongLu
 * @date 2020-06-09 16:53
 */
public class ServiceSuitProvider {

    /**
     * 加载服务套餐
     *
     * @param itemsCode 套餐标识编码
     * @return
     * @author LiCongLu
     * @date 2020-06-09 16:55
     */
    public String listServiceSuitByItemsCode(@Param("itemsCode") String itemsCode) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" distinct details.ItemDetailsId as suitId,details.ItemName as suitName,details.SortCode as sort ");
                SELECT(builder.toString());
                FROM(TableNames.ItemDetails + " details");
                JOIN(TableNames.Items + " items on items.ItemsId = details.ItemsId ");
                WHERE(" details.Enabled = 1 and items.Enabled = 1 ");
                WHERE(" items.Code = #{itemsCode} ");
                ORDER_BY(" details.SortCode ");
            }
        }.toString();
    }

    /**
     * 加载所有套餐的子服务项目
     *
     * @param itemsCode 套餐标记编码
     * @return
     * @author LiCongLu
     * @date 2020-06-10 08:55
     */
    public String listServiceSuitSetItemByItemsCode(@Param("itemsCode") String itemsCode) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" distinct sItem.id as itemId,sItem.ParentId as parentId,sItem.Name as itemName ");
                builder.append(" ,sBat.Price as itemPrice,sBat.Number as itemNumber,sItem.prickle as itemUnit,sItem.OrderBy as sort");
                builder.append(" ,sBat.TaoCanId as suitId,details.ItemName as suitName,sBat.Biaokey as suitKey");
                SELECT(builder.toString());
                FROM(TableNames.ServiceItemBat + " sBat ");
                JOIN(TableNames.ServiceItem + " sItem on sBat.pkid = sItem.id ");
                JOIN(TableNames.ItemDetails + " details on details.ItemDetailsId = sBat.TaoCanId");
                JOIN(TableNames.Items + " items on items.ItemsId = details.ItemsId ");
                WHERE(" sItem.IsDeleted = 0 and details.Enabled = 1 and items.Enabled = 1 ");
                WHERE(" items.Code = #{itemsCode} ");
                ORDER_BY(" sItem.OrderBy ");
            }
        }.toString();
    }

    /**
     * 按照套餐主键加载所有物品服务
     *
     * @param itemsCode 套餐标记编码
     * @param suitId    套餐主键
     * @return
     * @author LiCongLu
     * @date 2020-06-10 13:17
     */
    public String listServiceSuitSetItemBySuitId(@Param("itemsCode") String itemsCode, @Param("suitId") String suitId) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" distinct sItem.id as itemId,sItem.ParentId as parentId,sItem.Name as itemName ");
                builder.append(" ,sBat.Price as itemPrice,sBat.Number as itemNumber,sItem.prickle as itemUnit,sItem.OrderBy as sort");
                builder.append(" ,sBat.TaoCanId as suitId,details.ItemName as suitName,sBat.Biaokey as suitKey");
                SELECT(builder.toString());
                FROM(TableNames.ServiceItemBat + " sBat ");
                JOIN(TableNames.ServiceItem + " sItem on sBat.pkid = sItem.id ");
                JOIN(TableNames.ItemDetails + " details on details.ItemDetailsId = sBat.TaoCanId");
                JOIN(TableNames.Items + " items on items.ItemsId = details.ItemsId ");
                WHERE(" sItem.IsDeleted = 0 and details.Enabled = 1 and items.Enabled = 1 ");
                WHERE(" items.Code = #{itemsCode} ");
                WHERE(" details.ItemDetailsId = #{suitId}");
                ORDER_BY(" sItem.OrderBy ");
            }
        }.toString();
    }
}
