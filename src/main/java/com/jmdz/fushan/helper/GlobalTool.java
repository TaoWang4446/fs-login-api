package com.jmdz.fushan.helper;

import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.fushan.dao.DeadDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 接运审批工具类
 *
 * @author LiCongLu
 * @date 2020-07-17 09:28
 */
@Component("globalTool")
public class GlobalTool {

    @Resource
    private DeadDao deadDao;

    /**
     * 生成最新业务编号
     *
     * @return
     */
    public String createOperationNo() {
        String returnValue = DateUtil.formatPattern(new Date(), DateUtil.PATTERN_8);
        String maxNo = deadDao.getMaxOperationNo(returnValue);
        if (DataUtil.invalid(maxNo)) {
            return returnValue + "001";
        } else {
            int mxNo = Integer.parseInt(maxNo.replace(returnValue, ""));
            return returnValue + String.format("%03d", mxNo + 1);
        }
    }

}