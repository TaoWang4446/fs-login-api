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
import com.jmdz.fushan.pad.model.business.BusinessServiceData;
import com.jmdz.fushan.pad.model.business.BusinessServiceSuitItem;
import com.jmdz.fushan.pad.model.business.farewell.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessFarewellService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * BusinessFarewellTests
 *
 * @author LiCongLu
 * @date 2020-08-06 13:20
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessFarewellTests {

    @Resource
    private BusinessFarewellService businessFarewellService;

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
    @DisplayName("测试加载选择告别任务信息接口")
    public void testLoadFarewellMournList() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200807001");
        BaseResult<ArrayList<FarewellMournListItem>> baseResult = businessFarewellService.loadFarewellMournList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载选择告别厅信息接口")
    public void testLoadFarewellHallList() {
        FarewellHallListData data = new FarewellHallListData();
        data.setOperationNo("20200807002")
                .setBeginTime("2020-08-07 10:01");
        BaseResult<ArrayList<FarewellHallListItem>> baseResult = businessFarewellService.loadFarewellHallList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载选择告别任务信息接口")
    public void testLoadFarewellMournAllWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8198)
                .setOperationNo("20200807001");
        BaseResult<FarewellMournAllItem> baseResult = businessFarewellService.loadFarewellMournAllWithId(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试插入告别任务详情信息接口")
    public void testInsertFarewellMourn() {
        FarewellMournSaveData data = new FarewellMournSaveData();
        data.setLoginId(userId)
                .setUserId(userId);

        // 赋值数据
        data.setOperationNo("20200807003")
                .setHallId(15)
                .setBeginTime("2020-08-07 10:17")
                .setEndTime("")
                .setRemark("保存告别信息");

        // 丧葬物品
        ArrayListExt<BusinessServiceData> serviceItems = new ArrayListExt<>();
        BusinessServiceData serviceData1 = new BusinessServiceData();
        serviceData1.setItemId(3003)
                .setPrice(new BigDecimal(100))
                .setNumber(new BigDecimal(3))
                .setCharge(new BigDecimal(400))
                .setRemark("告别物品服务");
        serviceItems.add(serviceData1);

        BusinessServiceData serviceData2 = new BusinessServiceData();
        serviceData2.setItemId(2944)
                .setPrice(new BigDecimal(200))
                .setNumber(new BigDecimal(2))
                .setCharge(new BigDecimal(30))
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
            BaseResult baseResult = businessFarewellService.saveFarewellMourn(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试更新告别任务详情信息接口")
    public void testSaveFarewellMourn() {
        FarewellMournSaveData data = new FarewellMournSaveData();
        data.setLoginId(userId)
                .setUserId(userId);

        // 赋值数据
        data.setId(8203)
                .setOperationNo("20200807003")
                .setHallId(15)
                .setBeginTime("2020-08-07 10:17")
                .setEndTime("2020-08-07 12:17")
                .setRemark("更新告别信息");

        // 丧葬物品
        ArrayListExt<BusinessServiceData> serviceItems = new ArrayListExt<>();
        BusinessServiceData serviceData1 = new BusinessServiceData();
        serviceData1.setItemId(3001)
                .setPrice(new BigDecimal(580))
                .setNumber(new BigDecimal(2))
                .setCharge(new BigDecimal(400))
                .setRemark("告别物品服务");
        serviceItems.add(serviceData1);

        BusinessServiceData serviceData2 = new BusinessServiceData();
        serviceData2.setItemId(3065)
                .setPrice(new BigDecimal(20))
                .setNumber(new BigDecimal(10))
                .setCharge(new BigDecimal(200))
                .setRemark("");
        serviceItems.add(serviceData2);

        data.setServiceItems(serviceItems);

        // 删除物品和服务
        ArrayListExt<Integer> deleteIds = new ArrayListExt<>();
        deleteIds.add(481759);
        data.setDeleteIds(deleteIds);

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessFarewellService.saveFarewellMourn(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试删除告别业务信息接口")
    public void testDeleteFarewellMournWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setId(8204)
                .setOperationNo("20200807004");
        BaseResult<FarewellMournAllItem> baseResult = businessFarewellService.deleteFarewellMournWithId(loginItem, data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载告别厅套餐接口")
    public void testLoadFarewellServiceSuit() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200807004");
        BaseResult<ArrayList<BusinessServiceSuitItem>> baseResult = businessFarewellService.loadFarewellServiceSuit(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
