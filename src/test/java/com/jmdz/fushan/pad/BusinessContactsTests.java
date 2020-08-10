package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessContactsService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * BusinessContactsTests
 *
 * @author Wangshengtao
 * @date 2020-08-04 13:22
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessContactsTests {

    @Resource
    private BusinessContactsService businessContactsService;

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
    @DisplayName("测试保存联系人信息")
    public void testSaveConactsInfo() {
        BusinessContactsInfoData data = new BusinessContactsInfoData();
        //data.setId(58111);
        data.setOperationNo("20200804113");
        data.setName("xiaoming2");
        data.setSex("男");
        data.setDierRelation("父子");
        data.setPhone("15855556666");

        data.setFuneralDirectorSex("女");
        data.setFuneralDirector("小红");
        data.setFuneralDirectorPhone("1888888888");
        data.setUserId(loginItem.getUserId()).setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessContactsService.saveBusinessContactsInfo(data,loginItem);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }


    @Test
    @DisplayName("测试加载业务洽谈联系人信息接口")
    public void testLoadBusinessContactsInfoByOperationNo() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20171213001");
        BaseResult<BusinessContactsInfo> baseResult = businessContactsService.loadBusinessContactsInfoByOperationNo(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

}
