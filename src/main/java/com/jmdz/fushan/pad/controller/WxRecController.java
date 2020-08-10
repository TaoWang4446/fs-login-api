package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.weixin.WxRecInfoExamineItem;
import com.jmdz.fushan.pad.service.WxRecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * WxRecController
 *
 * @author LiCongLu
 * @date 2020-07-21 10:11
 */
@Api(tags = "业务洽谈微信接运预约接口", description = "业务洽谈微信接运预约接口")
@RestController()
@RequestMapping("/pad/wx/rec")
public class WxRecController extends LoginHandler {

    @Resource
    private WxRecService wxRecService;

    /**
     * 加载微信接运预约审核列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:12
     */
    @ApiOperation(value = "加载微信接运预约审核列表", notes = "加载微信接运预约审核列表，只加载未审核数据")
    @PostMapping(value = "/load-wx-rec-info-examine")
    public BaseResult<ArrayList<WxRecInfoExamineItem>> loadWxRecInfoExamine(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> wxRecService.loadWxRecInfoExamine());
    }

    /**
     * 保存审核接运预约信息通过
     *
     * @param data 接运主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:37
     */
    @ApiOperation(value = "保存审核接运预约信息通过", notes = "保存审核接运预约信息通过")
    @PostMapping(value = "/save-wx-rec-info-examine-pass")
    public BaseResult<ArrayList<WxRecInfoExamineItem>> saveWxRecInfoExaminePass(@RequestBody IdData data) {
        return loginHandler(data, loginItem -> wxRecService.saveWxRecInfoExaminePass(loginItem, data));
    }

    /**
     * 保存审核接运预约信息不通过
     *
     * @param data 接运主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-16 15:37
     */
    @ApiOperation(value = "保存审核接运预约信息不通过", notes = "保存审核接运预约信息不通过")
    @PostMapping(value = "/save-wx-rec-info-examine-fail")
    public BaseResult<ArrayList<WxRecInfoExamineItem>> saveWxRecInfoExamineFail(@RequestBody IdData data) {
        return loginHandler(data, loginItem -> wxRecService.saveWxRecInfoExamineFail(loginItem, data));
    }
}
