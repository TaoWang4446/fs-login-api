package com.jmdz.fushan.model.entity;

import com.jmdz.common.base.BaseBean;

/**
 * 资料目录实体类
 *
 * @author LiCongLu
 * @date 2020-06-12 15:10
 */
public class RecordList extends BaseBean {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 隐藏标记，默认非隐藏为0，隐藏时是1，有定义的常量值
     */
    private Integer hideFlag;

    /**
     * 目录名称
     */
    private String listName;

    /**
     * 目录标记，默认为0，
     */
    private Integer listCode;

    /**
     *
     * 目录排序
     */
    private Integer listOrder;

    /**
     * 更新帐号UserId
     */
    private String userId;

    /**
     * 更新时间
     */
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public RecordList setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getHideFlag() {
        return hideFlag;
    }

    public RecordList setHideFlag(Integer hideFlag) {
        this.hideFlag = hideFlag;
        return this;
    }

    public String getListName() {
        return listName;
    }

    public RecordList setListName(String listName) {
        this.listName = listName;
        return this;
    }

    public Integer getListCode() {
        return listCode;
    }

    public RecordList setListCode(Integer listCode) {
        this.listCode = listCode;
        return this;
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public RecordList setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public RecordList setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public RecordList setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
