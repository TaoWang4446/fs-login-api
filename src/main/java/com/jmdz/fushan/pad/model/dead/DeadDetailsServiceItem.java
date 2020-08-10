package com.jmdz.fushan.pad.model.dead;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 逝者业务服务信息
 *
 * @author Sunzhaoqi
 * @date 2020/7/16 13:37
 */
@ApiModel(value = "逝者业务服务信息", description = "逝者业务服务信息")
public class DeadDetailsServiceItem extends BaseBean {

    /**
     * 业务服务名称
     */
    @ApiModelProperty(value = "业务服务名称", name = "name", position = 1)
    private String name;

    /**
     * 业务服务内容
     */
    @ApiModelProperty(value = "业务服务内容", name = "content", position = 2)
    private String content;

    public DeadDetailsServiceItem() {
    }

    public DeadDetailsServiceItem(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public DeadDetailsServiceItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public DeadDetailsServiceItem setContent(String content) {
        this.content = content;
        return this;
    }
}
