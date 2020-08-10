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
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessProductService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * BusinessProductTests
 *
 * @author LiCongLu
 * @date 2020-08-03 16:16
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessProductTests {

    @Resource
    private BusinessProductService businessProductService;

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
    @DisplayName("测试按照类型加载殡葬用品费用列表接口")
    public void testLoadFuneralProductChargeWithType() {
        ServiceTypeData data = new ServiceTypeData();
        data.setType(BusinessServiceType.SangZangYongPin)
                .setOperationNo("20200804001");
        BaseResult<ArrayList<BusinessServiceItem>> baseResult = businessProductService.loadFuneralProductChargeWithType(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存殡葬用品等费用接口")
    public void testSaveFuneralProductService() {
        FuneralProductServiceData data = new FuneralProductServiceData();
        data.setType(BusinessServiceType.FuWuXiangMu)
                .setOperationNo("20200804001")
                .setLoginId(userId)
                .setUserId(userId);

        // 丧葬物品
        ArrayListExt<BusinessServiceData> serviceItems = new ArrayListExt<>();
        BusinessServiceData serviceData1 = new BusinessServiceData();
        serviceData1.setItemId(3067)
                .setPrice(new BigDecimal(120))
                .setNumber(new BigDecimal(1))
                .setCharge(new BigDecimal(40))
                .setRemark("抬收实体");
        serviceItems.add(serviceData1);

        BusinessServiceData serviceData2 = new BusinessServiceData();
        serviceData2.setItemId(3140)
                .setPrice(new BigDecimal(20))
                .setNumber(new BigDecimal(3))
                .setCharge(new BigDecimal(220))
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
            BaseResult baseResult = businessProductService.saveFuneralProductService(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试按照主键加载丧葬用品费用接口")
    public void testLoadFuneralProductChargeWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setOperationNo("20200804001");
        data.setId(481613);
        BaseResult<ChargeItem> baseResult = businessProductService.loadFuneralProductChargeWithId(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存丧葬用品费用接口")
    public void testSaveFuneralProductCharge() {
        FuneralProductChargeData data = new FuneralProductChargeData();
        data.setUserId(userId)
                .setLoginId(userId);

        data.setOperationNo("20200804001")
                .setId(481619)
                .setNumber(new BigDecimal(2))
                .setPrice(new BigDecimal(130))
                .setCharge(new BigDecimal(230))
                .setPreferentialCharge(new BigDecimal(10))
                .setRemark("测试更新");


        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = businessProductService.saveFuneralProductCharge(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试按照删除丧葬用品费用接口")
    public void testDeleteFuneralProductChargeWithId() {
        OperationNoIdData data = new OperationNoIdData();
        data.setOperationNo("20200804001");
        data.setId(481641);
        BaseResult baseResult = businessProductService.deleteFuneralProductChargeWithId(loginItem,data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
