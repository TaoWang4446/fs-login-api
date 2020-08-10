package com.jmdz.fushan.pad.model.task;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * TaskNoticeVo
 *
 * @author LiCongLu
 * @date 2020-07-29 13:39
 */
@ApiModel(value = "任务提醒消息通知信息", description = "任务提醒消息通知信息")
public class TaskNoticeVo extends BaseBean {
    /**
     * 索引
     */
    @ApiModelProperty(value = "索引", name = "id", position = 1)
    private Integer id;

    /**
     * 模块
     */
    @ApiModelProperty(value = "模块", name = "source", position = 2)
    private String module;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", name = "title", position = 3)
    private String title;

    /**
     * 内容，获取列表时，通知为空
     */
    @ApiModelProperty(value = "内容", name = "content", position = 4)
    private String content;

    public Integer getId() {
        return id;
    }

    public TaskNoticeVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getModule() {
        return module;
    }

    public TaskNoticeVo setModule(String module) {
        this.module = module;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TaskNoticeVo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TaskNoticeVo setContent(String content) {
        this.content = content;
        return this;
    }
}
