package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 费用明细
 *
 * @author Sunzhaqi
 * @date 2020/7/13 17:16
 */
@ApiModel(value = "加载逝者详情费用信息", description = "加载逝者详情费用信息，")
public class DeadDetailsChargeItem extends BaseBean {

    /**
     * 物品服务费用名称
     */
    @ApiModelProperty(value = "物品服务费用名称", name = "parentName", position = 1)
    private String itemName;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", name = "number", position = 2)
    private BigDecimal number;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额", name = "charge", position = 3)
    private BigDecimal charge;

    public String getItemName() {
        return itemName;
    }

    public DeadDetailsChargeItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public DeadDetailsChargeItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public DeadDetailsChargeItem setCharge(BigDecimal charge) {
        this.charge = charge;
        return this;
    }
}
