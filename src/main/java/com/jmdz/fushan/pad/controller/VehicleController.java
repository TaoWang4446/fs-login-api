package com.jmdz.fushan.pad.controller;

import com.jmdz.common.base.BaseResult;
import com.jmdz.fushan.model.entity.RecordImage;
import com.jmdz.fushan.pad.base.LoginHandler;
import com.jmdz.fushan.pad.model.ChargeItem;
import com.jmdz.fushan.pad.model.OperationNoData;
import com.jmdz.fushan.pad.model.OperationNoIdData;
import com.jmdz.fushan.pad.model.UserData;
import com.jmdz.fushan.pad.model.business.BusinessDataItem;
import com.jmdz.fushan.pad.model.business.DeadBasicData;
import com.jmdz.fushan.pad.model.business.DeadBasicItem;
import com.jmdz.fushan.pad.model.business.PickerItem;
import com.jmdz.fushan.pad.model.vehicle.*;
import com.jmdz.fushan.pad.service.BusinessBasicService;
import com.jmdz.fushan.pad.service.RecordService;
import com.jmdz.fushan.pad.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 遗体接运相关接口
 *
 * @author LiCongLu
 * @date 2020-07-09 09:55
 */
@Api(tags = "遗体接运相关接口", description = "遗体接运(上门收敛)相关接口")
@RestController()
@RequestMapping("/pad/vehicle")
public class VehicleController extends LoginHandler {

    @Resource
    private VehicleService vehicleService;

    @Resource
    private BusinessBasicService businessBasicService;

    @Resource
    private RecordService recordService;

