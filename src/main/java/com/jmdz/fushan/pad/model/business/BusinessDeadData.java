package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务洽谈逝者列表请求数据
 *
 * @author LiCongLu
 * @date 2020-07-28 13:23
 */
@ApiModel(value = "业务洽谈逝者列表请求数据", description = "业务洽谈逝者列表请求数据")
public class BusinessDeadData extends UserData {

    /**
     * 关键词
     */
    @AnValidate(name = "关键字", required = true)
    @ApiModelProperty(value = "关键字", name = "keyword", position = 6)
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public BusinessDeadData setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
