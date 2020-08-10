package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.model.config.TaskClientType;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.model.spirit.SendSpiritListItem;
import com.jmdz.fushan.pad.model.task.TaskNoticeData;
import com.jmdz.fushan.pad.model.task.TaskNoticeVo;
import com.jmdz.fushan.pad.service.TaskService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * TaskTests
 *
 * @author LiCongLu
 * @date 2020-07-29 14:46
 */
@SpringBootTest(classes = MainApplication.class)
public class TaskTests {

    @Resource
    private TaskService taskService;

    @Resource
    private UserDao userDao;

    private String userId;
    private LoginItem loginItem;

    @BeforeEach
    public void beforeEach() {
        userId = "3cd1cd06-3d77-4efb-a78d-ad9a9cea3d80";
        LoginUserItem loginUserItem = userDao.getLoginUserByUserId(userId);
        loginItem = BeanUtil.copy2Bean(loginUserItem, new LoginItem())
        .setLoginId(userId);
    }

    @Test
    @DisplayName("测试加载任务提醒信息接口")
    public void testLoadTaskNotice() {
        TaskNoticeData data = new TaskNoticeData();
        data.setClientType(TaskClientType.HALL);
        BaseResult<ArrayList<TaskNoticeVo>> baseResult = taskService.loadTaskNotice(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
