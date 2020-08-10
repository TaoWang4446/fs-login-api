package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.ChargeItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 车辆接运费用请求数据
 *
 * @author LiCongLu
 * @date 2020-07-10 11:15
 */
@ApiModel(value = "车辆接运费用请求数据", description = "车辆接运费用请求数据")
public class VehicleChargeData extends VehicleIdData {

    /**
     * 物品服务费用
     */
    @AnValidate(name = "物品服务费用", required = true)
    @ApiModelProperty(value = "物品服务费用", name = "chargeItems", position = 10)
    private ArrayList<ChargeItem> chargeItems;

    public ArrayList<ChargeItem> getChargeItems() {
        return chargeItems;
    }

    public VehicleChargeData setChargeItems(ArrayList<ChargeItem> chargeItems) {
        this.chargeItems = chargeItems;
        return this;
    }
}
