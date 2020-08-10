package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.StringUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.helper.GlobalTool;
import com.jmdz.fushan.helper.RecordTool;
import com.jmdz.fushan.model.config.BusinessConst;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.config.PickerType;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.ServiceItem;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.business.PickerItem;
import com.jmdz.fushan.pad.model.business.RelationItem;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.vehicle.*;
import com.jmdz.fushan.pad.model.weixin.VehicleManageItem;
import com.jmdz.fushan.pad.model.weixin.WxRecServiceItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * VehicleService
 *
 * @author LiCongLu
 * @date 2020-07-09 09:58
 */
@Service("vehicleService")
public class VehicleService extends BaseService {

    @Resource
    private VehicleDao vehicleDao;

    @Resource
    private AppDao appDao;

    @Resource
    private BusinessLogDao businessLogDao;

    @Resource
    private ChargeDao chargeDao;

    @Resource
    private ConfigData configData;

    @Resource
    private DeadDao deadDao;

    @Resource
    private RecordTool recordTool;

    @Resource
    private RelationDao relationDao;

    @Resource
    private ItemsDao itemsDao;

    @Resource
    private ServiceDao serviceDao;

    @Resource
    private GlobalTool globalTool;

    @Resource
    private VehicleManageDao vehicleManageDao;

    /**
     * 加载遗体接运任务列表
     *
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 10:06
     */
    public BaseResult<ArrayList<VehicleTaskListItem>> loadVehicleTaskList(LoginItem loginItem) {
        ArrayList<VehicleTaskListItem> loadAllItems = vehicleDao.listVehicleTaskList(loginItem.getUserId());

        ArrayList<VehicleTaskListItem> loadItems = new ArrayList<>();

        // 更新方式添加物品服务
        String parentId = configData.getVehicleServiceParent();
        String belongService = configData.getVehicleServiceBelong();

        if (DataUtil.valid(loadAllItems)) {
            ArrayList<String> operationNoList = new ArrayList<>();
            loadAllItems.forEach(loadItem -> {
                if (!operationNoList.contains(loadItem.getOperationNo())) {
                    operationNoList.add(loadItem.getOperationNo());
                }

                // 判断是否结算
                if (DataUtil.valid(loadItem.getAsFinished())) {
                    loadItem.setCarryStateText(loadItem.getCarryStateText() + "(已结算)");

                    // 已结算的，只显示任务完成的车辆信息
                    if (DataUtil.equals(loadItem.getCarryState(), Constants.CarryState.RenWuWanCheng)) {
                        loadItems.add(loadItem);
                    }
                } else {
                    loadItems.add(loadItem);
                }
            });

            // 所有车辆
            ArrayList<VehicleCarListItem> vehicleCarAllItems = vehicleDao.listVehicleCarList();

            // 获取所有费用集合
            ArrayList<ChargeItem> chargeAllItems = chargeDao.queryChargeItemForOperationNoList(operationNoList, parentId, belongService);
            // 拆分获取费用集合
            for (VehicleTaskListItem loadItem : loadItems) {
                ArrayList<ChargeItem> chargeItems = new ArrayList<>();
                chargeAllItems.forEach(chargeItem -> {
                    if (DataUtil.valid(chargeItem.getId())
                            && DataUtil.equals(loadItem.getOperationNo(), chargeItem.getOperationNo())
                            && DataUtil.equals(loadItem.getRandomId(), chargeItem.getRandomId())) {
                        chargeItems.add(chargeItem);
                    }
                });
                loadItem.setChargeItems(chargeItems);

                // 当车辆尚未出车时，设置车辆信息
                if (DataUtil.equals(loadItem.getCarryState(), Constants.CarryState.ShangWeiChuChe)) {
                    // 遍历车辆信息
                    ArrayList<VehicleCarListItem> vehicleCarItems = new ArrayList<>();
                    vehicleCarAllItems.forEach(carItem -> {
                        if (DataUtil.equals(carItem.getVehicleTypeId(), loadItem.getVehicleTypeId())) {
                            vehicleCarItems.add(BeanUtil.copy2Bean(carItem, new VehicleCarListItem()));
                        }
                    });
                    loadItem.setVehicleCarItems(vehicleCarItems);
                }
            }
        }
        return success(loadItems);
    }

