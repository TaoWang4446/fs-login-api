package com.jmdz.fushan.model.tv;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 冷藏整容电视右侧信息
 *
 * @author LiCongLu
 * @date 2020-08-07 14:54
 */
@ApiModel(value = "冷藏整容电视右侧信息", description = "冷藏整容电视右侧信息")
public class ColdFaceTvRightItem extends BaseBean {

    /**
     * 业务编号
     */
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 1)
    private String operationNo;

    /**
     * 逝者姓名
     */
    @ApiModelProperty(value = "逝者姓名", name = "deadName", position = 2)
    private String deadName;

    /**
     * 整容项目名称
     */
    @ApiModelProperty(value = "整容项目名称", name = "itemName", position = 3)
    private String itemName;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间", name = "excuteTime", position = 4)
    private String excuteTime;

    public String getOperationNo() {
        return operationNo;
    }

    public ColdFaceTvRightItem setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getDeadName() {
        return deadName;
    }

    public ColdFaceTvRightItem setDeadName(String deadName) {
        this.deadName = deadName;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public ColdFaceTvRightItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public String getExcuteTime() {
        return excuteTime;
    }

    public ColdFaceTvRightItem setExcuteTime(String excuteTime) {
        this.excuteTime = excuteTime;
        return this;
    }
}
