package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.DeadBasicData;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessBasicService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * BusinessBasicTests
 *
 * @author LiCongLu
 * @date 2020-07-09 13:20
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessBasicTests {

    @Resource
    private BusinessBasicService businessBasicService;

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
    @DisplayName("测试加载逝者基本信息")
    public void testLoadDeadBasicItem() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200709002");
        BaseResult<DeadBasicItem> baseResult = businessBasicService.loadDeadBasicItem(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存逝者基本信息")
    public void testSaveDeadBasicItem() {
        OperationNoData operationNoData = new OperationNoData();
        operationNoData.setOperationNo("20200709002");
        BaseResult<DeadBasicItem> dataResult = businessBasicService.loadDeadBasicItem(operationNoData);

        DeadBasicItem basicItem = dataResult.getData();
        DeadBasicData data = BeanUtil.copy2Bean(basicItem, new DeadBasicData());

        data.setDeadName("第一天下")
                .setDeathTime("2020-07-07")
                .setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessBasicService.saveDeadBasicItemFile(loginItem, data, null);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }
}
