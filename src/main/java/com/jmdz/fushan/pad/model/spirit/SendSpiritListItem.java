package com.jmdz.fushan.pad.model.spirit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

/**
 * 送灵任务列表接口
 *
 * @author LiCongLu
 * @date 2020-07-22 16:36
 */
@ApiModel(value = "送灵任务列表接口", description = "送灵任务列表接口")
public class SendSpiritListItem extends BaseBean implements Comparable<SendSpiritListItem> {
    /**
     * 火化任务主键
     */
    @ApiModelProperty(value = "火化任务主键", name = "id", position = 1)
    private Integer id;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 3)
    private String deadName;

    /**
     * 火化时间
     */
    @ApiModelProperty(value = "火化时间", name = "cremationTime", position = 4)
    private String cremationTime;

    /**
     * 火化状态值
     */
    @ApiModelProperty(value = "火化状态值", name = "cremationState", position = 5)
    private Integer cremationState;

    /**
     * 火化状态文本
     */
    @ApiModelProperty(value = "火化状态文本", name = "cremationStateText", position = 6)
    private String cremationStateText;

    /**
     * 是否确认送灵任务,0未确认/1已确认
     */
    @ApiModelProperty(value = "是否确认送灵任务,0未确认/1已确认", name = "confirmSendSpirit", position = 7)
    private Integer confirmSendSpirit;

    /**
     * 是否取灰,0未取灰/1已取灰
     */
    @ApiModelProperty(value = "是否取灰,0未取灰/1已取灰", name = "isGetAsh", position = 8)
    private Integer asGetAsh;

    /**
     * 是否高亮显示
     */
    @ApiModelProperty(value = "是否高亮显示", name = "asHighlight", position = 9)
    private Integer asHighlight;

    /**
     * 排序时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "排序时间", name = "orderTime", position = 10)
    private String orderTime;

    public Integer getId() {
        return id;
    }

    public SendSpiritListItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public SendSpiritListItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public SendSpiritListItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getCremationTime() {
        return cremationTime;
    }

    public SendSpiritListItem setCremationTime(String cremationTime) {
        this.cremationTime = cremationTime;
        return this;
    }

    public Integer getCremationState() {
        return cremationState;
    }

    public SendSpiritListItem setCremationState(Integer cremationState) {
        this.cremationState = cremationState;
        return this;
    }

    public String getCremationStateText() {
        return cremationStateText;
    }

    public SendSpiritListItem setCremationStateText(String cremationStateText) {
        this.cremationStateText = cremationStateText;
        return this;
    }

    public Integer getConfirmSendSpirit() {
        return confirmSendSpirit;
    }

    public SendSpiritListItem setConfirmSendSpirit(Integer confirmSendSpirit) {
        this.confirmSendSpirit = confirmSendSpirit;
        return this;
    }

    public Integer getAsGetAsh() {
        return asGetAsh;
    }

    public SendSpiritListItem setAsGetAsh(Integer asGetAsh) {
        this.asGetAsh = asGetAsh;
        return this;
    }

    public Integer getAsHighlight() {
        return asHighlight;
    }

    public SendSpiritListItem setAsHighlight(Integer asHighlight) {
        this.asHighlight = asHighlight;
        return this;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public SendSpiritListItem setOrderTime(String orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    @Override
    public int compareTo(SendSpiritListItem o) {
        if (o == null) {
            return 1;
        }
        return this.operationNo.compareTo(o.operationNo);
    }
}
