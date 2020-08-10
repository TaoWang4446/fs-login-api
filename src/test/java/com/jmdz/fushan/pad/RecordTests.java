package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.RecordService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * RecordTests
 *
 * @author LiCongLu
 * @date 2020-07-09 15:17
 */
@SuppressWarnings("unchecked")
@SpringBootTest(classes = MainApplication.class)
public class RecordTests {

    @Resource
    private RecordService recordService;

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
    @DisplayName("测试加载图片列表")
    public void testLoadRecordImageList() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200709002");
        BaseResult<ArrayList<RecordImage>> baseResult = recordService.loadRecordImageList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
