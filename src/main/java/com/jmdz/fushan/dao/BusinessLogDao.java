package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.BusinessLogProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * BusinessLogDao
 *
 * @author LiCongLu
 * @date 2020-06-12 15:52
 */
public interface BusinessLogDao {

    /**
     * 新增日志信息
     *
     * @param userId       当前账号
     * @param userName     当前账号名称
     * @param businessType 业务类型
     * @param businessName 业务名称
     * @param operateType  操作类型
     * @param logContent   日志内容
     * @param operationNo  逝者编号
     * @return
     * @author LiCongLu
     * @date 2020-06-12 15:52
     */
    @InsertProvider(type = BusinessLogProvider.class, method = "insertBusinessLog")
    void insertBusinessLog(@Param("userId") String userId, @Param("userName") String userName, @Param("businessType") String businessType
            , @Param("businessName") String businessName, @Param("operateType") String operateType, @Param("logContent") String logContent
            , @Param("operationNo") String operationNo);

}