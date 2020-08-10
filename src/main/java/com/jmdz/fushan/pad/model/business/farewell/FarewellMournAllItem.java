package com.jmdz.fushan.pad.model.business.farewell;

import com.jmdz.common.base.BaseBean;
import com.jmdz.fushan.pad.model.ChargeItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 业务洽谈告别任务综合信息
 *
 * @author LiCongLu
 * @date 2020-08-07 08:44
 */
@ApiModel(value = "业务洽谈告别任务综合信息", description = "业务洽谈告别任务综合信息")
public class FarewellMournAllItem extends BaseBean {

    /**
     * 告别任务信息
     */
    @ApiModelProperty(value = "告别任务信息", name = "farewellMournItem", position = 1)
    private FarewellMournItem farewellMournItem;

    /**
     * 服务和物品
     */
    @ApiModelProperty(value = "服务和物品", name = "chargeItems", position = 2)
    private ArrayList<ChargeItem> chargeItems;

    public FarewellMournItem getFarewellMournItem() {
        return farewellMournItem;
    }

    public FarewellMournAllItem setFarewellMournItem(FarewellMournItem farewellMournItem) {
        this.farewellMournItem = farewellMournItem;
        return this;
    }

    public ArrayList<ChargeItem> getChargeItems() {
        return chargeItems;
    }

    public FarewellMournAllItem setChargeItems(ArrayList<ChargeItem> chargeItems) {
        this.chargeItems = chargeItems;
        return this;
    }
}
