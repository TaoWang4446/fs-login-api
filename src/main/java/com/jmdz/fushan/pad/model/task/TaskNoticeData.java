package com.jmdz.fushan.pad.model.task;

import com.jmdz.common.base.BaseBean;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * TaskNoticeData
 *
 * @author LiCongLu
 * @date 2020-07-29 13:43
 */
@ApiModel(value = "任务提醒消息请求数据", description = "任务提醒消息请求数据")
public class TaskNoticeData extends UserData {

    /**
     * 客户端类型
     */
    @ApiModelProperty(value = "客户端类型", name = "clientType", position = 1)
    private String clientType;

    public String getClientType() {
        return clientType;
    }

    public TaskNoticeData setClientType(String clientType) {
        this.clientType = clientType;
        return this;
    }
}