    /**
     * 加载遗体接运任务列表
     *
     * @param data 当前数据
     * @return
     * @author LiCongLu
     * @date 2020-07-09 10:06
     */
    @ApiOperation(value = "加载遗体接运任务列表接口", notes = "接运任务列表加载接口")
    @PostMapping(value = "/load-vehicle-task-list")
    public BaseResult<ArrayList<VehicleTaskListItem>> loadVehicleTaskList(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> vehicleService.loadVehicleTaskList(loginItem));
    }

    /**
     * 加载逝者身份信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 13:19
     */
    @ApiOperation(value = "加载逝者身份信息接口", notes = "加载逝者身份信息接口")
    @PostMapping(value = "/load-dead-basic-item")
    public BaseResult<DeadBasicItem> loadDeadBasicItem(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> businessBasicService.loadDeadBasicItem(data));
    }

    /**
     * 逝者详情保存接口，不带有图片文件
     *
     * @param data 图片数据
     * @return
     * @author LiCongLu
     * @date 2020-07-09 13:59
     */
    @ApiOperation(value = "逝者详情保存接口", notes = "逝者详情保存接口，不带身份证图片")
    @PostMapping(value = "/save-dead-basic-item")
    public BaseResult saveDeadBasicItem(@RequestBody DeadBasicData data) {
        return loginHandler(data, loginItem -> businessBasicService.saveDeadBasicItemFile(loginItem, data, null));
    }

    /**
     * 逝者详情保存接口，带有图片文件
     *
     * @param data 图片数据
     * @return
     * @author LiCongLu
     * @date 2020-07-09 13:59
     */
    @ApiOperation(value = "逝者详情保存接口", notes = "逝者详情保存接口，可以有图片文件")
    @PostMapping(value = "/save-dead-basic-item-file")
    public BaseResult saveDeadBasicItemFile(DeadBasicData data, @RequestParam(value = "file", required = false) MultipartFile file) {
        return loginHandler(data, loginItem -> businessBasicService.saveDeadBasicItemFile(loginItem, data, file));
    }

    /**
     * 加载图片列表接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:13
     */
    @ApiOperation(value = "加载图片列表接口", notes = "加载图片列表接口")
    @PostMapping(value = "/load-record-image-list")
    public BaseResult<ArrayList<RecordImage>> loadRecordImageList(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> recordService.loadRecordImageList(data));
    }

    /**
     * 上传图片文件接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:26
     */
    @ApiOperation(value = "上传图片文件接口", notes = "上传图片文件")
    @PostMapping(value = "/upload-record-image")
    public BaseResult uploadRecordImage(OperationNoData data, @RequestParam("file") MultipartFile file) {
        return loginHandler(data, loginItem -> recordService.uploadRecordImage(loginItem, data, file));
    }

    /**
     * 删除图片文件接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:29
     */
    @ApiOperation(value = "删除图片文件接口", notes = "删除图片文件接口")
    @PostMapping(value = "/delete-record-image")
    public BaseResult deleteRecordImage(@RequestBody OperationNoIdData data) {
        return loginHandler(data, loginItem -> recordService.deleteRecordImage(loginItem, data));
    }

    /**
     * 保存实时定位接口
     *
     * @param data 定位数据
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:58
     */
    @ApiOperation(value = "保存实时定位接口", notes = "保存实时定位接口")
    @PostMapping(value = "/save-location")
    public BaseResult saveLocation(@RequestBody LocationData data) {
        return loginHandler(data, loginItem -> vehicleService.saveLocation(loginItem, data));
    }

    /**
     * 接运任务出车接口
     *
     * @param data 业务数据
     * @return
     * @author LiCongLu
     * @date 2020-07-10 08:48
     */
    @ApiOperation(value = "接运任务出车接口", notes = "接运任务出车接口")
    @PostMapping(value = "/save-vehicle-out")
    public BaseResult saveVehicleOut(@RequestBody VehicleOutData data) {
        return loginHandler(data, loginItem -> vehicleService.saveVehicleOut(loginItem, data));
    }

    /**
     * 接运任务回车接口
     *
     * @param data 业务数据
     * @return
     * @author LiCongLu
     * @date 2020-07-10 08:48
     */
    @ApiOperation(value = "接运任务回车接口", notes = "接运任务回车接口")
    @PostMapping(value = "/save-vehicle-back")
    public BaseResult saveVehicleBack(@RequestBody VehicleIdData data) {
        return loginHandler(data, loginItem -> vehicleService.saveVehicleBack(loginItem, data));
    }

    /**
     * 加载接运随车服务物品费用列表接口
     *
     * @param data 业务数据
     * @return
     * @author LiCongLu
     * @date 2020-07-10 10:26
     */
    @ApiOperation(value = "加载接运随车服务物品费用列表接口", notes = "加载接运随车服务物品费用列表接口")
    @PostMapping(value = "/load-vehicle-charge-list")
    public BaseResult<ArrayList<ChargeItem>> loadVehicleChargeList(@RequestBody VehicleIdData data) {
        return loginHandler(data, loginItem -> vehicleService.loadVehicleChargeList(data));
    }

    /**
     * 保存物品服务费用接口
     *
     * @param data 物品服务费用
     * @return
     * @author LiCongLu
     * @date 2020-07-10 11:18
     */
    @ApiOperation(value = "保存物品服务费用接口", notes = "保存物品服务费用接口")
    @PostMapping(value = "/save-vehicle-charge")
    public BaseResult saveVehicleCharge(@RequestBody VehicleChargeData data) {
        return loginHandler(data, loginItem -> vehicleService.saveVehicleCharge(loginItem, data));
    }

    /**
     * 加载二维码信息接口
     *
     * @param data 业务编号
     * @return
     * @author LiCongLu
     * @date 2020-07-16 08:45
     */
    @ApiOperation(value = "加载二维码信息接口", notes = "加载二维码信息接口")
    @PostMapping(value = "/load-qr-code")
    public BaseResult<QrCodeItem> loadQrCode(@RequestBody OperationNoData data) {
        return loginHandler(data, loginItem -> vehicleService.loadQrCode(loginItem, data));
    }

    /**
     * 加载字典数据等车辆基础数据接口
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-08-10 11:39
     */
    @ApiOperation(value = "加载字典数据等车辆基础数据接口", notes = "加载字典数据等车辆基础数据接口,包含性别、车型等数据")
    @PostMapping(value = "/load-vehicle-data-picker")
    public BaseResult<VehicleDataItem> loadVehicleDataPicker(@RequestBody UserData data) {
        return loginHandler(data, loginItem -> vehicleService.loadVehicleDataPicker());
    }

    /**
     * 新增接运任务接口
     *
     * @param data 接运任务信息
     * @return
     * @author LiCongLu
     * @date 2020-08-10 13:11
     */
    @ApiOperation(value = "新增接运任务接口", notes = "新增接运任务接口")
    @PostMapping(value = "/save-vehicle-task")
    public BaseResult saveVehicleTask(@RequestBody VehicleTaskData data) {
        return loginHandler(data, loginItem -> vehicleService.saveVehicleTask(loginItem,data));
    }
}
