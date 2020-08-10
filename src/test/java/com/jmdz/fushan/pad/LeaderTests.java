package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.LeaderService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * LeaderTests
 *
 * @author LiCongLu
 * @date 2020-07-13 11:29
 */
@SpringBootTest(classes = MainApplication.class)
public class LeaderTests {

    @Resource
    private LeaderService leaderService;

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
    @DisplayName("测试加载领导查询数据汇总")
    public void testLoadLeaderAllInfo() {
        LeaderAllData data = new LeaderAllData();
        data.setQueryDate(DateUtil.parsePattern("2020-07-21", BaseBean.yyyy_MM_dd))
                .setQueryType(0)
                .setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        BaseResult baseResult = leaderService.loadLeaderAllInfo(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
