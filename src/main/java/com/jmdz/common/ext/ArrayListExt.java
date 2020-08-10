package com.jmdz.common.ext;

import java.util.ArrayList;

/**
 * ArrayList 扩展类
 *
 * @author LiCongLu
 * @date 2019-12-13 16:06
 */
@SuppressWarnings("unchecked")
public class ArrayListExt<T> extends ArrayList<T> {
    public ArrayListExt() {
    }


    public ArrayListExt<T> clearExt() {
        this.clear();
        return this;
    }

    /**
     * 添加多个实体
     *
     * @param items 动态参数
     * @return
     * @author LiCongLu
     * @date 2019-12-13 16:06
     */
    public ArrayListExt<T> addExt(T... items) {
        if (items != null) {
            for (T item : items) {
                this.add(item);
            }
        }
        return this;
    }

    /**
     * 添加多个实体，但进行重复验证，即包含则只保留一个
     *
     * @param items 动态参数
     * @return
     * @author LiCongLu
     * @date 2019-12-16 15:16
     */
    public ArrayListExt<T> addSingle(T... items) {
        if (items != null) {
            for (T item : items) {
                if (!this.contains(item)) {
                    this.add(item);
                }
            }
        }
        return this;
    }
}
