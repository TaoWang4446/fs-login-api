package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.business.FuneralProductItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * FuneralProductProvider
 *
 * @author LiCongLu
 * @date 2020-08-04 11:16
 */
public class FuneralProductProvider {

    /**
     * 新增丧葬用品服务
     *
     * @param item   丧葬服务物品
     * @param userId 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 11:18
     */
    public String insertFuneralProductItem(@Param("item") FuneralProductItem item, @Param("userId") String userId) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.FuneralProduct);
                VALUES("OperationNO", "#{item.operationNo}");
                VALUES("ServiceItemId", "#{item.serviceItemId}");
                VALUES("OperateTime", "convert(varchar(16),getdate(),120)");
                VALUES("Number", "#{item.number}");
                VALUES("Price", "#{item.price}");
                VALUES("Charge", "#{item.charge}");
                VALUES("Random_Id", "#{item.randomId}");
                VALUES("Remark", "#{item.remark}");
                VALUES("Biaokey", "#{item.biaokey}");
                VALUES("Operator", "#{userId}");
            }
        }.toString();
    }
}
