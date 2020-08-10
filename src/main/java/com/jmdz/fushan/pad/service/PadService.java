package com.jmdz.fushan.pad.service;

import com.jmdz.common.base.BaseData;
import com.jmdz.common.base.BaseResult;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.third.DESUtil;
import com.jmdz.common.util.third.MD5Util;
import com.jmdz.fushan.base.BaseService;
import com.jmdz.fushan.dao.AppDeviceDao;
import com.jmdz.fushan.dao.UserDao;
import com.jmdz.fushan.model.config.ConfigData;
import com.jmdz.fushan.model.config.Messages;
import com.jmdz.fushan.pad.model.login.LoginData;
import com.jmdz.fushan.pad.model.login.LoginItem;
import com.jmdz.fushan.pad.model.login.LoginUserItem;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * PadService
 *
 * @author LiCongLu
 * @date 2020-06-05 13:35
 */
@Service("padService")
public class PadService extends BaseService {

    @Resource
    private UserDao userDao;

    @Resource
    private Cache applicationCache;

    @Resource
    private AppDeviceDao appDeviceDao;

    @Resource
    private ConfigData configData;

    /**
     * 验证登录账号，并不缓存账号
     *
     * @param data 登录账号及密码
     * @return
     * @author LiCongLu
     * @date 2020-06-05 13:30
     */
    public BaseResult<LoginItem> login(LoginData data) {
        // 查询登录
        LoginUserItem userItem = userDao.getLoginUserByAccount(data.getAccount());
        if (userItem == null || DataUtil.invalid(userItem.getUserId())) {
            return failure(Messages.ACCOUNT_PASSWORD_ERROR);
        }

        // 验证密码
        String dbPassword = MD5Util.MD5(DESUtil.encrypt(data.getPassword(), userItem.getSecretKey()), MD5Util.code32);
        if (!DataUtil.equals(dbPassword, userItem.getPassword())) {
            return failure(Messages.ACCOUNT_PASSWORD_ERROR);
        }

        // 验证状态
        if (DataUtil.invalid(userItem.getEnabled())) {
            return failure("此用户已被禁用");
        }

        // 验证设备号
        if (configData.isAppDeviceStartup()) {
            String deviceId = data.getImei();
            if (DataUtil.invalid(data.getImei())) {
                return failure("未能找到识别号，不能进行登录");
            }
            Integer appId = appDeviceDao.getAppIdByDeviceId(deviceId);
            if (DataUtil.invalid(appId)) {
                return failure("设备未注册，不能进行登录");
            }
        }

        // 返回实体
        LoginItem loginItem = BeanUtil.copy2Bean(userItem, new LoginItem());
        loginItem.setLoginId(DataUtil.getUUID());
        applicationCache.put(loginItem.getLoginId(), BeanUtil.copy2Bean(userItem, new LoginItem()));
        return success(loginItem);
    }


    /**
     * 注册移动设备
     *
     * @param data 请求数据
     * @return
     * @author LiCongLu
     * @date 2020-07-08 17:22
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseResult saveDeviceImei(BaseData data) throws ActionException {
        String deviceId = data.getData();
        if (DataUtil.invalid(deviceId)) {
            return failure("注册码不能为空");
        }
        Integer appId = appDeviceDao.getAppIdByDeviceId(deviceId);
        if (DataUtil.invalid(appId)) {
            appDeviceDao.insertDeviceId(deviceId);
        }
        return success("注册完成");
    }
}
