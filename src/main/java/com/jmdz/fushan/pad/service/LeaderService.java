package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.pad.model.leader.LeaderAllChargeItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllData;
import com.jmdz.fushan.pad.model.leader.LeaderAllInfoItem;
import com.jmdz.fushan.pad.model.leader.LeaderAllLabelItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * LeaderService
 *
 * @author LiCongLu
 * @date 2020-07-13 09:06
 */
@SuppressWarnings("unchecked")
@Service("leaderService")
public class LeaderService extends BaseService {

    @Resource
    private ServiceDao serviceDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private CrematingDao crematingDao;

    @Resource
    private ConfigData configData;

    @Resource
    private MournDao mournDao;

    @Resource
    private VehicleDao vehicleDao;

    @Resource
    private ColdStorageDao coldStorageDao;

    /**
     * 加载运营信息总览信息
     *
     * @param data 查询条件
     * @return
     * @author LiCongLu
     * @date 2020-07-13 09:09
     */
    public BaseResult<LeaderAllInfoItem> loadLeaderAllInfo(LeaderAllData data) {
        LeaderAllInfoItem loadItem = new LeaderAllInfoItem();
        // 服务项目分类费用统计
        loadLeaderCharge(data, loadItem);

        // 火化量
        ArrayList<LeaderAllLabelItem> machineTypeItems = serviceDao.listServiceParentByParentId(configData.getLeaderCrematingMachineParent());
        ArrayList<LeaderAllLabelItem> crematingItems = getHeaderLabelList(crematingDao.countCrematingForLeader(data), machineTypeItems);
        LeaderAllLabelItem crematingTotalItem = new LeaderAllLabelItem(0, "cremating", "合计", new BigDecimal(0));
        crematingItems.forEach(item -> {
            item.setType("cremating");
            crematingTotalItem.setNumber(crematingTotalItem.getNumber().add(item.getNumber()));
        });
        loadItem.setCrematingItems(crematingItems)
                .setCrematingTotalItem(crematingTotalItem);

        // 守灵及告别场次
        loadLeaderMourn(data, loadItem);

        // 接运数量
        ArrayList<LeaderAllLabelItem> vehicleTypeItems = serviceDao.listServiceParentByParentId(configData.getLeaderVehicleTypeParent());
        ArrayList<LeaderAllLabelItem> vehicleItems = getHeaderLabelList(vehicleDao.countVehicleForLeader(data), vehicleTypeItems);
        LeaderAllLabelItem vehicleTotalItem = new LeaderAllLabelItem(0, "vehicle", "合计", new BigDecimal(0));
        vehicleItems.forEach(item -> {
            item.setType("vehicle");
            vehicleTotalItem.setNumber(vehicleTotalItem.getNumber().add(item.getNumber()));
        });
        loadItem.setVehicleItems(vehicleItems)
                .setVehicleTotalItem(vehicleTotalItem);

        // 冷藏数量
        LeaderAllLabelItem coldStorageInItem = coldStorageDao.countColdStorageInForLeader(data);
        coldStorageInItem.setId(0).setName("入藏量").setType("coldStorageIn");
        LeaderAllLabelItem coldStorageOutItem = coldStorageDao.countColdStorageOutForLeader(data);
        coldStorageOutItem.setId(0).setName("出藏量").setType("coldStorageOut");
        LeaderAllLabelItem coldStorageCurrentItem = coldStorageDao.countColdStorageCurrentForLeader();
        coldStorageCurrentItem.setId(0).setName("当前在柜").setType("coldStorageCurrent");
        LeaderAllLabelItem coldStorageUsableItem = coldStorageDao.countColdStorageUsableForLeader();
        coldStorageUsableItem.setId(0).setName("空柜").setType("coldStorageUsable");

        loadItem.setColdStorageInItem(coldStorageInItem)
                .setColdStorageOutItem(coldStorageOutItem)
                .setColdStorageCurrentItem(coldStorageCurrentItem)
                .setColdStorageUsableItem(coldStorageUsableItem);


        return success(loadItem);
    }

