package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * ServiceProvider
 *
 * @author LiCongLu
 * @date 2020-06-10 15:06
 */
public class ServiceProvider {

    /**
     * 获取业务费用列表
     *
     * @param parentId      父主键
     * @param belongService 所属业务
     * @return
     * @author LiCongLu
     * @date 2020-07-17 10:06
     */
    public String listServiceItemByBelong(@Param("parentId") String parentId, @Param("belongService") String belongService) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" id as id,ParentId as parentId,Name as name,DisplayName as displayName,Price as price ");
                builder.append(" ,IsFixed as asFixed,IsDeleted as asDeleted ");
                builder.append(" ,BelongServiceItem as belongServiceItem,prickle as prickle,IsFlower as asFlower,IsPackage as asPackage,Biaokey as biaoKey ");
                SELECT(builder.toString());
                FROM(TableNames.ServiceItem);
                WHERE(" IsDeleted=0 and ParentId in (" + parentId + ")");
                if (DataUtil.valid(belongService)) {
                    if (belongService.contains(",")) {
                        String[] services = belongService.split(",");
                        String serviceLike = "";
                        for (int i = 0; i < services.length; i++) {
                            if (i == 0) {
                                serviceLike += " BelongServiceItem like'%" + services[i] + "%' ";
                            } else {
                                serviceLike += " or BelongServiceItem like'%" + services[i] + "%'";
                            }
                        }
                        WHERE(" (" + serviceLike + ") ");
                    } else {
                        WHERE(" BelongServiceItem like'%" + belongService + "%'");
                    }
                }
                ORDER_BY(" OrderBy asc ");
            }
        }.toString();
    }

    /**
     * 获取业务费用列表
     *
     * @param parentId      父主键
     * @param belongService 所属业务
     * @return
     * @author LiCongLu
     * @date 2020-08-03 13:12
     */
    public String listBusinessServiceByBelong(@Param("parentId") String parentId, @Param("belongService") String belongService) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" id as id,Name as name,DisplayName as displayName,Price as price,prickle as unit ");
                SELECT(builder.toString());
                FROM(TableNames.ServiceItem);
                WHERE(" IsDeleted=0 and ParentId in (" + parentId + ")");
                if (DataUtil.valid(belongService)) {
                    if (belongService.contains(",")) {
                        String[] services = belongService.split(",");
                        String serviceLike = "";
                        for (int i = 0; i < services.length; i++) {
                            if (i == 0) {
                                serviceLike += " BelongServiceItem like'%" + services[i] + "%' ";
                            } else {
                                serviceLike += " or BelongServiceItem like'%" + services[i] + "%'";
                            }
                        }
                        WHERE(" (" + serviceLike + ") ");
                    } else {
                        WHERE(" BelongServiceItem like'%" + belongService + "%'");
                    }
                }
                ORDER_BY(" OrderBy asc ");
            }
        }.toString();
    }
}
