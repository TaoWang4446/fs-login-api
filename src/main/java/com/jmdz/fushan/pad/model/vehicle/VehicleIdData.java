package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 接运任务主键请求数据
 *
 * @author LiCongLu
 * @date 2020-07-10 08:43
 */
@ApiModel(value = "接运任务主键请求数据", description = "接运任务主键请求数据")
public class VehicleIdData extends UserData {

    /**
     * 接运管理表主键
     */
    @AnValidate(name = "接运管理表主键", required = true)
    @ApiModelProperty(value = "接运管理表主键", name = "vehicleId", position = 1)
    private String vehicleId;

    /**
     * 业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleIdData setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public VehicleIdData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }
}
