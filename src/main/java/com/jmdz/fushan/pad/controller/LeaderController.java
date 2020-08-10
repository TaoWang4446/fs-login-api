package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.leader.LeaderAllInfoItem;
import com.jmdz.fushan.pad.service.LeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * LeaderController
 *
 * @author LiCongLu
 * @date 2020-07-13 09:05
 */
@Api(tags = "领导查询接口", description = "领导查询接口")
@RestController()
@RequestMapping("/pad/leader")
public class LeaderController extends LoginHandler {

    @Resource
    private LeaderService leaderService;

    /**
     * 加载运营信息总览信息
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 09:09
     */
    @ApiOperation(value = "加载运营信息总览信息", notes = "加载运营信息总览信息")
    @PostMapping(value = "/load-leader-all-info")
    public BaseResult<LeaderAllInfoItem> loadLeaderAllInfo(@RequestBody LeaderAllData data) {
        return loginHandler(data, loginItem -> leaderService.loadLeaderAllInfo(data));
    }
}
