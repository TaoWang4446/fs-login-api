package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 返回给前端的详情所有信息
 *
 * @author Sunzhaoqi
 * @date 2020/7/13 9:18
 */
@ApiModel(value = "加载逝者详情响应信息", description = "加载逝者详情响应信息，包含逝者详情信息和费用明细以及服务详情")
public class DeadDetailsAllItem extends BaseBean {

    /**
     * 逝者详情信息
     */
    @ApiModelProperty(value = "逝者详情信息", name = "deadDetailsItem", position = 1)
    private DeadDetailsItem deadDetailsItem;

    /**
     * 逝者详情费用单价及名称
     */
    @ApiModelProperty(value = "逝者详情费用信息", name = "chargeItems", position = 2)
    private ArrayList<DeadDetailsChargeItem> chargeItems;

    /**
     * 逝者详情费用合计
     */
    @ApiModelProperty(value = "逝者详情费用合计", name = "chargeTotalItem", position = 3)
    private DeadChargeTotalItem chargeTotalItem;

    /**
     * 逝者服务信息
     */
    @ApiModelProperty(value = "逝者服务信息 ", name = "deadAllChargeItem", position = 4)
    private ArrayList<DeadDetailsServiceItem> serviceItems;

    public DeadDetailsItem getDeadDetailsItem() {
        return deadDetailsItem;
    }

    public DeadDetailsAllItem setDeadDetailsItem(DeadDetailsItem deadDetailsItem) {
        this.deadDetailsItem = deadDetailsItem;
        return this;
    }

    public ArrayList<DeadDetailsChargeItem> getChargeItems() {
        return chargeItems;
    }

    public DeadDetailsAllItem setChargeItems(ArrayList<DeadDetailsChargeItem> chargeItems) {
        this.chargeItems = chargeItems;
        return this;
    }

    public DeadChargeTotalItem getChargeTotalItem() {
        return chargeTotalItem;
    }

    public DeadDetailsAllItem setChargeTotalItem(DeadChargeTotalItem chargeTotalItem) {
        this.chargeTotalItem = chargeTotalItem;
        return this;
    }

    public ArrayList<DeadDetailsServiceItem> getServiceItems() {
        return serviceItems;
    }

    public DeadDetailsAllItem setServiceItems(ArrayList<DeadDetailsServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
        return this;
    }
}
