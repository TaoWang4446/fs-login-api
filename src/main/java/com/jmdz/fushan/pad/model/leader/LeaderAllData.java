package com.jmdz.fushan.pad.model.leader;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 领导加载所有信息请求数据
 *
 * @author LiCongLu
 * @date 2020-07-13 09:07
 */
@ApiModel(value = "领导加载所有信息请求数据", description = "领导加载所有信息请求数据")
public class LeaderAllData extends UserData {
    /**
     * 查询日期
     */
    @AnValidate(value = "查询日期", required = true)
    @ApiModelProperty(value = "查询日期", name = "queryDate", example = "2020-03-17", position = 3)
    @JsonFormat(pattern = yyyy_MM_dd)
    private Date queryDate;

    /**
     * 查询日期类型
     */
    @AnValidate(value = "查询类型", required = true)
    @ApiModelProperty(value = "查询类型", name = "queryType", notes = "0,按天查；1,按月查；默认按照天", example = "0", position = 3)
    private Integer queryType;

    public Date getQueryDate() {
        return queryDate;
    }

    public LeaderAllData setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
        return this;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public LeaderAllData setQueryType(Integer queryType) {
        this.queryType = queryType;
        return this;
    }
}

