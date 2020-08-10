package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.awt.*;

/**
 * CrematingProvider
 *
 * @author LiCongLu
 * @date 2020-07-13 10:10
 */
public class CrematingProvider {

    /**
     * 按照查询日期统计火化数量
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 10:07
     */
    public String countCrematingForLeader(@Param("data") LeaderAllData data) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" sItem.id as id,sItem.Name as name,COUNT(*) as number ");

                SELECT(builder.toString());
                FROM(TableNames.Cremating + " cremating ");
                JOIN(TableNames.ServiceItem + "  sItem on sItem.id = cremating.CrematingMachineTypeID ");

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


                GROUP_BY(" sItem.id,sItem.Name ");
                ORDER_BY(" sItem.id ");
            }
        }.toString();
    }

    /**
     * 加载送灵任务列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-22 16:40
     */
    public String listSendSpirit() {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" distinct cremating.id as id,dead.OperationNO as operationNo,dead.DierName as deadName");
                builder.append(" ,CremationTime as cremationTime,ZX_state as cremationState");
                builder.append(" ,ISNULL(ConfirmSendSpirit,0) as confirmSendSpirit,IsGetAsh as asGetAsh");
                builder.append(" ,ISNULL(CremationTime,OrderCremationTime) as orderTime");
                SELECT(builder.toString());
                FROM(TableNames.Cremating + " cremating ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = cremating.OperationNO");
                JOIN(TableNames.Charge + " charge on charge.OperationNO = dead.OperationNO ");
                JOIN(TableNames.ServiceItem + "  sItem on charge.ServiceItem = sItem.id ");
                WHERE(" dead.IsDead = 1 ");
                WHERE(" charge.IsDelete = 0 ");
                WHERE(" sItem.IsSendSpirit = 1 ");
                WHERE(" CONVERT(varchar(10),OrderCremationTime,120)= CONVERT(varchar(10),GETDATE(),120) ");

                ORDER_BY(" CremationTime ");
            }
        }.toString();
    }

    /**
     * 按照主键加载送灵任务信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-23 10:02
     */
    public String getSendSpiritById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 cremating.id as id,dead.OperationNO as operationNo,dead.DierName as deadName");
                builder.append(" ,CremationTime as cremationTime,ZX_state as cremationState");
                builder.append(" ,ISNULL(ConfirmSendSpirit,0) as confirmSendSpirit,IsGetAsh as asGetAsh");
                SELECT(builder.toString());
                FROM(TableNames.Cremating + " cremating ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = cremating.OperationNO");
                JOIN(TableNames.Charge + " charge on charge.OperationNO = dead.OperationNO ");
                JOIN(TableNames.ServiceItem + "  sItem on charge.ServiceItem = sItem.id ");
                WHERE(" dead.IsDead = 1 ");
                WHERE(" charge.IsDelete = 0 ");
                WHERE(" sItem.IsSendSpirit = 1 ");
                WHERE(" cremating.id = #{id}");
            }
        }.toString();
    }
}
