package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.MournProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.PickerItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.leader.LeaderAllLabelItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

/**
 * MournDao
 *
 * @author LiCongLu
 * @date 2020-07-13 10:24
 */
public interface MournDao {

    /**
     * 获取守灵或告别的礼厅
     *
     * @param isMourn 是否告别
     * @return
     * @author LiCongLu
     * @date 2020-07-13 14:53
     */
    @Select("select MournHallId as id,MournName as name,0 as number from " + TableNames.MournHall
            + " where isMourn = #{isMourn} and IsDeleted = 0 "
            + " order by MournHallId ")
    ArrayList<LeaderAllLabelItem> listMournHallForLeader(@Param("isMourn") Integer isMourn);

    /**
     * 按照查询日期统计告别守灵数量
     *
     * @param data    查询条件
     * @param isMourn 是否告别
     * @return
     * @author LiCongLu
     * @date 2020-07-13 10:25
     */
    @SelectProvider(type = MournProvider.class, method = "countMournForLeader")
    ArrayList<LeaderAllLabelItem> countMournForLeader(@Param("data") LeaderAllData data, @Param("isMourn") Integer isMourn);

    /**
     * 加载所有告别词模板，以选项公用类的形式返回
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-03 11:15
     */
    @Select(" select id as value,eulogyTitle as text,eulogyContent as tag " +
            " from f_eulogy ORDER BY eulogyOrder asc")
    ArrayList<PickerItem> listPickerEulogy();
}
