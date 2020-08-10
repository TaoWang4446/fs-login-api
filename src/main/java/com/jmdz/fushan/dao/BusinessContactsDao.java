package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.BusinessContactsProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

/**
 * BusinessContactsDao
 *
 * @author Wangshengtao
 * @date 2020-08-04 13:22
 */
public interface BusinessContactsDao {

    /**
     * 新增联系人信息
     *
     * @param item      联系人信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-04 13:22
     */
    @InsertProvider(type = BusinessContactsProvider.class, method = "insertBusinessContactsInfo")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertBusinessContactsInfo(@Param("item") BusinessContactsInfoData item, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新联系人信息
     *
     * @param item      联系人信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-04 13:22
     */
    @UpdateProvider(type = BusinessContactsProvider.class, method = "updateBusinessContactsInfo")
    void updateBusinessContactsInfo(@Param("item") BusinessContactsInfoData item, @Param("loginItem") LoginItem loginItem);

    /**
     * 按照业务编号加载联系人信息
     *
     * @param operationNo 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-04 13:24
     */
    @SelectProvider(type = BusinessContactsProvider.class, method = "getBusinessContactsInfoByOperationNo")
    BusinessContactsInfo getBusinessContactsInfoByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 按照联系人主键加载联系人信息
     *
     * @param id 主键
     * @return
     * @author WangShengtao
     * @date 2020-08-05 10:24
     */
    @Select("select ID as id from " + TableNames.Contacts + " where ID=#{id}")
    @ResultType(Integer.class)
    Integer getBusinessContactsInfoById(@Param("id") Integer id);
}

