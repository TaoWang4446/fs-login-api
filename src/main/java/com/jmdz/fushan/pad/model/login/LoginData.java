package com.jmdz.fushan.pad.model.login;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录请求数据
 *
 * @author LiCongLu
 * @date 2020-06-05 13:11
 */
@ApiModel(value = "登录请求数据", description = "登录请求数据")
public class LoginData extends BaseData {

    /**
     * 登录帐号
     */
    @AnValidate(name = "登录账号", required = true)
    @ApiModelProperty(value = "登录账号", name = "account", position = 1)
    private String account;

    /**
     * 登录密码
     */
    @AnValidate(name = "登录密码", required = true)
    @ApiModelProperty(value = "登录密码", name = "password", position = 2)
    private String password;

    /**
     * 设备唯一码
     */
    @AnValidate(name = "设备唯一码")
    @ApiModelProperty(value = "设备唯一码", name = "imei", position = 3)
    private String imei;

    /**
     * 终端类型
     */
    @AnValidate(name = "终端类型", required = true)
    @ApiModelProperty(value = "终端类型", name = "appType", position = 4)
    private String appType;

    public String getAccount() {
        return account;
    }

    public LoginData setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginData setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public LoginData setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getAppType() {
        return appType;
    }

    public LoginData setAppType(String appType) {
        this.appType = appType;
        return this;
    }
}
