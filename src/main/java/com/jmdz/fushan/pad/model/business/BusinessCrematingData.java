package com.jmdz.fushan.pad.model.business;
import com.jmdz.common.base.BaseBean;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *@ClassName BusinessCrematingData
 *@Description TODO
 *@Author Wangshengtao
 *@Date 2020-08-06 16:45
 */
@ApiModel(value = "业务洽谈火化任务详情&费用信息", description = "业务洽谈火化任务详情&费用信息")
public class BusinessCrematingData extends UserData {

    /**
     * 火化任务详情信息
     */
    @ApiModelProperty(value = "火化任务详情信息", name = "businessCrematingInfoData", position = 1)
    private BusinessCrematingInfoData businessCrematingInfoData;

    /**
     * 火化任务费用信息
     */
    @ApiModelProperty(value = "火化任务费用信息", name = "chargeItem", position = 2)
    private ChargeItem chargeItem;

    public BusinessCrematingInfoData getBusinessCrematingInfoData() {
        return businessCrematingInfoData;
    }

    public void setBusinessCrematingInfoData(BusinessCrematingInfoData businessCrematingInfoData) {
        this.businessCrematingInfoData = businessCrematingInfoData;
    }

    public ChargeItem getChargeItem() {
        return chargeItem;
    }

    public void setChargeItem(ChargeItem chargeItem) {
        this.chargeItem = chargeItem;
    }
}
