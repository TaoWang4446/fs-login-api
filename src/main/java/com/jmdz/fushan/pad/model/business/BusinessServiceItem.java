package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 业务洽谈服务物品服务
 *
 * @author LiCongLu
 * @date 2020-08-03 11:32
 */
@ApiModel(value = "业务洽谈物品及服务信息", description = "业务洽谈物品及服务信息")
public class BusinessServiceItem extends BaseBean {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", position = 1)
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name", position = 2)
    private String name;

    /**
     * 显示名称
     */
    @ApiModelProperty(value = "显示名称", name = "displayName", position = 3)
    private String displayName;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", name = "price", position = 4)
    private BigDecimal price;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位", name = "unit", position = 5)
    private String unit;

    /**
     * 套餐主键
     */
    @ApiModelProperty(value = "套餐主键", name = "suitId", position = 6)
    private String suitId;

    public Integer getId() {
        return id;
    }

    public BusinessServiceItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BusinessServiceItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BusinessServiceItem setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BusinessServiceItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public BusinessServiceItem setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getSuitId() {
        return suitId;
    }

    public BusinessServiceItem setSuitId(String suitId) {
        this.suitId = suitId;
        return this;
    }
}
