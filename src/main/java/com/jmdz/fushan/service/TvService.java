package com.jmdz.fushan.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.TvDao;
import com.jmdz.fushan.model.tv.ColdFaceTvItem;
import com.jmdz.fushan.model.tv.ColdFaceTvLeftItem;
import com.jmdz.fushan.model.tv.ColdFaceTvRightItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * TvService
 *
 * @author LiCongLu
 * @date 2020-08-07 14:47
 */
@Service("tvService")
public class TvService extends BaseService {

    @Resource
    private TvDao tvDao;

    /**
     * 加载冷藏整容车间电视信息接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-07 14:55
     */
    public BaseResult<ColdFaceTvItem> loadColdFaceTv() {
        ColdFaceTvItem loadItem = new ColdFaceTvItem();

        // 获取左侧信息
        ArrayList<ColdFaceTvLeftItem> leftItems = tvDao.listColdFaceTvLeft();
        for (ColdFaceTvLeftItem leftItem : leftItems) {
            ArrayList<String> coffinList = tvDao.listColdFaceTvLeftCoffinByOperationNo(leftItem.getOperationNo());
            if (DataUtil.valid(coffinList)) {
                leftItem.setCoffinName(StringUtil.join("、", coffinList));
            }
        }
        loadItem.setLeftItems(leftItems);

        // 获取右侧信息
        ArrayList<ColdFaceTvRightItem> rightAllItems = tvDao.listColdFaceTvRight();
        ArrayList<ColdFaceTvRightItem> rightItems = new ArrayList<>();

        // 组合数据
        HashMap<String, ColdFaceTvRightItem> rightMap = new HashMap<>(16);
        for (ColdFaceTvRightItem tempItem : rightAllItems) {
            String key = tempItem.getOperationNo() + tempItem.getExcuteTime();
            if (!rightMap.containsKey(key)) {
                ColdFaceTvRightItem rightItem = new ColdFaceTvRightItem();
                rightItem.setOperationNo(tempItem.getOperationNo())
                        .setDeadName(tempItem.getDeadName())
                        .setExcuteTime(tempItem.getExcuteTime());
                rightMap.put(key, rightItem);
            }
        }

        // 综合设置项目名称
        for (Map.Entry<String, ColdFaceTvRightItem> entry : rightMap.entrySet()) {
            ColdFaceTvRightItem rightItem = entry.getValue();
            ArrayList<String> itemNameList = new ArrayList<>();
            for (ColdFaceTvRightItem tempItem : rightAllItems) {
                if (DataUtil.equals(entry.getKey(), tempItem.getOperationNo() + tempItem.getExcuteTime())) {
                    itemNameList.add(tempItem.getItemName());
                }
            }
            if (DataUtil.valid(itemNameList)) {
                rightItem.setItemName(StringUtil.join("、", itemNameList));
            }
            rightItems.add(rightItem);
        }
        loadItem.setRightItems(rightItems);

        return success(loadItem);
    }
}
