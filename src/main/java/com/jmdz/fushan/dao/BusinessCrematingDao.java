package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.BusinessCrematingProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfo;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfoData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.*;

/**
 * BusinessCrematingDao
 *
 * @author LiCongLu
 * @date 2020-08-05 11:27
 */
public interface BusinessCrematingDao {

    /**
     * 按照业务编号加载火化 任务详情 信息
     *
     * @param operationNo 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-06 09:24
     */
    @SelectProvider(type = BusinessCrematingProvider.class, method = "getBusinessCrematingInfoByOperationNo")
    BusinessCrematingInfo getBusinessCrematingInfoByOperationNo(@Param("operationNo") String operationNo);


    /**
     * 新增火化 任务详情 信息
     *
     * @param item      火化 任务详情 信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 13:22
     */
    @InsertProvider(type = BusinessCrematingProvider.class, method = "insertBusinessCrematingInfo")
    @Options(useGeneratedKeys = true, keyProperty = "item.id", keyColumn = "id")
    void insertBusinessCrematingInfo(@Param("item") BusinessCrematingInfoData item, @Param("loginItem") LoginItem loginItem);

    /**
     * 更新火化 任务详情 信息
     *
     * @param item     火化 任务详情 信息
     * @param loginItem 当前账号
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 13:22
     */
    @UpdateProvider(type = BusinessCrematingProvider.class, method = "updateBusinessCrematingInfo")
    void updateBusinessCrematingInfo(@Param("item") BusinessCrematingInfoData item, @Param("loginItem") LoginItem loginItem);

    /**
     * 删除 火化任务 记录
     *
     * @param operationNo 业务编号
     * @return
     * @author Wangshengtao
     * @date 2020-08-06 13:22
     */
    @Delete("delete from " + TableNames.Cremating + " where OperationNo=#{operationNo} ;" +
            "  delete from " + TableNames.Charge + " where OperationNO = #{operationNo} and IsFinished = 0 ;")
    void deleteRecordCremating(@Param("operationNo") String operationNo);

    /**
     * 查询 火化任务 记录
     *
     * @param businessCrematingInfoData 火化实体类
     * @return
     * @author Wangshengtao
     * @date 2020-08-10 18:22
     */
    @SelectProvider(type = BusinessCrematingProvider.class, method = "getBusinessCrematingInfoByCreating")
    BusinessCrematingInfo getBusinessCrematingInfoByCreating(BusinessCrematingInfoData businessCrematingInfoData);
}
