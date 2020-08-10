package com.jmdz.fushan.pad.model;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.base.BaseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 带有账号主键的请求数据基类
 *
 * @author LiCongLu
 * @date 2020-06-08 09:56
 */
@ApiModel(value = "带有用户账号的请求数据", description = "带有用户账号(UserId)的请求数据")
public class UserData extends BaseData {

    /**
     * 登录会话值
     */
    @AnValidate(value = "登录会话值", required = true)
    @ApiModelProperty(value = "登录会话值", name = "loginId", position = 1)
    private String loginId;

    /**
     * 账号主键
     */
    @AnValidate(value = "账号主键", required = true)
    @ApiModelProperty(value = "账号主键", name = "userId", position = 2)
    private String userId;

    public String getLoginId() {
        return loginId;
    }

    public UserData setLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserData setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
