package com.jmdz.fushan.dao;

import com.jmdz.fushan.dao.provider.BusinessFarewellProvider;
import com.jmdz.fushan.dao.provider.TvProvider;
import com.jmdz.fushan.model.config.TableNames;
import com.jmdz.fushan.model.tv.ColdFaceTvLeftItem;
import com.jmdz.fushan.model.tv.ColdFaceTvRightItem;
import com.jmdz.fushan.pad.model.business.BusinessServiceItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;

/**
 * TvDao
 *
 * @author LiCongLu
 * @date 2020-08-07 14:58
 */
public interface TvDao {

    /**
     * 获取冷藏整容电视左侧信息集合
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-07 15:20
     */
    @SelectProvider(type = TvProvider.class, method = "listColdFaceTvLeft")
    ArrayList<ColdFaceTvLeftItem> listColdFaceTvLeft();

    /**
     * 按照业务编号获取卫生棺信息
     *
     * @param operationNo 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-08-07 16:48
     */
    @Select("select sItem.Name from " + TableNames.Charge + " charge" +
            " join " + TableNames.ServiceItem + " sItem on sItem.id = charge.ServiceItem " +
            " where charge.IsDelete = 0 and ISNULL(sItem.IsWeiShengGuan,0) = 1" +
            " and charge.OperationNO = #{operationNo} ")
    @ResultType(String.class)
    ArrayList<String> listColdFaceTvLeftCoffinByOperationNo(@Param("operationNo") String operationNo);

    /**
     * 获取冷藏整容电视右侧信息集合
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-07 16:58
     */
    @SelectProvider(type = TvProvider.class, method = "listColdFaceTvRight")
    ArrayList<ColdFaceTvRightItem> listColdFaceTvRight();
}
