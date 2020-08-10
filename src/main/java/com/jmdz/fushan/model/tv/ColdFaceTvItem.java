package com.jmdz.fushan.model.tv;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 冷藏整容电视信息
 *
 * @author LiCongLu
 * @date 2020-08-07 14:54
 */
@ApiModel(value = "冷藏整容电视信息", description = "冷藏整容电视信息")
public class ColdFaceTvItem extends BaseBean {

    /**
     * 电视左侧表格信息
     */
    @ApiModelProperty(value = "电视左侧表格信息", name = "leftItems", position = 1)
    private ArrayList<ColdFaceTvLeftItem> leftItems;

    /**
     * 电视右侧表格信息
     */
    @ApiModelProperty(value = "电视右侧表格信息", name = "rightItems", position = 2)
    private ArrayList<ColdFaceTvRightItem> rightItems;

    public ArrayList<ColdFaceTvLeftItem> getLeftItems() {
        return leftItems;
    }

    public ColdFaceTvItem setLeftItems(ArrayList<ColdFaceTvLeftItem> leftItems) {
        this.leftItems = leftItems;
        return this;
    }

    public ArrayList<ColdFaceTvRightItem> getRightItems() {
        return rightItems;
    }

    public ColdFaceTvItem setRightItems(ArrayList<ColdFaceTvRightItem> rightItems) {
        this.rightItems = rightItems;
        return this;
    }
}
