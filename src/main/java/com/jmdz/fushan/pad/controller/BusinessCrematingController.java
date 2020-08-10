package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.BusinessCrematingData;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfo;
import com.jmdz.fushan.pad.model.business.BusinessCrematingInfoData;
import com.jmdz.fushan.pad.service.BusinessCrematingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;


/**
 * 业务洽谈火化相关接口
 *
 * @author LiCongLu
 * @date 2020-08-05 11:26
 */
@Api(tags = "业务洽谈火化相关接口", description = "业务洽谈火化相关接口")
@RestController()
@RequestMapping("/pad/business-cremating")
public class BusinessCrematingController extends LoginHandler {

    @Resource
    private BusinessCrematingService businessCrematingService;

    /**
     * 业务洽谈火化任务详情信息保存接口
     *
     * @param data 火化任务详情信息
     * @return
     * @author WangShengtao
     * @date 2020-08-06 08:51
     */
    @ApiOperation(value = "业务洽谈火化详情信息保存接口", notes = "业务洽谈火化详情信息保存接口")
    @PostMapping(value = "/save-business-cremating-info")
    public BaseResult<BusinessCrematingInfoData> saveBusinessCrematingInfo(@RequestBody BusinessCrematingData data) {
        return loginHandler(data, loginItem -> businessCrematingService.saveBusinessCrematingInfo(data,loginItem));
    }

    /**
     * 业务洽火化任务详情信息加载接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-06 08:51
     */
    @ApiOperation(value = "业务洽谈火化任务详情/列表信息加载接口", notes = "业务洽谈火化任务详情/列表信息加载接口")
    @PostMapping(value = "/load-business-cremating-info-by-operation-no")
    public BaseResult<BusinessCrematingInfo> loadBusinessCrematingInfoByOperationNo(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessCrematingService.loadBusinessCrematingInfoByOperationNo(data));
    }


    /**
     * 按照业务编号删除火化任务记录接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-06 08:51
     */
    @ApiOperation(value = "按照业务编号删除火化任务记录接口", notes = "按照业务编号删除火化任务记录接口")
    @PostMapping(value = "/delete-business-cremating-row-with-operation-no")
    public BaseResult deleteBusinessCrematingRowWithOperationNo(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessCrematingService.deleteBusinessCrematingRowWithOperationNo(loginItem,data));
    }
}
