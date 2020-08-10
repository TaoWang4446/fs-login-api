package com.jmdz.fushan.dao.provider;

import com.jmdz.fushan.model.config.TableNames;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * TaskRecordProvider
 *
 * @author LiCongLu
 * @date 2020-07-15 09:01
 */
public class TaskRecordProvider {

    /**
     * 按照业务编号查询费用任务记录
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-15 09:00
     */
    public String listTaskRecordByOperationNo(@Param("operationNo") String operationNo) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" record.Id as id,record.OperationNO as operationNo,sItem.id as itemId,sItem.Name as itemName,sItem.ParentId as parentId ");
                builder.append(" ,RecordType as recordType,MainServiceId as mainServiceId,EquipmentId as equipmentId ");
                builder.append(" ,Number as number,record.Price as price,Charge as charge,IsPid as asPid ");
                builder.append(" ,ChangeDescribe as changeDescribe,SendState as sendState,SendTime as sendTime");
                SELECT(builder.toString());
                FROM(TableNames.TaskRecord + " record ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = record.ServiceItemId ");
                WHERE(" record.OperationNO = #{operationNo} ");
            }
        }.toString();
    }
}
