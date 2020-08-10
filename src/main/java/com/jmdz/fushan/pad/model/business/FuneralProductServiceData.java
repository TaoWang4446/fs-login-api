package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 丧葬用品保存请求数据
 *
 * @author LiCongLu
 * @date 2020-08-04 09:34
 */
@ApiModel(value = "丧葬用品保存请求数据", description = "丧葬用品保存请求数据,即丧葬用品/骨灰盒/寿衣/纸棺/鲜花/服务项目等")
public class FuneralProductServiceData extends UserData {
    /**
     * 业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 3)
    private String operationNo;

    /**
     * 业务洽谈服务和物品类型
     */
    @AnValidate(name = "业务洽谈服务和物品类型", required = true)
    @ApiModelProperty(value = "业务洽谈服务和物品类型", name = "type", position = 4)
    private String type;

    /**
     * 服务和物品费用集合
     */
    @AnValidate(name = "服务和物品集合", required = true)
    @ApiModelProperty(value = "服务和物品集合", name = "serviceItems", position = 5)
    private ArrayList<BusinessServiceData> serviceItems;

    public String getOperationNo() {
        return operationNo;
    }

    public FuneralProductServiceData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getType() {
        return type;
    }

    public FuneralProductServiceData setType(String type) {
        this.type = type;
        return this;
    }

    public ArrayList<BusinessServiceData> getServiceItems() {
        return serviceItems;
    }

    public FuneralProductServiceData setServiceItems(ArrayList<BusinessServiceData> serviceItems) {
        this.serviceItems = serviceItems;
        return this;
    }
}