    /**
     * 保存实时定位接口
     *
     * @param data 定位数据
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:58
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveLocation(LoginItem loginItem, LocationData data) throws ActionException {
        try {
            BigDecimal longitude = new BigDecimal(data.getLongitude());
            BigDecimal latitude = new BigDecimal(data.getLatitude());

            // 判断坐标值
            if (!isValidLocation(longitude, latitude)) {
                throw exception("坐标值超出范围");
            }
        } catch (Exception ex) {
            throw exception("坐标值错误");
        }

        // 获取最近的业务编号
        String operationNo = vehicleDao.getLastOperationNoByUserId(data.getUserId());

        // 创建坐标数据
        AppLocation item = new AppLocation();
        item.setLoginId(loginItem.getLoginId());
        item.setUserId(loginItem.getUserId());
        item.setLongitude(data.getLongitude());
        item.setLatitude(data.getLatitude());
        item.setLocationCode("0");
        item.setLocationFlag(0);
        item.setRemark(data.getRemark());
        item.setOperationNo(operationNo);

        appDao.insertAppLocation(item);
        if (DataUtil.invalid(item.getUserId())) {
            throw exception("保存定位错误");
        }

        return success("保存成功");
    }

    /**
     * 百度经纬度区域范围经纬度常量
     */
    public final class BaiDuLngLat {
        public static final double MaxLng = 136;
        public static final double MinLng = 70;
        public static final double MaxLat = 55;
        public static final double MinLat = 10;
    }

    /**
     * @Author liconglu
     * @Description 验证百度坐标经纬度有效值
     * @Date 2019/1/17  13:52
     * @Param
     * @Return
     */
    public boolean isValidLocation(BigDecimal longitude, BigDecimal latitude) {
        return longitude.doubleValue() <= BaiDuLngLat.MaxLng && longitude.doubleValue() >= BaiDuLngLat.MinLng
                && latitude.doubleValue() <= BaiDuLngLat.MaxLat && latitude.doubleValue() >= BaiDuLngLat.MinLat;
    }

    /**
     * 接运任务出车接口
     *
     * @param data 业务数据
     * @return
     * @author LiCongLu
     * @date 2020-07-10 08:48
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveVehicleOut(LoginItem loginItem, VehicleOutData data) throws ActionException {
        // 判断车辆信息
        VehicleTaskListItem loadItem = vehicleDao.getVehicleTaskByVehicleId(data.getVehicleId());
        if (loadItem == null || DataUtil.invalid(loadItem.getVehicleId(), loadItem.getOperationNo())) {
            throw exception("接运任务主键错误，此接运任务不存在");
        }

        // 判断业务编号
        if (!DataUtil.equals(data.getOperationNo(), loadItem.getOperationNo())) {
            throw exception("业务编号与接运任务不匹配");
        }

        // 判断接运状态
        if (!DataUtil.equals(loadItem.getCarryState(), Constants.CarryState.ShangWeiChuChe)) {
            throw exception("非尚未出车状态，不能出车");
        }

        // 加载车辆信息
        VehicleCarListItem carItem = vehicleDao.getVehicleCarById(data.getVehicleCarId());
        if (carItem == null || DataUtil.invalid(carItem.getId())) {
            throw exception("车辆主键错误，车辆信息不存在");
        }

        // 判断车型
        if (!DataUtil.equals(loadItem.getVehicleTypeId(), carItem.getVehicleTypeId())) {
            throw exception("出车车型与接运任务预约车型不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(loadItem.getAsFinished())) {
            throw exception("已结算，无法修改");
        }

        // 出车
        vehicleDao.updateVehicleOut(loginItem, loadItem.getVehicleId(), carItem);

        String logContent = StringUtil.format("接运任务出车,逝者【{0}】【{1}】,联系人【{2}】，联系电话【{3}】，预约车型【{4}】，出车时间【{5}】，接尸地点【{6}】。", loadItem.getOperationNo(), loadItem.getDeadName(), loadItem.getLinkman(), loadItem.getContactNumber(), loadItem.getVehicleType(), DateUtil.formatPattern19(new Date()), loadItem.getCarryPlace());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "接运任务", BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());

        return success("出车成功");
    }

    /**
     * 接运任务回车接口
     *
     * @param data 业务数据
     * @return
     * @author LiCongLu
     * @date 2020-07-10 08:48
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveVehicleBack(LoginItem loginItem, VehicleIdData data) throws ActionException {
        // 判断车辆信息
        VehicleTaskListItem loadItem = vehicleDao.getVehicleTaskByVehicleId(data.getVehicleId());
        if (loadItem == null || DataUtil.invalid(loadItem.getVehicleId(), loadItem.getOperationNo())) {
            throw exception("接运任务主键错误，此接运任务不存在");
        }

        // 判断业务编号
        if (!DataUtil.equals(data.getOperationNo(), loadItem.getOperationNo())) {
            throw exception("业务编号与接运任务不匹配");
        }

        // 判断接运状态
        if (!DataUtil.equals(loadItem.getCarryState(), Constants.CarryState.YiChuChe)) {
            throw exception("非已出车状态，不能回车");
        }

        // 判断是否结算
        if (DataUtil.valid(loadItem.getAsFinished())) {
            throw exception("已结算，无法修改");
        }

        // 回车
        vehicleDao.updateVehicleBack(loginItem, loadItem.getVehicleId());

        String logContent = StringUtil.format("接运任务回车,逝者【{0}】【{1}】,联系人【{2}】，联系电话【{3}】，预约车型【{4}】，回车时间【{5}】，接尸地点【{6}】。", loadItem.getOperationNo(), loadItem.getDeadName(), loadItem.getLinkman(), loadItem.getContactNumber(), loadItem.getVehicleType(), DateUtil.formatPattern19(new Date()), loadItem.getCarryPlace());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "接运任务", BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());


        return success("回车成功");
    }

    /**
     * 加载接运随车服务物品费用列表接口
     *
     * @param data 业务数据
     * @return
     * @author LiCongLu
     * @date 2020-07-10 10:26
     */
    public BaseResult<ArrayList<ChargeItem>> loadVehicleChargeList(VehicleIdData data) {
        // 判断车辆信息
        VehicleTaskListItem loadItem = vehicleDao.getVehicleTaskByVehicleId(data.getVehicleId());
        if (loadItem == null || DataUtil.invalid(loadItem.getVehicleId(), loadItem.getOperationNo())) {
            throw exception("接运任务主键错误，此接运任务不存在");
        }

        // 判断业务编号
        if (!DataUtil.equals(data.getOperationNo(), loadItem.getOperationNo())) {
            throw exception("业务编号与接运任务不匹配");
        }

        String parentId = configData.getVehicleServiceParent();
        String belongService = configData.getVehicleServiceBelong();
        ArrayList<ChargeItem> chargeItems = chargeDao.queryChargeItemForBusinessRandomId(loadItem.getOperationNo(), loadItem.getRandomId(), parentId, belongService);
        return success(chargeItems);
    }

