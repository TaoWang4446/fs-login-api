package com.jmdz.fushan.pad.model;

import com.jmdz.common.annotation.AnValidate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务主键请求数据
 *
 * @author LiCongLu
 * @date 2020-07-16 15:35
 */
@ApiModel(value = "业务主键请求数据", description = "业务主键请求数据")
public class IdData extends UserData {
    /**
     * 业务主键
     */
    @AnValidate(name = "业务主键", required = true)
    @ApiModelProperty(value = "业务主键", name = "id", position = 2)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public IdData setId(Integer id) {
        this.id = id;
        return this;
    }
}
