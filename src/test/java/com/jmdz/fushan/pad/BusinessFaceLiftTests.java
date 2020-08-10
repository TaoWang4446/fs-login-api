package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessFaceLiftService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * BusinessFaceLiftTests
 *
 * @author LiCongLu
 * @date 2020-08-04 15:12
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessFaceLiftTests {

    @Resource
    private BusinessFaceLiftService businessFaceLiftService;

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
        loginItem = BeanUtil.copy2Bean(loginUserItem, new LoginItem())
                .setLoginId(userId);
    }

    @Test
    @DisplayName("测试加载整容服务项目费用列表接口")
    public void testLoadFaceLiftChargeList() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200804001");
        BaseResult<ArrayList<FaceLiftChargeListItem>> baseResult = businessFaceLiftService.loadFaceLiftChargeList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存整容项目接口")
    public void testSaveFaceLiftService() {
        FaceLiftServiceData data = new FaceLiftServiceData();
        data.setOperationNo("20200804001")
                .setLoginId(userId)
                .setUserId(userId);

        // 整容项目
        ArrayListExt<BusinessServiceData> serviceItems = new ArrayListExt<>();
        BusinessServiceData serviceData1 = new BusinessServiceData();
        serviceData1.setItemId(3340)
                .setPrice(new BigDecimal(210))
                .setNumber(new BigDecimal(2))
                .setCharge(new BigDecimal(10))
                .setRemark("解刨服务");
        serviceItems.add(serviceData1);

        BusinessServiceData serviceData2 = new BusinessServiceData();
        serviceData2.setItemId(3011)
                .setPrice(new BigDecimal(100))
                .setNumber(new BigDecimal(3))
                .setCharge(new BigDecimal(000))
                .setRemark("遗体探视");
        serviceItems.add(serviceData2);

        data.setServiceItems(serviceItems);

        data.setExcuteTime("2020-08-20 09:20");

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessFaceLiftService.saveFaceLiftService(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试按照费用主键加载整容服务项目费用接口")
    public void testLoadFaceLiftChargeWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(481635)
                .setOperationNo("20200804001");
        BaseResult<FaceLiftChargeListItem> baseResult = businessFaceLiftService.loadFaceLiftChargeWithId(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存整容费用接口")
    public void testSaveFaceLiftCharge() {
        FaceLiftChargeData data = new FaceLiftChargeData();
        data.setUserId(userId)
                .setLoginId(userId);

        data.setOperationNo("20200804001")
                .setId(481644)
                .setNumber(new BigDecimal(3))
                .setPrice(new BigDecimal(100))
                .setCharge(new BigDecimal(200))
                .setExcuteTime("2020-08-05 10:20")
                .setRemark("更新整容费用");


        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessFaceLiftService.saveFaceLiftCharge(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试按照删除整容费用接口")
    public void testDeleteFaceLiftChargeWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setOperationNo("20200804001");
        data.setId(481646);
        BaseResult baseResult = businessFaceLiftService.deleteFaceLiftChargeWithId(loginItem,data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
