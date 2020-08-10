package com.jmdz.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 验证辅助工具类
 *
 * @author LiCongLu
 * @date 2020-04-14 10:52
 */
public final class ValidateUtil {
    /**
     * 验证是否空字符串
     * 默认，返回false
     *
     * @param object 对象
     * @return
     * @author LiCongLu
     * @date 2020-03-10 10:32
     */
    public static boolean invalidString(Object object) {
        if (object == null) {
            return true;
        }
        // 验证字符串是否为空
        if (object instanceof String) {
            String value = ((String) object);
            return value.length() == 0 || value.trim().length() == 0;
        }
        return false;
    }

    /**
     * 验证是否空集合
     * 默认，返回false
     *
     * @param object 对象
     * @return
     * @author LiCongLu
     * @date 2020-03-10 10:34
     */
    public static boolean invalidList(Object object) {
        if (object == null) {
            return true;
        }
        // 验证集合是否为空
        if (object instanceof ArrayList) {
            return ((ArrayList) object).size() == 0;
        }
        return false;
    }

    /**
     * 验证是否不小于零
     * 默认，返回null，即非数字值类型
     *
     * @param object 对象
     * @return
     * @author LiCongLu
     * @date 2020-04-08 14:29
     */
    public static Boolean noLessZero(Object object) {
        // 如果是数值类型，则判断是否小于零
        if (object instanceof Short) {
            return (Short) object >= 0;
        } else if (object instanceof Integer) {
            return (Integer) object >= 0;
        } else if (object instanceof Float) {
            return (Float) object >= 0;
        } else if (object instanceof Double) {
            return (Double) object >= 0;
        } else if (object instanceof Long) {
            return (Long) object >= 0;
        } else if (object instanceof BigDecimal) {
            BigDecimal value = (BigDecimal) object;
            return value.compareTo(new BigDecimal(0)) >= 0;
        }
        return null;
    }

    /**
     * 验证是否大于零
     * 默认，返回null，即非数字值类型
     *
     * @param object 对象
     * @return
     * @author LiCongLu
     * @date 2020-04-14 10:16
     */
    public static Boolean greaterZero(Object object) {
        // 如果是数值类型，则判断是否小于零
        if (object instanceof Short) {
            return (Short) object > 0;
        } else if (object instanceof Integer) {
            return (Integer) object > 0;
        } else if (object instanceof Float) {
            return (Float) object > 0;
        } else if (object instanceof Double) {
            return (Double) object > 0;
        } else if (object instanceof Long) {
            return (Long) object > 0;
        } else if (object instanceof BigDecimal) {
            BigDecimal value = (BigDecimal) object;
            return value.compareTo(new BigDecimal(0)) > 0;
        }
        return null;
    }
}
