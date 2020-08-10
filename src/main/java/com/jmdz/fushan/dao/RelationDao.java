package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.RelationProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.AgentItem;
import com.jmdz.fushan.pad.model.business.RelationItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.*;

/**
 * RelationDao
 *
 * @author LiCongLu
 * @date 2020-07-16 09:01
 */
public interface RelationDao {
    /**
     * 通过业务编号获取亲属信息
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-16 09:01
     */
    @Select("select TOP 1 ID as relationId,OperationNO as operationNo,Name as relationName,sex as relationSex " +
            ",Phone as relationPhone,Address as relationAddress,DierRelation as deadRelation,CertificateNO as relationCertificateNo " +
            " from " + TableNames.Relation +
            " where OperationNO=#{operationNo}")
    RelationItem getRelationItemByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 新增亲属信息
     *
     * @param item      亲属信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:48
     */
    @InsertProvider(type = RelationProvider.class, method = "insertRelationItem")
    @Options(useGeneratedKeys = true, keyProperty = "item.relationId", keyColumn = "ID")
    void insertRelationItem(@Param("item") RelationItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新亲属信息
     *
     * @param item      亲属信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:48
     */
    @UpdateProvider(type = RelationProvider.class, method = "updateRelationItem")
    void updateRelationItem(@Param("item") RelationItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 通过业务编号获取承办人信息
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:49
     */
    @Select("select TOP 1 ID as agentId,OperationNO as operationNo,FuneralDirector as agentName,FuneralDirectorSex as agentSex" +
            ",FuneralDirectorPhone as agentPhone,FuneralDirectorCertificateNO as agentCertificateNo " +
            " from " + TableNames.Agent +
            " where OperationNO=#{operationNo}")
    AgentItem getAgentItemByOperationNo(@Param("operationNo") String operationNo);


    /**
     * 新增承办人信息
     *
     * @param item      承办人信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:43
     */
    @InsertProvider(type = RelationProvider.class, method = "insertAgentItem")
    @Options(useGeneratedKeys = true, keyProperty = "item.agentId", keyColumn = "ID")
    void insertAgentItem(@Param("item") AgentItem item, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新承办人信息
     *
     * @param item      承办人信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:44
     */
    @UpdateProvider(type = RelationProvider.class, method = "updateAgentItem")
    void updateAgentItem(@Param("item") AgentItem item, @Param("loginItem") LoginItem loginItem);
}
