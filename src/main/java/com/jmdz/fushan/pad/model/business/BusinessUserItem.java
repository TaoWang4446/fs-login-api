package com.jmdz.fushan.pad.model.business;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务员工账号信息
 *
 * @author LiCongLu
 * @date 2020-07-31 13:44
 */
@ApiModel(value = "业务员工账号信息", description = "业务员工账号信息")
public class BusinessUserItem extends BaseBean {
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
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "sortCode", position = 4)
    private String sortCode;

    public String getUserId() {
        return userId;
    }

    public BusinessUserItem setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public BusinessUserItem setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public BusinessUserItem setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getSortCode() {
        return sortCode;
    }

    public BusinessUserItem setSortCode(String sortCode) {
        this.sortCode = sortCode;
        return this;
    }
}
