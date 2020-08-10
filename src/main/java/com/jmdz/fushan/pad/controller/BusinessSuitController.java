package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.service.ServiceSuitData;
import com.jmdz.fushan.pad.model.service.ServiceSuitItem;
import com.jmdz.fushan.pad.service.BusinessSuitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务洽谈套餐相关接口
 *
 * @author LiCongLu
 * @date 2020-08-10 09:32
 */
@Api(tags = "业务洽谈套餐相关接口", description = "业务洽谈逝者相关接口")
@RestController()
@RequestMapping("/pad/business-suit")
public class BusinessSuitController extends LoginHandler {

    @Resource
    private BusinessSuitService businessSuitService;

    /**
     * 业务洽谈服务套餐加载接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-10 09:38
     */
    @ApiOperation(value = "业务洽谈服务套餐加载接口", notes = "业务洽谈服务套餐加载接口")
    @PostMapping(value = "/load-business-service-suit")
    public BaseResult<ArrayList<ServiceSuitItem>> loadBusinessServiceSuit(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessSuitService.loadBusinessServiceSuit(data));
    }

    /**
     * 业务洽谈添加服务套餐接口
     *
     * @param data 服务套餐主键
     * @return
     * @author LiCongLu
     * @date 2020-08-10 10:10
     */
    @ApiOperation(value = "业务洽谈添加服务套餐接口", notes = "业务洽谈添加服务套餐接口")
    @PostMapping(value = "/save-business-service-suit")
    public BaseResult saveBusinessServiceSuit(@RequestBody ServiceSuitData data) {
        return loginHandler(data, loginItem -> businessSuitService.saveBusinessServiceSuit(loginItem, data));
    }

    /**
     * 删除业务洽谈服务套餐接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-10 11:16
     */
    @ApiOperation(value = "删除业务洽谈服务套餐接口", notes = "删除业务洽谈服务套餐接口")
    @PostMapping(value = "/delete-business-service-suit")
    public BaseResult deleteBusinessServiceSuit(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessSuitService.deleteBusinessServiceSuit(loginItem, data));
    }
}
