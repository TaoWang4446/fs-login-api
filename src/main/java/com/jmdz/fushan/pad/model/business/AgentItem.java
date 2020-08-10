package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 承办人信息
 *
 * @author LiCongLu
 * @date 2020-07-17 09:41
 */
@ApiModel(value = "承办人信息", description = "承办人信息")
public class AgentItem extends BaseBean {
    /**
     * 承办人主键
     */
    @ApiModelProperty(value = "承办人主键", name = "agentId", position = 1)
    private int agentId;

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 2)
    private String operationNo;

    /**
     * 承办人姓名
     */
    @ApiModelProperty(value = "承办人姓名", name = "agentName", position = 3)
    private String agentName;

    /**
     * 承办人性别
     */
    @ApiModelProperty(value = "承办人性别", name = "agentSex", position = 4)
    private String agentSex;

    /**
     * 承办人联系电话
     */
    @ApiModelProperty(value = "承办人联系电话", name = "agentPhone", position = 5)
    private String agentPhone;

    /**
     * 承办人身份证号
     */
    @ApiModelProperty(value = "承办人身份证号", name = "agentCertificateNo", position = 6)
    private String agentCertificateNo;

    /**
     * 身份证识别图片
     */
    @ApiModelProperty(value = "身份证识别图片", name = "imagePath", position = 7)
    private String imagePath;

    public int getAgentId() {
        return agentId;
    }

    public AgentItem setAgentId(int agentId) {
        this.agentId = agentId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public AgentItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getAgentName() {
        return agentName;
    }

    public AgentItem setAgentName(String agentName) {
        this.agentName = agentName;
        return this;
    }

    public String getAgentSex() {
        return agentSex;
    }

    public AgentItem setAgentSex(String agentSex) {
        this.agentSex = agentSex;
        return this;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public AgentItem setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
        return this;
    }

    public String getAgentCertificateNo() {
        return agentCertificateNo;
    }

    public AgentItem setAgentCertificateNo(String agentCertificateNo) {
        this.agentCertificateNo = agentCertificateNo;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public AgentItem setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }
}