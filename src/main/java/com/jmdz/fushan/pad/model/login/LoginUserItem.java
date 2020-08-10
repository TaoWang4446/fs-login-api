package com.jmdz.fushan.pad.model.login;

import com.jmdz.common.base.BaseBean;

/**
 * 登录账号信息
 *
 * @author LiCongLu
 * @date 2020-06-05 14:19
 */
public class LoginUserItem extends BaseBean {
    /**
     * 账号主键
     */
    private String userId;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 员工工号
     */
    private String userCode;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 秘钥
     */
    private String secretKey;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 是否可用
     */
    private Integer enabled;

    public String getUserId() {
        return userId;
    }

    public LoginUserItem setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public LoginUserItem setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public LoginUserItem setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserItem setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public LoginUserItem setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public LoginUserItem setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public LoginUserItem setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public LoginUserItem setEnabled(Integer enabled) {
        this.enabled = enabled;
        return this;
    }
}
