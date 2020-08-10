package com.jmdz.fushan.pad.model.service;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author LiCongLu
 * @date 2020-08-10 10:10
 */
@ApiModel(value = "物品服务套餐信息添加请求数据", description = "物品服务套餐信息添加请求数据")
public class ServiceSuitData extends UserData {

    /**
     * 业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 4)
    private String operationNo;

    /**
     * 套餐主键
     */
    @AnValidate(name = "套餐主键", required = true)
    @ApiModelProperty(value = "套餐主键", name = "suitId", position = 5)
    private String suitId;

    public String getOperationNo() {
        return operationNo;
    }

    public ServiceSuitData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getSuitId() {
        return suitId;
    }

    public ServiceSuitData setSuitId(String suitId) {
        this.suitId = suitId;
        return this;
    }
}
