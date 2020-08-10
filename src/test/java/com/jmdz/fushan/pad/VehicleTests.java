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
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.model.vehicle.*;
import com.jmdz.fushan.pad.service.VehicleService;
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
 * VehicleTests
 *
 * @author LiCongLu
 * @date 2020-07-09 11:18
 */
@SpringBootTest(classes = MainApplication.class)
public class VehicleTests {

    @Resource
    private VehicleService vehicleService;

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
    @DisplayName("测试加载接运任务信息")
    public void testLoadVehicleTaskList() {
        BaseResult<ArrayList<VehicleTaskListItem>> baseResult = vehicleService.loadVehicleTaskList(loginItem);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存定位信息")
    public void testSaveLocation() {
        LocationData data = new LocationData();
        data.setLongitude("117.127414")
                .setLatitude("36.689572")
                .setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = vehicleService.saveLocation(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试保存出车信息")
    public void testSaveVehicleOut() {
        VehicleOutData data = new VehicleOutData();
        data.setOperationNo("20200720011")
                .setVehicleId("727600be-def8-48e8-a04c-6a0e143c70a4");
        data.setVehicleCarId(19)
                .setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = vehicleService.saveVehicleOut(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试保存回车信息")
    public void testSaveVehicleBack() {
        VehicleIdData data = new VehicleIdData();
        data.setOperationNo("20200709002")
                .setVehicleId("6aa7267e-4233-4444-845b-d666707ce283");
        data.setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = vehicleService.saveVehicleBack(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试加载服务物品费用信息")
    public void testLoadVehicleChargeList() {
        VehicleIdData data = new VehicleIdData();
        data.setOperationNo("20200709002")
                .setVehicleId("6aa7267e-4233-4444-845b-d666707ce283");
        data.setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        BaseResult<ArrayList<ChargeItem>> baseResult = vehicleService.loadVehicleChargeList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
    @Test
    @DisplayName("测试添加物品服务")
    public void testSaveVehicleCharge() {
        VehicleChargeData data = new VehicleChargeData();
        data.setOperationNo("20200709002")
                .setVehicleId("6aa7267e-4233-4444-845b-d666707ce283");
        data.setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        BaseResult<ArrayList<ChargeItem>> chargeResult = vehicleService.loadVehicleChargeList(data);

        ArrayList<ChargeItem> chargeItems = chargeResult.getData();

        // 调整价格
        ChargeItem chargeItem = chargeItems.get(0);
        chargeItem.setNumber(new BigDecimal(11))
                .setCharge(chargeItem.getNumber().multiply(chargeItem.getPrice()));
        chargeItem = chargeItems.get(1);
        chargeItem.setNumber(new BigDecimal(0))
                .setCharge(chargeItem.getNumber().multiply(chargeItem.getPrice()));
        data.setChargeItems(chargeItems);


        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = vehicleService.saveVehicleCharge(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试加载二维码图片信息")
    public void testLoadQrCode() {
        OperationNoData data = new OperationNoData();
        data.setOperationNo("20200709002");
        BaseResult baseResult = vehicleService.loadQrCode(loginItem, data);
        LogUtil.line("加载结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试接运字典信息")
    public void testLoadVehicleDataPicker() {
        BaseResult baseResult = vehicleService.loadVehicleDataPicker();
        LogUtil.line("加载结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试添加车辆接运信息")
    public void testSaveVehicleTask() {
        VehicleTaskData data = new VehicleTaskData();
        data.setUserId(loginItem.getUserId())
                .setLoginId(loginItem.getUserId());

        // 请求数据
        data.setDeadName("赵孙002")
                .setDeadAge("68")
                .setDeadSex("女")
                .setRelationName("孙里002")
                .setRelationPhone("15047856356")
                .setDeadRelation("父女")
                .setVehicleTypeId(2388)
                .setCarryTime("2020-08-10 16:58")
                .setCarryPlace("济南市历下区河区8号楼706室")
                .setCheLiangYongTu("91eca613-edca-47ae-b276-87c461226dd6");

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        try {
            BaseResult baseResult = vehicleService.saveVehicleTask(loginItem, data);
            LogUtil.line("执行结果：" + JacksonUtil.obj2Json(baseResult));
        } catch (ActionException e) {
            LogUtil.line("执行异常：" + e.getMessage());
        }
    }
}
