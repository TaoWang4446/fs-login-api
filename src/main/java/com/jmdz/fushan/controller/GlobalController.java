package com.jmdz.fushan.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.ext.StringBuilderExt;
import com.jmdz.fushan.base.HttpResult;
import com.jmdz.fushan.model.config.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局接口
 *
 * @author LiCongLu
 * @date 2020-06-05 13:14
 */
@Api(tags = "公用接口", description = "公用接口")
@RestController
public class GlobalController implements ErrorController {
    /**
     * 默认接口
     *
     * @return
     */
    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public BaseResult root() {
        return rootA();
    }

    /**
     * 默认/接口
     *
     * @return
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public BaseResult rootA() {
        return HttpResult.success("已启动接口");
    }

    /**
     * 错误页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = Constants.ERROR_PATH, method = {RequestMethod.GET, RequestMethod.POST})
    public BaseResult error(HttpServletRequest request, HttpServletResponse response) {
        StringBuilderExt builder = new StringBuilderExt();
        builder.formatLine("请求地址错误;status:{0}", String.valueOf(response.getStatus()));
        return HttpResult.failure(builder.toString());
    }

    @Override
    public String getErrorPath() {
        return Constants.ERROR_PATH;
    }

    /**
     * 验证网络请求
     *
     * @return
     */
    @ApiOperation(value = "验证网络接口", notes = "验证网络接口")
    @RequestMapping(value = "/net-test", method = {RequestMethod.GET, RequestMethod.POST})
    public BaseResult netTest() {
        return HttpResult.success();
    }
}