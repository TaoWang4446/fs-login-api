package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.DeadProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.BusinessDeadInfo;
import com.jmdz.fushan.pad.model.business.BusinessDeadInfoData;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.dead.DeadAllListItem;
import com.jmdz.fushan.pad.model.dead.DeadChargeAllItem;
import com.jmdz.fushan.pad.model.dead.DeadDetailsItem;
import com.jmdz.fushan.pad.model.dead.DeadServiceAllItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * DeadDao
 *
 * @author LiCongLu
 * @date 2020-07-09 13:22
 */
public interface DeadDao {

    /**
     * 按照业务编号加载逝者基本信息
     *
     * @param operationNo 逝者业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 13:24
     */
    @Select("select top 1 OperationNO as operationNo,DierName as deadName,DierSex as deadSex,DierAge as deadAge " +
            " ,BirthDay as deadBirthday,DeathTime as deathTime,DeathAddress as deathAddress,CertificateNO as deadCertificateNo " +
            " ,DeathCause as deathCause " +
            " from " + TableNames.Dead +
            " where IsDead=1 and OperationNO=#{operationNo}")
    DeadBasicItem getDeadBasicByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 新增逝者基本信息
     *
     * @param item      逝者信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 14:13
     */
    @InsertProvider(type = DeadProvider.class, method = "insertDeadBasicItem")
    void insertDeadBasicItem(@Param("item") DeadBasicItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新逝者基本信息
     *
     * @param item      逝者信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 14:13
     */
    @UpdateProvider(type = DeadProvider.class, method = "updateDeadBasicItem")
    void updateDeadBasicItem(@Param("item") DeadBasicItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 查询指定日期的逝者任务列表
     *
     * @param queryDate 查询日期
     * @return
     * @author LiCongLu
     * @date 2020-07-10 14:12
     */
    @SelectProvider(type = DeadProvider.class, method = "listDeadAllListByDate")
    ArrayList<DeadAllListItem> listDeadAllListByDate(@Param("queryDate") Date queryDate);

    /**
     * 获取最近的业务编号，以便生成新的业务编号
     *
     * @param currentDate 当前日期
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:29
     */
    @Select("Select Max(OperationNO) as MaxNo From f_dead where IsDead=1 and Left(OperationNO,8)=#{currentDate}")
    @ResultType(String.class)
    String getMaxOperationNo(@Param("currentDate") String currentDate);

    /**
     * 按照业务编号加载逝者详细信息
     *
     * @param operationNo 业务编号
     * @return
     * @author Sunzhqoi
     * @date 2020-06-13 09:50
     */
    @SelectProvider(type = DeadProvider.class, method = "getDeadDetailsByOperationNo")
    DeadDetailsItem getDeadDetailsByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 按照业务编号加载逝者费用明细信息
     *
     * @param operationNo 业务编号
     * @return
     * @author Sunzhqoi
     * @date 2020-06-13 16:15
     */
    @SelectProvider(type = DeadProvider.class, method = "listDeadChargeAllByOperationNo")
    ArrayList<DeadChargeAllItem> listDeadChargeAllByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 按照业务编号加载逝者服务信息
     *
     * @param operationNo 业务编号
     * @return
     * @author Sunzhqoi
     * @date 2020-06-13 16:15
     */
    @SelectProvider(type = DeadProvider.class, method = "getDeadServiceAllByOperationNo")
    DeadServiceAllItem getDeadServiceAllByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 按照业务编号加载逝者信息
     *
     * @param operationNo 逝者业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-03 11:24
     */

    @SelectProvider(type = DeadProvider.class, method = "getBusinessDeadInfoByOperationNo")
    BusinessDeadInfo getBusinessDeadInfoByOperationNo(@Param("operationNo") String operationNo);


    /**
     * 业务洽谈逝者信息更新
     *
     * @param item 逝者信息
     * @param loginItem 当前账号
     * @return
     * @author WangShengtao
     * @date 2020-08-03 11:29
     */
    @UpdateProvider(type = DeadProvider.class, method = "updateBusinessDeadInfo")
    void updateBusinessDeadInfo(@Param("loginItem") LoginItem loginItem, @Param("item") BusinessDeadInfoData item);
}
