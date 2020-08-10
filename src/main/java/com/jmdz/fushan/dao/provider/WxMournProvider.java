package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.ExamineState;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * WxMournProvider
 *
 * @author LiCongLu
 * @date 2020-07-21 10:18
 */
public class WxMournProvider {


    /**
     * 查询微信预约告别未审核信息列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 14:08
     */
    public String listWxMournExamineList() {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  id, dead.OperationNO as operationNo,dead.DierName as deadName,ContactId as contactId,ContactName as contactName ");
                builder.append(" ,ContactPhone as contactPhone,BeginTime as beginTime,EndTime as endTime");
                builder.append(" ,ISNULL(ExamineState,0) as examineState,mourn.Remark as remark");
                builder.append(" ,mourn.MournHallId as hallId,hall.MournName as hallName");
                builder.append(" ,details.ItemDetailsId as itemDetailsId,details.ItemName as itemDetailsName ");

                SELECT(builder.toString());
                FROM(TableNames.WX_Mourn + " mourn ");
                JOIN(TableNames.Dead + " dead on dead.OperationNO = mourn.OperationNo");
                LEFT_OUTER_JOIN(TableNames.ItemDetails + " details on details.ItemDetailsId = ItemTypeName ");
                LEFT_OUTER_JOIN(TableNames.MournHall + " hall on hall.MournHallId = mourn.MournHallId ");

                // 未删除未取消预约
                WHERE(" ISNULL(Deleted,0) = 0 and ISNULL(AppointmentState,0) = 0 ");
                // 只加载未审核接运信息
                WHERE(" ISNULL(ExamineState,0)= " + ExamineState.WeiShenHe);
                ORDER_BY(" id ");
            }
        }.toString();
    }

    /**
     * 按照预约任务主键获取服务物品
     *
     * @param idList        主键集合
     * @param parentId      物品父类
     * @param belongService 物品所属业务
     * @return
     * @author LiCongLu
     * @date 2020-07-21 13:02
     */
    public String listWxMournServiceForIdList(@Param("idList") ArrayList<Integer> idList, @Param("parentId") String parentId, @Param("belongService") String belongService) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" wxService.id,sItem.id as itemId,sItem.Name as itemName ");
                builder.append(" ,ISNULL(wxService.Price,sItem.Price) as price,ISNULL(wxService.Number,1) as number,wxService.WxMournId as wxMournId ");

                SELECT(builder.toString());
                FROM(TableNames.WX_MournService + " wxService ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = wxService.ServiceItemId ");

                WHERE(" sItem.IsDeleted = 0 ");

                String ids = StringUtils.arrayToDelimitedString(idList.toArray(new Integer[]{}), ",");
                WHERE(" wxService.WxMournId in (" + ids + ")");

                WHERE(" wxService.Deleted = 0 ");

                if (DataUtil.valid(parentId)) {
                    WHERE(" sItem.ParentId in (" + parentId + ")");
                }
                if (DataUtil.valid(belongService)) {
                    if (!belongService.contains(",")) {
                        WHERE(" sItem.BelongServiceItem like'%" + belongService + "%'");
                    } else {
                        String[] services = belongService.split(",");
                        String serviceLike = "";
                        for (int i = 0; i < services.length; i++) {
                            if (i == 0) {
                                serviceLike += " sItem.BelongServiceItem like'%" + services[i] + "%' ";
                            } else {
                                serviceLike += " or sItem.BelongServiceItem like'%" + services[i] + "%'";
                            }
                        }

                        WHERE(" (" + serviceLike + ") ");
                    }
                }
                ORDER_BY(" sItem.OrderBy ");
            }
        }.toString();
    }

    /**
     * 按照主键获取预约告别信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:03
     */
    public String getWxMournById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append("  id,OperationNo as operationNo,ContactId as contactId,ContactName as contactName ");
                builder.append(" ,ContactPhone as contactPhone,BeginTime as beginTime,EndTime as endTime");
                builder.append(" ,ISNULL(ExamineState,0) as examineState,Remark as remark");
                builder.append(" ,mourn.MournHallId as hallId,hall.ServiceItemID as hallTypeId,hall.MournName as hallName");
                builder.append(" ,details.ItemDetailsId as itemDetailsId,details.ItemName as itemDetailsName ");
                builder.append(" ,MournId as mournId ");

                SELECT(builder.toString());
                FROM(TableNames.WX_Mourn + " mourn ");
                LEFT_OUTER_JOIN(TableNames.ItemDetails + " details on details.ItemDetailsId = ItemTypeName ");
                LEFT_OUTER_JOIN(TableNames.MournHall + " hall on hall.MournHallId = mourn.MournHallID ");

                // 未删除未取消预约
                WHERE(" ISNULL(Deleted,0) = 0 and ISNULL(AppointmentState,0) = 0 ");
                WHERE(" mourn.id = #{id} ");
            }
        }.toString();
    }

    /**
     * 更新审核状态通过
     *
     * @param id          业务主键
     * @param operationNo 通过业务编号
     * @param mournId     任务主键
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:05
     */
    public String updateExaminePass(@Param("id") Integer id, @Param("operationNo") String operationNo, @Param("mournId") Integer mournId, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.WX_Mourn);

                SET(" MournId = #{mournId}");

                SET(" ExamineUserId = #{loginItem.userId}");
                SET(" ExamineUserName= #{loginItem.realName}");
                SET(" ExamineState= " + ExamineState.TongGuo);
                SET(" ExamineTime = GETDate()");

                SET(" ModifiedUserId= #{loginItem.userId}");
                SET(" GmtModified = GETDATE()");
                WHERE(" ISNULL(Deleted,0) = 0 and ISNULL(AppointmentState,0) = 0  ");
                WHERE(" id = #{id} ");
                WHERE(" operationNo = #{operationNo}");
            }
        }.toString();
    }

    /**
     * 更新审核状态不通过
     *
     * @param id        业务主键
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-21 14:05
     */
    public String updateExamineFail(@Param("id") Integer id, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.WX_Mourn);
                SET(" ExamineUserId = #{loginItem.userId}");
                SET(" ExamineUserName= #{loginItem.realName}");
                SET(" ExamineState= " + ExamineState.BuTongGuo);
                SET(" ExamineTime = GETDate()");

                SET(" ModifiedUserId= #{loginItem.userId}");
                SET(" GmtModified = GETDATE()");
                WHERE(" ISNULL(Deleted,0) = 0 and ISNULL(AppointmentState,0) = 0  ");
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
