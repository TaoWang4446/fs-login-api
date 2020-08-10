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
import com.jmdz.fushan.pad.model.weixin.WxMournExamineItem;
import com.jmdz.fushan.pad.model.weixin.WxMournExamineItem;
import com.jmdz.fushan.pad.service.WxMournService;
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
 * WxMournTests
 *
 * @author LiCongLu
 * @date 2020-07-21 11:46
 */
@SpringBootTest(classes = MainApplication.class)
public class WxMournTests {

    @Resource
    private WxMournService wxMournService;

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
    @DisplayName("测试加载未审核告别任务")
    public void testLoadWxMournExamine() {
        BaseResult<ArrayList<WxMournExamineItem>> baseResult = wxMournService.loadWxMournExamine();
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存微信告别预约审核通过")
    public void testSaveWxMournExaminePass() {
        IdData data = new IdData();
        data.setId(190)
                .setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        BaseResult baseResult = wxMournService.saveWxMournExaminePass(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存微信告别预约审核不通过")
    public void testSaveWxMournExamineFail() {
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

        BaseResult baseResult = wxMournService.saveWxMournExamineFail(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
