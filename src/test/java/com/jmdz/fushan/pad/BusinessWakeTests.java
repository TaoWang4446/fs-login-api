package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.model.config.BusinessServiceType;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.business.wake.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessFaceLiftService;
import com.jmdz.fushan.pad.service.BusinessWakeService;
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
 * BusinessWakeTests
 *
 * @author LiCongLu
 * @date 2020-08-05 13:20
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessWakeTests {

    @Resource
    private BusinessWakeService businessWakeService;

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
    @DisplayName("测试加载选择守灵任务信息接口")
    public void testLoadWakeMournList() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200804001");
        BaseResult<ArrayList<WakeMournListItem>> baseResult = businessWakeService.loadWakeMournList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载选择守灵厅信息接口")
    public void testLoadWakeHallList() {
        WakeHallListData data = new WakeHallListData();
        data.setOperationNo("20200806002")
                .setBeginTime("2020-08-06 14:27");
        BaseResult<ArrayList<WakeHallListItem>> baseResult = businessWakeService.loadWakeHallList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试计算守灵费用金额")
    public void testResetWakeCharge() {
        ChargeItem chargeItem = new ChargeItem();
        chargeItem.setPrice(new BigDecimal(50.00));
        String beginTime = "2020-08-06 09:23";
        String endTime = "2020-08-08 19:52";
        businessWakeService.resetWakeCharge(beginTime, endTime, chargeItem);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(chargeItem));
    }

    @Test
    @DisplayName("测试加载选择守灵任务信息接口")
    public void testLoadWakeMournAllWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8194)
                .setOperationNo("20200806002");
        BaseResult<WakeMournAllItem> baseResult = businessWakeService.loadWakeMournAllWithId(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试插入守灵任务详情信息接口")
    public void testInsertWakeMourn() {
        WakeMournSaveData data = new WakeMournSaveData();
        data.setOperationNo("20200806002")
                .setLoginId(userId)
                .setUserId(userId);

        // 赋值数据
        data.setOperationNo("20200806002")
                .setHallId(26)
                .setBeginTime("2020-08-06 14:27")
                .setEndTime("2020-08-07 20:32")
                .setRemark("测试插入守灵信息");

        // 丧葬物品
        ArrayListExt<BusinessServiceData> serviceItems = new ArrayListExt<>();
        BusinessServiceData serviceData1 = new BusinessServiceData();
        serviceData1.setItemId(2723)
                .setPrice(new BigDecimal(15))
                .setNumber(new BigDecimal(11))
                .setCharge(new BigDecimal(400))
                .setRemark("守灵物品服务");
        serviceItems.add(serviceData1);

        BusinessServiceData serviceData2 = new BusinessServiceData();
        serviceData2.setItemId(2944)
                .setPrice(new BigDecimal(300))
                .setNumber(new BigDecimal(2))
                .setCharge(new BigDecimal(200))
                .setRemark("");
        serviceItems.add(serviceData2);

        data.setServiceItems(serviceItems);

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessWakeService.saveWakeMourn(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试更新守灵任务详情信息接口")
    @Transactional(rollbackFor = Exception.class)
    public void testSaveWakeMourn() {
        WakeMournSaveData data = new WakeMournSaveData();
        data.setOperationNo("20200806002")
                .setLoginId(userId)
                .setUserId(userId);

        // 赋值数据
        data.setId(8194)
                .setOperationNo("20200806002")
                .setHallId(26)
                .setBeginTime("2020-08-06 14:27")
                .setEndTime("2020-08-08 20:32")
                .setRemark("测试插入守灵信息");

        // 丧葬物品
        ArrayListExt<BusinessServiceData> serviceItems = new ArrayListExt<>();
        BusinessServiceData serviceData1 = new BusinessServiceData();
        serviceData1.setItemId(3028)
                .setPrice(new BigDecimal(3680))
                .setNumber(new BigDecimal(1))
                .setCharge(new BigDecimal(400))
                .setRemark("守灵物品服务");
        serviceItems.add(serviceData1);

        BusinessServiceData serviceData2 = new BusinessServiceData();
        serviceData2.setItemId(3030)
                .setPrice(new BigDecimal(4980))
                .setNumber(new BigDecimal(1))
                .setCharge(new BigDecimal(200))
                .setRemark("");
        serviceItems.add(serviceData2);

        data.setServiceItems(serviceItems);

        // 删除物品和服务
        ArrayListExt<Integer> deleteIds = new ArrayListExt<>();
        //deleteIds.add(481678);
        data.setDeleteIds(deleteIds);

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessWakeService.saveWakeMourn(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试删除守灵业务信息接口")
    public void testDeleteWakeMournWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8205)
                .setOperationNo("20200807004");
        BaseResult<WakeMournAllItem> baseResult = businessWakeService.deleteWakeMournWithId(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

}
