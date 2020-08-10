package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * MournProvider
 *
 * @author LiCongLu
 * @date 2020-07-13 10:25
 */
public class MournProvider {

    /**
     * 按照查询日期统计告别守灵数量
     *
     * @param data    查询条件
     * @param isMourn 是否告别
     * @return
     * @author LiCongLu
     * @date 2020-07-13 10:25
     */
    public String countMournForLeader(@Param("data") LeaderAllData data, @Param("isMourn") Integer isMourn) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" hall.MournHallId as id,hall.MournName as name,COUNT(*) as number ");

                SELECT(builder.toString());
                FROM(TableNames.Mourn+" mourn ");
                JOIN(TableNames.MournHall+" hall on hall.MournHallId = mourn.MournHallID ");

                WHERE(" mourn.IsMourn = #{isMourn} ");

                // 当无查询日期时默认为当前日期
                if (DataUtil.invalid(data.getQueryDate())) {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),BeginTime,120) = Convert(varchar(10),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),BeginTime,120) = Convert(varchar(7),GETDATE(),120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),BeginTime,120) = Convert(varchar(4),GETDATE(),120) ");
                    }
                } else {
                    if (DataUtil.invalid(data.getQueryType())) {
                        WHERE(" Convert(varchar(10),BeginTime,120) = Convert(varchar(10),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 1) {
                        WHERE(" Convert(varchar(7),BeginTime,120) = Convert(varchar(7),#{data.queryDate},120) ");
                    } else if (data.getQueryType().intValue() == 2) {
                        WHERE(" Convert(varchar(4),BeginTime,120) = Convert(varchar(4),#{data.queryDate},120) ");
                    }
                }


                GROUP_BY(" hall.MournHallId,hall.MournName ");
                ORDER_BY(" hall.MournHallId ");
            }
        }.toString();
    }
}
