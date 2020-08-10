package com.jmdz.fushan.pad.model.dead;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 逝者任务请求数据
 *
 * @author LiCongLu
 * @date 2020-07-10 14:03
 */
@ApiModel(value = "逝者任务请求数据", description = "逝者任务请求数据")
public class DeadAllData extends UserData {

    /**
     * 查询日期
     */
    @AnValidate(value = "查询日期", required = true)
    @ApiModelProperty(value = "查询日期", name = "queryDate",example = "2020-03-17", position = 3)
    @JsonFormat(pattern = yyyy_MM_dd)
    private Date queryDate;

    public Date getQueryDate() {
        return queryDate;
    }

    public DeadAllData setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
        return this;
    }
}
