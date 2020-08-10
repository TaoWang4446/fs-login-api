package com.jmdz.fushan.pad.model.leader;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 领导加载所有信息响应实体
 *
 * @author LiCongLu
 * @date 2020-07-13 09:08
 */
@ApiModel(value = "领导加载所有信息响应实体", description = "领导加载所有信息响应实体")
public class LeaderAllInfoItem extends BaseBean {

    /**
     * 物品服务分类费用统计列表
     */
    @ApiModelProperty(value = "物品服务分类费用统计列表", name = "chargeItems", position = 1)
    private ArrayList<LeaderAllLabelItem> chargeItems;

    /**
     * 物品服务分类费用合计应收
     */
    @ApiModelProperty(value = "物品服务分类费用合计应收", name = "chargeTotalItem", position = 2)
    private LeaderAllLabelItem chargeTotalItem;

    /**
     * 物品服务分类费用合计惠民
     */
    @ApiModelProperty(value = "物品服务分类费用合计惠民", name = "chargeBenefitItem", position = 3)
    private LeaderAllLabelItem chargeBenefitItem;

    /**
     * 物品服务分类费用合计实收
     */
    @ApiModelProperty(value = "物品服务分类费用合计实收", name = "chargeRealItem", position = 4)
    private LeaderAllLabelItem chargeRealItem;

    /**
     * 火化量统计列表
     */
    @ApiModelProperty(value = "火化量统计列表", name = "crematingItems", position = 5)
    private ArrayList<LeaderAllLabelItem> crematingItems;

    /**
     * 火化量统计合计
     */
    @ApiModelProperty(value = "火化量统计合计", name = "crematingTotalItem", position = 6)
    private LeaderAllLabelItem crematingTotalItem;

    /**
     * 守灵场次统计列表
     */
    @ApiModelProperty(value = "守灵场次统计列表", name = "wakeItems", position = 7)
    private ArrayList<LeaderAllLabelItem> wakeItems;

    /**
     * 守灵场次统计合计
     */
    @ApiModelProperty(value = "守灵场次统计合计", name = "wakeTotalItem", position = 8)
    private LeaderAllLabelItem wakeTotalItem;

    /**
     * 告别场次统计列表
     */
    @ApiModelProperty(value = "告别场次统计列表", name = "farewellItems", position = 9)
    private ArrayList<LeaderAllLabelItem> farewellItems;

    /**
     * 告别场次统计合计
     */
    @ApiModelProperty(value = "告别场次统计合计", name = "farewellTotalItem", position = 10)
    private LeaderAllLabelItem farewellTotalItem;

    /**
     * 接运数量统计列表
     */
    @ApiModelProperty(value = "接运数量统计列表", name = "vehicleItems", position = 11)
    private ArrayList<LeaderAllLabelItem> vehicleItems;

    /**
     * 接运数量统计合计
     */
    @ApiModelProperty(value = "接运数量统计合计", name = "vehicleTotalItem", position = 12)
    private LeaderAllLabelItem vehicleTotalItem;

    /**
     * 入藏量
     */
    @ApiModelProperty(value = "入藏量", name = "coldStorageInItem", position = 13)
    private LeaderAllLabelItem coldStorageInItem;

    /**
     * 出藏量
     */
    @ApiModelProperty(value = "出藏量", name = "coldStorageOutItem", position = 14)
    private LeaderAllLabelItem coldStorageOutItem;

    /**
     * 当前在柜
     */
    @ApiModelProperty(value = "当前在柜", name = "coldStorageCurrentItem", position = 15)
    private LeaderAllLabelItem coldStorageCurrentItem;

    /**
     * 空柜
     */
    @ApiModelProperty(value = "空柜", name = "coldStorageUsableItem", position = 16)
    private LeaderAllLabelItem coldStorageUsableItem;

    public ArrayList<LeaderAllLabelItem> getChargeItems() {
        return chargeItems;
    }

    public LeaderAllInfoItem setChargeItems(ArrayList<LeaderAllLabelItem> chargeItems) {
        this.chargeItems = chargeItems;
        return this;
    }

    public LeaderAllLabelItem getChargeTotalItem() {
        return chargeTotalItem;
    }

