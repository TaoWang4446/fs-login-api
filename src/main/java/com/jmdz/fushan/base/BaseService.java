package com.jmdz.fushan.base;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.Messages;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * BaseService
 *
 * @author LiCongLu
 * @date 2020-05-29 13:30
 */
public class BaseService {

    /**
     * 抛出异常信息
     *
     * @param message 异常信息
     * @return
     * @author LiCongLu
     * @date 2020-02-06 11:51
     */
    public ActionException exception(String message) {
        return new ActionException(message);
    }

    /**
     * @Author guankui
     * @Description 成功（普通）
     * @Date 2019/10/17  16:27
     * @Param
     * @Return
     */
    public static BaseResult success() {
        return success(Messages.SUCCESS);
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
        return HttpResult.success(bean);
    }

    /**
     * @Author guankui
     * @Description 成功，设置返回数组
     * @Date 2019/10/17  16:31
     * @Param
     * @Return
     */
    public static <T extends BaseBean> BaseResult success(List<T> list) {
        return HttpResult.success(list);
    }

    /**
     * @Author guankui
     * @Description 失败（普通）
     * @Date 2019/10/17  16:27
     * @Param message
     * @Return
     */
    public static BaseResult failure() {
        return failure(Messages.EORROR);
    }

    /**
     * @Author guankui
     * @Description 没有数据
     * @Date 2019/10/17  16:27
     * @Param message
     * @Return
     */
    public static BaseResult noData() {
        return failure(Messages.NO_DATA);
    }


    /**
     * @Author guankui
     * @Description 失败（自定义）
     * @Date 2019/10/17  16:27
     * @Param message 自定义message
     * @Return
     */
    public static BaseResult failure(String message) {
        return HttpResult.failure(Constants.FAILURE, message);
    }

    /**
     * 登录会话失效结果
     * 
     * @param message 错误信息提示
     * @return
     * @author LiCongLu
     * @date 2020-07-08 13:14
     */
    public static BaseResult loginInvalid(String message) {
        return HttpResult.failure(Constants.LOGIN_INVALID, message);
    }

    /**
     * 获取Request请求
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取 Response响应
     *
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取基地址
     * @return
     */
    public String getBaserUrl() {
        HttpServletRequest request = getRequest();
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return baseUrl;
    }
}
