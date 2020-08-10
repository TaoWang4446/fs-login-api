package com.jmdz.fushan.base;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.wrapper.HandlerWrapper;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author LiCongLu
 * @date 2020-07-08 11:08
 */
public class UserHandler extends BaseHandler {

    @Resource
    private HandlerWrapper handlerWrapper;

    @Resource
    private ConfigData configData;

    /**
     * 验证执行Handler操作
     *
     * @param handler 处理监听器
     * @return
     * @author LiCongLu
     * @date 2020-06-08 09:53
     */
    public <T extends BaseBean, D extends Serializable> BaseResult<D> userHandler(OnUserHandler handler) {
        return userHandler(new BaseBean(), false, handler);
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
    public <T extends BaseBean, D extends Serializable> BaseResult<D> userHandler(T data, OnUserHandler handler) {
        return userHandler(data, true, handler);
    }

    /**
     * 验证执行Handler操作
     *
     * @param data         请求数据
     * @param validateFlag 是否验证数据
     * @param handler      处理监听
     * @return
     * @author LiCongLu
     * @date 2020-06-08 09:53
     */
    public <T extends BaseBean, D extends Serializable> BaseResult<D> userHandler(T data, boolean validateFlag, OnUserHandler handler) {
        // 验证token
        String token = getToken();
        if (DataUtil.valid(configData.getAppToken())) {
            if (!DataUtil.equals(configData.getAppToken(), token)) {
                return BaseService.failure("token错误");
            }
        }

        // 按照条件进行验证数据
        if (validateFlag) {
            // 需要验证请求数据的，请求数据不可为空
            if (data == null) {
                return BaseService.failure("请求数据为空");
            }

            // 验证请求数据
            BaseResult result = handlerWrapper.validateBean(data);
            if (result != null) {
                BaseResult<D> dataResult = new BaseResult<D>();
                BeanUtil.copy2Bean(result, dataResult);
                return dataResult;
            }
        }

        // 判断是否存在处理监听
        if (handler == null) {
            return BaseService.failure("未执行处理操作");
        }

        try {
            // 处理执行
            return handler.onHandler();
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
    public interface OnUserHandler<T extends Serializable> {
        /**
         * 实际执行Handler
         *
         * @return BaseResult
         * @throws ActionException
         * @author LiCongLu
         * @date 2020-01-02 10:21
         */
        BaseResult<T> onHandler() throws ActionException;
    }
}
