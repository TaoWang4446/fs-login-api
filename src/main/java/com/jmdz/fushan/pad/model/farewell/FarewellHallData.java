package com.jmdz.fushan.pad.model.farewell;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 告别厅任务列表请求数据
 *
 * @author LiCongLu
 * @date 2020-07-13 15:33
 */
@ApiModel(value = "告别厅任务列表请求数据", description = "告别厅任务列表请求数据")
public class FarewellHallData extends UserData {
    /**
     * 告别厅主键
     */
    @AnValidate(name = "告别厅主键", required = true)
    @ApiModelProperty(value = "告别厅主键", name = "hallId", position = 1)
    private Integer hallId;

    public Integer getHallId() {
        return hallId;
    }

    public FarewellHallData setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }
}
