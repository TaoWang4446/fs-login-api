package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.business.wake.WakeMournSaveData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.model.service.ServiceSuitData;
import com.jmdz.fushan.pad.model.service.ServiceSuitItem;
import com.jmdz.fushan.pad.service.BusinessDeadService;
import com.jmdz.fushan.pad.service.BusinessSuitService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * BusinessSuitTests
 *
 * @author LiCongLu
 * @date 2020-08-10 09:52
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessSuitTests {

    @Resource
    private BusinessSuitService businessSuitService;

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
    @DisplayName("测试业务洽谈套餐加载接口")
    public void testLoadBusinessServiceSuit() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200810001");
        BaseResult<ArrayList<ServiceSuitItem>> baseResult = businessSuitService.loadBusinessServiceSuit(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试业务洽谈添加服务套餐接口")
    public void testSaveBusinessServiceSuit() {
        ServiceSuitData data = new ServiceSuitData();
        data.setOperationNo("20200810001")
                .setSuitId("97f9818b-228c-4fa6-8647-38b86bdfc8a1")
                .setLoginId(userId)
                .setUserId(userId);

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessSuitService.saveBusinessServiceSuit(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试删除业务洽谈服务套餐接口")
    public void testDeleteBusinessServiceSuit() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200810001")
                .setLoginId(userId)
                .setUserId(userId);

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessSuitService.deleteBusinessServiceSuit(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }
}
