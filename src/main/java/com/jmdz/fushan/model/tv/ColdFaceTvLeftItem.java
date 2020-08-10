package com.jmdz.fushan.model.tv;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 冷藏整容电视左侧信息
 *
 * @author LiCongLu
 * @date 2020-08-07 14:54
 */
@ApiModel(value = "冷藏整容电视左侧信息", description = "冷藏整容电视左侧信息")
public class ColdFaceTvLeftItem extends BaseBean {

    /**
     * 任务主键
     */
    @ApiModelProperty(value = "任务主键", name = "taskId", position = 1)
    private Integer taskId;

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
     * 冷藏设备号或守灵厅名称
     */
    @ApiModelProperty(value = "冷藏设备号或守灵厅名称", name = "noName", position = 4)
    private String noName;

    /**
     * 卫生棺名称
     */
    @ApiModelProperty(value = "卫生棺名称", name = "coffinName", position = 5)
    private String coffinName;

    public Integer getTaskId() {
        return taskId;
    }

    public ColdFaceTvLeftItem setTaskId(Integer taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public ColdFaceTvLeftItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public ColdFaceTvLeftItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getNoName() {
        return noName;
    }

    public ColdFaceTvLeftItem setNoName(String noName) {
        this.noName = noName;
        return this;
    }

    public String getCoffinName() {
        return coffinName;
    }

    public ColdFaceTvLeftItem setCoffinName(String coffinName) {
        this.coffinName = coffinName;
        return this;
    }
}
