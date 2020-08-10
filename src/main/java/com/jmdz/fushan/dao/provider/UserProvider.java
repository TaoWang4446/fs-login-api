package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.vehicle.AppLocation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


/**
 * UserProvider
 *
 * @author LiCongLu
 * @date 2020-07-31 13:51
 */
public class UserProvider {

    /**
     * 按照角色编码查询账号信息
     *
     * @param roleCode 角色编码
     * @return
     * @author LiCongLu
     * @date 2020-07-31 13:51
     */
    public String listBusinessUserByRoleCode(@Param("roleCode") String roleCode) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" distinct fUser.UserId as userId,fUser.Code as userCode,fUser.RealName as realName,fUser.SortCode as sort ");
                SELECT(builder.toString());
                FROM(TableNames.User + " fUser ");
                JOIN(TableNames.UserRole + " userRole on fUser.UserId = userRole.UserId ");
                JOIN(TableNames.Roles + " roles on roles.RoleId = userRole.RoleId ");

                WHERE(" fUser.Enabled = 1 ");
                WHERE(" roles.Code = #{roleCode} ");

                ORDER_BY(" fUser.SortCode ");
            }
        }.toString();
    }

    /**
     * 按照角色查询账号信息，以选项公用类的形式返回
     *
     * @param type     类别
     * @param roleCode 角色编码
     * @return
     * @author LiCongLu
     * @date 2020-07-31 16:40
     */
    public String listPickerUserByRoleCode(@Param("type") String type, @Param("roleCode") String roleCode) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" distinct #{type} as type,fUser.UserId as value,fUser.RealName as text,fUser.Code as tag,fUser.SortCode as row ");
                SELECT(builder.toString());
                FROM(TableNames.User + " fUser ");
                JOIN(TableNames.UserRole + " userRole on fUser.UserId = userRole.UserId ");
                JOIN(TableNames.Roles + " roles on roles.RoleId = userRole.RoleId ");

                WHERE(" fUser.Enabled = 1 ");
                WHERE(" roles.Code = #{roleCode} ");

                ORDER_BY(" fUser.SortCode ");
            }
        }.toString();
    }
}
