package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessDeadService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * BusinessDeadTests
 *
 * @author LiCongLu
 * @date 2020-07-28 15:22
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessDeadTests {

    @Resource
    private BusinessDeadService businessDeadService;

    @Resource
    private HandlerWrapper handlerWrapper;

    @Resource
    private UserDao userDao;

    private String userId;
    private LoginItem loginItem;

    @BeforeEach
    public void beforeEach() {
        userId = "3cd1cd06-3d77-4efb-a78d-ad9a9cea3d80";
        LoginUserItem loginUserItem = userDao.getLoginUserByUserId(userId);
        loginItem = BeanUtil.copy2Bean(loginUserItem, new LoginItem());
        loginItem.setLoginId(userId);
    }


    @Test
    @DisplayName("测试业务洽谈逝者列表加载接口")
    public void testLoadBusinessDeadList() {
        BusinessDeadData data = new BusinessDeadData();
        data.setKeyword("20200723002");
        BaseResult<ArrayList<BusinessDeadListItem>> baseResult = businessDeadService.loadBusinessDeadList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载业务洽谈逝者详情接口")
    public void testLoadBusinessDeadAllWithOperationNo() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200723002");
        BaseResult<BusinessDeadAllItem> baseResult = businessDeadService.loadBusinessDeadAllWithOperationNo(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载业务洽谈逝者信息接口")
    public void testLoadBusinessDeadInfoByOperationNo() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20171218011");
        BaseResult<BusinessDeadInfo> baseResult = businessDeadService.loadBusinessDeadInfoByOperationNo(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试更新逝者信息")
    public void testUpdateDeadInfo() {
        BusinessDeadInfoData data = new BusinessDeadInfoData();
        data.setDierName("xiaoming2");
        data.setDierSex("男");
        data.setBirthDay("1949-07-02");
        data.setDeathCause("交通事故");
        data.setOperationNo("20200803001");
        data.setQiaTanYuan("022");
        data.setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessDeadService.saveBusinessDeadInfo(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

}