    public LeaderAllInfoItem setChargeTotalItem(LeaderAllLabelItem chargeTotalItem) {
        this.chargeTotalItem = chargeTotalItem;
        return this;
    }

    public LeaderAllLabelItem getChargeBenefitItem() {
        return chargeBenefitItem;
    }

    public LeaderAllInfoItem setChargeBenefitItem(LeaderAllLabelItem chargeBenefitItem) {
        this.chargeBenefitItem = chargeBenefitItem;
        return this;
    }

    public LeaderAllLabelItem getChargeRealItem() {
        return chargeRealItem;
    }

    public LeaderAllInfoItem setChargeRealItem(LeaderAllLabelItem chargeRealItem) {
        this.chargeRealItem = chargeRealItem;
        return this;
    }

    public ArrayList<LeaderAllLabelItem> getCrematingItems() {
        return crematingItems;
    }

    public LeaderAllInfoItem setCrematingItems(ArrayList<LeaderAllLabelItem> crematingItems) {
        this.crematingItems = crematingItems;
        return this;
    }

    public LeaderAllLabelItem getCrematingTotalItem() {
        return crematingTotalItem;
    }

    public LeaderAllInfoItem setCrematingTotalItem(LeaderAllLabelItem crematingTotalItem) {
        this.crematingTotalItem = crematingTotalItem;
        return this;
    }

    public ArrayList<LeaderAllLabelItem> getWakeItems() {
        return wakeItems;
    }

    public LeaderAllInfoItem setWakeItems(ArrayList<LeaderAllLabelItem> wakeItems) {
        this.wakeItems = wakeItems;
        return this;
    }

    public LeaderAllLabelItem getWakeTotalItem() {
        return wakeTotalItem;
    }

    public LeaderAllInfoItem setWakeTotalItem(LeaderAllLabelItem wakeTotalItem) {
        this.wakeTotalItem = wakeTotalItem;
        return this;
    }

    public ArrayList<LeaderAllLabelItem> getFarewellItems() {
        return farewellItems;
    }

    public LeaderAllInfoItem setFarewellItems(ArrayList<LeaderAllLabelItem> farewellItems) {
        this.farewellItems = farewellItems;
        return this;
    }

    public LeaderAllLabelItem getFarewellTotalItem() {
        return farewellTotalItem;
    }

    public LeaderAllInfoItem setFarewellTotalItem(LeaderAllLabelItem farewellTotalItem) {
        this.farewellTotalItem = farewellTotalItem;
        return this;
    }

    public ArrayList<LeaderAllLabelItem> getVehicleItems() {
        return vehicleItems;
    }

    public LeaderAllInfoItem setVehicleItems(ArrayList<LeaderAllLabelItem> vehicleItems) {
        this.vehicleItems = vehicleItems;
        return this;
    }

    public LeaderAllLabelItem getVehicleTotalItem() {
        return vehicleTotalItem;
    }

    public LeaderAllInfoItem setVehicleTotalItem(LeaderAllLabelItem vehicleTotalItem) {
        this.vehicleTotalItem = vehicleTotalItem;
        return this;
    }

    public LeaderAllLabelItem getColdStorageInItem() {
        return coldStorageInItem;
    }

    public LeaderAllInfoItem setColdStorageInItem(LeaderAllLabelItem coldStorageInItem) {
        this.coldStorageInItem = coldStorageInItem;
        return this;
    }

    public LeaderAllLabelItem getColdStorageOutItem() {
        return coldStorageOutItem;
    }

    public LeaderAllInfoItem setColdStorageOutItem(LeaderAllLabelItem coldStorageOutItem) {
        this.coldStorageOutItem = coldStorageOutItem;
        return this;
    }

    public LeaderAllLabelItem getColdStorageCurrentItem() {
        return coldStorageCurrentItem;
    }

    public LeaderAllInfoItem setColdStorageCurrentItem(LeaderAllLabelItem coldStorageCurrentItem) {
        this.coldStorageCurrentItem = coldStorageCurrentItem;
        return this;
    }

    public LeaderAllLabelItem getColdStorageUsableItem() {
        return coldStorageUsableItem;
    }

    public LeaderAllInfoItem setColdStorageUsableItem(LeaderAllLabelItem coldStorageUsableItem) {
        this.coldStorageUsableItem = coldStorageUsableItem;
        return this;
    }
}
