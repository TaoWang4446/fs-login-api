package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseResult;
import com.jmdz.common.ext.ArrayListExt;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.*;
import com.jmdz.fushan.model.config.BusinessServiceType;
import com.jmdz.fushan.model.config.PickerType;
import com.jmdz.fushan.pad.model.IdData;
import com.jmdz.fushan.pad.model.business.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * BusinessService
 *
 * @author LiCongLu
 * @date 2020-07-31 08:51
 */
@Service("businessService")
public class BusinessService extends BaseService {

    @Resource
    private ItemsDao itemsDao;

    @Resource
    private UserDao userDao;

    @Resource
    private AreaDao areaDao;

    @Resource
    private ServiceDao serviceDao;

    @Resource
    private DeadDao deadDao;

    @Resource
    private MournDao mournDao;

    /**
     * 加载字典数据等业务基础数据接口
     *
     * @return
     * @author LiCongLu
     * @date 2020-07-31 10:14
     */
    public BaseResult<BusinessDataItem> loadBusinessDataPicker() {
        BusinessDataItem loadItem = new BusinessDataItem();

        // 加载字典数据
        ArrayList<PickerItem> dictItems = new ArrayList<>();
        // 性别(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.XingBie, "XTZL_XB"));
        // 证件类型(主键)
        dictItems.addAll(itemsDao.getItemDetailsIdByCode(PickerType.ZhengJianLeiXing, "XTZL_ZJLX"));
        // 遗体类型(主键)
        dictItems.addAll(itemsDao.getItemDetailsIdByCode(PickerType.YiTiLeiXing, "YWZL_YTLX"));
        // 遗体状态(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.YiTiZhuangTai, "YWZL_YTZT"));
        // 死亡原因(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.SiWangYuanYin, "YWZL_SWYY"));
        // 民族(主键)
        dictItems.addAll(itemsDao.getItemDetailsIdByCode(PickerType.MinZu, "XTZL_MZ"));
        // 国籍(主键)
        dictItems.addAll(itemsDao.getItemDetailsIdByCode(PickerType.GuoJi, "XTZL_GJ"));
        // 减免类型(主键)-惠民类型
        dictItems.addAll(itemsDao.getItemDetailsIdByCode(PickerType.JianMianLeiXing, "YWZL_MXTC"));
        // 骨灰处理方式(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.GuHuiChuLiFangShi, "YWZL_GHCLFS"));
        // 与逝者关系(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.YuShiZheGuanXi, "YWZL_YSZGX"));
        // 馆外司机(文本)
        dictItems.addAll(itemsDao.getItemNameByCode(PickerType.GuanWaiSiJi, "YWZL_GWSJ"));
        // 洽谈司机(主键)
        dictItems.addAll(userDao.listPickerUserByRoleCode(PickerType.QiaTanYuan, "CDDJ_YeWuQiaTan"));
        loadItem.setDictItems(dictItems);

        // 省行政区划
        loadItem.setProvinceItems(areaDao.listPickerAreaByPid(0));

        // 冷藏格位类型
        loadItem.setEquipmentTypes(serviceDao.listPickerServiceByParentId(118));
        // 火化炉型
        loadItem.setMachineTypes(serviceDao.listPickerServiceByParentId(119));
        // 告别词模板
        loadItem.setEulogyItems(mournDao.listPickerEulogy());

        return success(loadItem);
    }

    /**
     * 加载子行政区划接口
     *
     * @param data 父主键
     * @return
     * @author LiCongLu
     * @date 2020-08-03 09:45
     */
    public BaseResult loadAreaPickerWithPid(IdData data) {
        ArrayList<PickerItem> loadItems = areaDao.listPickerAreaByPid(data.getId());
        return success(loadItems);
    }


    /**
     * 按照类型加载服务和物品接口
     *
     * @param data 服务和物品类型
     * @return
     * @author LiCongLu
     * @date 2020-08-03 11:49
     */
    public BaseResult<ArrayList<BusinessServiceItem>> loadBusinessServiceListWithType(ServiceTypeData data) {
        DeadBasicItem deadBasicItem = deadDao.getDeadBasicByOperationNo(data.getOperationNo());
        if (deadBasicItem == null || DataUtil.invalid(deadBasicItem.getOperationNo())) {
            return failure("业务编号错误，未找到此逝者");
        }

        ArrayList<BusinessServiceItem> loadItems = listBusinessServiceItems(data.getType());
        return success(loadItems);
    }

    /**
     * 获取不同业务的服务和物品
     *
     * @param serviceType 业务类型
     * @return
     * @author LiCongLu
     * @date 2020-08-04 09:55
     */
    public ArrayList<BusinessServiceItem> listBusinessServiceItems(String serviceType) {
        // 父类主键
        String parentId = null;
        // 所属业务
        String belongService = "";
        ArrayList<BusinessServiceItem> loadItems = new ArrayList<>();
        // 忽略字段
        ArrayListExt<Integer> ignoreList = new ArrayListExt<>();

        switch (serviceType) {
            case BusinessServiceType.JieYun:
                // 接运
                parentId = "114,122";
                belongService = "40";
                break;
            case BusinessServiceType.LengCang:
                // 冷藏
                parentId = "114,115,122";
                belongService = "50";
                break;
            case BusinessServiceType.ZhengRong:
                // 整容
                parentId = "115";
                break;
            case BusinessServiceType.GaoBie:
                // 告别
                parentId = "114,122";
                belongService = "20";
                break;
            case BusinessServiceType.ShouLing:
                // 守灵
                parentId = "114,122";
                belongService = "25";
                break;
            case BusinessServiceType.HuoHua:
                // 火化
                parentId = "114,122";
                belongService = "70";
                break;
            case BusinessServiceType.SangZangYongPin:
                // 殡葬用品
                parentId = "114,2099";
                break;
            case BusinessServiceType.GuHuiHe:
                // 骨灰盒
                parentId = "113";
                break;
            case BusinessServiceType.ShouYi:
                // 寿衣
                parentId = "2354";
                break;
            case BusinessServiceType.ZhiGuan:
                // 纸棺
                parentId = "3141";
                break;
            case BusinessServiceType.XianHua:
                // 鲜花
                parentId = "3142";
                break;
            case BusinessServiceType.FuWuXiangMu:
                // 服务项目
                parentId = "122,116";
                belongService = "60";
                ignoreList.add(2460);
                break;
        }

        // 当查询条件存在时，查询服务和物品
        if (DataUtil.valid(parentId)) {
            ArrayList<BusinessServiceItem> loadAllItems = serviceDao.listBusinessServiceByBelong(parentId, belongService);
            for (BusinessServiceItem loadItem : loadAllItems) {
                if (!ignoreList.contains(loadItem.getId())) {
                    loadItems.add(loadItem);
                }
            }
        }
        return loadItems;
    }

    /**
     * 获取不同业务的服务和物品，以HashMap的形式返回
     *
     * @param serviceType 业务类型
     * @return
     * @author LiCongLu
     * @date 2020-08-04 10:16
     */
    public HashMap<Integer, BusinessServiceItem> getBusinessServiceMap(String serviceType) {
        ArrayList<BusinessServiceItem> loadItems = listBusinessServiceItems(serviceType);
        HashMap<Integer, BusinessServiceItem> loadMap = new HashMap<>(16);
        for (BusinessServiceItem loadItem : loadItems) {
            loadMap.put(loadItem.getId(), loadItem);
        }
        return loadMap;
    }
}
