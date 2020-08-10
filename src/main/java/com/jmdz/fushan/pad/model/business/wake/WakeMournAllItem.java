package com.jmdz.fushan.pad.model.business.wake;

import com.jmdz.common.base.BaseBean;
import com.jmdz.fushan.pad.model.ChargeItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 业务洽谈守灵任务综合信息
 *
 * @author LiCongLu
 * @date 2020-08-06 10:33
 */
@ApiModel(value = "业务洽谈守灵任务综合信息", description = "业务洽谈守灵任务综合信息")
public class WakeMournAllItem extends BaseBean {

    /**
     * 守灵任务信息
     */
    @ApiModelProperty(value = "守灵任务信息", name = "wakeMournItem", position = 1)
    private WakeMournItem wakeMournItem;

    /**
     * 服务和物品
     */
    @ApiModelProperty(value = "服务和物品", name = "chargeItems", position = 2)
    private ArrayList<ChargeItem> chargeItems;

    public WakeMournItem getWakeMournItem() {
        return wakeMournItem;
    }

    public WakeMournAllItem setWakeMournItem(WakeMournItem wakeMournItem) {
        this.wakeMournItem = wakeMournItem;
        return this;
    }

    public ArrayList<ChargeItem> getChargeItems() {
        return chargeItems;
    }

    public WakeMournAllItem setChargeItems(ArrayList<ChargeItem> chargeItems) {
        this.chargeItems = chargeItems;
        return this;
    }
}
