package com.jmdz.fushan.base;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.Messages;

import java.util.List;

/**
 * @Author guankui
 * @Description
 * @Date 2019/10/17 15:50
 */
public class HttpResult {
    /**
     * @Author guankui
     * @Description 成功（普通）
     * @Date 2019/10/17  16:27
     * @Param
     * @Return
     */
    public static BaseResult success() {
        return new BaseResult(Constants.SUCCESS, Messages.SUCCESS);
    }

    /**
     * @Author guankui
     * @Description 成功（自定义消息）
     * @Date 2019/10/17  16:27
     * @Param message 自定义消息
     * @Return
     */
    public static BaseResult success(String message) {
        return new BaseResult(Constants.SUCCESS, message);
    }

    /**
     * @Author guankui
     * @Description 成功，设置返回数据
     * @Date 2019/10/17  16:31
     * @Param
     * @Return
     */
    public static <T extends BaseBean> BaseResult success(T bean) {
        BaseResult result = success();
        result.setData(bean);
        return result;
    }

    /**
     * @Author guankui
     * @Description 成功，设置返回数组
     * @Date 2019/10/17  16:31
     * @Param
     * @Return
     */
    public static <T extends BaseBean> BaseResult success(List<T> list) {

        if (list == null || list.size() == 0) {
            return (BaseResult) noData();
        }
        BaseResult result = (BaseResult) success();
        result.setData(list);
        return result;
    }

    /**
     * @Author guankui
     * @Description 成功（无查询结果--用于列表查询）
     * @Date 2019/10/17  16:27
     * @Param message
     * @Return
     */
    public static BaseResult noData() {
        return success(Messages.NO_DATA);
    }

    /**
     * @Author guankui
     * @Description 失败（普通）
     * @Date 2019/10/17  16:27
     * @Param message
     * @Return
     */
    public static BaseResult failure() {
        return failure(Constants.FAILURE, Messages.EORROR);
    }

    /**
     * @Author guankui
     * @Description 失败（自定义code）
     * @Date 2019/10/17  16:27
     * @Param code 自定义code
     * @Return
     */
    public static BaseResult failure(int code) {
        return failure(code, Messages.EORROR);
    }

    /**
     * @Author guankui
     * @Description 失败（自定义）
     * @Date 2019/10/17  16:27
     * @Param message 自定义message
     * @Return
     */
    public static BaseResult failure(String message) {
        return failure(Constants.FAILURE, message);
    }

    /**
     * @Author guankui
     * @Description 失败（自定义）
     * @Date 2019/10/17  16:27
     * @Param code 自定义code
     * @Param message 自定义message
     * @Return
     */
    public static BaseResult failure(int code, String message) {
        return new BaseResult(code, message);
    }

}
