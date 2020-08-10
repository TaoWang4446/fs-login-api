package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.spirit.SendSpiritListItem;
import com.jmdz.fushan.pad.model.weixin.WxMournExamineItem;
import com.jmdz.fushan.pad.service.SendSpiritService;
import com.jmdz.fushan.pad.service.WxMournService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 业务洽谈送灵任务接口
 *
 * @author LiCongLu
 * @date 2020-07-22 16:34
 */
@Api(tags = "业务洽谈送灵任务接口", description = "业务洽谈送灵任务接口")
@RestController()
@RequestMapping("/pad/send-spirit")
public class SendSpiritController extends LoginHandler {

    @Resource
    private SendSpiritService sendSpiritService;

    /**
     * 加载送灵任务列表接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-22 16:38
     */
    @ApiOperation(value = "加载送灵任务列表接口", notes = "加载送灵任务列表接口，只加载当天任务")
    @PostMapping(value = "/load-send-spirit-list")
    public BaseResult<ArrayList<SendSpiritListItem>> loadSendSpiritList(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> sendSpiritService.loadSendSpiritList());
    }

    /**
     * 保存确认送灵任务接口
     *
     * @param data 主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-23 09:46
     */
    @ApiOperation(value = "保存确认送灵任务接口", notes = "保存确认送灵任务接口")
    @PostMapping(value = "/save-confirm-send-spirit")
    public BaseResult<ArrayList<SendSpiritListItem>> saveConfirmSendSpirit(@RequestBody IdData data) {
        return loginHandler(data, loginItem -> sendSpiritService.saveConfirmSendSpirit(loginItem,data));
    }
}
