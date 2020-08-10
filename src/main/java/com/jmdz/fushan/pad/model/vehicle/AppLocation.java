package com.jmdz.fushan.pad.model.vehicle;

import com.jmdz.common.base.BaseBean;

/**
 * @author LiCongLu
 * @date 2020-07-09 16:00
 */
public class AppLocation extends BaseBean {

    private int id;

    /**
     * 定位会话ID
     */
    private String loginId;

    /**
     * 定位帐号UserId
     */
    private String userId;

    /**
     * 定位时间
     */
    private String locationTime;

    /**
     * 经度(119.268498)
     */
    private String longitude;

    /**
     * 纬度(35.523556)
     */
    private String latitude;

    /**
     * 关联编码，默认为空字符串
     */
    private String locationCode;

    /**
     * 定位标记
     */
    private int locationFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最近的出车未回的业务编号
     */
    private String operationNo;

    public int getId() {
        return id;
    }

    public AppLocation setId(int id) {
        this.id = id;
        return this;
    }

    public String getLoginId() {
        return loginId;
    }

    public AppLocation setLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public AppLocation setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getLocationTime() {
        return locationTime;
    }

    public AppLocation setLocationTime(String locationTime) {
        this.locationTime = locationTime;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public AppLocation setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public AppLocation setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public AppLocation setLocationCode(String locationCode) {
        this.locationCode = locationCode;
        return this;
    }

    public int getLocationFlag() {
        return locationFlag;
    }

    public AppLocation setLocationFlag(int locationFlag) {
        this.locationFlag = locationFlag;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public AppLocation setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public AppLocation setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }
}
