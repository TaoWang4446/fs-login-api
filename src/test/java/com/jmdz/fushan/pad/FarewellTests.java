package com.jmdz.fushan.pad;


import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.farewell.FarewellHallData;
import com.jmdz.fushan.pad.model.farewell.FarewellTaskServiceItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.FarewellService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * FarewellTests
 *
 * @author LiCongLu
 * @date 2020-06-08 10:34
 */
@SpringBootTest(classes = MainApplication.class)
public class FarewellTests {

    @Resource
    private FarewellService farewellService;

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
    @DisplayName("测试加载告别厅列表")
    public void testLoadFarewellHallList() {
        BaseResult baseResult = farewellService.loadFarewellHallList();
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载告别任务列表")
    public void testLoadFarewellTaskList() {
        FarewellHallData data = new FarewellHallData();
        data.setHallId(15);
        BaseResult baseResult = farewellService.loadFarewellTaskList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载告别任务详情")
    public void testLoadFarewellTaskWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8174).setOperationNo("20200729004");

        BaseResult<FarewellTaskServiceItem>  baseResult = farewellService.loadFarewellTaskWithId(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试告别任务准备完成")
    public void testSaveFarewellTaskFlagReady() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8126).setOperationNo("20200721008" );

        BaseResult baseResult = farewellService.saveFarewellTaskFlagReady(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试告别任务遗体入厅接运")
    public void testSaveFarewellTaskFlagBegin() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8120).setOperationNo("20200721007" );

        BaseResult baseResult = farewellService.saveFarewellTaskFlagBegin(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试告别任务遗体出厅接运")
    public void testSaveFarewellTaskFlagEnd() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8174).setOperationNo("20200729004" );

        BaseResult baseResult = farewellService.saveFarewellTaskFlagEnd(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }


}
