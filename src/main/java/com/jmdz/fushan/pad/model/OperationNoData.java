package com.jmdz.fushan.pad.model;

import com.jmdz.common.annotation.AnValidate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author LiCongLu
 * @date 2020-07-09 13:06
 */
@ApiModel(value = "业务编号请求数据", description = "业务编号请求数据")
public class OperationNoData extends UserData {
    /**
     * 业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 1)
    private String operationNo;

    public String getOperationNo() {
        return operationNo;
    }

    public OperationNoData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }
}
