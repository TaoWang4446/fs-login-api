package com.jmdz.fushan.pad.model.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 字典数据PickerItem
 *
 * @author LiCongLu
 * @date 2020-07-31 09:22
 */
@ApiModel(value = "字典选择框公用信息", description = "字典选择框公用信息")
public class PickerItem extends BaseBean {

    /**
     * 行数
     */
    @ApiModelProperty(value = "行数", name = "row", position = 1)
    private Integer row;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型", name = "type", position = 2)
    private String type;

    /**
     * 项目值
     */
    @ApiModelProperty(value = "项目值", name = "value", position = 3)
    private String value;

    /**
     * 显示文本
     */
    @ApiModelProperty(value = "显示文本", name = "text", position = 4)
    private String text;

    /**
     * 附属拓展值
     */
    @ApiModelProperty(value = "附属扩展值", name = "tag", position = 5)
    private String tag;

    /**
     * 拓展int值
     */
    @ApiModelProperty(value = "附属扩展Int值", name = "tagInt", position = 6)
    private Integer tagInt;

    /**
     * 特别价格
     */
    @ApiModelProperty(value = "特别价格", name = "price", position = 7)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    public Integer getRow() {
        return row;
    }

    public PickerItem setRow(Integer row) {
        this.row = row;
        return this;
    }

    public String getType() {
        return type;
    }

    public PickerItem setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PickerItem setValue(String value) {
        this.value = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public PickerItem setText(String text) {
        this.text = text;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public PickerItem setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Integer getTagInt() {
        return tagInt;
    }

    public PickerItem setTagInt(Integer tagInt) {
        this.tagInt = tagInt;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PickerItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
