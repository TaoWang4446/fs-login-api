package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseData;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.login.LoginData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.model.spirit.SendSpiritListItem;
import com.jmdz.fushan.pad.service.PadService;
import com.jmdz.fushan.pad.service.SendSpiritService;
import com.jmdz.fushan.pad.service.WxMournService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * SendSpiritTests
 *
 * @author LiCongLu
 * @date 2020-07-23 08:42
 */
@SpringBootTest(classes = MainApplication.class)
public class SendSpiritTests {

    @Resource
    private SendSpiritService sendSpiritService;

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
    @DisplayName("测试加载送灵任务接口")
    public void testLoadSendSpiritList() {
        BaseResult<ArrayList<SendSpiritListItem>> baseResult = sendSpiritService.loadSendSpiritList();
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试送灵任务确认")
    public void testSaveConfirmSendSpirit() {
        IdData data = new IdData();
        data.setId(21387);
        BaseResult<ArrayList<SendSpiritListItem>> baseResult = sendSpiritService.saveConfirmSendSpirit(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
