package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.AgentItem;
import com.jmdz.fushan.pad.model.business.RelationItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * RelationProvider
 *
 * @author LiCongLu
 * @date 2020-07-17 09:45
 */
public class RelationProvider {
    /**
     * 新增亲属信息
     *
     * @param item      亲属信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:48
     */
    public String insertRelationItem(@Param("item") RelationItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO( TableNames.Relation );
                VALUES( "OperationNO", "#{item.operationNo}" );
                VALUES( "Name", "#{item.relationName}" );
                VALUES( "sex", "#{item.relationSex}" );
                VALUES( "Phone", "#{item.relationPhone}" );
                VALUES( "Address", "#{item.relationAddress}" );
                VALUES( "DierRelation", "#{item.deadRelation}" );
                VALUES( "CertificateNO", "#{item.relationCertificateNo}" );
                VALUES( "Operator", "#{loginItem.userCode}" );
                VALUES( "OperateTime", "getdate()" );
            }
        }.toString();
    }

    /**
     * 更新亲属信息
     *
     * @param item      亲属信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:48
     */
    public String updateRelationItem(@Param("item") RelationItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE( TableNames.Relation );
                SET( "Name = #{item.relationName}" );
                SET( "sex = #{item.relationSex}" );
                SET( "Phone = #{item.relationPhone}" );
                SET( "Address = #{item.relationAddress}" );
                SET( "DierRelation = #{item.deadRelation}" );
                SET( "CertificateNO = #{item.relationCertificateNo}" );
                SET( "Operator = #{loginItem.userCode}" );
                SET( "OperateTime = getdate()" );
                WHERE( " OperationNO = #{item.operationNo} " );
            }
        }.toString();
    }

    /**
     * 新增承办人信息
     *
     * @param item      承办人信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:43
     */
    public String insertAgentItem(@Param("item") AgentItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO( TableNames.Agent );
                VALUES( "OperationNO", "#{item.operationNo}" );
                VALUES( "Name", "''" );
                VALUES( "FuneralDirector", "#{item.agentName}" );
                VALUES( "FuneralDirectorSex", "#{item.agentSex}" );
                VALUES( "FuneralDirectorPhone", "#{item.agentPhone}" );
                VALUES( "FuneralDirectorCertificateNO", "#{item.agentCertificateNo}" );
                VALUES( "Operator", "#{loginItem.userCode}" );
                VALUES( "OperateTime", "getdate()" );
            }
        }.toString();
    }

    /**
     * 更新承办人信息
     *
     * @param item      承办人信息
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-17 09:44
     */
    public String updateAgentItem(@Param("item") AgentItem item, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE( TableNames.Agent );
                SET( "FuneralDirector = #{item.agentName}" );
                SET( "FuneralDirectorSex = #{item.agentSex}" );
                SET( "FuneralDirectorPhone = #{item.agentPhone}" );
                SET( "FuneralDirectorCertificateNO = #{item.agentCertificateNo}" );
                SET( "Operator = #{loginItem.userCode}" );
                SET( "OperateTime = getdate()" );
                WHERE( " OperationNO = #{item.operationNo} " );
            }
        }.toString();
    }
}
