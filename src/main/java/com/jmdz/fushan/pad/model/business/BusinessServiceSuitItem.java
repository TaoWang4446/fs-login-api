package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 物品服务套餐信息
 *
 * @author LiCongLu
 * @date 2020-08-07 11:10
 */
@ApiModel(value = "物品服务套餐信息", description = "物品服务套餐信息")
public class BusinessServiceSuitItem extends BaseBean {

    /**
     * 套餐主键
     */
    @ApiModelProperty(value = "套餐主键", name = "suitId", position = 1)
    private String suitId;

    /**
     * 套餐名称
     */
    @ApiModelProperty(value = "套餐名称", name = "suitName", position = 2)
    private String suitName;

    /**
     * serviceItems
     */
    @ApiModelProperty(value = "套餐包含物品和服务", name = "serviceItems", position = 3)
    private ArrayList<BusinessServiceItem> serviceItems;

    public String getSuitId() {
        return suitId;
    }

    public BusinessServiceSuitItem setSuitId(String suitId) {
        this.suitId = suitId;
        return this;
    }

    public String getSuitName() {
        return suitName;
    }

    public BusinessServiceSuitItem setSuitName(String suitName) {
        this.suitName = suitName;
        return this;
    }

    public ArrayList<BusinessServiceItem> getServiceItems() {
        return serviceItems;
    }

    public BusinessServiceSuitItem setServiceItems(ArrayList<BusinessServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
        return this;
    }
}
