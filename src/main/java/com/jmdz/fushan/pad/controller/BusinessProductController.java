package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
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
 * 业务洽谈丧葬物品相关接口
 *
 * @author LiCongLu
 * @date 2020-08-03 16:15
 */
@Api(tags = "业务洽谈丧葬物品相关接口", description = "业务洽谈丧葬物品相关接口")
@RestController()
@RequestMapping("/pad/business-product")
public class BusinessProductController extends LoginHandler {

    @Resource
    private BusinessProductService businessProductService;

    /**
     * 按照类型加载丧葬用品费用列表接口
     *
     * @param data 服务和物品类型
     * @return
     * @author LiCongLu
     * @date 2020-08-03 15:57
     */
    @ApiOperation(value = "按照类型加载丧葬用品费用列表接口", notes = "按照类型加载丧葬用品费用列表接口，即丧葬用品/骨灰盒/寿衣/纸棺/鲜花/服务项目等")
    @PostMapping(value = "/load-funeral-product-charge-with-type")
    public BaseResult<ArrayList<ChargeItem>> loadFuneralProductChargeWithType(@RequestBody ServiceTypeData data) {
        return loginHandler(data, loginItem -> businessProductService.loadFuneralProductChargeWithType(data));
    }

    /**
     * 保存丧葬用品等费用接口
     *
     * @param data 保存物品费用
     * @return
     * @author LiCongLu
     * @date 2020-08-04 09:33
     */
    @ApiOperation(value = "保存丧葬用品等费用接口", notes = "按照类型保存丧葬用品等费用接口，即丧葬用品/骨灰盒/寿衣/纸棺/鲜花/服务项目等")
    @PostMapping(value = "/save-funeral-product-service")
    public BaseResult saveFuneralProductService(@RequestBody FuneralProductServiceData data) {
        return loginHandler(data, loginItem -> businessProductService.saveFuneralProductService(loginItem, data));
    }

    /**
     * 按照主键加载丧葬用品费用接口
     *
     * @param data 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:07
     */
    @ApiOperation(value = "按照主键加载丧葬用品费用接口", notes = "按照主键加载丧葬用品费用接口")
    @PostMapping(value = "/load-funeral-product-charge-with-id")
    public BaseResult<ChargeItem> loadFuneralProductChargeWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessProductService.loadFuneralProductChargeWithId(data));
    }

    /**
     * 保存丧葬用品费用接口
     *
     * @param data 丧葬用品数据
     * @return
     * @author LiCongLu
     * @date 2020-08-04 13:15
     */
    @ApiOperation(value = "保存丧葬用品费用接口", notes = "保存丧葬用品费用接口")
    @PostMapping(value = "/save-funeral-product-charge")
    public BaseResult saveFuneralProductCharge(@RequestBody FuneralProductChargeData data) {
        return loginHandler(data, loginItem -> businessProductService.saveFuneralProductCharge(loginItem, data));
    }

    /**
     * 按照删除丧葬用品费用接口
     *
     * @param data 主键
     * @return
     * @author LiCongLu
     * @date 2020-08-04 14:17
     */
    @ApiOperation(value = "按照删除丧葬用品费用接口", notes = "按照删除丧葬用品费用接口")
    @PostMapping(value = "/delete-funeral-product-charge-with-id")
    public BaseResult deleteFuneralProductChargeWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessProductService.deleteFuneralProductChargeWithId(loginItem,data));
    }
}
