package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.WxMournProvider;
import com.jmdz.fushan.dao.provider.WxRecInfoProvider;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.weixin.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.ArrayList;

/**
 * WxMournDao
 *
 * @author LiCongLu
 * @date 2020-07-21 10:17
 */
public interface WxMournDao {

    /**
     * 查询微信预约告别未审核信息列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 14:08
     */
    @SelectProvider(type = WxMournProvider.class, method = "listWxMournExamineList")
    ArrayList<WxMournExamineItem> listWxMournExamineList();

    /**
     * 按照预约任务主键获取服务物品
     *
     * @param idList        主键集合
     * @param parentId      物品父类
     * @param belongService 物品所属业务
     * @return
     * @author LiCongLu
     * @date 2020-07-21 13:02
     */
    @SelectProvider(type = WxMournProvider.class, method = "listWxMournServiceForIdList")
    ArrayList<WxMournServiceItem> listWxMournServiceForIdList(@Param("idList") ArrayList<Integer> idList, @Param("parentId") String parentId, @Param("belongService") String belongService);

    /**
     * 按照主键获取预约告别信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:03
     */
    @SelectProvider(type = WxMournProvider.class, method = "getWxMournById")
    WxMournItem getWxMournById(@Param("id") Integer id);

    /**
     * 更新审核状态通过
     *
     * @param id          业务主键
     * @param operationNo 通过业务编号
     * @param mournId     任务主键
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:05
     */
    @UpdateProvider(type = WxMournProvider.class, method = "updateExaminePass")
    void updateExaminePass(@Param("id") Integer id, @Param("operationNo") String operationNo, @Param("mournId") Integer mournId, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新审核状态不通过
     *
     * @param id        业务主键
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:05
     */
    @UpdateProvider(type = WxMournProvider.class, method = "updateExamineFail")
    void updateExamineFail(@Param("id") Integer id, @Param("loginItem") LoginItem loginItem);
}
