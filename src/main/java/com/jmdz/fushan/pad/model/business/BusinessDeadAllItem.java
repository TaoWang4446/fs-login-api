package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务洽谈逝者详情信息
 *
 * @author LiCongLu
 * @date 2020-07-28 13:33
 */
@ApiModel(value = "业务洽谈逝者详情信息", description = "业务洽谈逝者详情信息")
public class BusinessDeadAllItem extends BaseBean {

    /**
     * 逝者基本信息
     */
    @ApiModelProperty(value = "逝者基本信息", name = "deadBasicItem", position = 1)
    private DeadBasicItem deadBasicItem;

    /**
     * 亲属信息
     */
    @ApiModelProperty(value = "亲属信息", name = "relationItem", position = 2)
    private RelationItem relationItem;

    /**
     * 承办人信息
     */
    @ApiModelProperty(value = "承办人信息", name = "agentItem", position = 3)
    private AgentItem agentItem;

    public DeadBasicItem getDeadBasicItem() {
        return deadBasicItem;
    }

    public BusinessDeadAllItem setDeadBasicItem(DeadBasicItem deadBasicItem) {
        this.deadBasicItem = deadBasicItem;
        return this;
    }

    public RelationItem getRelationItem() {
        return relationItem;
    }

    public BusinessDeadAllItem setRelationItem(RelationItem relationItem) {
        this.relationItem = relationItem;
        return this;
    }

    public AgentItem getAgentItem() {
        return agentItem;
    }

    public BusinessDeadAllItem setAgentItem(AgentItem agentItem) {
        this.agentItem = agentItem;
        return this;
    }
}
