package com.jmdz.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bean工具类
 *
 * @author LiCongLu
 * @date 2019-12-13 16:35
 */
public final class BeanUtil {
    /**
     * 转化Bean对象为HashMap<String,String>对象
     *
     * @param bean 实体
     * @param <T>
     * @return
     */
    public static <T> HashMap<String, String> toHashMap(T bean) {
        HashMap<String, String> hashMap = new HashMap<>(16);
        if (bean != null) {
            HashMap<String, Field> fieldMap = ReflectUtil.getFieldMap(bean);
            for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
                try {
                    Field field = entry.getValue();
                    hashMap.put(entry.getKey(), StringUtil.getObjectValue(field.get(bean)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return hashMap;
    }

    /**
     * 复制属性值
     * 只支持单层的基础属性复制，非深度复制
     *
     * @param source 数据来源
     * @param target 被覆盖对象
     * @param <S>
     * @param <T>
     */
    public static <S, T> T copy2Bean(S source, T target) {
        if (source != null && target != null) {
            HashMap<String, Field> sourceMap = ReflectUtil.getFieldMap(source);
            HashMap<String, Field> targetMap = ReflectUtil.getFieldMap(target);
            for (Map.Entry<String, Field> entry : sourceMap.entrySet()) {
                try {
                    String key = entry.getKey();
                    if (targetMap.containsKey(key)) {
                        Field field = entry.getValue();
                        try {
                            targetMap.get(key).set(target, field.get(source));
                        } catch (Exception e) {
                            ReflectUtil.setFieldValue(targetMap.get(key), target, StringUtil.getObjectValue(field.get(source)));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return target;
    }

    /**
     * 遍历复制属性值
     *
     * @param sourceList 源数据集合
     * @param clazz 目标集合实体类型
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> ArrayList<T> copy2List(List<S> sourceList, Class<T> clazz) {
        ArrayList<T> targetList = new ArrayList<>();

        // 遍历创建实体赋值
        if (sourceList != null && sourceList.size() > 0) {
            try {
                for (S source : sourceList) {
                    T target = (T) clazz.newInstance();
                    targetList.add(BeanUtil.copy2Bean(source, target));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return targetList;
    }
}
