package com.jmdz.fushan.dao.provider;

import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.ExamineState;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * WxRecInfoProvider
 *
 * @author LiCongLu
 * @date 2020-07-16 14:06
 */
public class WxRecInfoProvider {

    /**
     * 查询微信预约接运未审核信息列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 14:08
     */
    public String listWxRecInfoExamineList() {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" recInfo.id,deadName,deadAge,sex as deadSex,contact as contactName,contactPhone,sItem.Name as carType");
                builder.append(" ,address,CONVERT(varchar(16),arrivalTime,120) as arrivalTime,CONVERT(varchar(16),SysChangeDate,120) as changeDate,remark");
                builder.append(" ,operationNO as operationNo,ISNULL(examineState,0) as examineState ");
                builder.append(" ,openId ");

                SELECT(builder.toString());
                FROM(TableNames.WX_RecInfo + " recInfo");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = carTypeCode ");

                // 微信唯一键不能为空
                WHERE(" ISNULL(openId,'')!='' ");

                // 未删除未取消预约
                WHERE(" ISNULL(recInfo.deleted,0) = 0 and ISNULL(recInfo.asCancel,0) = 0 ");
                // 只加载未审核接运信息
                WHERE(" ISNULL(examineState,0)= " + ExamineState.WeiShenHe);
                ORDER_BY(" recInfo.id ");
            }
        }.toString();
    }

    /**
     * 按照主键查询微信预约接运信息
     *
     * @param id 主键
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:42
     */
    public String getWxRecInfoById(@Param("id") Integer id) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" top 1 recInfo.id,deadName,deadAge,sex as deadSex,contact as contactName,contactPhone ");
                builder.append(" ,sItem.id as carTypeCode,sItem.Name as carType");
                builder.append(" ,address,CONVERT(varchar(16),arrivalTime,120) as arrivalTime,CONVERT(varchar(16),SysChangeDate,120) as changeDate,remark");
                builder.append(" ,operationNO as operationNo,examineUserName,examineUserId,ISNULL(examineState,0) as examineState ");
                builder.append(" ,openId,vehicleId ");

                SELECT(builder.toString());
                FROM(TableNames.WX_RecInfo + " recInfo");
                LEFT_OUTER_JOIN(TableNames.ServiceItem + " sItem on sItem.id = carTypeCode ");

                // 微信唯一键不能为空
                WHERE(" ISNULL(openId,'')!='' ");

                // 未删除未取消预约
                WHERE(" ISNULL(recInfo.deleted,0) = 0 and ISNULL(recInfo.asCancel,0) = 0 ");

                WHERE(" recInfo.id = #{id} ");
            }
        }.toString();
    }

    /**
     * 更新审核状态通过
     *
     * @param id          业务主键
     * @param operationNo 通过业务编号
     * @param vehicleId   接运任务主键
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:56
     */
    public String updateExaminePass(@Param("id") Integer id, @Param("operationNo") String operationNo, @Param("vehicleId") String vehicleId, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.WX_RecInfo);
                SET(" operationNo = #{operationNo}");
                SET(" vehicleId = #{vehicleId}");

                SET(" examineUserName = #{loginItem.realName}");
                SET(" examineUserId = #{loginItem.userId}");
                SET(" examineTime = CONVERT(varchar(19),GETDATE(),120)");
                SET(" examineState= " + ExamineState.TongGuo);

                SET(" SysChangeDate = GETDATE()");
                WHERE(" ISNULL(deleted,0) = 0 and ISNULL(asCancel,0) = 0 ");
                WHERE(" id = #{id} ");
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
     * @date 2020-07-16 15:56
     */
    public String updateExamineFail(@Param("id") Integer id, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                UPDATE(TableNames.WX_RecInfo);
                SET(" examineUserName= #{loginItem.realName}");
                SET(" examineUserId = #{loginItem.userId}");
                SET(" examineTime = CONVERT(varchar(19),GETDATE(),120)");
                SET(" examineState= " + ExamineState.BuTongGuo);
                SET(" SysChangeDate = GETDATE()");
                WHERE(" ISNULL(deleted,0) = 0 and ISNULL(asCancel,0) = 0 ");
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

    /**
     * 查找微信接运预约随车物品集合
     *
     * @param idList        主键集合
     * @param parentId      物品父类
     * @param belongService 物品所属业务
     * @return
     * @author LiCongLu
     * @date 2020-07-17 08:51
     */
    public String listWxRecServiceForIdList(@Param("idList") ArrayList<Integer> idList, @Param("parentId") String parentId, @Param("belongService") String belongService) {
        return new SQL() {
            {
                StringBuilder builder = new StringBuilder();
                builder.append(" selItem.id,sItem.id as itemId,sItem.Name as itemName ");
                builder.append(" ,ISNULL(selItem.price,sItem.Price) as price,ISNULL(selItem.amount,1) as number,selItem.recId as recId ");

                SELECT(builder.toString());
                FROM(TableNames.WX_SelServiceItem + " selItem ");
                JOIN(TableNames.ServiceItem + " sItem on sItem.id = selItem.serviceItemId ");

                WHERE(" sItem.IsDeleted = 0 ");

                String ids = StringUtils.arrayToDelimitedString(idList.toArray(new Integer[]{}), ",");
                WHERE(" selItem.recId in (" + ids + ")");

                WHERE(" deleted = 0 ");

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
     * 预约通过时，自动绑定关联
     *
     * @param operationNo 业务编号
     * @param openId      微信唯一键
     * @param loginItem   当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-30 09:49
     */
    public String insertWxBusinessBinding(@Param("operationNo") String operationNo, @Param("openId") String openId, @Param("loginItem") LoginItem loginItem) {
        return new SQL() {
            {
                INSERT_INTO(TableNames.WX_BusinessBinding);
                VALUES("OpenId", "#{openId}");
                VALUES("OperationNo", "#{operationNo}");

                VALUES("Deleted", "0");
                VALUES("FuneralParlourCode", "''");
                VALUES("CreateUserId", "#{loginItem.userId}");
                VALUES("GmtCreate", "getdate()");
                VALUES("Unmodified", "0");
                VALUES("ModifiedUserId", "#{loginItem.userId}");
                VALUES("GmtModified", "getdate()");
                VALUES("version", "0");
            }
        }.toString();
    }

}