    /**
     * 保存物品服务费用接口
     *
     * @param loginItem 当前账号
     * @param data      物品服务费用
     * @return
     * @author LiCongLu
     * @date 2020-07-10 11:19
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveVehicleCharge(LoginItem loginItem, VehicleChargeData data) throws ActionException {
        // 判断车辆信息
        VehicleTaskListItem loadItem = vehicleDao.getVehicleTaskByVehicleId(data.getVehicleId());
        if (loadItem == null || DataUtil.invalid(loadItem.getVehicleId(), loadItem.getOperationNo())) {
            throw exception("接运任务主键错误，此接运任务不存在");
        }

        // 判断业务编号
        if (!DataUtil.equals(data.getOperationNo(), loadItem.getOperationNo())) {
            throw exception("业务编号与接运任务不匹配");
        }

        // 判断是否结算
        if (DataUtil.valid(loadItem.getAsFinished())) {
            throw exception("已结算，无法修改");
        }

        // 判断物品服务
        if (DataUtil.invalid(data.getChargeItems())) {
            throw exception("物品服务为空，添加失败");
        }

        // 更新方式添加物品服务
        String parentId = configData.getVehicleServiceParent();
        String belongService = configData.getVehicleServiceBelong();
        ArrayList<ChargeItem> oldCItems = chargeDao.queryChargeItemForBusinessRandomId(loadItem.getOperationNo(), loadItem.getRandomId(), parentId, belongService);
        HashMap<String, ChargeItem> oldMap = new HashMap<>();
        oldCItems.forEach(oldCItem -> {
            if (DataUtil.valid(oldCItem.getId())) {
                oldMap.put(String.valueOf(oldCItem.getId()), oldCItem);
            }
        });

        // 新增及更新非结算费用
        ArrayList<ChargeItem> newCItems = data.getChargeItems();
        final String randomId = loadItem.getRandomId();
        final String operationNo = loadItem.getOperationNo();
        newCItems.forEach(newCItem -> {
            String newCId = String.valueOf(newCItem.getId());
            if (DataUtil.greaterAllZero(newCItem.getNumber())) {
                newCItem.setOperationNo(operationNo);
                newCItem.setRandomId(randomId);
                newCItem.setChargeDate(DateUtil.formatPattern16(new Date()));
                newCItem.setServiceCode(Constants.ServiceConst.CheLiangZuYong)
                        .setProductFrom(0);

                if (oldMap.containsKey(newCId)) {
                    ChargeItem oldItem = oldMap.get(newCId);
                    oldMap.remove(newCId);
                    if (DataUtil.invalid(oldItem.getAsFinished())) {
                        chargeDao.updateChargeItem(newCItem, loginItem.getUserId());
                        // 更新业务费用详情关联表
                        chargeDao.insertChargeServiceItemDetails(newCItem.getId(), newCItem.getItemId(), newCItem.getNumber());
                    }
                } else {
                    chargeDao.insertChargeItem(newCItem, loginItem.getUserId());
                    // 更新业务费用详情关联表
                    chargeDao.insertChargeServiceItemDetails(newCItem.getId(), newCItem.getItemId(), newCItem.getNumber());
                }
            } else if (oldMap.containsKey(newCId)) {
                ChargeItem oldItem = oldMap.get(newCId);
                oldMap.remove(newCId);
                if (DataUtil.invalid(oldItem.getAsFinished())) {
                    chargeDao.deleteChargeById(oldItem.getOperationNo(), oldItem.getId());
                }
            }
        });

        // 清除非结算历史信息
        for (Map.Entry<String, ChargeItem> entry : oldMap.entrySet()) {
            ChargeItem oldItem = entry.getValue();
            if (DataUtil.invalid(oldItem.getAsFinished())) {
                chargeDao.deleteChargeById(oldItem.getOperationNo(), oldItem.getId());
            }
        }

        String logContent = StringUtil.format("接运任务项目,业务编号【{0}】，修改接运服务项目", loadItem.getOperationNo());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "接运任务", BusinessConst.OperateType.XiuGai, logContent, loadItem.getOperationNo());


        return success("添加物品服务成功");
    }

    /**
     * 加载二维码信息接口
     *
     * @param data      业务编号
     * @param loginItem 当前账号
     * @return
     * @author LiCongLu
     * @date 2020-07-16 08:45
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<QrCodeItem> loadQrCode(LoginItem loginItem, OperationNoData data) throws ActionException {
        DeadBasicItem deadItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadItem == null || DataUtil.invalid(deadItem.getOperationNo())) {
            return failure("业务编号错误，未找到逝者信息");
        }

        // 获取图片
        RecordImage recordImage = recordTool.getOrCreateQRCode(data.getOperationNo(), Constants.RecordCode.RecordListCode, Constants.RecordCode.OperationNoQRCodeCode, loginItem.getUserId(), "业务编号二维码");
        if (recordImage == null || DataUtil.invalid(recordImage.getImagePath())) {
            return failure("二维码生成失败");
        }

        // 逝者基本信息
        QrCodeItem loadItem = new QrCodeItem();
        loadItem.setOperationNo(deadItem.getOperationNo());
        loadItem.setDeadName(deadItem.getDeadName());
        loadItem.setDeadSex(deadItem.getDeadSex());
        loadItem.setDeadAge(deadItem.getDeadAge());
        loadItem.setDeathCause(deadItem.getDeathCause());

        // 家属信息
        RelationItem relationItem = relationDao.getRelationItemByOperationNo(deadItem.getOperationNo());
        if (relationItem != null) {
            loadItem.setRelationName(relationItem.getRelationName());
        }

        // 二维码基本信息
        loadItem.setImagePath(recordImage.getImagePath());
        return success(loadItem);
    }

    /**
     * 加载字典数据等车辆基础数据接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-10 11:39
     */
    public BaseResult<VehicleDataItem> loadVehicleDataPicker() {
        VehicleDataItem loadItem = new VehicleDataItem();

        ArrayList<PickerItem> dictItems = new ArrayList<>();

        // 性别(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.XingBie, "XTZL_XB"));
        // 与逝者关系(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.YuShiZheGuanXi, "YWZL_YSZGX"));
        // 死亡原因(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.SiWangYuanYin, "YWZL_SWYY"));
        // 车辆用途(主键)
        dictItems.addAll(itemsDao.getItemDetailsIdByCode(PickerType.CheLiangYongTu, "YWZL_CLYT"));

        loadItem.setDictItems(dictItems);

        // 预约车型
        loadItem.setVehicleTypes(serviceDao.listPickerServiceByParentId(117));

        return success(loadItem);
    }

