package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.DeadDao;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.pad.model.dead.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * DeadService
 *
 * @author LiCongLu
 * @date 2020-07-10 13:53
 */
@SuppressWarnings("unchecked")
@Service("deadService")
public class DeadService extends BaseService {

    @Resource
    private DeadDao deadDao;

    @Resource
    private ConfigData configData;

    /**
     * 加载逝者任务列表
     *
     * @param data 请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-10 13:54
     */
    public BaseResult<ArrayList<DeadAllListItem>> loadDeadAllList(DeadAllData data) {
        ArrayList<DeadAllListItem> loadItems = deadDao.listDeadAllListByDate(data.getQueryDate());
        return success(loadItems);
    }

    /**
     * 加载逝者任务列表详情信息
     *
     * @param data 业务编号
     * @return
     * @author SunZhaoQi
     * @date 2020-06-13 10:23
     */
    public BaseResult<DeadDetailsAllItem> loadDeadDetailsAll(DeadDetailsAllData data) {
        DeadDetailsAllItem loadItem = new DeadDetailsAllItem();

        //获取逝者详细信息
        DeadDetailsItem deadDetailsItem = deadDao.getDeadDetailsByOperationNo(data.getOperationNo());
        if (deadDetailsItem == null || DataUtil.invalid(deadDetailsItem.getOperationNo())) {
            return failure("逝者业务编号错误，不存在逝者详情信息");
        }

        //设置逝者详细信息
        loadItem.setDeadDetailsItem(deadDetailsItem);

        //费用明细
        ArrayList<DeadChargeAllItem> chargeAllItems = deadDao.listDeadChargeAllByOperationNo(data.getOperationNo());

        //汇总实收 优惠 实收
        DeadChargeTotalItem chargeTotalItem = new DeadChargeTotalItem();
        chargeTotalItem.setCharge(new BigDecimal(0))
                .setBenefitCharge(new BigDecimal(0))
                .setRealCharge(new BigDecimal(0));

        // 卫生棺信息
        ArrayList<String> coffinList = new ArrayList<>();

        ArrayList<DeadDetailsChargeItem> chargeItems = new ArrayList<>();
        for (DeadChargeAllItem item : chargeAllItems) {
            DeadDetailsChargeItem chargeItem = BeanUtil.copy2Bean(item, new DeadDetailsChargeItem());
            chargeItems.add(chargeItem);
            // 合计总费用信息
            chargeTotalItem.setCharge(chargeTotalItem.getCharge().add(item.getCharge()));
            //费用减免 惠民
            chargeTotalItem.setBenefitCharge(chargeTotalItem.getBenefitCharge().add(item.getBenefitCharge()));
            //实收
            chargeTotalItem.setRealCharge(chargeTotalItem.getRealCharge().add(item.getRealCharge()));

            //判断数据里面是否有卫生棺信息
            if (DataUtil.valid(item.getAsZhiGuan())) {
                coffinList.add(item.getItemName());
            }
        }

        // 设置费用详情
        loadItem.setChargeItems(chargeItems);
        // 设置费用合计
        loadItem.setChargeTotalItem(chargeTotalItem);

        // 查询业务服务信息
        DeadServiceAllItem serviceAllItem = deadDao.getDeadServiceAllByOperationNo(data.getOperationNo());
        if (serviceAllItem == null) {
            return failure("逝者业务编号错误");
        }

        ArrayList<DeadDetailsServiceItem> serviceItems = new ArrayList<>();

        //接运
        if (DataUtil.valid(serviceAllItem.getBespeakVehicleTypeName())) {
            serviceItems.add(new DeadDetailsServiceItem("接运", serviceAllItem.getBespeakVehicleTypeName()));
        }

        //冷藏信息
        String coldStorageStr = StringUtil.join(" ", serviceAllItem.getEquipmentTypeName(), serviceAllItem.getEquipmentName()
                , serviceAllItem.getColdStorageInTime(), serviceAllItem.getColdStorageOutTime());
        if (DataUtil.valid(coldStorageStr)) {
            serviceItems.add(new DeadDetailsServiceItem("冷藏", coldStorageStr));
        }

        //守灵厅信息
        String wakeStr = StringUtil.join(" ", serviceAllItem.getWakeHallName(), serviceAllItem.getWakeBeginTime(), serviceAllItem.getWakeEndTime());
        if (DataUtil.valid(wakeStr)) {
            serviceItems.add(new DeadDetailsServiceItem("守灵", wakeStr));
        }

        //告别厅信息
        String farewellStr = StringUtil.join(" ", serviceAllItem.getFarewellHallName(), serviceAllItem.getFarewellBeginTime(), serviceAllItem.getFarewellEndTime());
        if (DataUtil.valid(farewellStr)) {
            serviceItems.add(new DeadDetailsServiceItem("告别", farewellStr));
        }

        //火化信息
        String crematingStr = StringUtil.join(" ", serviceAllItem.getMachineTypeName(), serviceAllItem.getCremationBeginTime());
        if (DataUtil.valid(crematingStr)) {
            serviceItems.add(new DeadDetailsServiceItem("火化", crematingStr));
        }

        // 卫生棺
        if (null != coffinList && !coffinList.isEmpty()) {
            String coffinStr = StringUtil.join(" ", coffinList);
            if (DataUtil.valid(coffinStr)) {
                serviceItems.add(new DeadDetailsServiceItem("卫生棺", coffinStr));
            }
        }

        //设置所有信息
        loadItem.setServiceItems(serviceItems);
        return success(loadItem);
    }
}
