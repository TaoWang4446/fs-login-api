package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.dead.DeadAllData;
import com.jmdz.fushan.pad.model.dead.DeadAllListItem;
import com.jmdz.fushan.pad.model.dead.DeadDetailsAllData;
import com.jmdz.fushan.pad.model.dead.DeadDetailsAllItem;
import com.jmdz.fushan.pad.service.DeadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 逝者任务接口
 *
 * @author LiCongLu
 * @date 2020-07-10 13:51
 */
@Api(tags = "逝者任务接口", description = "逝者任务接口")
@RestController()
@RequestMapping("/pad/dead")
public class DeadController extends LoginHandler {

    @Resource
    private DeadService deadService;

    /**
     * 加载逝者任务列表
     *
     * @param data 查询日期
     * @return
     * @author LiCongLu
     * @date 2020-07-10 13:54
     */
    @ApiOperation(value = "加载逝者任务列表", notes = "加载逝者任务列表")
    @PostMapping(value = "/load-dead-all-list")
    public BaseResult<ArrayList<DeadAllListItem>> loadDeadAllList(@RequestBody DeadAllData data) {
        return loginHandler(data, loginItem -> deadService.loadDeadAllList(data));
    }

    /**
     * 逝者任务详情信息
     *
     * @param data 业务编码
     * @return
     * @author Sunzhaoqi
     * @date 2020-07-13 09:26
     */
    @ApiOperation(value = "加载逝者任务详情信息", notes = "加载逝者任务详情信息")
    @PostMapping(value = "/load-dead-details-all")
    public BaseResult<DeadDetailsAllItem> loadDeadDetailsAll(@RequestBody DeadDetailsAllData data) {
        return loginHandler(data, loginItem -> deadService.loadDeadDetailsAll(data));
    }
}
