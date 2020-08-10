package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.ColdStorageProvider;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.leader.LeaderAllLabelItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * ColdStorageDao
 *
 * @author LiCongLu
 * @date 2020-07-13 11:09
 */
public interface ColdStorageDao {

    /**
     * 按照时间查询入藏数量
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 11:12
     */
    @SelectProvider(type = ColdStorageProvider.class, method = "countColdStorageInForLeader")
    LeaderAllLabelItem countColdStorageInForLeader(@Param("data") LeaderAllData data);


    /**
     * 按照时间查询出藏数量
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 11:12
     */
    @SelectProvider(type = ColdStorageProvider.class, method = "countColdStorageOutForLeader")
    LeaderAllLabelItem countColdStorageOutForLeader(@Param("data") LeaderAllData data);

    /**
     * 查询当前在柜数量
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-13 11:21
     */
    @Select("select top 1 '当前在柜' as name,COUNT(*) as number from " + TableNames.ColdStorage +
            " where RemainsState =  " + Constants.ColdStorageState.In)
    LeaderAllLabelItem countColdStorageCurrentForLeader();


    /**
     * 查找空柜数量
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-13 11:24
     */
    @Select("select top 1 '空柜' as name,COUNT(*) as number from " + TableNames.ColdStorageEquipment + " equipment " +
            " left join f_ColdStorage cold on cold.ColdStorageNO = equipment.id and cold.RemainsState = " + Constants.ColdStorageState.In +
            " where cold.id is null and equipment.IsDeleted = 0 and equipment.Enabled = 1 ")
    LeaderAllLabelItem countColdStorageUsableForLeader();
}
