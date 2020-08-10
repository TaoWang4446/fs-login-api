package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * ColdStorageProvider
 *
 * @author LiCongLu
 * @date 2020-07-13 11:13
 */
public class ColdStorageProvider {

    /**
     * 按照时间查询入藏数量
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 11:12
     */
    public String countColdStorageInForLeader(@Param("data") LeaderAllData data) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" 0 as id,'入藏量' as name,COUNT(*) as number ");

                SELECT(builder.toString());
                FROM(TableNames.ColdStorage);

                // 当无查询日期时默认为当前日期
                if (DataUtil.invalid(data.getQueryDate())) {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),ColdStorageInTime,120) = Convert(varchar(10),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),ColdStorageInTime,120) = Convert(varchar(7),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),ColdStorageInTime,120) = Convert(varchar(4),GETDATE(),120) ");
                    }
                } else {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),ColdStorageInTime,120) = Convert(varchar(10),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),ColdStorageInTime,120) = Convert(varchar(7),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),ColdStorageInTime,120) = Convert(varchar(4),#{data.queryDate},120) ");
                    }
                }
            }
        }.toString();
    }

    /**
     * 按照时间查询出藏数量
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 11:12
     */
    public String countColdStorageOutForLeader(@Param("data") LeaderAllData data) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" 0 as id,'出藏量' as name,COUNT(*) as number ");

                SELECT(builder.toString());
                FROM(TableNames.ColdStorage);

                // 当无查询日期时默认为当前日期
                if (DataUtil.invalid(data.getQueryDate())) {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),ColdStorageOutTime,120) = Convert(varchar(10),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),ColdStorageOutTime,120) = Convert(varchar(7),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),ColdStorageOutTime,120) = Convert(varchar(4),GETDATE(),120) ");
                    }
                } else {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),ColdStorageOutTime,120) = Convert(varchar(10),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),ColdStorageOutTime,120) = Convert(varchar(7),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),ColdStorageOutTime,120) = Convert(varchar(4),#{data.queryDate},120) ");
                    }
                }
            }
        }.toString();
    }
}
