package com.jmdz.fushan.pad.model.business.wake;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务洽谈守灵厅选择请求数据
 *
 * @author LiCongLu
 * @date 2020-08-05 11:43
 */
@ApiModel(value = "业务洽谈守灵厅选择请求数据", description = "业务洽谈守灵厅选择请求数据")
public class WakeHallListData extends UserData {
    /**
     * 业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 4)
    private String operationNo;

    /**
     * 预约开始时间
     */
    @AnValidate(name = "预约开始时间", required = true)
    @ApiModelProperty(value = "预约开始时间", name = "beginTime", position = 5)
    private String beginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间", name = "endTime", position = 6)
    private String endTime;

    public String getOperationNo() {
        return operationNo;
    }

    public WakeHallListData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public WakeHallListData setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public WakeHallListData setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }
}
