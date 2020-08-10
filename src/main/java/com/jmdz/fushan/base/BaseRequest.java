package com.jmdz.fushan.base;

import com.jmdz.common.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * 添加请求基础工具
 *
 */
public class BaseRequest extends BaseBean {

    /**
     * 即SessionID，需要GUID(举例：3cd1cd06-3d77-4efb-a78d-ad9a9cea3d80)，登录请求时产生，在后续操作中实时传送
     */
    @ApiModelProperty(name = "sid", value = "登录会话SID")
    private String sid;


}
