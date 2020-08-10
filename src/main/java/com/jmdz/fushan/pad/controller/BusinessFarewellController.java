package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.BusinessServiceSuitItem;
import com.jmdz.fushan.pad.model.business.farewell.*;
import com.jmdz.fushan.pad.service.BusinessFarewellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务洽谈告别相关接口
 *
 * @author LiCongLu
 * @date 2020-08-05 11:19
 */
@Api(tags = "业务洽谈告别相关接口", description = "业务洽谈告别相关接口")
@RestController()
@RequestMapping("/pad/business-farewell")
public class BusinessFarewellController extends LoginHandler {

    @Resource
    private BusinessFarewellService businessFarewellService;

    /**
     * 按照业务编号加载告别任务列表信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 09:42
     */
    @ApiOperation(value = "按照业务编号加载告别任务列表信息接口", notes = "按照业务编号加载告别任务列表信息接口")
    @PostMapping(value = "/load-farewell-mourn-list")
    public BaseResult<ArrayList<FarewellMournListItem>> loadFarewellMournList(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessFarewellService.loadFarewellMournList(data));
    }

    /**
     * 加载选择告别厅信息接口
     *
     * @param data 选择时间
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:00
     */
    @ApiOperation(value = "加载选择告别厅信息接口", notes = "加载选择告别厅信息接口")
    @PostMapping(value = "/load-farewell-hall-list")
    public BaseResult<ArrayList<FarewellHallListItem>> loadFarewellHallList(@RequestBody FarewellHallListData data) {
        return loginHandler(data, loginItem -> businessFarewellService.loadFarewellHallList(data));
    }

    /**
     * 按照主键加载告别任务详情信息接口
     *
     * @param data 业务主键
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:07
     */
    @ApiOperation(value = "按照主键加载告别任务详情信息接口", notes = "按照主键加载告别任务详情信息接口")
    @PostMapping(value = "/load-farewell-mourn-all-with-id")
    public BaseResult<FarewellMournAllItem> loadFarewellMournAllWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessFarewellService.loadFarewellMournAllWithId(data));
    }

    /**
     * 加载告别厅物品服务套餐接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 11:13
     */
    @ApiOperation(value = "加载告别厅物品服务套餐接口", notes = "加载告别厅物品服务套餐接口")
    @PostMapping(value = "/load-farewell-service-suit")
    public BaseResult<ArrayList<BusinessServiceSuitItem>> loadFarewellServiceSuit(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessFarewellService.loadFarewellServiceSuit(data));
    }

    /**
     * 保存告别任务详情信息接口
     *
     * @param data 告别任务信息
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:12
     */
    @ApiOperation(value = "保存告别任务详情信息接口", notes = "保存告别任务详情信息接口")
    @PostMapping(value = "/save-farewell-mourn")
    public BaseResult saveFarewellMourn(@RequestBody FarewellMournSaveData data) {
        return loginHandler(data, loginItem -> businessFarewellService.saveFarewellMourn(loginItem, data));
    }

    /**
     * 删除告别任务详情信息接口
     *
     * @param data 主键信息
     * @return
     * @author LiCongLu
     * @date 2020-08-07 10:29
     */
    @ApiOperation(value = "删除告别任务详情信息接口", notes = "删除告别任务详情信息接口")
    @PostMapping(value = "/delete-farewell-mourn-with-id")
    public BaseResult deleteFarewellMournWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessFarewellService.deleteFarewellMournWithId(loginItem, data));
    }
}
