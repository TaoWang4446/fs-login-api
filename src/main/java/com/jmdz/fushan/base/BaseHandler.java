package com.jmdz.fushan.base;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 封装Handler基类
 *
 * @author LiCongLu
 * @date 2020-05-26 15:30
 */
@SuppressWarnings("unchecked")
public class BaseHandler extends RequestResponse {

    @Resource
    private HandlerWrapper handlerWrapper;

    @Resource
    private ConfigData configData;

    @Autowired
    protected HttpServletRequest request;


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
     * @param handler 处理监听器
     * @return
     * @author LiCongLu
     * @date 2020-05-26 13:48
     */
    public <T extends BaseBean, D extends Serializable> BaseResult<D> dataHandler(OnDataHandler handler) {
        return dataHandler(new BaseBean(), false, handler);
    }

    /**
     * 验证执行Handler操作
     *
     * @param data    请求数据
     * @param handler 处理监听
     * @return
     * @author LiCongLu
     * @date 2020-05-26 13:48
     */
    public <T extends BaseBean, D extends Serializable> BaseResult<D> dataHandler(T data, OnDataHandler handler) {
        return dataHandler(data, true, handler);
    }

    /**
     * 验证执行Handler操作
     *
     * @param data         请求数据
     * @param validateFlag 是否验证数据
     * @param handler      处理监听
     * @return
     * @author LiCongLu
     * @date 2020-05-26 13:48
     */
    public <T extends BaseBean, D extends Serializable> BaseResult<D> dataHandler(T data, boolean validateFlag, OnDataHandler handler) {
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
     * 验证执行Handler操作
     *
     * @param dataList 请求数据集合
     * @param handler  处理监听
     * @return
     * @author LiCongLu
     * @date 2020-05-26 16:18
     */
    public <T extends BaseBean, D extends Serializable> BaseResult<D> dataHandler(ArrayList<T> dataList, OnDataHandler handler) {
        return dataHandler(dataList, true, handler);
    }

    /**
     * 验证执行Handler操作
     *
     * @param dataList     请求数据集合
     * @param validateFlag 是否验证数据
     * @param handler      处理监听
     * @return
     * @author LiCongLu
     * @date 2020-05-26 15:37
     */
    private <T extends BaseBean, D extends Serializable> BaseResult<D> dataHandler(ArrayList<T> dataList, boolean validateFlag, OnDataHandler handler) {

        // 按照条件进行验证数据
        if (validateFlag) {
            // 需要验证请求数据的，请求数据不可为空
            if (dataList == null || dataList.size() == 0) {
                return BaseService.failure("请求数据为空");
            }

            // 验证请求数据
            BaseResult result = handlerWrapper.validateList(dataList);
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
     * OnDataHandler
     *
     * @author LiCongLu
     * @date 2020-05-26 15:32
     */
    @FunctionalInterface
    public interface OnDataHandler<T extends Serializable> {
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
