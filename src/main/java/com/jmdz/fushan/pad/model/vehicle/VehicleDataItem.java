package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.base.BaseBean;
import com.jmdz.fushan.pad.model.business.PickerItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 车辆接运基础数据信息
 *
 * @author LiCongLu
 * @date 2020-08-10 12:05
 */
@ApiModel(value = "车辆接运基础数据信息", description = "车辆接运基础数据信息")
public class VehicleDataItem extends BaseBean {

    /**
     * 字典数据
     */
    @ApiModelProperty(value = "字典数据", name = "dictItems", notes = "包含数据字典(性别(文本)，死亡原因(文本)，与逝者关系(文本)，车辆用途(主键)等", position = 1)
    private ArrayList<PickerItem> dictItems;

    /**
     * 预约车型
     */
    @ApiModelProperty(value = "预约车型", name = "vehicleTypes", position = 2)
    private ArrayList<PickerItem> vehicleTypes;

    public ArrayList<PickerItem> getDictItems() {
        return dictItems;
    }

    public VehicleDataItem setDictItems(ArrayList<PickerItem> dictItems) {
        this.dictItems = dictItems;
        return this;
    }

    public ArrayList<PickerItem> getVehicleTypes() {
        return vehicleTypes;
    }

    public VehicleDataItem setVehicleTypes(ArrayList<PickerItem> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
        return this;
    }
}
