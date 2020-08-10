package com.jmdz.fushan.pad;


import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.HallService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * HallTests
 *
 * @author LiCongLu
 * @date 2020-07-15 17:03
 */
@SpringBootTest(classes = MainApplication.class)
public class HallTests {

    @Resource
    private HallService hallService;

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
    @DisplayName("测试加载出入厅告别任务提醒列表")
    public void testLoadFarewellDeadRemindList() {
        BaseResult baseResult = hallService.loadFarewellDeadRemindList(loginItem);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

}
