package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.weixin.WxMournExamineItem;
import com.jmdz.fushan.pad.model.weixin.WxMournExamineItem;
import com.jmdz.fushan.pad.service.WxMournService;
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
 * WxMournController
 *
 * @author LiCongLu
 * @date 2020-07-21 10:11
 */
@Api(tags = "业务洽谈微信告别预约接口", description = "业务洽谈微信告别预约接口")
@RestController()
@RequestMapping("/pad/wx/mourn")
public class WxMournController extends LoginHandler {

    @Resource
    private WxMournService wxMournService;

    /**
     * 加载微信告别预约审核列表
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-21 10:14
     */
    @ApiOperation(value = "加载微信告别预约审核列表", notes = "加载微信告别预约审核列表，只加载未审核数据")
    @PostMapping(value = "/load-wx-mourn-examine")
    public BaseResult<ArrayList<WxMournExamineItem>> loadWxMournExamine(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> wxMournService.loadWxMournExamine());
    }

    /**
     * 保存审核告别预约信息通过
     *
     * @param data 告别主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-21 13:56
     */
    @ApiOperation(value = "保存审核告别预约信息通过", notes = "保存审核告别预约信息通过")
    @PostMapping(value = "/save-wx-mourn-examine-pass")
    public BaseResult<ArrayList<WxMournExamineItem>> saveWxMournExaminePass(@RequestBody IdData data) {
        return loginHandler(data, loginItem -> wxMournService.saveWxMournExaminePass(loginItem, data));
    }

    /**
     * 保存审核告别预约信息不通过
     *
     * @param data 告别主键信息
     * @return
     * @author LiCongLu
     * @date 2020-07-21 13:56
     */
    @ApiOperation(value = "保存审核告别预约信息不通过", notes = "保存审核告别预约信息不通过")
    @PostMapping(value = "/save-wx-mourn-examine-fail")
    public BaseResult<ArrayList<WxMournExamineItem>> saveWxMournExamineFail(@RequestBody IdData data) {
        return loginHandler(data, loginItem -> wxMournService.saveWxMournExamineFail(loginItem, data));
    }
}
