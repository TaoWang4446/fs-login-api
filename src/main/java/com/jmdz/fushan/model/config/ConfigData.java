package com.jmdz.fushan.model.config;

import com.jmdz.common.base.BaseBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * ConfigData
 *
 * @author LiCongLu
 * @date 2020-05-29 11:32
 */
@Component("configData")
@PropertySource(value = {"classpath:config-data.properties"}, encoding = "utf-8")
public class ConfigData extends BaseBean {

    /**
     * 定时是否开启
     */
    @Value("${quartz.startup:false}")
    private boolean startup;

    /**
     * 定时触发器cron
     */
    @Value("${quartz.global.cron-expression}")
    private String cronExpression;

    /**
     * 访问固定token
     */
    @Value("${app.token}")
    private String appToken;

    /**
     * 是否开启验证AppDevice编码,false，不开启；true，开启
     */
    @Value("${app.device.startup:false}")
    private boolean appDeviceStartup;

    /**
     * 告别厅PAD服务套餐编码
     */
    @Value("${pad.service.suit.items_code}")
    private String padServiceSuitItemsCode;

    /**
     * 告别厅附加服务的控制条件1.父类主键，格式是114,122的字符串
     */
    @Value("${pad.farewell-service-parent}")
    private String padFarewellServiceParent;

    /**
     * 告别厅附加服务的控制条件2.服务业务，格式是30,60的字符串
     */
    @Value("${pad.farewell-service-belong}")
    private String padFarewellServiceBelong;

    /**
     * 接运附加服务的控制条件1.父类主键，格式是114,122的字符串
     */
    @Value("${dict.vehicle-service-parent}")
    private String vehicleServiceParent;

    /**
     * 接运附加服务的控制条件2.服务业务，格式是30,60的字符串
     */
    @Value("${dict.vehicle-service-belong}")
    private String vehicleServiceBelong;

    /**
     * 费用顶层父类举例主键多个时英文逗号隔开，是110,111,112的字符串
     */
    @Value("${pad.leader-service-parent:110,111,112}")
    private String leaderServiceParent;

    /**
     * #火化炉类型父类主键
     */
    @Value("${pad.leader-cremating-machine-parent:119}")
    private String leaderCrematingMachineParent;

    /**
     * 告别厅特殊厅主键1.主键多个时英文逗号隔开，主键的顺序即告别厅统计显示的数据，举例是16,27,15的字符串
     */
    @Value("${pad.leader-farewell-hall-id:16,27,15}")
    private String leaderFarewellHallId;

    /**
     * 接运车辆类型父类主键
     */
    @Value("${pad.leader-vehicle-type-parent:117}")
    private String leaderVehicleTypeParent;

    /**
     * 纸棺父类主键
     */
    @Value("${pad.dead-coffin-parent-id:3141}")
    private Integer deadCoffinParentId;

    /**
     * 任务提醒消息读取时间，单位秒
     */
    @Value("${task.notice-view-time:180}")
    private Integer taskNoticeViewTime;

    public boolean isStartup() {
        return startup;
    }

    public ConfigData setStartup(boolean startup) {
        this.startup = startup;
        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public ConfigData setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public String getAppToken() {
        return appToken;
    }

    public ConfigData setAppToken(String appToken) {
        this.appToken = appToken;
        return this;
    }

    public boolean isAppDeviceStartup() {
        return appDeviceStartup;
    }

    public ConfigData setAppDeviceStartup(boolean appDeviceStartup) {
        this.appDeviceStartup = appDeviceStartup;
        return this;
    }

    public String getPadServiceSuitItemsCode() {
        return padServiceSuitItemsCode;
    }

    public ConfigData setPadServiceSuitItemsCode(String padServiceSuitItemsCode) {
        this.padServiceSuitItemsCode = padServiceSuitItemsCode;
        return this;
    }

    public String getPadFarewellServiceParent() {
        return padFarewellServiceParent;
    }

    public ConfigData setPadFarewellServiceParent(String padFarewellServiceParent) {
        this.padFarewellServiceParent = padFarewellServiceParent;
        return this;
    }

    public String getPadFarewellServiceBelong() {
        return padFarewellServiceBelong;
    }

    public ConfigData setPadFarewellServiceBelong(String padFarewellServiceBelong) {
        this.padFarewellServiceBelong = padFarewellServiceBelong;
        return this;
    }

    public String getVehicleServiceParent() {
        return vehicleServiceParent;
    }

    public ConfigData setVehicleServiceParent(String vehicleServiceParent) {
        this.vehicleServiceParent = vehicleServiceParent;
        return this;
    }

    public String getVehicleServiceBelong() {
        return vehicleServiceBelong;
    }

    public ConfigData setVehicleServiceBelong(String vehicleServiceBelong) {
        this.vehicleServiceBelong = vehicleServiceBelong;
        return this;
    }

    public String getLeaderServiceParent() {
        return leaderServiceParent;
    }

    public ConfigData setLeaderServiceParent(String leaderServiceParent) {
        this.leaderServiceParent = leaderServiceParent;
        return this;
    }

    public String getLeaderCrematingMachineParent() {
        return leaderCrematingMachineParent;
    }

    public ConfigData setLeaderCrematingMachineParent(String leaderCrematingMachineParent) {
        this.leaderCrematingMachineParent = leaderCrematingMachineParent;
        return this;
    }

    public String getLeaderFarewellHallId() {
        return leaderFarewellHallId;
    }

    public ConfigData setLeaderFarewellHallId(String leaderFarewellHallId) {
        this.leaderFarewellHallId = leaderFarewellHallId;
        return this;
    }

    public String getLeaderVehicleTypeParent() {
        return leaderVehicleTypeParent;
    }

    public ConfigData setLeaderVehicleTypeParent(String leaderVehicleTypeParent) {
        this.leaderVehicleTypeParent = leaderVehicleTypeParent;
        return this;
    }

    public Integer getDeadCoffinParentId() {
        return deadCoffinParentId;
    }

    public ConfigData setDeadCoffinParentId(Integer deadCoffinParentId) {
        this.deadCoffinParentId = deadCoffinParentId;
        return this;
    }

    public Integer getTaskNoticeViewTime() {
        return taskNoticeViewTime;
    }

    public ConfigData setTaskNoticeViewTime(Integer taskNoticeViewTime) {
        this.taskNoticeViewTime = taskNoticeViewTime;
        return this;
    }
}