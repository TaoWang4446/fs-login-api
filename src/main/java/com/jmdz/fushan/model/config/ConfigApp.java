package com.jmdz.fushan.model.config;

import com.jmdz.common.base.BaseBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ConfigApp
 *
 * @author LiCongLu
 * @date 2020-05-29 11:32
 */
@Component("configApp")
public class ConfigApp extends BaseBean {

    @Value("${file.dir-path}")
    private String dirPath;

    public String getDirPath() {
        return dirPath;
    }

    public ConfigApp setDirPath(String dirPath) {
        this.dirPath = dirPath;
        return this;
    }
}
