package com.jmdz.fushan.pad;

import com.jmdz.common.base.BaseBean;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.MainApplication;
import com.jmdz.fushan.pad.model.dead.DeadAllData;
import com.jmdz.fushan.pad.model.dead.DeadAllListItem;
import com.jmdz.fushan.pad.model.dead.DeadDetailsAllData;
import com.jmdz.fushan.pad.model.dead.DeadDetailsAllItem;
import com.jmdz.fushan.pad.service.DeadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * DeadTests
 *
 * @author LiCongLu
 * @date 2020-07-10 14:20
 */
@SpringBootTest(classes = MainApplication.class)
public class DeadTests {

    @Resource
    private DeadService deadService;

    @Test
    @DisplayName("测试加载逝者任务列表")
    public void testLoadDeadAllList() {
        DeadAllData data = new DeadAllData();
        data.setQueryDate(DateUtil.parsePattern("2020-07-31", BaseBean.yyyy_MM_dd));
        BaseResult<ArrayList<DeadAllListItem>> baseResult = deadService.loadDeadAllList(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }

    @Test
    @DisplayName("测试加载逝者任务列表详细信息")
    void testLoadDeadDetailsAll()   {
        DeadDetailsAllData data = new DeadDetailsAllData();
        data.setOperationNo("20200807001");
        BaseResult<DeadDetailsAllItem> deadDetailsAllData = deadService.loadDeadDetailsAll(data);
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(deadDetailsAllData));

    }
}
