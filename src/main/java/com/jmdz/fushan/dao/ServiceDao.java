package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.ServiceProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.business.BusinessServiceItem;
import com.jmdz.fushan.pad.model.business.PickerItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllLabelItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

/**
 * @author LiCongLu
 * @date 2020-07-13 14:28
 */
public interface ServiceDao {

    /**
     * 按照父类主键获取物品分类或服务项目名称
     *
     * @param parent 服务类主键
     * @return
     * @author LiCongLu
     * @date 2020-07-13 14:33
     */
    @Select("select id,Name as name,0 as number from " + TableNames.ServiceItem
            + " where parentId in (${parent}) and IsDeleted = 0 "
            + " order by id")
    ArrayList<LeaderAllLabelItem> listServiceParentByParentId(@Param("parent") String parent);


    /**
     * 获取收费服务项目
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-17 10:01
     */
    @Select(" select id as id,ParentId as parentId,Name as name,DisplayName as displayName,Price as price " +
            " ,IsFixed as asFixed,IsDeleted as asDeleted" +
            " ,BelongServiceItem as belongServiceItem,prickle as prickle,IsFlower as asFlower,IsPackage as asPackage,Biaokey as biaoKey " +
            " from " + TableNames.ServiceItem +
            " where id = #{id} and IsDeleted=0")
    ServiceItem getServiceItemById(@Param("id") int id);

    /**
     * 获取业务费用列表
     *
     * @param parentId      父主键
     * @param belongService 所属业务
     * @return
     * @author LiCongLu
     * @date 2020-07-17 10:06
     */
    @SelectProvider(type = ServiceProvider.class, method = "listServiceItemByBelong")
    ArrayList<ServiceItem> listServiceItemByBelong(@Param("parentId") String parentId, @Param("belongService") String belongService);

    /**
     * 按照父类加载费用集合，以选项公用类的形式返回
     *
     * @param parentId 父主键
     * @return
     * @author LiCongLu
     * @date 2020-08-03 10:56
     */
    @Select(" select id as value,Name as text,Price as price,id as tagInt " +
            " from " + TableNames.ServiceItem +
            " where ParentId = #{parentId} and IsDeleted=0")
    ArrayList<PickerItem> listPickerServiceByParentId(@Param("parentId") Integer parentId);

    /**
     * 获取业务费用列表
     *
     * @param parentId      父主键
     * @param belongService 所属业务
     * @return
     * @author LiCongLu
     * @date 2020-08-03 13:12
     */
    @SelectProvider(type = ServiceProvider.class, method = "listBusinessServiceByBelong")
    ArrayList<BusinessServiceItem> listBusinessServiceByBelong(@Param("parentId") String parentId, @Param("belongService") String belongService);

}
