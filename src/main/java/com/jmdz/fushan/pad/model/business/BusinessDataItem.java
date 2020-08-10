package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.ext.ArrayListExt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 *业务基础数据信息
 *
 * @author LiCongLu
 * @date 2020-07-31 10:15
 */
@ApiModel(value = "业务基础数据信息", description = "业务基础数据信息")
public class BusinessDataItem extends BaseBean {

    /**
     * 字典数据
     */
    @ApiModelProperty(value = "字典数据", name = "dictItems",notes = "包含数据字典(性别(文本)，证件类型(主键)，遗体类型(主键)，遗体状态(文本)，死亡原因(文本)，民族(主键)，国籍(主键)，减免类型(主键)，骨灰处理方式(文本)，与逝者关系(文本)等", position = 1)
    private ArrayList<PickerItem> dictItems;

    /**
     * 省市机构
     */
    @ApiModelProperty(value = "省", name = "provinceItems", position = 2)
    private ArrayList<PickerItem>  provinceItems;

    /**
     * 冷藏格位类型
     */
    @ApiModelProperty(value = "冷藏格位类型", name = "equipmentTypes", position = 3)
    private ArrayList<PickerItem>  equipmentTypes;

    /**
     * 告别词模板
     */
    @ApiModelProperty(value = "告别词模板", name = "eulogyItems", position = 4)
    private ArrayList<PickerItem> eulogyItems;

    /**
     * 火化炉型
     */
    @ApiModelProperty(value = "火化炉型", name = "machineTypes", position = 5)
    private ArrayList<PickerItem>  machineTypes;

    public ArrayList<PickerItem> getDictItems() {
        return dictItems;
    }

    public BusinessDataItem setDictItems(ArrayList<PickerItem> dictItems) {
        this.dictItems = dictItems;
        return this;
    }

    public ArrayList<PickerItem> getProvinceItems() {
        return provinceItems;
    }

    public BusinessDataItem setProvinceItems(ArrayList<PickerItem> provinceItems) {
        this.provinceItems = provinceItems;
        return this;
    }

    public ArrayList<PickerItem> getEquipmentTypes() {
        return equipmentTypes;
    }

    public BusinessDataItem setEquipmentTypes(ArrayList<PickerItem> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
        return this;
    }

    public ArrayList<PickerItem> getEulogyItems() {
        return eulogyItems;
    }

    public BusinessDataItem setEulogyItems(ArrayList<PickerItem> eulogyItems) {
        this.eulogyItems = eulogyItems;
        return this;
    }

    public ArrayList<PickerItem> getMachineTypes() {
        return machineTypes;
    }

    public BusinessDataItem setMachineTypes(ArrayList<PickerItem> machineTypes) {
        this.machineTypes = machineTypes;
        return this;
    }
}
