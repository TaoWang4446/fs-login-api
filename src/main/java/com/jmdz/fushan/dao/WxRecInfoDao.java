package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.ChargeProvider;
import com.jmdz.fushan.dao.provider.WxRecInfoProvider;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.WxRecInfoExamineItem;
import com.jmdz.fushan.pad.model.weixin.WxRecInfoItem;
import com.jmdz.fushan.pad.model.weixin.WxRecServiceItem;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.ArrayList;

/**
 * WxRecInfoDao
 *
 * @author LiCongLu
 * @date 2020-07-21 10:17
 */
public interface WxRecInfoDao {

    /**
     * 查询微信预约接运未审核信息列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 14:08
     */
    @SelectProvider(type = WxRecInfoProvider.class, method = "listWxRecInfoExamineList")
    ArrayList<WxRecInfoExamineItem> listWxRecInfoExamineList();

    /**
     * 按照主键查询微信预约接运信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:42
     */
    @SelectProvider(type = WxRecInfoProvider.class, method = "getWxRecInfoById")
    WxRecInfoItem getWxRecInfoById(@Param("id") Integer id);

    /**
     * 更新审核状态通过
     *
     * @param id          业务主键
     * @param operationNo 通过业务编号
     * @param vehicleId   接运任务主键
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:56
     */
    @UpdateProvider(type = WxRecInfoProvider.class, method = "updateExaminePass")
    void updateExaminePass(@Param("id") Integer id, @Param("operationNo") String operationNo, @Param("vehicleId") String vehicleId, @Param("loginItem") LoginItem loginItem);


    /**
     * 更新审核状态不通过
     *
     * @param id        业务主键
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:56
     */
    @UpdateProvider(type = WxRecInfoProvider.class, method = "updateExamineFail")
    void updateExamineFail(@Param("id") Integer id, @Param("loginItem") LoginItem loginItem);

    /**
     * 查找微信接运预约随车物品集合
     *
     * @param idList        主键集合
     * @param parentId      物品父类
     * @param belongService 物品所属业务
     * @return
     * @author LiCongLu
     * @date 2020-07-17 08:51
     */
    @SelectProvider(type = WxRecInfoProvider.class, method = "listWxRecServiceForIdList")
    ArrayList<WxRecServiceItem> listWxRecServiceForIdList(@Param("idList") ArrayList<Integer> idList, @Param("parentId") String parentId, @Param("belongService") String belongService);

    /**
     * 预约通过时，自动绑定关联
     *
     * @param operationNo 业务编号
     * @param openId      微信唯一键
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-30 09:49
     */
    @InsertProvider(type = WxRecInfoProvider.class, method = "insertWxBusinessBinding")
    void insertWxBusinessBinding(@Param("operationNo") String operationNo, @Param("openId") String openId, @Param("loginItem") LoginItem loginItem);
}
