package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * BusinessLogProvider
 *
 * @author LiCongLu
 * @date 2020-06-12 15:53
 */
public class BusinessLogProvider {

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
    public String insertBusinessLog(@Param("userId") String userId, @Param("userName") String userName, @Param("businessType") String businessType
            , @Param("businessName") String businessName, @Param("operateType") String operateType, @Param("logContent") String logContent, @Param("operationNo") String operationNo) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.BusinessLog);
                VALUES("LogUserId", "#{userId}");
                VALUES("LogUserName", "#{userName}");
                VALUES("LogDate", "getdate()");
                VALUES("LogContent", "#{logContent}");
                VALUES("LogBusinessType", "#{businessType}");
                VALUES("LogBusinessName", "#{businessName}");
                VALUES("LogOperateType", "#{operateType}");
                VALUES("OperationNo", "#{operationNo}");
            }
        }.toString();
    }
}
