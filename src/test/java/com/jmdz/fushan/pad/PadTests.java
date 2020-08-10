package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseData;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.pad.model.login.LoginData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.service.PadService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * PadTests
 *
 * @author LiCongLu
 * @date 2020-06-05 15:28
 */
@SuppressWarnings("unchecked")
@SpringBootTest(classes = MainApplication.class)
public class PadTests {
    @Resource
    private HandlerWrapper handlerWrapper;

    @Resource
    private PadService padService;

    private String operationNo;

    @BeforeEach
    public void beforeEach() {
        operationNo = "20200603001";
    }

    @Test
    @DisplayName("测试登录")
    public void testLogin() {
        LoginData data = new LoginData();
        data.setAccount("admin")
                .setPassword("0000");

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        BaseResult<LoginItem> baseResult = padService.login(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试保存设备信息")
    public void testSaveDeviceImei() {
        BaseData data = new BaseData();
        data.setData("33ddd");

        // 验证请求数据
        BaseResult result = handlerWrapper.validateBean(data);
        if (result != null) {
            LogUtil.line("验证结果：" + JacksonUtil.obj2Json(result));
            return;
        }

        BaseResult<LoginItem> baseResult = padService.saveDeviceImei(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
