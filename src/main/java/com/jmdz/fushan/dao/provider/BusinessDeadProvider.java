package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;

/**
 * BusinessDeadProvider
 *
 * @author LiCongLu
 * @date 2020-07-28 13:42
 */
public class BusinessDeadProvider {

    /**
     * 按照关键字查询逝者信息
     *
     * @param keyword 关键字
     * @return
     * @author LiCongLu
     * @date 2020-07-28 13:41
     */
    public String listBusinessDeadByKeyword(@Param("keyword") String keyword) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 10 dead.OperationNO as operationNo,DocumentState as documentState ");
                builder.append(" ,DierName as deadName,DierSex as deadSex,DierAge as deadAge,DeathAddress as deathAddress ");
                builder.append(" ,wakeHall.MournName as wakeHallName,farewellHall.MournName as farewellHallName");
                builder.append(" ,crematingItem.Name as machineTypeName ");

                SELECT(builder.toString());
                FROM(TableNames.Dead + " dead ");
                LEFT_OUTER_JOIN(TableNames.Mourn + " wake on wake.OperationNO= dead.OperationNO and wake.IsMourn = 0");
                LEFT_OUTER_JOIN(TableNames.MournHall + " wakeHall on wakeHall.MournHallId = wake.MournHallID");
                LEFT_OUTER_JOIN(TableNames.Mourn + " farewell on farewell.OperationNO= dead.OperationNO and farewell.IsMourn = 1");
                LEFT_OUTER_JOIN(TableNames.MournHall + " farewellHall on farewellHall.MournHallId = farewell.MournHallID");
                LEFT_OUTER_JOIN(TableNames.Cremating + " cremating on cremating.OperationNO = dead.OperationNO");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " crematingItem on crematingItem.id = cremating.CrematingMachineTypeID");

                WHERE(" dead.IsDead = 1 ");

                WHERE(" (dead.OperationNO = #{keyword} or DierName like '%'+'" + keyword + "'+'%' ) ");

                ORDER_BY(" dead.OperationNO desc ");
            }
        }.toString();
    }
}
