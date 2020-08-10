package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.service.BusinessProductService;
import com.jmdz.fushan.pad.service.BusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * BusinessController
 *
 * @author LiCongLu
 * @date 2020-07-31 08:51
 */
@Api(tags = "业务洽谈公用相关接口", description = "业务洽谈公用相关接口")
@RestController()
@RequestMapping("/pad/business")
public class BusinessController extends LoginHandler {

    @Resource
    private BusinessService businessService;

    /**
     * 加载字典数据等业务基础数据接口
     *
     * @param data 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-31 10:14
     */
    @ApiOperation(value = "加载字典数据等业务基础数据接口", notes = "加载字典数据等业务基础数据接口,包含逝者字典、类型字典等数据")
    @PostMapping(value = "/load-business-data-picker")
    public BaseResult<BusinessDataItem> loadBusinessDataPicker(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> businessService.loadBusinessDataPicker());
    }

    /**
     * 加载子行政区划接口
     *
     * @param data 父主键
     * @return
     * @author LiCongLu
     * @date 2020-08-03 09:45
     */
    @ApiOperation(value = "加载子行政区划接口", notes = "加载子行政区划接口,按照父主键加载数据")
    @PostMapping(value = "/load-area-picker-with-pid")
    public BaseResult<ArrayList<PickerItem>> loadAreaPickerWithPid(@RequestBody IdData data) {
        return loginHandler(data, loginItem -> businessService.loadAreaPickerWithPid(data));
    }

    /**
     * 按照类型加载服务和物品接口
     *
     * @param data 服务和物品类型
     * @return
     * @author LiCongLu
     * @date 2020-08-03 11:49
     */
    @ApiOperation(value = "按照类型加载服务和物品接口", notes = "按照类型加载服务和物品接口")
    @PostMapping(value = "/load-business-service-list-with-type")
    public BaseResult<ArrayList<BusinessServiceItem>> loadBusinessServiceListWithType(@RequestBody ServiceTypeData data) {
        return loginHandler(data, loginItem -> businessService.loadBusinessServiceListWithType(data));
    }
}
