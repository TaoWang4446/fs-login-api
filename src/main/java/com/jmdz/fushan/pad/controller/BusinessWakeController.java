package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.wake.*;
import com.jmdz.fushan.pad.service.BusinessWakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务洽谈守灵相关接口
 *
 * @author LiCongLu
 * @date 2020-08-05 11:19
 */
@Api(tags = "业务洽谈守灵相关接口", description = "业务洽谈守灵相关接口")
@RestController()
@RequestMapping("/pad/business-wake")
public class BusinessWakeController extends LoginHandler {

    @Resource
    private BusinessWakeService businessWakeService;

    /**
     * 按照业务编号加载守灵任务列表信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-05 15:07
     */
    @ApiOperation(value = "按照业务编号加载守灵任务列表信息接口", notes = "按照业务编号加载守灵任务列表信息接口")
    @PostMapping(value = "/load-wake-mourn-list")
    public BaseResult<ArrayList<WakeMournListItem>> loadWakeMournList(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessWakeService.loadWakeMournList(data));
    }

    /**
     * 加载选择守灵厅信息接口
     *
     * @param data 选择时间
     * @return
     * @author LiCongLu
     * @date 2020-08-05 11:44
     */
    @ApiOperation(value = "加载选择守灵厅信息接口", notes = "加载选择守灵厅信息接口")
    @PostMapping(value = "/load-wake-hall-list")
    public BaseResult<ArrayList<WakeHallListItem>> loadWakeHallList(@RequestBody WakeHallListData data) {
        return loginHandler(data, loginItem -> businessWakeService.loadWakeHallList(data));
    }

    /**
     * 按照主键加载守灵任务详情信息接口
     *
     * @param data 业务主键
     * @return
     * @author LiCongLu
     * @date 2020-08-06 10:32
     */
    @ApiOperation(value = "按照主键加载守灵任务详情信息接口", notes = "按照主键加载守灵任务详情信息接口")
    @PostMapping(value = "/load-wake-mourn-all-with-id")
    public BaseResult<WakeMournAllItem> loadWakeMournAllWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessWakeService.loadWakeMournAllWithId(data));
    }

    /**
     * 保存守灵任务详情信息接口
     *
     * @param data 守灵任务信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 11:48
     */
    @ApiOperation(value = "保存守灵任务详情信息接口", notes = "保存守灵任务详情信息接口")
    @PostMapping(value = "/save-wake-mourn")
    public BaseResult saveWakeMourn(@RequestBody WakeMournSaveData data) {
        return loginHandler(data, loginItem -> businessWakeService.saveWakeMourn(loginItem, data));
    }

    /**
     * 删除守灵任务详情信息接口
     *
     * @param data 主键信息
     * @return
     * @author LiCongLu
     * @date 2020-08-06 16:36
     */
    @ApiOperation(value = "删除守灵任务详情信息接口", notes = "删除守灵任务详情信息接口")
    @PostMapping(value = "/delete-wake-mourn-with-id")
    public BaseResult deleteWakeMournWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> businessWakeService.deleteWakeMournWithId(loginItem, data));
    }
}
