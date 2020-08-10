package com.jmdz.fushan.pad.base;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.base.RequestResponse;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.helper.CacheHelper;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 * LoginHandler
 *
 * @author LiCongLu
 * @date 2020-06-08 09:00
 */
@SuppressWarnings("unchecked")
public class LoginHandler extends RequestResponse {

    @Resource
    private HandlerWrapper handlerWrapper;

    @Resource
    private ConfigData configData;

    @Resource
    private UserDao userDao;

    @Autowired
    protected HttpServletRequest request;

    @Resource
    private Cache applicationCache;

    @Override
    public HttpServletRequest getRequest() {
        return this.request;
    }

    /**
     * 获取header里的token
     *
     * @return
     */
    public String getToken() {
        return this.getRequest().getHeader(Constants.AUTHORIZATION);
    }


    /**
     * 验证执行Handler操作
     *
     * @param data    请求数据
     * @param handler 处理监听
     * @return
     * @author LiCongLu
     * @date 2020-06-08 09:53
     */
    public <T extends UserData, D extends Serializable> BaseResult<D> loginHandler(T data, OnLoginHandler handler) {
        // 验证token
        String token = getToken();
        if (DataUtil.valid(configData.getAppToken())) {
            if (!DataUtil.equals(configData.getAppToken(), token)) {
                return BaseService.failure("token错误");
            }
        }

        // 需要验证请求数据的，请求数据不可为空
        if (data == null) {
            return BaseService.failure("请求数据为空");
        }

        // 验证账号会话值
        String loginId = data.getLoginId();
        if (DataUtil.invalid(loginId)) {
            return BaseService.loginInvalid("登录会话信息为空");
        }

        // 验证登录账号
        String userId = data.getUserId();
        if (DataUtil.invalid(userId)) {
            return BaseService.loginInvalid("登录账号信息为空");
        }

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            BaseResult<D> dataResult = new BaseResult<D>();
            BeanUtil.copy2Bean(result, dataResult);
            return dataResult;
        }

        // 判断是否存在处理监听
        if (handler == null) {
            return BaseService.failure("未执行处理操作");
        }

        // 验证会话账号
        LoginItem loginItem = CacheHelper.getCacheValue(applicationCache, loginId);
        if (loginItem == null || !DataUtil.equals(userId, loginItem.getUserId())) {
            return BaseService.loginInvalid("登录会话失效！");
        }

        // 查询账号信息
        LoginUserItem loginUserItem = userDao.getLoginUserByUserId(userId);
        if (loginUserItem == null || DataUtil.invalid(loginUserItem.getUserId())) {
            return BaseService.failure("登录账号错误，未找到相关账号");
        }

        try {
            // 处理执行
            return handler.onHandler(BeanUtil.copy2Bean(loginUserItem, new LoginItem()).setLoginId(loginId));
        } catch (ActionException e) {
            LogUtil.info("请求异常，信息：" + e.getMessage());
            return BaseService.failure(e.getMessage());
        }
    }


    /**
     * OnUserHandler
     *
     * @author LiCongLu
     * @date 2020-06-08 09:51
     */
    @FunctionalInterface
    public interface OnLoginHandler<T extends Serializable> {
        /**
         * 实际执行Handler
         *
         * @param loginItem 登录账号
         * @return BaseResult
         * @throws ActionException
         * @author LiCongLu
         * @date 2020-01-02 10:21
         */
        BaseResult<T> onHandler(LoginItem loginItem) throws ActionException;
    }
}