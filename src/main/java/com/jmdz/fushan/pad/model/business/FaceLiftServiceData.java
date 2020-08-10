package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.fushan.pad.model.UserData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 整容服务项目保存请求数据
 *
 * @author LiCongLu
 * @date 2020-08-04 15:20
 */
@ApiModel(value = "整容服务项目保存请求数据", description = "整容服务项目保存请求数据")
public class FaceLiftServiceData extends UserData {
    /**
     * 业务编号
     */
    @AnValidate(name = "业务编号", required = true)
    @ApiModelProperty(value = "业务编号", name = "operationNo", position = 3)
    private String operationNo;

    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间", name = "excuteTime", example = "2020-02-02 08:30", position = 4)
    private String excuteTime;

    /**
     * 整容服务集合
     */
    @AnValidate(name = "整容服务集合", required = true)
    @ApiModelProperty(value = "整容服务集合", name = "serviceItems", position = 5)
    private ArrayList<BusinessServiceData> serviceItems;

    public String getOperationNo() {
        return operationNo;
    }

    public FaceLiftServiceData setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getExcuteTime() {
        return excuteTime;
    }

    public FaceLiftServiceData setExcuteTime(String excuteTime) {
        this.excuteTime = excuteTime;
        return this;
    }

    public ArrayList<BusinessServiceData> getServiceItems() {
        return serviceItems;
    }

    public FaceLiftServiceData setServiceItems(ArrayList<BusinessServiceData> serviceItems) {
        this.serviceItems = serviceItems;
        return this;
    }
}
