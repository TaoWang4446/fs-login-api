package com.jmdz.fushan.pad.model.farewell;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 告别厅信息
 *
 * @author LiCongLu
 * @date 2020-06-08 10:34
 */
@ApiModel(value = "告别厅信息", description = "告别厅信息")
public class FarewellHallItem extends BaseBean {
    /**
     * 告别厅主键
     */
    @ApiModelProperty(value = "告别厅主键", name = "hallId", position = 1)
    private Integer hallId;

    /**
     * 告别厅编号
     */
    @ApiModelProperty(value = "告别厅编号", name = "hallNo", position = 2)
    private String hallNo;

    /**
     * 告别厅名称
     */
    @ApiModelProperty(value = "告别厅名称", name = "hallName", position = 3)
    private String hallName;

    /**
     * 告别厅类型主键
     */
    @ApiModelProperty(value = "告别厅类型主键", name = "hallItemId", position = 4)
    private Integer hallItemId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "sort", position = 5)
    private String sort;

    /**
     * 当前入厅告别任务主键
     */
    @ApiModelProperty(value = "当前入厅告别任务主键", name = "farewellId", position = 6)
    private Integer farewellId;

    public Integer getHallId() {
        return hallId;
    }

    public FarewellHallItem setHallId(Integer hallId) {
        this.hallId = hallId;
        return this;
    }

    public String getHallNo() {
        return hallNo;
    }

    public FarewellHallItem setHallNo(String hallNo) {
        this.hallNo = hallNo;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public FarewellHallItem setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public Integer getHallItemId() {
        return hallItemId;
    }

    public FarewellHallItem setHallItemId(Integer hallItemId) {
        this.hallItemId = hallItemId;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public FarewellHallItem setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public Integer getFarewellId() {
        return farewellId;
    }

    public FarewellHallItem setFarewellId(Integer farewellId) {
        this.farewellId = farewellId;
        return this;
    }
}
