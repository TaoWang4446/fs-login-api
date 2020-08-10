package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.model.weixin.WxRecInfoExamineItem;
import com.jmdz.fushan.pad.service.WxRecService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * WxRecTests
 *
 * @author LiCongLu
 * @date 2020-07-16 15:18
 */
@SpringBootTest(classes = MainApplication.class)
public class WxRecTests {

    @Resource
    private WxRecService wxRecService;

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
    }

    @Test
    @DisplayName("测试加载未审核接运任务")
    public void testLoadWxRecInfoExamine() {
        BaseResult<ArrayList<WxRecInfoExamineItem>> baseResult = wxRecService.loadWxRecInfoExamine();
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存微信接运预约审核通过")
    public void testSaveWxRecInfoExaminePass() {
        IdData data = new IdData();
        data.setId(88)
                .setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        BaseResult baseResult = wxRecService.saveWxRecInfoExaminePass(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存微信接运预约审核不通过")
    @Transactional(rollbackFor = Exception.class)
    public void testSaveWxRecInfoExamineFail() {
        IdData data = new IdData();
        data.setId(2)
                .setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        BaseResult baseResult = wxRecService.saveWxRecInfoExamineFail(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
