package com.jmdz.common.wrapper;

import com.jmdz.common.annotation.AnValidate;
import com.jmdz.common.constant.EValidateCode;
import com.jmdz.common.util.ReflectUtil;
import com.jmdz.common.util.ValidateUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * validate 处理封装类
 *
 * @author LiCongLu
 * @date 2020-01-10 10:19
 */
@SuppressWarnings("unchecked")
public final class ValidateWrapper {
    /**
     * org.springframework.boot.devtools.restart.classloader.RestartClassLoader
     * 运行时普通自定义实体属于此Loader
     */
    private static String RESTART_CLASS_LOADER = "RestartClassLoader";

    /**
     * sun.misc.Launcher$AppClassLoader
     * 测试时普通自定义实体属于此Loader
     */
    private static String APP_CLASS_LOADER = "AppClassLoader";

    /**
     * 验证集合数据
     *
     * @param rootKey  rootKey
     * @param beanList 所要验证的数据
     * @return
     * @author LiCongLu
     * @date 2020-03-10 09:32
     */
    public static <T> HashMap<String, String> validateList(String rootKey, ArrayList<T> beanList) {
        if (!ValidateUtil.invalidString(rootKey)) {
            rootKey += ".";
        }

        HashMap<String, String> hashMap = new HashMap<>(16);
        if (!ValidateUtil.invalidList(beanList)) {
            // 得到反射类型
            Class<?> clazz = beanList.get(0).getClass();
            // 得到反射映射属性
            HashMap<String, Field> fieldMap = ReflectUtil.getFieldMapByClass(clazz);
            // 进行遍历判断
            for (T bean : beanList) {
                hashMap.putAll(validateBean(rootKey, fieldMap, bean));
            }
        }
        return hashMap;
    }


    /**
     * 验证数据结构
     *
     * @param rootKey rootKey
     * @param clazz   clazz
     * @param bean    所要验证的数据
     * @return
     * @author LiCongLu
     * @date 2020-01-10 10:53
     */
    public static <T> HashMap<String, String> validateBean(String rootKey, Class<?> clazz, T bean) {
        HashMap<String, Field> fieldMap = ReflectUtil.getFieldMapByClass(clazz);
        return validateBean(rootKey, fieldMap, bean);
    }

    /**
     * 验证数据结构
     *
     * @param rootKey  rootKey
     * @param fieldMap 反射Map
     * @param bean     所要验证的数据
     * @return
     * @author LiCongLu
     * @date 2020-03-10 09:32
     */
    private static <T> HashMap<String, String> validateBean(String rootKey, HashMap<String, Field> fieldMap, T bean) {
        // 准备拼接key
        if (!ValidateUtil.invalidString(rootKey)) {
            rootKey += ".";
        }

        // 创建返回结果
        HashMap<String, String> hashMap = new HashMap<>(16);
        if (fieldMap == null || fieldMap.size() == 0 || bean == null) {
            return hashMap;
        }

        // 利用反射遍历判断
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            Field field = entry.getValue();
            // 判断是否包含判断注解
            if (field.isAnnotationPresent(AnValidate.class)) {
                String key = entry.getKey();
                // 解析注解
                AnValidate anValidate = field.getAnnotation(AnValidate.class);
                String message = anValidate.message();
                String name = anValidate.name();
                // 如果没有配置name则获取当前属性名称
                if (ValidateUtil.invalidString(name)) {
                    name = field.getName();
                }

                // 获取属性值
                Object object;
                try {
                    object = field.get(bean);
                } catch (Exception e) {
                    object = null;
                }

                //  判断必填
                if (anValidate.required() || EValidateCode.REQUIRED == anValidate.code()) {
                    // 值为空，或者字符串为空
                    boolean emptyFlag = object == null || ValidateUtil.invalidString(object) || ValidateUtil.invalidList(object);
                    if (emptyFlag) {
                        message = EValidateCode.REQUIRED.message(message);
                        hashMap.put(rootKey + key, name + message);
                        continue;
                    }
                }

                // 如果值为空，则继续下一个循环
                if (object == null) {
                    continue;
                }

                // 如果是数组集合，且有数据，则验证数据
                if (object instanceof ArrayList) {
                    hashMap.putAll(validateList(key, ((ArrayList) object)));
                    continue;
                }

                // 如果值不为空，则继续判断数据类型
                ClassLoader classLoader = field.getType().getClassLoader();
                // 自定义类型，对此对象进行验证
                if (classLoader != null) {
                    // System.out.println(key + " ==> " + classLoader.getClass().getSimpleName() + "-->" + classLoader.getClass().getName());
                    if (classLoader.getClass() != null) {
                        String simpleName = classLoader.getClass().getSimpleName();
                        if (RESTART_CLASS_LOADER.equals(simpleName)
                                || APP_CLASS_LOADER.equals(simpleName)) {
                            // 深层次进一步验证
                            hashMap.putAll(validateBean(key, ReflectUtil.getFieldMapByClass(field.getType()), object));
                        }
                    }
                } else {
                    // 获取判断编码
                    EValidateCode validateCode = anValidate.code();
                    // 判断不小于零，即数值类型的值大于等于零
                    if (validateCode == EValidateCode.NO_LESS_ZERO) {
                        Boolean validateFlag = ValidateUtil.noLessZero(object);
                        // 当验证标记不为null表明数据进行了验证
                        if (validateFlag != null) {
                            // 为数值类型，且非不小于零，即小于零
                            if (!validateFlag) {
                                message = EValidateCode.NO_LESS_ZERO.message(message);
                                hashMap.put(rootKey + key, name + message);
                            }
                            continue;
                        }
                    }

                    // 判断是否大于零，即数值类型的值大于零
                    if (validateCode == EValidateCode.GREATER_ZERO) {
                        Boolean validateFlag = ValidateUtil.greaterZero(object);
                        // 当验证标记不为null表明数据进行了验证
                        if (validateFlag != null) {
                            // 为数值类型，且非大于零，即小于或等于零
                            if (!validateFlag) {
                                message = EValidateCode.GREATER_ZERO.message(message);
                                hashMap.put(rootKey + key, name + message);
                            }
                            continue;
                        }
                    }
                    // System.out.println(key + " ==> 包含日期在内的基本类型");
                }
            }
        }

        return hashMap;
    }
}
