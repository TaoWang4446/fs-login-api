package com.jmdz.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


/**
 * StringUtil
 *
 * @author LiCongLu
 * @date 2020-05-29 13:23
 */
public class StringUtil {

    public static boolean isEmpty(String temp) {
        if (temp == null) {
            return true;
        }
        temp = temp.trim();
        if ("".equals(temp) || "null".equalsIgnoreCase(temp)) {
            return true;
        }
        return false;
    }

    public static int str2Int(String temp) {
        if (isEmpty(temp)) {
            return 0;
        }
        try {
            return Integer.valueOf(temp);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long str2Long(String temp) {
        if (isEmpty(temp)) {
            return 0;
        }
        try {
            return Long.valueOf(temp);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * String转double
     *
     * @param str 输入数据
     * @return double
     */
    public static double strToDouble(String str) {
        double temp = 0.00;
        if (!isEmpty(str)) {

            try {
                temp = Double.parseDouble(str);

            } catch (Exception e) {
            } catch (Throwable e) {

            }
        }
        return temp;
    }

    /**
     * 比较两个字符串是否相等
     *
     * @param strA
     * @param strB
     * @return
     */
    public static boolean equals(String strA, String strB) {
        strA = isEmpty(strA) ? "" : strA;
        strB = isEmpty(strB) ? "" : strB;
        return strA.equals(strB);
    }

    /**
     * double 转String
     * 输入数据
     *
     * @return String
     */
    public static String doubleToStr(double doubleData) {
        String temp = "0.00";
        double num = doubleData;
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
            temp = df.format(num);

            if (temp.startsWith(".")) {
                temp = "0" + temp;
            }
            if (temp.endsWith(".00")) {
                temp = temp.replace(".00", "");
            } else if (temp.endsWith("0")) {
                temp = temp.substring(0, temp.length() - 1);
            }

        } catch (Exception e) {
        }
        return temp;

    }


    /**
     * 防C#里的字符串链接，使用{}格式链接字符串
     *
     * @param format 字符串
     * @param args   链接对象
     * @return String
     * @author LiCongLu
     * @date 2019-12-16 13:55
     */
    public static String format(String format, String... args) {
        String newValue = format;
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                newValue = newValue.replace("{" + i + "}", args[i] != null ? args[i] : "");
            }
        }
        return newValue;
    }

    /**
     * 串联字符串
     *
     * @param separator 间隔符号
     * @param values    字符串集合
     * @return String
     * @author LiCongLu
     * @date 2019-12-17 10:29
     */
    public static String join(String separator, ArrayList<String> values) {
        if (values != null && values.size() > 0) {
            return join(separator, values.toArray(new String[]{}));
        }
        return values != null ? values.toString() : "";
    }

    /**
     * 串联字符串
     * 在Spring环境里，推荐使用StringUtils.arrayToDelimitedString
     *
     * @param separator 间隔符号
     * @param args      字符串参数
     * @return
     * @author LiCongLu
     * @date 2019-12-17 10:34
     */
    public static String join(String separator, String... args) {
        StringBuilder builder = new StringBuilder();
        if (args != null && args.length > 0) {
            int length = args.length;
            for (int i = 0; i < length; i++) {
                if (DataUtil.valid(args[i])) {
                    if (i != length - 1) {
                        builder.append(args[i] + separator);
                    } else {
                        builder.append(args[i]);
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 默认使用逗号间隔链接字符串
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2019-12-27 14:02
     */
    public static String joinComma(String... args) {
        return join(",", args);
    }

    /**
     * 获取字符串值
     *
     * @param obj
     * @return
     */
    public static String getObjectValue(Object obj) {
        return getObjectValue(obj, null);
    }

    /**
     * 获取对象字符串值
     *
     * @param obj
     * @param pattern 转日期格式
     * @return
     * @author LiCongLu
     * @date 2020-01-02 16:13
     */
    public static String getObjectValue(Object obj, String pattern) {
        if (obj == null) {
            return "";
        }

        if (!(obj instanceof Date)) {
            if ((obj instanceof Float) || (obj instanceof Double) || (obj instanceof Long)) {
                return new BigDecimal(String.valueOf(obj)).toPlainString();
            }

            return String.valueOf(obj);
        }

        if (pattern == null || pattern.length() == 0) {
            return DateUtil.formatPattern19((Date) obj);
        }
        return DateUtil.formatPattern((Date) obj, pattern);
    }
}
