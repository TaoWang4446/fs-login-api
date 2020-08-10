package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.farewell.FarewellDeadRemindListItem;
import com.jmdz.fushan.pad.service.HallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * HallController
 *
 * @author LiCongLu
 * @date 2020-07-15 15:20
 */
@Api(tags = "告别出入厅任务接口", description = "告别出入厅任务接口")
@RestController()
@RequestMapping("/pad/hall")
public class HallController extends LoginHandler {

    @Resource
    private HallService hallService;

    /**
     * 加载出入厅告别任务提醒列表
     *
     * @param data 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-15 15:24
     */
    @ApiOperation(value = "加载出入厅告别任务提醒列表", notes = "加载出入厅告别任务提醒列表，即出入厅遗体接运")
    @PostMapping(value = "/load-farewell-dead-remind-list")
    public BaseResult<ArrayList<FarewellDeadRemindListItem>> loadFarewellDeadRemindList(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> hallService.loadFarewellDeadRemindList(loginItem));
    }
}
