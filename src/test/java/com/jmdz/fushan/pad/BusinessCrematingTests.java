package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessCrematingService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * BusinessContactsTests
 *
 * @author Wangshengtao
 * @date 2020-08-06 11:22
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessCrematingTests {

    @Resource
    private BusinessCrematingService businessCrematingService;

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
    @DisplayName("测试保存火化任务详细信息")
    public void testSaveConactsInfo() {
        BusinessCrematingData data  = new BusinessCrematingData();
        BusinessCrematingInfoData businessCrematingInfoData = new BusinessCrematingInfoData();

        //data.setId(58111);
        businessCrematingInfoData.setOperationNo("20200806112");
        businessCrematingInfoData.setCrematingMachineTypeID(780);
        businessCrematingInfoData.setAshesManageMode("带走");
        businessCrematingInfoData.setIsBespeak(1);
        businessCrematingInfoData.setCrematingPrice(new BigDecimal(590.00));
        businessCrematingInfoData.setCharge(new BigDecimal(600.00));

        ChargeItem chargeItem = new ChargeItem();
        data.setBusinessCrematingInfoData(businessCrematingInfoData);
        data.setChargeItem(chargeItem);
        data.setUserId(loginItem.getUserId()).setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessCrematingService.saveBusinessCrematingInfo(data,loginItem);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }


    @Test
    @DisplayName("测试加载业务洽谈火化任务 详情 接口")
    public void testLoadBusinessCrematingInfoByOperationNo() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20171213001");
        BaseResult<BusinessCrematingInfo> baseResult = businessCrematingService.loadBusinessCrematingInfoByOperationNo(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试删除业务洽谈火化任务记录 接口")
    public void testDeleteBusinessCrematingInfoByOperationNo() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200806112");
        BaseResult baseResult = businessCrematingService.deleteBusinessCrematingRowWithOperationNo(loginItem,data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

}
