package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseData;
import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.base.BaseController;
import com.jmdz.fushan.pad.model.login.LoginData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.task.TaskNoticeData;
import com.jmdz.fushan.pad.model.task.TaskNoticeVo;
import com.jmdz.fushan.pad.service.PadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 公用接口
 *
 * @author LiCongLu
 * @date 2020-06-04 16:31
 */
@Api(tags = "公用接口", description = "公用接口")
@RestController()
@RequestMapping("/pad")
public class PadController extends BaseController {

    @Resource
    private PadService padService;

    /**
     * 登录接口
     *
     * @param data 登录账号
     * @return
     * @author LiCongLu
     * @date 2020-06-05 13:24
     */
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @PostMapping(value = "/login")
    public BaseResult<LoginItem> login(@RequestBody LoginData data) {
        return dataHandler(data, () -> padService.login(data));
    }

    /**
     * 注册接口
     *
     * @param data 注册数据
     * @return
     * @author LiCongLu
     * @date 2020-07-08 17:22
     */
    @ApiOperation(value = "注册接口", notes = "注册pad接口")
    @PostMapping(value = "/save-device-imei")
    public BaseResult saveDeviceImei(@RequestBody BaseData data) {
        return padService.saveDeviceImei(data);
    }
}
