package com.jmdz.fushan.pad.model.farewell;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 告别任务详情信息
 *
 * @author LiCongLu
 * @date 2020-07-14 08:41
 */
@ApiModel(value = "告别任务详情信息", description = "告别任务详情信息")
public class FarewellTaskItem extends FarewellTaskListItem {

    /**
     * 告别厅主键
     */
    @ApiModelProperty(value = "告别厅主键", name = "hallId", position = 9)
    private Integer hallId;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "hallName", position = 10)
    private String hallName;

    /**
     * 业务随机码
     */
    @ApiModelProperty(value = "业务随机码", name = "randomId", position = 13)
    private String randomId;

    /**
     * 当前执行告别任务主键，包含准备工作
     */
    @ApiModelProperty(value = "执行任务主键", name = "executeId", position = 14)
    private Integer executeId;

    public Integer getHallId() {
        return hallId;
    }

    public FarewellTaskItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public FarewellTaskItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getRandomId() {
        return randomId;
    }

    public FarewellTaskItem setRandomId(String randomId) {
        this.randomId = randomId;
        return this;
    }

    public Integer getExecuteId() {
        return executeId;
    }

    public FarewellTaskItem setExecuteId(Integer executeId) {
        this.executeId = executeId;
        return this;
    }
}
