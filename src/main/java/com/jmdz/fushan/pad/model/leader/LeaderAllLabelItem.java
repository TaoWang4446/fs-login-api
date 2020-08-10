package com.jmdz.fushan.pad.model.leader;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 领导加载所有信息里列表信息
 *
 * @author LiCongLu
 * @date 2020-07-13 09:13
 */
@ApiModel(value = "领导加载所有信息里列表信息", description = "领导加载所有信息里列表信息")
public class LeaderAllLabelItem extends BaseBean {

    /**
     * 总览分类主键
     */
    @ApiModelProperty(value = "主键", name = "id", position = 1)
    private Integer id;

    /**
     * 总览分类标记
     */
    @ApiModelProperty(value = "总览分类标记", name = "type", position = 1)
    private String type;

    /**
     * 总览分类名称
     */
    @ApiModelProperty(value = "名称", name = "name", position = 2)
    private String name;

    /**
     * 总览数量
     */
    @ApiModelProperty(value = "总览数量", name = "number", position = 3)
    private BigDecimal number;

    public LeaderAllLabelItem() {
    }

    public LeaderAllLabelItem(Integer id, String type, String name, BigDecimal number) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public LeaderAllLabelItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public LeaderAllLabelItem setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public LeaderAllLabelItem setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public LeaderAllLabelItem setNumber(BigDecimal number) {
        this.number = number;
        return this;
    }
}
