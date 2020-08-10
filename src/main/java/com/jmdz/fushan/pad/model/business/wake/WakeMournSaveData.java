package com.jmdz.fushan.pad.model.business.wake;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.business.BusinessServiceData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 业务洽谈守灵任务保存请求数据
 *
 * @author LiCongLu
 * @date 2020-08-06 11:49
 */
@ApiModel(value = "业务洽谈守灵任务保存请求数据", description = "业务洽谈守灵任务保存请求数据")
public class WakeMournSaveData extends UserData {
    /**
     * 守灵主键
     */
    @ApiModelProperty(value = "守灵主键", name = "id", position = 4)
    private Integer id;

    /**
     * 占用守灵任务，业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 5)
    private String operationNo;

    /**
     * 守灵厅主键
     */
    @AnValidate(name = "守灵厅主键", required = true)
    @ApiModelProperty(value = "守灵厅主键", name = "hallId", position = 6)
    private Integer hallId;

    /**
     * 预约开始时间
     */
    @AnValidate(name = "预约开始时间", required = true)
    @ApiModelProperty(value = "预约开始时间", name = "beginTime", position = 7)
    private String beginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间", name = "endTime", position = 8)
    private String endTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", position = 9)
    private String remark;

    /**
     * 新增服务和物品费用集合
     */
    @AnValidate(name = "新增服务和物品集合")
    @ApiModelProperty(value = "新增服务和物品集合", name = "serviceItems", position = 10)
    private ArrayList<BusinessServiceData> serviceItems;

    /**
     * 删除费用主键集合
     */
    @ApiModelProperty(value = "删除费用主键集合", name = "deleteIds", position = 11)
    private ArrayList<Integer> deleteIds;

    public Integer getId() {
        return id;
    }

    public WakeMournSaveData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public WakeMournSaveData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public Integer getHallId() {
        return hallId;
    }

    public WakeMournSaveData setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public WakeMournSaveData setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public WakeMournSaveData setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WakeMournSaveData setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public ArrayList<BusinessServiceData> getServiceItems() {
        return serviceItems;
    }

    public WakeMournSaveData setServiceItems(ArrayList<BusinessServiceData> serviceItems) {
        this.serviceItems = serviceItems;
        return this;
    }

    public ArrayList<Integer> getDeleteIds() {
        return deleteIds;
    }

    public WakeMournSaveData setDeleteIds(ArrayList<Integer> deleteIds) {
        this.deleteIds = deleteIds;
        return this;
    }
}
