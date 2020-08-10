package com.jmdz.fushan.dao;


import com.jmdz.fushan.dao.provider.UserProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.BusinessUserItem;
import com.jmdz.fushan.pad.model.business.PickerItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

/**
 * 系统账号Dao
 *
 * @author LiCongLu
 * @date 2020-06-05 14:14
 */
public interface UserDao {

    /**
     * 通过账号获取账号信息
     *
     * @param account
     * @return
     * @author LiCongLu
     * @date 2020-06-05 14:16
     */
    @Select("select UserId as userId,Account as account,Code as userCode,Password as password,Secretkey as secretkey,RealName as realName,Mobile as mobile,Enabled as enabled " +
            " from  " + TableNames.User + " where Account = #{account}")
    LoginUserItem getLoginUserByAccount(@Param("account") String account);

    /**
     * 通过UserId查询账号信息
     *
     * @param userId 账号主键
     * @return
     * @author LiCongLu
     * @date 2020-06-05 14:18
     */
    @Select("select UserId as userId,Account as account,Code as userCode,Password as password,Secretkey as secretkey,RealName as realName,Mobile as mobile,Enabled as enabled " +
            " from  " + TableNames.User + " where UserId = #{userId}")
    LoginUserItem getLoginUserByUserId(@Param("userId") String userId);

    /**
     * 按照角色编码查询账号信息
     *
     * @param roleCode 角色编码
     * @return
     * @author LiCongLu
     * @date 2020-07-31 13:51
     */
    @SelectProvider(type = UserProvider.class, method = "listBusinessUserByRoleCode")
    ArrayList<BusinessUserItem> listBusinessUserByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 按照角色查询账号信息，以选项公用类的形式返回
     *
     * @param type     类别
     * @param roleCode 角色编码
     * @return
     * @author LiCongLu
     * @date 2020-07-31 16:40
     */
    @SelectProvider(type = UserProvider.class, method = "listPickerUserByRoleCode")
    ArrayList<PickerItem> listPickerUserByRoleCode(@Param("type") String type, @Param("roleCode") String roleCode);
}
