package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.farewell.FarewellHallData;
import com.jmdz.fushan.pad.model.farewell.FarewellHallItem;
import com.jmdz.fushan.pad.model.farewell.FarewellTaskListItem;
import com.jmdz.fushan.pad.model.farewell.FarewellTaskServiceItem;
import com.jmdz.fushan.pad.service.FarewellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * FarewellController
 *
 * @author LiCongLu
 * @date 2020-07-13 13:18
 */
@Api(tags = "告别业务接口", description = "告别业务接口")
@RestController()
@RequestMapping("/pad/farewell")
public class FarewellController extends LoginHandler {

    @Resource
    private FarewellService farewellService;

    /**
     * 加载告别厅列表
     *
     * @param data 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-13 15:24
     */
    @ApiOperation(value = "加载告别厅列表", notes = "加载告别厅列表")
    @PostMapping(value = "/load-farewell-hall-list")
    public BaseResult<ArrayList<FarewellHallItem>> loadFarewellHallList(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> farewellService.loadFarewellHallList());
    }

    /**
     * 加载告别厅告别任务列表
     *
     * @param data 告别厅主键
     * @return
     * @author LiCongLu
     * @date 2020-07-13 16:28
     */
    @ApiOperation(value = "加载告别厅告别任务列表", notes = "加载告别厅告别任务列表")
    @PostMapping(value = "/load-farewell-task-list")
    public BaseResult<ArrayList<FarewellTaskListItem>> loadFarewellTaskList(@RequestBody FarewellHallData data) {
        return loginHandler(data, loginItem -> farewellService.loadFarewellTaskList(data));
    }

    /**
     * 加载告别任务详情
     *
     * @param data 业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-14 08:42
     */
    @ApiOperation(value = "加载告别任务详情", notes = "加载告别任务详情")
    @PostMapping(value = "/load-farewell-task-with-id")
    public BaseResult<FarewellTaskServiceItem> loadFarewellTaskWithId(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> farewellService.loadFarewellTaskWithId(data));
    }

    /**
     * 告别任务准备完成
     *
     * @param data 业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-15 10:57
     */
    @ApiOperation(value = "告别任务准备完成", notes = "告别任务准备完成")
    @PostMapping(value = "/save-farewell-task-flag-ready")
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskFlagReady(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> farewellService.saveFarewellTaskFlagReady(loginItem, data));
    }

    /**
     * 告别任务遗体入厅
     *
     * @param data 业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-15 11:00
     */
    @ApiOperation(value = "告别任务遗体入厅", notes = "告别任务遗体入厅，遗体接运入厅通知，非实际入厅")
    @PostMapping(value = "/save-farewell-task-flag-begin")
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskFlagBegin(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> farewellService.saveFarewellTaskFlagBegin(loginItem, data));
    }

    /**
     * 告别任务遗体出厅
     *
     * @param data 业务请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-15 11:01
     */
    @ApiOperation(value = "告别任务遗体出厅", notes = "告别任务遗体出厅，遗体接运出厅通知，非实际出厅")
    @PostMapping(value = "/save-farewell-task-flag-end")
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskFlagEnd(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> farewellService.saveFarewellTaskFlagEnd(loginItem, data));
    }

    /**
     * 告别任务家属签字
     *
     * @param data 告别业务
     * @return
     * @author LiCongLu
     * @date 2020-07-15 14:31
     */
    @ApiOperation(value = "告别任务家属签字", notes = "告别任务家属签字")
    @PostMapping(value = "/save-farewell-task-sign")
    public BaseResult<FarewellTaskServiceItem> saveFarewellTaskSign(OperationNoIdData data, @RequestParam("file") MultipartFile file) {
        return loginHandler(data, loginItem -> farewellService.saveFarewellTaskSign(loginItem, data, file));
    }
}
