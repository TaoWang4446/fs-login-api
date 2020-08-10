package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.service.BusinessDeadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务洽谈逝者相关接口
 *
 * @author LiCongLu
 * @date 2020-07-28 13:17
 */
@Api(tags = "业务洽谈逝者相关接口", description = "业务洽谈逝者相关接口")
@RestController()
@RequestMapping("/pad/business-dead")
public class BusinessDeadController extends LoginHandler {

    @Resource
    private BusinessDeadService businessDeadService;

   /**
    * 业务洽谈逝者列表加载接口
    *
    * @param data 关键字
    * @return
    * @author LiCongLu
    * @date 2020-07-28 13:22
    */
    @ApiOperation(value = "业务洽谈逝者列表加载接口", notes = "业务洽谈逝者列表加载接口")
    @PostMapping(value = "/load-business-dead-list")
    public BaseResult<ArrayList<BusinessDeadListItem>> loadBusinessDeadList(@RequestBody BusinessDeadData data) {
        return loginHandler(data, loginItem -> businessDeadService.loadBusinessDeadList(data));
    }

    /**
     * 业务洽谈逝者详情信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-28 13:35
     */
    @ApiOperation(value = "业务洽谈逝者详情信息接口", notes = "业务洽谈逝者详情信息接口")
    @PostMapping(value = "/load-business-dead-all-with-operation-no")
    public BaseResult<BusinessDeadAllItem> loadBusinessDeadAllWithOperationNo(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessDeadService.loadBusinessDeadAllWithOperationNo(data));
    }

    /**
     * 业务洽谈逝者信息加载接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-03 11:15
     */
    @ApiOperation(value = "业务洽谈逝者信息加载接口", notes = "业务洽谈逝者信息加载接口")
    @PostMapping(value = "/load-business-dead-info-by-operation-no")
    public BaseResult<BusinessDeadInfo> loadBusinessDeadInfoByOperationNo(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessDeadService.loadBusinessDeadInfoByOperationNo(data));
    }

    /**
     * 业务洽谈逝者信息更新接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-03 13:15
     */
    @ApiOperation(value = "业务洽谈逝者信息更新接口", notes = "业务洽谈逝者信息更新接口")
    @PostMapping(value = "/save-business-dead-info")
    public BaseResult<BusinessDeadInfoData> saveBusinessDeadInfo(@RequestBody BusinessDeadInfoData data) {
        return loginHandler(data, loginItem -> businessDeadService.saveBusinessDeadInfo(loginItem,data));
    }



}
