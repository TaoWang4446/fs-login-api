package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.farewell.FarewellDeadRemindListItem;
import com.jmdz.fushan.pad.model.task.TaskNoticeData;
import com.jmdz.fushan.pad.model.task.TaskNoticeVo;
import com.jmdz.fushan.pad.service.HallService;
import com.jmdz.fushan.pad.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * TaskController
 *
 * @author LiCongLu
 * @date 2020-07-29 13:56
 */
@Api(tags = "任务提醒相关接口", description = "任务提醒相关接口")
@RestController()
@RequestMapping("/pad/task")
public class TaskController extends LoginHandler {

    @Resource
    private TaskService taskService;

    /**
     * 加载提醒信息接口
     *
     * @param data 加载类型
     * @return
     * @author LiCongLu
     * @date 2020-07-29 13:02
     */
    @ApiOperation(value = "加载提醒信息接口", notes = "加载提醒信息接口")
    @PostMapping(value = "/load-task-notice")
    public BaseResult<ArrayList<TaskNoticeVo>> loadTaskNotice(@RequestBody TaskNoticeData data) {
        return loginHandler(data, loginItem -> taskService.loadTaskNotice(loginItem, data));
    }
}
