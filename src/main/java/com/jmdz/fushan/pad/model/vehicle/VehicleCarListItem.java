package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 接运车辆信息
 *
 * @author LiCongLu
 * @date 2020-07-10 08:53
 */
@ApiModel(value = "接运车辆信息", description = "接运车辆信息")
public class VehicleCarListItem extends BaseBean {
    /**
     * 主键
     */
    @ApiModelProperty(value = "车辆主键", name = "id", position = 1)
    private Integer id;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", name = "vehicleNo", position = 2)
    private String vehicleNo;

    /**
     * 车型主键
     */
    @ApiModelProperty(value = "车型主键", name = "vehicleTypeId", position = 3)
    private int vehicleTypeId;

    /**
     * 车型
     */
    @ApiModelProperty(value = "车型", name = "vehicleType", position = 4)
    private String vehicleType;

    public Integer getId() {
        return id;
    }

    public VehicleCarListItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public VehicleCarListItem setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
        return this;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public VehicleCarListItem setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public VehicleCarListItem setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }
}
