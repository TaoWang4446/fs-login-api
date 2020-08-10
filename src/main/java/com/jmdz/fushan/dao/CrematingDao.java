package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.CrematingProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.leader.LeaderAllLabelItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.spirit.SendSpiritListItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * @author LiCongLu
 * @date 2020-07-13 10:04
 */
public interface CrematingDao {

    /**
     * 按照查询日期统计火化数量
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 10:07
     */
    @SelectProvider(type = CrematingProvider.class, method = "countCrematingForLeader")
    ArrayList<LeaderAllLabelItem> countCrematingForLeader(@Param("data") LeaderAllData data);

    /**
     * 加载送灵任务列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-22 16:40
     */
    @SelectProvider(type = CrematingProvider.class, method = "listSendSpirit")
    ArrayList<SendSpiritListItem> listSendSpirit();

    /**
     * 按照主键加载送灵任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-23 10:02
     */
    @SelectProvider(type = CrematingProvider.class, method = "getSendSpiritById")
    SendSpiritListItem getSendSpiritById(@Param("id") Integer id);

    /**
     * 执行送灵任务确认
     *
     * @param id        主键
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-23 10:35
     */
    @Update(" update "+TableNames.Cremating +" set ConfirmSendSpirit = 1,Operator = #{loginItem.userId} " +
            " where id = #{id} and ISNULL(ConfirmSendSpirit,0) = 0")
    void updateConfirmSendSpirit(@Param("id") Integer id, @Param("loginItem") LoginItem loginItem);
}
