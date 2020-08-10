package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.annotation.AnValidate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 出车请求数据
 *
 * @author LiCongLu
 * @date 2020-07-10 08:43
 */
@ApiModel(value = "出车请求数据", description = "出车请求数据")
public class VehicleOutData extends VehicleIdData {

    /**
     * 车辆主键
     */
    @AnValidate(name = "车辆主键", required = true)
    @ApiModelProperty(value = "车辆主键", name = "vehicleCarId", position = 1)
    private Integer vehicleCarId;

    public Integer getVehicleCarId() {
        return vehicleCarId;
    }

    public VehicleOutData setVehicleCarId(Integer vehicleCarId) {
        this.vehicleCarId = vehicleCarId;
        return this;
    }
}
