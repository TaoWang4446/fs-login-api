package com.jmdz.fushan.pad.model.login;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录响应Item
 *
 * @author LiCongLu
 * @date 2020-06-05 13:26
 */
@ApiModel(value = "登录账号信息", description = "登录返回信息")
public class LoginItem extends BaseBean {
    /**
     * 账号主键
     */
    @ApiModelProperty(value = "账号主键", name = "userId", position = 1)
    private String userId;

    /**
     * 账号名称
     */
    @ApiModelProperty(value = "账号名称", name = "realName", position = 2)
    private String realName;

    /**
     * 员工号
     */
    @ApiModelProperty(value = "员工号", name = "userCode", position = 3)
    private String userCode;

    /**
     * 登录会话值
     */
    @ApiModelProperty(value = "登录会话值", name = "loginId", position = 4)
    private String loginId;

    public String getUserId() {
        return userId;
    }

    public LoginItem setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public LoginItem setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public LoginItem setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getLoginId() {
        return loginId;
    }

    public LoginItem setLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }
}
