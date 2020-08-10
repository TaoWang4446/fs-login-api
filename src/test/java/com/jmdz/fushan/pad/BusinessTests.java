package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.model.config.BusinessServiceType;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.business.*;
import com.jmdz.fushan.pad.service.BusinessService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * BusinessTests
 *
 * @author LiCongLu
 * @date 2020-07-31 10:17
 */
@SpringBootTest(classes = MainApplication.class)
public class BusinessTests {

    @Resource
    private BusinessService businessService;

    @Test
    @DisplayName("测试加载业务洽谈基础数据接口")
    public void testLoadBusinessDataPicker() {
        BaseResult<BusinessDataItem> baseResult = businessService.loadBusinessDataPicker();
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载子行政区划接口")
    public void testLoadAreaPickerWithPid() {
        IdData data = new IdData();
        data.setId(2721);
        BaseResult<BusinessDataItem> baseResult = businessService.loadAreaPickerWithPid(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试按照类型加载服务和物品接口")
    public void testLoadBusinessServiceListWithType() {
        ServiceTypeData data = new ServiceTypeData();
        data.setType(BusinessServiceType.ShouLing)
                .setOperationNo("20200804001");
        BaseResult<ArrayList<BusinessServiceItem>> baseResult = businessService.loadBusinessServiceListWithType(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
