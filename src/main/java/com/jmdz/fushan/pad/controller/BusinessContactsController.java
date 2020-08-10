package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.service.BusinessContactsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 业务洽谈 家属/承办人 相关接口
 *
 * @author Wangshengtao
 * @date 2020-08-04 11:01
 */
@Api(tags = "业务洽谈联系人相关接口", description = "业务洽谈联系人相关接口")
@RestController()
@RequestMapping("/pad/business-contacts")
public class BusinessContactsController extends LoginHandler {

    @Resource
    private BusinessContactsService businessContactsService;


    /**
     * 业务洽谈联系人信息保存接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-04 11:15
     */
    @ApiOperation(value = "业务洽谈联系人信息保存接口", notes = "业务洽谈联系人信息保存接口")
    @PostMapping(value = "/save-business-contacts-info")
    public BaseResult<BusinessContactsInfoData> saveBusinessContactsInfo(@RequestBody BusinessContactsInfoData data) {
        return loginHandler(data, loginItem -> businessContactsService.saveBusinessContactsInfo(data,loginItem));
    }


    /**
     * 业务洽谈联系人信息加载接口
     *
     * @param data 业务编号
     * @return
     * @author WangShengtao
     * @date 2020-08-04 11:15
     */
    @ApiOperation(value = "业务洽谈联系人信息加载接口", notes = "业务洽谈联系人信息加载接口")
    @PostMapping(value = "/load-business-conacts-info-by-operation-no")
    public BaseResult<BusinessContactsInfo> loadBusinessContactsInfoByOperationNo(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessContactsService.loadBusinessContactsInfoByOperationNo(data));
    }
}
