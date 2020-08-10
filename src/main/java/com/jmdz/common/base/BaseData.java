package com.jmdz.common.base;

/**
 * BaseData
 *
 * @author LiCongLu
 * @date 2020-06-12 15:50
 */
public class BaseData extends BaseBean {

    /**
     * 预留，非继承请求数据时，存储JSON数据
     */
    private String data;


    /**
     * 预留，非继承请求数据时，存储JSON数据
     */
    private String action;

    public String getData() {
        return data;
    }

    public BaseData setData(String data) {
        this.data = data;
        return this;
    }

    public String getAction() {
        return action;
    }

    public BaseData setAction(String action) {
        this.action = action;
        return this;
    }
}