    /**
     * 新增接运任务接口
     *
     * @param loginItem 当前账号
     * @param data      接运任务信息
     * @return
     * @author LiCongLu
     * @date 2020-08-10 13:11
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveVehicleTask(LoginItem loginItem, VehicleTaskData data) throws ActionException {

        // 生成业务编号
        String operationNo = globalTool.createOperationNo();
        String randomId = DataUtil.getUUID();
        DeadBasicItem deadItem = new DeadBasicItem();
        deadItem.setOperationNo(operationNo);
        if (DataUtil.invalid(deadItem.getOperationNo())) {
            throw exception("生成新业务编号错误");
        }
        deadItem.setDeadName(DataUtil.invalid(data.getDeadName()) ? "" : data.getDeadName())
                .setDeadAge(data.getDeadAge())
                .setDeadSex(data.getDeadSex())
                .setDeathCause(data.getDeathCause());
        // 新增逝者
        deadDao.insertDeadBasicItem(deadItem, loginItem);

        // 家属信息
        RelationItem relationItem = new RelationItem();
        relationItem.setOperationNo(deadItem.getOperationNo())
                .setRelationName(DataUtil.invalid(data.getRelationName()) ? "" : data.getRelationName())
                .setRelationPhone(data.getRelationPhone())
                .setRelationAddress(data.getRelationAddress())
                .setDeadRelation(data.getDeadRelation());
        relationDao.insertRelationItem(relationItem, loginItem);
        if (DataUtil.invalid(relationItem.getRelationId())) {
            throw exception("保存家属信息失败");
        }

        // 接运费用信息
        ServiceItem typeItem = serviceDao.getServiceItemById(data.getVehicleTypeId());
        if (typeItem == null || DataUtil.invalid(typeItem.getId())) {
            throw exception("预约车型错误，未找到此车型");
        }

        // 接运信息
        VehicleManageItem vehicleItem = new VehicleManageItem();
        vehicleItem.setVehicleId(randomId)
                .setOperationNo(operationNo)
                .setLinkmanName(data.getRelationName())
                .setLinkmanPhone(data.getRelationPhone())
                .setDeadRelation(data.getDeadRelation())
                .setCarryPlace(data.getCarryPlace())
                .setCarryTime(data.getCarryTime())
                .setCheLiangYongTu(data.getCheLiangYongTu())
                .setRemark(data.getRemark())
                .setRandomId(randomId)
                .setChargeStandard(typeItem.getPrice())
                .setFactStandard(typeItem.getPrice())
                .setBespeakVehicleType(typeItem.getId())
                .setCarryState(Constants.CarryState.ShangWeiChuChe);

        // 插入接运信息
        vehicleManageDao.insertVehicleManage(vehicleItem, loginItem);

        // 接运费用
        ChargeItem chargeTypeItem = new ChargeItem();
        chargeTypeItem.setOperationNo(operationNo);
        chargeTypeItem.setServiceCode(40);
        chargeTypeItem.setRandomId(randomId);
        chargeTypeItem.setItemId(typeItem.getId());
        chargeTypeItem.setNumber(new BigDecimal(1));
        chargeTypeItem.setPrice(typeItem.getPrice());
        chargeTypeItem.setCharge(typeItem.getPrice());
        chargeTypeItem.setChargeDate(DateUtil.formatPattern16(new Date()))
                .setProductFrom(0);
        // 插入接运费用
        chargeDao.insertChargeItem(chargeTypeItem, loginItem.getUserId());
        if (DataUtil.invalid(chargeTypeItem.getId())) {
            throw exception("添加接运费用失败");
        }

        // 添加日志
        String logContent = StringUtil.format("车辆信息登记,操作人员【{0}】,逝者【{1}】【{2}】,联系人姓名【{3}】，联系电话【{4}】，预约车型【{5}】，预约时间【{6}】，接尸地点【{7}】。"
                , loginItem.getRealName(), deadItem.getOperationNo(), deadItem.getDeadName(), relationItem.getRelationName(), relationItem.getRelationPhone(), typeItem.getName(), vehicleItem.getCarryTime(), vehicleItem.getCarryPlace());
        businessLogDao.insertBusinessLog(loginItem.getUserId(), loginItem.getRealName(), BusinessConst.BusinessType.CheLiang, "接运办理", BusinessConst.OperateType.TianJia, logContent, deadItem.getOperationNo());


        return success("添加完成");
    }
}
