package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.BusinessWakeProvider;
import com.jmdz.fushan.dao.provider.FarewellProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.business.wake.WakeHallDeadItem;
import com.jmdz.fushan.pad.model.business.wake.WakeHallListItem;
import com.jmdz.fushan.pad.model.business.wake.WakeMournItem;
import com.jmdz.fushan.pad.model.business.wake.WakeMournListItem;
import com.jmdz.fushan.pad.model.farewell.FarewellHallItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.MournItem;
import com.jmdz.fushan.pad.model.weixin.WxMournItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * BusinessWakeDao
 *
 * @author LiCongLu
 * @date 2020-08-05 11:20
 */
public interface BusinessWakeDao {

    /**
     * 按照业务编号查询守灵信息
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-05 15:09
     */
    @SelectProvider(type = BusinessWakeProvider.class, method = "listWakeMournListByOperationNo")
    ArrayList<WakeMournListItem> listWakeMournListByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 加载守灵厅信息
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-05 13:13
     */
    @SelectProvider(type = BusinessWakeProvider.class, method = "listWakeHallList")
    ArrayList<WakeHallListItem> listWakeHallList();

    /**
     * 按照主键,加载守灵厅信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:19
     */
    @SelectProvider(type = BusinessWakeProvider.class, method = "getWakeHallListById")
    WakeHallListItem getWakeHallListById(@Param("id") Integer id);

    /**
     * 利用时间查询守灵任务的逝者信息
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-05 13:35
     */
    @SelectProvider(type = BusinessWakeProvider.class, method = "listWakeHallDeadByTime")
    ArrayList<WakeHallDeadItem> listWakeHallDeadByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 按照守灵厅主键查询当前占用守灵任务主键
     *
     * @param mournId   守灵任务排除主键
     * @param hallId    守灵厅主键
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:25
     */
    @SelectProvider(type = BusinessWakeProvider.class, method = "getWakeMournIdByHallId")
    @ResultType(Integer.class)
    Integer getWakeMournIdByHallId(@Param("mournId") Integer mournId, @Param("hallId") Integer hallId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询当前守灵逝者任务信息
     *
     * @param beginTime 开始时间
     * @return
     * @author LiCongLu
     * @date 2020-08-05 14:20
     */
    @SelectProvider(type = BusinessWakeProvider.class, method = "listWakeHallDeadForBeginTime")
    ArrayList<WakeHallDeadItem> listWakeHallDeadForBeginTime(@Param("beginTime") String beginTime);

    /**
     * 按照主键加载守灵任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 10:45
     */
    @SelectProvider(type = BusinessWakeProvider.class, method = "getWakeMournById")
    WakeMournItem getWakeMournById(@Param("id") Integer id);

    /**
     * 新增守灵任务信息
     *
     * @param item      守灵任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:13
     */
    @InsertProvider(type = BusinessWakeProvider.class, method = "insertBusinessWakeMourn")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertBusinessWakeMourn(@Param("item") MournItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新逝者关联守灵厅信息
     *
     * @param operationNo 业务编号
     * @param beginTime   开始时间
     * @param endTime     结束时间
     * @param hallItem    守灵厅信息
     * @param mournStatus 守灵状态
     * @param loginItem   当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:54
     */
    @UpdateProvider(type = BusinessWakeProvider.class, method = "updateDeadWakeMourn")
    void updateDeadWakeMourn(@Param("operationNo") String operationNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("hallItem") WakeHallListItem hallItem
            , @Param("mournStatus") String mournStatus, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新守灵任务信息
     *
     * @param item      守灵任务信息
     * @param loginItem 当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 15:30
     */
    @UpdateProvider(type = BusinessWakeProvider.class, method = "updateBusinessWakeMourn")
    void updateBusinessWakeMourn(@Param("item") WakeMournItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 删除守灵任务
     *
     * @param id          主键
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 16:58
     */
    @Delete("delete from " + TableNames.Mourn + " where id = #{id} and OperationNO = #{operationNo}")
    void deleteWakeMournById(@Param("id") Integer id, @Param("operationNo") String operationNo);

    /**
     * 删除逝者信息里的守灵信息
     *
     * @param operationNo 业务编号
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:46
     */
    @UpdateProvider(type = BusinessWakeProvider.class, method = "deleteDeadWakeMourn")
    void deleteDeadWakeMourn(@Param("operationNo") String operationNo, @Param("loginItem") LoginItem loginItem);
}
