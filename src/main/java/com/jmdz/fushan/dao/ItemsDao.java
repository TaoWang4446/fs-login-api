package com.jmdz.fushan.dao;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.PickerItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * ItemsDao
 *
 * @author LiCongLu
 * @date 2020-07-31 09:43
 */
public interface ItemsDao {

    /**
     * 获取字典数据
     * 利用Items.Code获取ItemDetails.ItemName值
     * 不考虑ItemDetails.ItemDetailsId值
     *
     * @param type 类型
     * @param code 编码
     * @return
     * @author LiCongLu
     * @date 2020-07-31 09:42
     */
    @Select(" select #{type} as type,details.ItemName as text,details.SortCode as sort" +
            " from " + TableNames.Items + " items " +
            " join " + TableNames.ItemDetails + " details on items.ItemsId = details.ItemsId " +
            " where details.Enabled=1 and items.Code = #{code} order by details.SortCode")
    @Results({
            @Result(column = "type", property = "type"),
            @Result(column = "text", property = "value"),
            @Result(column = "text", property = "text"),
            @Result(column = "sort", property = "row")
    })
    ArrayList<PickerItem> getItemNameByCode(@Param("type") String type, @Param("code") String code);

    /**
     * 利用Items.Code获取ItemDetails.ItemDetailsId值
     *
     * @param type 类型
     * @param code 编码
     * @return
     * @author LiCongLu
     * @date 2020-07-31 09:53
     */
    @Select(" select #{type} as type,details.ItemDetailsId as value,details.ItemName as text,details.SortCode as sort" +
            " from " + TableNames.Items + " items " +
            " join " + TableNames.ItemDetails + " details on items.ItemsId = details.ItemsId " +
            " where details.Enabled=1 and items.Code = #{code} order by details.SortCode")
    @Results({
            @Result(column = "type", property = "type"),
            @Result(column = "value", property = "value"),
            @Result(column = "text", property = "text"),
            @Result(column = "sort", property = "row")
    })
    ArrayList<PickerItem> getItemDetailsIdByCode(@Param("type") String type, @Param("code") String code);
}
