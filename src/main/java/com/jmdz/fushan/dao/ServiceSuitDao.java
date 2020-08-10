package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.ServiceSuitProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.service.ServiceSuitItem;
import com.jmdz.fushan.pad.model.service.ServiceSuitSetItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

/**
 * 服务套餐Dao
 *
 * @author LiCongLu
 * @date 2020-06-09 16:28
 */
public interface ServiceSuitDao {

    /**
     * 加载服务套餐
     *
     * @param itemsCode 套餐标识编码
     * @return
     * @author LiCongLu
     * @date 2020-06-09 16:55
     */
    @SelectProvider(type = ServiceSuitProvider.class, method = "listServiceSuitByItemsCode")
    ArrayList<ServiceSuitItem> listServiceSuitByItemsCode(@Param("itemsCode") String itemsCode);

    /**
     * 加载所有套餐的子服务项目
     *
     * @param itemsCode 套餐标记编码
     * @return
     * @author LiCongLu
     * @date 2020-06-10 08:55
     */
    @SelectProvider(type = ServiceSuitProvider.class, method = "listServiceSuitSetItemByItemsCode")
    ArrayList<ServiceSuitSetItem> listServiceSuitSetItemByItemsCode(@Param("itemsCode") String itemsCode);

    /**
     * 按照套餐主键加载所有物品服务
     *
     * @param itemsCode 套餐标记编码
     * @param suitId    套餐主键
     * @return
     * @author LiCongLu
     * @date 2020-06-10 13:17
     */
    @SelectProvider(type = ServiceSuitProvider.class, method = "listServiceSuitSetItemBySuitId")
    ArrayList<ServiceSuitSetItem> listServiceSuitSetItemBySuitId(@Param("itemsCode") String itemsCode, @Param("suitId") String suitId);

    /**
     * 加载告别厅套餐
     *
     * @param itemTypeName 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-22 10:33
     */
    @Select(" select sItem.id as itemId,sItem.Name as itemName,sItem.ParentId as parentId,sItem.price as itemPrice" +
            "  ,a.Number as itemNumber,a.ItemTypeName as suitId " +
            "  from "+TableNames.TaoCanItemMourn + " a " +
            "  left join "+TableNames.ServiceItem+ " sItem on a.ServiceItemId = sItem.id " +
            "  where a.ItemTypeName= #{itemTypeName} ")
    ArrayList<ServiceSuitSetItem> listTaoCanItemMournByItemTypeName(@Param("itemTypeName") String itemTypeName);
}