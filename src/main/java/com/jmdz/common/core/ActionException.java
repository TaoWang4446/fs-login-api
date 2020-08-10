package com.jmdz.common.core;

/**
 * 自定义处理异常
 * 主要事务回滚抛出异常
 *
 * @author LiCongLu
 * @date 2020-02-06 12:30
 */
public class ActionException extends RuntimeException {
    public ActionException(String message) {
        super(message);
    }
}
