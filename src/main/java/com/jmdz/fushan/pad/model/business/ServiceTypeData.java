package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务洽谈服务和物品类型请求数据
 *
 * @author LiCongLu
 * @date 2020-08-03 11:45
 */
@ApiModel(value = "业务洽谈服务和物品类型请求数据", description = "业务洽谈服务和物品类型请求数据")
public class ServiceTypeData extends OperationNoData {

    /**
     * 业务洽谈服务和物品类型
     */
    @AnValidate(name = "业务洽谈服务和物品类型", required = true)
    @ApiModelProperty(value = "业务洽谈服务和物品类型", name = "type", position = 4)
    private String type;

    public String getType() {
        return type;
    }

    public ServiceTypeData setType(String type) {
        this.type = type;
        return this;
    }
}
