package com.jmdz.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * reflect工具类
 *
 * @author LiCongLu
 * @date 2019-12-13 16:17
 */
public final class ReflectUtil {

    /**
     * 判断是否非Object.class，即是否顶层类，是否无父类
     *
     * @param clazz
     * @return
     */
    public static boolean noObjectClass(Class clazz) {
        return clazz != null && clazz != Object.class && !"java.lang.object".equals(clazz.getName().toLowerCase());
    }

    /**
     * 反射设置字段值
     *
     * @param field
     * @param bean
     * @param value
     */
    public static <T> void setFieldValue(Field field, T bean, String value) {
        try {
            value = DataUtil.valid(value) ? value : "";
            if (field != null && bean != null) {
                // 不处理静态变量或常量
                int modifier = field.getModifiers();
                if (Modifier.isStatic(modifier) && Modifier.isFinal(modifier)) {
                    return;
                }
                Class clazz = field.getType();
                if (clazz == String.class) {
                    field.set(bean, value);
                } else if (DataUtil.valid(value)) {
                    if (clazz == boolean.class) {
                        field.setBoolean(bean, Boolean.parseBoolean(value));
                    } else if (clazz == Boolean.class) {
                        field.set(bean, new Boolean(value));
                    } else if (clazz == byte.class) {
                        field.setByte(bean, Byte.parseByte(value));
                    } else if (clazz == Byte.class) {
                        field.set(bean, new Byte(value));
                    } else if (clazz == char.class && value.length() > 0) {
                        field.setChar(bean, value.charAt(0));
                    } else if (clazz == Date.class) {
                        field.set(bean, DateUtil.parsePattern(value));
                    } else if (DataUtil.isNumeric(value)) {
                        if (clazz == short.class) {
                            field.setShort(bean, Short.parseShort(value));
                        } else if (clazz == Short.class) {
                            field.set(bean, new Short(value));
                        } else if (clazz == int.class) {
                            field.setInt(bean, Integer.parseInt(value));
                        } else if (clazz == Integer.class) {
                            field.set(bean, new Integer(value));
                        } else if (clazz == float.class) {
                            field.setFloat(bean, Float.parseFloat(value));
                        } else if (clazz == Float.class) {
                            field.set(bean, new Float(value));
                        } else if (clazz == double.class) {
                            field.setDouble(bean, Double.parseDouble(value));
                        } else if (clazz == Double.class) {
                            field.set(bean, new Double(value));
                        } else if (clazz == long.class) {
                            field.setLong(bean, Long.parseLong(value));
                        } else if (clazz == Long.class) {
                            field.set(bean, new Long(value));
                        } else if (clazz == BigDecimal.class) {
                            field.set(bean, new BigDecimal(value));
                        } else {
                            field.set(bean, value);
                        }
                    } else {
                        field.set(bean, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性Field的Map集
     *
     * @param bean 实体对象
     * @param <T>
     */
    public static <T> HashMap<String, Field> getFieldMap(T bean) {
        HashMap<String, Field> fieldMap = new HashMap<>(16);
        if (bean != null) {
            Class<? extends Object> clazz = bean.getClass();
            return getFieldMapByClass(clazz);
        }
        return fieldMap;
    }

    /**
     * 利用类获取Field的Map集
     *
     * @param clazz 对象类型
     * @return
     */
    public static HashMap<String, Field> getFieldMapByClass(Class<?> clazz) {
        HashMap<String, Field> fieldMap = new HashMap<>(16);
        try {

            HashSet<String> keys = new HashSet<String>();
            while (ReflectUtil.noObjectClass(clazz)) {
                // 获取自己声明的各种字段
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {

                    // 不处理静态变量或常量
                    int modifier = field.getModifiers();
                    if (Modifier.isStatic(modifier) && Modifier.isFinal(modifier)) {
                        continue;
                    }

                    if (Modifier.isPrivate(field.getModifiers())) {
                        // private 标记的需设置此项，否则无法获取数值
                        field.setAccessible(true);
                    }
                    String tKey = field.getName();
                    if (keys.contains(tKey)) {
                        continue;
                    }
                    keys.add(tKey);
                    fieldMap.put(tKey, field);
                }
                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldMap;
    }
}