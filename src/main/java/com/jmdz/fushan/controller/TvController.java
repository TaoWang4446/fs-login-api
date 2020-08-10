package com.jmdz.fushan.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.model.tv.ColdFaceTvItem;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.business.BusinessDataItem;
import com.jmdz.fushan.pad.model.business.BusinessServiceItem;
import com.jmdz.fushan.pad.model.business.PickerItem;
import com.jmdz.fushan.pad.model.business.ServiceTypeData;
import com.jmdz.fushan.pad.service.BusinessService;
import com.jmdz.fushan.service.TvService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * TvController
 *
 * @author LiCongLu
 * @date 2020-08-07 14:47
 */
@Api(tags = "电视相关接口", description = "电视相关接口")
@RestController()
@RequestMapping("/tv")
public class TvController extends LoginHandler {

    @Resource
    private ConfigData configData;

    @Resource
    private TvService tvService;

    /**
     * 公用验证token
     *
     * @return
     */
    private boolean checkAppToken() {
        // 验证token
        String token = getToken();
        if (DataUtil.valid(configData.getAppToken())) {
            if (!DataUtil.equals(configData.getAppToken(), token)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 加载冷藏整容车间电视信息接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-07 14:55
     */
    @ApiOperation(value = "加载冷藏整容车间电视信息接口", notes = "加载冷藏整容车间电视信息接口,包含出藏/出厅任务、整容信息等数据")
    @PostMapping(value = "/load-cold-face-tv")
    public BaseResult<ColdFaceTvItem> loadColdFaceTv() {
        if (!checkAppToken()) {
            return BaseService.failure("token错误");
        }
        return tvService.loadColdFaceTv();
    }
}