    /**
     * 服务项目分类费用统计
     *
     * @param data     查询条件
     * @param loadItem 返回加载数据
     * @return
     * @author LiCongLu
     * @date 2020-07-13 09:57
     */
    private void loadLeaderCharge(LeaderAllData data, LeaderAllInfoItem loadItem) {

        // 获取分类名称
        ArrayList<LeaderAllLabelItem> chargeTypeItems = serviceDao.listServiceParentByParentId(configData.getLeaderServiceParent());
        ArrayList<LeaderAllChargeItem> chargeAllItems = getChargeHeaderLabelList(chargeDao.countChargeForLeaderByType(data), chargeTypeItems);
        LeaderAllChargeItem chargeTotalItem = new LeaderAllChargeItem();
        chargeTotalItem.setParentId(0)
                .setParentName("合计")
                .setTotalCharge(new BigDecimal(0))
                .setBenefitCharge(new BigDecimal(0))
                .setRealCharge(new BigDecimal(0));
        // 费用统计
        ArrayList<LeaderAllLabelItem> chargeItems = new ArrayList<>();
        chargeAllItems.forEach(item -> {
            // 添加费用汇总
            chargeItems.add(new LeaderAllLabelItem(item.getParentId(), "charge", item.getParentName(), item.getTotalCharge()));

            // 合计总费用信息
            chargeTotalItem.setTotalCharge(chargeTotalItem.getTotalCharge().add(item.getTotalCharge()));
            chargeTotalItem.setBenefitCharge(chargeTotalItem.getBenefitCharge().add(item.getBenefitCharge()));
            chargeTotalItem.setRealCharge(chargeTotalItem.getRealCharge().add(item.getRealCharge()));
        });

        // 重新调整拼接
        loadItem.setChargeItems(chargeItems)
                .setChargeTotalItem(new LeaderAllLabelItem(0, "chargeTotal", "应收", chargeTotalItem.getTotalCharge()))
                .setChargeBenefitItem(new LeaderAllLabelItem(0, "chargeBenefit", "惠民", chargeTotalItem.getBenefitCharge()))
                .setChargeRealItem(new LeaderAllLabelItem(0, "chargeReal", "实收", chargeTotalItem.getRealCharge()));
    }

    /**
     * 按照费用重新拼接费用
     *
     * @param chargeLabelItems 物品费用集合
     * @param typeItems        类型集合
     * @return
     * @author LiCongLu
     * @date 2020-07-13 15:10
     */
    private ArrayList<LeaderAllChargeItem> getChargeHeaderLabelList(ArrayList<LeaderAllChargeItem> chargeLabelItems, ArrayList<LeaderAllLabelItem> typeItems) {
        ArrayList<LeaderAllChargeItem> chargeItems = new ArrayList<>();
        for (LeaderAllLabelItem typeItem : typeItems) {

            // 查看统计数
            LeaderAllChargeItem tempItem = null;
            for (LeaderAllChargeItem labelItem : chargeLabelItems) {
                if (DataUtil.equals(labelItem.getParentId(), typeItem.getId())) {
                    tempItem = labelItem;
                    break;
                }
            }

            // 设置新的统计数
            if (tempItem != null) {
                chargeItems.add(BeanUtil.copy2Bean(tempItem, new LeaderAllChargeItem()));
                chargeLabelItems.remove(tempItem);
            } else {
                LeaderAllChargeItem chargeItem = new LeaderAllChargeItem();
                chargeItem.setParentId(typeItem.getId())
                        .setParentName(typeItem.getName())
                        .setTotalCharge(new BigDecimal(0))
                        .setBenefitCharge(new BigDecimal(0))
                        .setRealCharge(new BigDecimal(0));
                chargeItems.add(chargeItem);
            }
        }

        return chargeItems;
    }

