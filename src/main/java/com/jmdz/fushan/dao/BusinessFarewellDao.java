package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.BusinessFarewellProvider;
import com.jmdz.fushan.dao.provider.ServiceProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.BusinessServiceItem;
import com.jmdz.fushan.pad.model.business.farewell.FarewellHallDeadItem;
import com.jmdz.fushan.pad.model.business.farewell.FarewellHallListItem;
import com.jmdz.fushan.pad.model.business.farewell.FarewellMournItem;
import com.jmdz.fushan.pad.model.business.farewell.FarewellMournListItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.MournItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * BusinessFarewellDao
 *
 * @author LiCongLu
 * @date 2020-08-06 11:25
 */
public interface BusinessFarewellDao {
    /**
     * 按照业务编号查询告别信息
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 15:09
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "listFarewellMournListByOperationNo")
    ArrayList<FarewellMournListItem> listFarewellMournListByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 加载告别厅信息
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:13
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "listFarewellHallList")
    ArrayList<FarewellHallListItem> listFarewellHallList();

    /**
     * 按照主键,加载告别厅信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:19
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "getFarewellHallListById")
    FarewellHallListItem getFarewellHallListById(@Param("id") Integer id);

    /**
     * 利用时间查询告别任务的逝者信息
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:35
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "listFarewellHallDeadByTime")
    ArrayList<FarewellHallDeadItem> listFarewellHallDeadByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 按照告别厅主键查询当前占用告别任务主键
     *
     * @param mournId   告别任务排除主键
     * @param hallId    告别厅主键
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:25
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "getFarewellMournIdByHallId")
    @ResultType(Integer.class)
    Integer getFarewellMournIdByHallId(@Param("mournId") Integer mournId, @Param("hallId") Integer hallId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询当前告别逝者任务信息
     *
     * @param beginTime 开始时间
     * @return
     * @author LiCongLu
     * @date 2020-08-06 14:20
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "listFarewellHallDeadForBeginTime")
    ArrayList<FarewellHallDeadItem> listFarewellHallDeadForBeginTime(@Param("beginTime") String beginTime);

    /**
     * 按照主键加载告别任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 10:45
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "getFarewellMournById")
    FarewellMournItem getFarewellMournById(@Param("id") Integer id);

    /**
     * 新增告别任务信息
     *
     * @param item      告别任务
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:13
     */
    @InsertProvider(type = BusinessFarewellProvider.class, method = "insertBusinessFarewellMourn")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertBusinessFarewellMourn(@Param("item") MournItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新逝者关联告别厅信息
     *
     * @param operationNo    业务编号
     * @param beginTime      开始时间
     * @param endTime        结束时间
     * @param hallItem       告别厅信息
     * @param farewellStatus 告别状态
     * @param loginItem      当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 13:54
     */
    @UpdateProvider(type = BusinessFarewellProvider.class, method = "updateDeadFarewellMourn")
    void updateDeadFarewellMourn(@Param("operationNo") String operationNo, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("hallItem") FarewellHallListItem hallItem
            , @Param("farewellStatus") String farewellStatus, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新告别任务信息
     *
     * @param item      告别任务信息
     * @param loginItem 当前登录信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 15:30
     */
    @UpdateProvider(type = BusinessFarewellProvider.class, method = "updateBusinessFarewellMourn")
    void updateBusinessFarewellMourn(@Param("item") FarewellMournItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 删除告别任务
     *
     * @param id          主键
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-06 16:58
     */
    @Delete("delete from " + TableNames.Mourn + " where id = #{id} and OperationNO = #{operationNo}")
    void deleteFarewellMournById(@Param("id") Integer id, @Param("operationNo") String operationNo);

    /**
     * 清除逝者告别信息
     *
     * @param operationNo 业务编号
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:43
     */
    @UpdateProvider(type = BusinessFarewellProvider.class, method = "deleteDeadFarewellMourn")
    void deleteDeadFarewellMourn(@Param("operationNo") String operationNo, @Param("loginItem") LoginItem loginItem);

    /**
     * 获取告别套餐服务信息
     *
     * @param code 套餐编码
     * @return
     * @author LiCongLu
     * @date 2020-08-07 11:25
     */
    @SelectProvider(type = BusinessFarewellProvider.class, method = "listFarewellSuitServiceByCode")
    ArrayList<BusinessServiceItem> listFarewellSuitServiceByCode(@Param("code") String code);
}
