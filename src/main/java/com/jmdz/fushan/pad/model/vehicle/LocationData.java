package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 定位坐标请求数据
 *
 * @author LiCongLu
 * @date 2020-07-09 15:57
 */
@ApiModel(value = "定位坐标请求数据", description = "定位坐标请求数据")
public class LocationData extends UserData {
    /**
     * 经度(119.268498)
     */
    @AnValidate(name = "百度定位经度",required = true)
    @ApiModelProperty(value = "百度定位经度",name = "longitude", position = 1)
    private String longitude;

    /**
     * 纬度(35.523556)
     */
    @AnValidate(name = "百度定位维度",required = true)
    @ApiModelProperty(value = "百度定位维度",name = "latitude", position = 2)
    private String latitude;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息",name = "remark", position = 3)
    private String remark;

    public String getLongitude() {
        return longitude;
    }

    public LocationData setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public LocationData setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public LocationData setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
