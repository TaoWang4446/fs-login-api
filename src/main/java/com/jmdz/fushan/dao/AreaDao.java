package com.jmdz.fushan.dao;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.PickerItem;
import com.jmdz.fushan.pad.model.farewell.FarewellHallItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * AreaDao
 *
 * @author LiCongLu
 * @date 2020-08-03 08:41
 */
public interface AreaDao {

    /**
     * 按照父主键加载行政区划，以选项公用类的形式返回
     * 
     * @param pid 父主键
     * @return
     * @author LiCongLu
     * @date 2020-08-03 09:34
     */
    @Select("select  AreaId as tagInt,AreaId as value,AreaName as text,AreaOrder as sort " +
            " from " + TableNames.Area + " where AreaParentId = #{pid} " +
            " order by AreaOrder")
    ArrayList<PickerItem> listPickerAreaByPid(@Param("pid") Integer pid);

}
