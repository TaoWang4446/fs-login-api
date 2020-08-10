package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 逝者任务详情请求数据
 *
 * @author Sunzhaoqi
 * @date 2020/7/13 9:11
 */
@ApiModel(value = "逝者任务详情请求数据", description = "逝者任务详情请求数据")
public class DeadDetailsAllData extends UserData {

    /**
     * 查询业务编号
     */
    @AnValidate(value = "查询业务编号", required = true)
    @ApiModelProperty(value = "查询业务编号", name = "operationNo", example = "20200309035", position = 3)
    private String operationNo;

    public String getOperationNo() {
        return operationNo;
    }

    public DeadDetailsAllData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

}