    /**
     * 守灵及告别场次统计
     *
     * @param data     查询条件
     * @param loadItem 返回加载数据
     * @return
     * @author LiCongLu
     * @date 2020-07-13 10:50
     */
    private void loadLeaderMourn(LeaderAllData data, LeaderAllInfoItem loadItem) {
        // 守灵场次
        ArrayList<LeaderAllLabelItem> wakeTypeItems = mournDao.listMournHallForLeader(0);
        ArrayList<LeaderAllLabelItem> wakeItems = getHeaderLabelList(mournDao.countMournForLeader(data, 0), wakeTypeItems);
        LeaderAllLabelItem wakeTotalItem = new LeaderAllLabelItem(0, "wake", "合计", new BigDecimal(0));
        wakeItems.forEach(item -> {
            item.setType("wake");
            wakeTotalItem.setNumber(wakeTotalItem.getNumber().add(item.getNumber()));
        });
        loadItem.setWakeItems(wakeItems)
                .setWakeTotalItem(wakeTotalItem);

        // 告别量
        ArrayList<LeaderAllLabelItem> farewellTypeItems = mournDao.listMournHallForLeader(1);
        ArrayList<LeaderAllLabelItem> farewellAllItems = getHeaderLabelList(mournDao.countMournForLeader(data, 1), farewellTypeItems);
        ArrayList<LeaderAllLabelItem> farewellItems = new ArrayList<>();
        LeaderAllLabelItem farewellTotalItem = new LeaderAllLabelItem(0, "farewell", "合计", new BigDecimal(0));
        String[] hallIds = configData.getLeaderFarewellHallId().split(",");
        ArrayList<String> ids = new ArrayList<>();
        for (int i = 0; i < hallIds.length; i++) {
            String id = hallIds[i];
            if (ids.contains(id)) {
                continue;
            }
            ids.add(id);

            // 按照规则排
            LeaderAllLabelItem idItem = null;
            for (LeaderAllLabelItem item : farewellAllItems) {
                if (DataUtil.equals(String.valueOf(item.getId()), id)) {
                    idItem = item;
                    break;
                }
            }

            if (idItem != null) {
                farewellItems.add(idItem);
                farewellAllItems.remove(idItem);
                idItem.setType("wake");
                farewellTotalItem.setNumber(farewellTotalItem.getNumber().add(idItem.getNumber()));
            }
        }

        // 其它
        LeaderAllLabelItem farewellOtherItem = new LeaderAllLabelItem(0, "farewellOther", "其它", new BigDecimal(0));
        farewellAllItems.forEach(item -> {
            farewellOtherItem.setNumber(farewellOtherItem.getNumber().add(item.getNumber()));
            farewellTotalItem.setNumber(farewellTotalItem.getNumber().add(item.getNumber()));
        });
        farewellItems.add(farewellOtherItem);
        loadItem.setFarewellItems(farewellItems)
                .setFarewellTotalItem(farewellTotalItem);
    }

    /**
     * 重新拼接数据
     *
     * @param labelItems 查询结果
     * @param typeItems  类型数据
     * @return
     * @author LiCongLu
     * @date 2020-07-13 14:40
     */
    private ArrayList<LeaderAllLabelItem> getHeaderLabelList(ArrayList<LeaderAllLabelItem> labelItems, ArrayList<LeaderAllLabelItem> typeItems) {
        for (LeaderAllLabelItem typeItem : typeItems) {

            // 查看统计数
            LeaderAllLabelItem tempItem = null;
            for (LeaderAllLabelItem labelItem : labelItems) {
                if (DataUtil.equals(labelItem.getId(), typeItem.getId())) {
                    tempItem = labelItem;
                    break;
                }
            }

            // 设置新的统计数
            if (tempItem != null) {
                typeItem.setType(tempItem.getType())
                        .setNumber(tempItem.getNumber());
                labelItems.remove(tempItem);
            }
        }

        return typeItems;
    }
}
