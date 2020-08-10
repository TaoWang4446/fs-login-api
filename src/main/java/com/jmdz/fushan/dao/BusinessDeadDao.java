package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.BusinessDeadProvider;
import com.jmdz.fushan.dao.provider.DeadProvider;
import com.jmdz.fushan.pad.model.business.BusinessDeadListItem;
import com.jmdz.fushan.pad.model.dead.DeadChargeAllItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

/**
 * BusinessDeadDao
 *
 * @author LiCongLu
 * @date 2020-07-28 13:38
 */
public interface BusinessDeadDao {

    /**
     * 按照关键字查询逝者信息
     *
     * @param keyword 关键字
     * @return
     * @author LiCongLu
     * @date 2020-07-28 13:41
     */
    @SelectProvider(type = BusinessDeadProvider.class, method = "listBusinessDeadByKeyword")
    ArrayList<BusinessDeadListItem> listBusinessDeadByKeyword(@Param("keyword") String keyword);

}
