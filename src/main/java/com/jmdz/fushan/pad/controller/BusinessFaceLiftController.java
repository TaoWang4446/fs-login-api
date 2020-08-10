package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.service.BusinessFaceLiftService;
import com.jmdz.fushan.pad.service.BusinessProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务洽谈整容相关接口
 *
 * @author LiCongLu
 * @date 2020-08-04 15:11
 */
@Api(tags = "业务洽谈整容相关接口", description = "业务洽谈整容相关接口")
@RestController()
@RequestMapping("/pad/business-face-lift")
public class BusinessFaceLiftController extends LoginHandler {

    @Resource
    private BusinessFaceLiftService businessFaceLiftService;

    /**
     * 加载整容服务项目费用列表接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-04 16:48
     */
    @ApiOperation(value = "加载整容服务项目费用列表接口", notes = "加载整容服务项目费用列表接口")
    @PostMapping(value = "/load-face-lift-charge-list")
    public BaseResult<ArrayList<FaceLiftChargeListItem>> loadFaceLiftChargeList(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessFaceLiftService.loadFaceLiftChargeList(data));
    }

    /**
     * 保存整容服务项目接口
     *
     * @param data 整容项目
     * @return
     * @author LiCongLu
     * @date 2020-08-04 15:18
     */
    @ApiOperation(value = "保存整容服务项目接口", notes = "保存整容服务项目接口")
    @PostMapping(value = "/save-face-lift-service")
    public BaseResult saveFaceLiftService(@RequestBody FaceLiftServiceData data) {
        return loginHandler(data, loginItem -> businessFaceLiftService.saveFaceLiftService(loginItem, data));
    }

    /**
     * 按照费用主键加载整容服务项目费用接口
     *
     * @param data 费用主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 17:07
     */
    @ApiOperation(value = "按照费用主键加载整容服务项目费用接口", notes = "按照费用主键加载整容服务项目费用接口")
    @PostMapping(value = "/load-face-lift-charge-with-id")
    public BaseResult<FaceLiftChargeListItem> loadFaceLiftChargeWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessFaceLiftService.loadFaceLiftChargeWithId(data));
    }

    /**
     * 保存整容服务项目费用接口
     *
     * @param data 整容服务费用
     * @return
     * @author LiCongLu
     * @date 2020-08-05 09:18
     */
    @ApiOperation(value = "保存整容服务项目费用接口", notes = "保存整容服务项目费用接口")
    @PostMapping(value = "/save-face-lift-charge")
    public BaseResult saveFaceLiftCharge(@RequestBody FaceLiftChargeData data) {
        return loginHandler(data, loginItem -> businessFaceLiftService.saveFaceLiftCharge(loginItem, data));
    }

    /**
     * 删除整容服务项目费用接口
     *
     * @param data 主键信息
     * @return
     * @author LiCongLu
     * @date 2020-08-05 09:00
     */
    @ApiOperation(value = "删除整容服务项目费用接口", notes = "删除整容服务项目费用接口")
    @PostMapping(value = "/delete-face-lift-charge-with-id")
    public BaseResult deleteFaceLiftChargeWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessFaceLiftService.deleteFaceLiftChargeWithId(loginItem, data));
    }
}
