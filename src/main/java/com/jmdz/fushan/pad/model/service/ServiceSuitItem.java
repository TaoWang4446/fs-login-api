package com.jmdz.fushan.pad.model.service;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 服务套餐信息
 *
 * @author LiCongLu
 * @date 2020-06-09 16:45
 */
@ApiModel(value = "服务套餐信息", description = "服务套餐信息")
public class ServiceSuitItem extends BaseBean {
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
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "sort", position = 3)
    private Integer sort;

    /**
     * 套餐所包含的物品服务信息
     */
    @ApiModelProperty(value = "物品服务信息", name = "serviceItems", position = 4)
    private ArrayList<ServiceListItem> serviceItems;

    public String getSuitId() {
        return suitId;
    }

    public ServiceSuitItem setSuitId(String suitId) {
        this.suitId = suitId;
        return this;
    }

    public String getSuitName() {
        return suitName;
    }

    public ServiceSuitItem setSuitName(String suitName) {
        this.suitName = suitName;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ServiceSuitItem setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public ArrayList<ServiceListItem> getServiceItems() {
        return serviceItems;
    }

    public ServiceSuitItem setServiceItems(ArrayList<ServiceListItem> serviceItems) {
        this.serviceItems = serviceItems;
        return this;
    }
}
