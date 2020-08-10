package com.jmdz.fushan;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.LogUtil;
import com.jmdz.common.util.third.JacksonUtil;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.model.tv.ColdFaceTvItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.business.DeadBasicData;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import com.jmdz.fushan.pad.service.BusinessBasicService;
import com.jmdz.fushan.service.TvService;
import com.jmdz.fushan.wrapper.HandlerWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * TvTests
 *
 * @author LiCongLu
 * @date 2020-08-07 14:56
 */
@SpringBootTest(classes = MainApplication.class)
public class TvTests {

    @Resource
    private TvService tvService;

    @Test
    @DisplayName("测试加载冷藏整容车间电视信息接口")
    public void testLoadColdFaceTv() {
        BaseResult<ColdFaceTvItem> baseResult = tvService.loadColdFaceTv();
        LogUtil.line("查询结果：" + JacksonUtil.obj2Json(baseResult));
    }
}
