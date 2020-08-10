package com.jmdz.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * data工具类
 *
 * @author LiCongLu
 * @date 2019-12-13 16:12
 */
public final class DataUtil {

    /**
     * 是否数字正则表达式
     */
    private static Pattern numericPattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    /**
     * 判断是否是是数字
     * 源数据为空时，返回false
     *
     * @param source 判断源数据
     * @return
     */
    public static boolean isNumeric(String source) {
        if (source == null || source.trim().length() == 0) {
            return false;
        }
        return numericPattern.matcher(source).matches();
    }

    /**
     * 批量判断多字符串是否无效
     * 返回valid判断的非值，即不定参数里只要存在一个非有效值，返回true
     * 否则，返回false
     * 默认，返回true
     *
     * @param args 不定参数
     * @return
     */
    public static Boolean invalid(String... args) {
        return !valid(args);
    }

    /**
     * 批量判断多个字符串是否有效
     * 当所有不定参数字符串均有效时，返回true
     * 有效是指不为空null且字符串长度大于零且去除空格后长度大于零
     * 否则，返回false
     * 默认，返回false
     *
     * @param args 不定参数
     * @return
     */
    public static boolean valid(String... args) {
        boolean valid = false;
        if (args != null && args.length > 0) {
            valid = true;
            for (String str : args) {
                valid = valid && (str != null && str.length() > 0 && str.trim().length() > 0);
            }
        }
        return valid;
    }

    /**
     * 批量判断多整数对象是否无效
     * 返回valid判断的非值，即不定参数里只要存在一个非有效值，返回true
     * 否则，返回false
     * 默认，返回true
     *
     * @param args 数字
     * @return
     */
    public static boolean invalid(Integer... args) {
        return !valid(args);
    }

    /**
     * 批量判断多个整数对象是否有效
     * 当所有不定参数整数对象均有效时，返回true
     * 有效是指不为null且大于零
     * 否则，返回false
     * 默认，返回false
     *
     * @param args 不定参数
     * @return
     */
    public static boolean valid(Integer... args) {
        boolean valid = false;
        if (args != null && args.length > 0) {
            valid = true;
            for (Integer integer : args) {
                valid = valid && (integer != null && integer.intValue() > 0);
            }
        }
        return valid;
    }

    /**
     * 判断数据集合无效，即对象为null或大小不大于零
     *
     * @param sourceList
     * @param <T>
     * @return
     */
    public static <T> boolean invalid(ArrayList<T> sourceList) {
        return !valid(sourceList);
    }

    /**
     * 判断数据集合有效，即集合对象不为null且大小大于零
     *
     * @param sourceList 源数据集合
     * @param <T>
     * @return
     */
    public static <T> boolean valid(ArrayList<T> sourceList) {
        return sourceList != null && sourceList.size() > 0;
    }

    /**
     * 验证日期是否无效，目前暂时判断是否为null
     *
     * @param date
     * @return
     */
    public static boolean invalid(Date date) {
        return !valid(date);
    }

    /**
     * 验证日期是否有效，目前暂时判断是否为null
     *
     * @param date
     * @return
     */
    public static boolean valid(Date date) {
        return date != null;
    }

    /**
     * 批量判断多对象是否无效
     * 返回noNullOrEmpty判断的非值，即不定参数里只要存在一个为null或空值，返回true
     * 否则，返回false
     * 默认，返回true
     *
     * @param args 数字
     * @return
     */
    public static Boolean isNull(Object... args) {
        return !noNullOrEmpty(args);
    }

    /**
     * 批量判断多个多对象是否非null或非空
     * 当所有不定参数对象均有效时，返回true
     * 有效是指不为null，且若是字符串，则长度大于零
     * 否则，返回false
     * 默认，返回false
     *
     * @param args 不定参数
     * @return
     */
    public static boolean noNullOrEmpty(Object... args) {
        boolean valid = false;
        if (args != null && args.length > 0) {
            valid = true;
            for (Object obj : args) {
                if (obj == null) {
                    valid = valid && false;
                } else if (obj instanceof String) {
                    String value = obj.toString();
                    valid = valid && (value.length() > 0 && value.trim().length() > 0);
                }
            }
        }
        return valid;
    }

    /**
     * 比较两个字符串是否相等
     * 当两个字符串都无效时，即为null或空字符串，返回true
     * 当两个字符串都有效时，返回两个字符串的小写字符串并去除空格后的字符串equals比较值
     * 否则，返回false
     *
     * @param strA 字符串
     * @param strB 字符串
     * @return
     */
    public static boolean equals(String strA, String strB) {
        if (DataUtil.invalid(strA) || invalid(strB)) {
            return invalid(strA) && invalid(strB);
        } else {
            return strA.toLowerCase().trim().equals(strB.toLowerCase().trim());
        }
    }

    /**
     * 判断两个整数对象是否相等
     * 当两个整数对象都是null时，返回true
     * 当两个整数对象都不是null时，比较整数对象取整数的大小，相等时返回true
     * 否则返回false
     *
     * @param intA 整数对象
     * @param intB 整数对象
     * @return
     */
    public static boolean equals(Integer intA, Integer intB) {
        if (intA == null || intB == null) {
            return intA == null && intB == null;
        } else {
            return intA.intValue() == intB.intValue();
        }
    }

    /**
     * 判断两个长整数对象是否相等
     * 当两个长整数对象都是null时，返回true
     * 当两个长整数对象都不是null时，比较长整数对象取长整数的大小，相等时返回true
     * 否则返回false
     *
     * @param longA 长整数
     * @param longB 长整数
     * @return
     */
    public static boolean equals(Long longA, Long longB) {
        if (longA == null || longB == null) {
            return longA == null && longB == null;
        } else {
            return longA.longValue() == longB.longValue();
        }
    }

    /**
     * 判断字符串是否在字符串数组内
     * 只要字符串与字符串数组里的任何一个字符串相等，返回true
     * 其中两个字符串是否相等，参考本工具类equals函数
     * 默认，返回false
     *
     * @param value  字符串
     * @param values 字符串数组
     * @return
     */
    public static boolean inStrings(String value, String... values) {
        boolean flag = false;
        if (values != null && values.length > 0) {
            for (String tValue : values) {
                flag = flag || equals(value, tValue);
            }
        }
        return flag;
    }

    /**
     * 判断整数对象是否在整数对象数组内
     * 只要整数对象与整数对象数组里的任何一个整数对象相等，返回true
     * 其中两个整数对象是否相等，参考本工具类equals函数
     * 默认，返回false
     *
     * @param value  整数对象
     * @param values 整数对象数组
     * @return
     */
    public static boolean inIntegers(Integer value, Integer... values) {
        boolean flag = false;
        for (Integer tValue : values) {
            flag = flag || equals(value, tValue);
        }
        return flag;
    }

    /**
     * 获取UUID即GUID字符串，小写字符串
     *
     * @return
     */
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().toLowerCase();
    }

    /**
     * 获取随机数字字符串
     * 只包含数字和大写字母，主要用于简单随机码等，不验证重复性
     * 若是使用不可重复的请使用getUUID函数
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        String value = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int bound = value.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(value.charAt(random.nextInt(bound)));
        }
        return builder.toString();
    }

    /**
     * ArrayList去重，使用HashSet去重
     * 返回去重后的源数据集合
     *
     * @param sourceList 数据集合
     * @return
     */
    public static <T> ArrayList<T> repeatArrayList(ArrayList<T> sourceList) {
        //初始化HashSet对象，并把list对象元素赋值给HashSet对象
        HashSet set = new HashSet(sourceList);
        //把List集合所有元素清空
        sourceList.clear();
        //把HashSet对象添加至List集合
        sourceList.addAll(set);
        return sourceList;
    }

    /**
     * 获取去除尾部零的数字转字符串
     * 默认，返回空字符串
     *
     * @param source 数字数据
     * @return
     */
    public static String getStripZerosString(BigDecimal source) {
        if (source != null) {
            return source.stripTrailingZeros().toPlainString();
        }
        return "";
    }

    /**
     * 获取真实数字字符串
     * 默认，返回空字符串
     *
     * @param source
     * @return
     */
    public static String getPlainString(BigDecimal source) {
        if (source != null) {
            return source.toPlainString();
        }
        return "";
    }

    /**
     * 保留两位小数点
     * 默认，返回null
     *
     * @param source
     * @return
     */
    public static BigDecimal reset2Scale(BigDecimal source) {
        if (source != null) {
            return source.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }

    /**
     * 得到不定参数值的累加和
     * 默认，返回0
     *
     * @param args 不定参数
     * @return
     */
    public static BigDecimal add(BigDecimal... args) {
        BigDecimal totalValue = new BigDecimal(0);
        if (args != null && args.length > 0) {
            for (BigDecimal value : args) {
                if (value != null) {
                    totalValue = totalValue.add(value);
                }
            }
        }
        return totalValue;
    }

    /**
     * 判断是否存在小于零的值
     * 存在为null值或负值时，返回true
     * 默认，返回false
     *
     * @param args
     * @return
     */
    public static boolean lessInZero(BigDecimal... args) {
        if (args != null && args.length > 0) {
            BigDecimal zero = new BigDecimal(0);
            for (BigDecimal value : args) {
                // 为null
                if (value == null) {
                    return true;
                }

                // 小于零
                if (value.compareTo(zero) < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断所有值是否都大于零
     * 只有全部不为null且都大于零时，返回true
     * 默认，返回false
     *
     * @param args
     * @return
     */
    public static boolean greaterAllZero(BigDecimal... args) {
        if (args != null && args.length > 0) {
            BigDecimal zero = new BigDecimal(0);
            boolean flag = true;
            for (BigDecimal value : args) {
                // 为null
                if (value == null) {
                    return false;
                }

                // 小于零
                if (value.compareTo(zero) <= 0) {
                    return false;
                }

                // 大于零
                if (value.compareTo(zero) > 0) {
                    flag = flag && true;
                }
            }
            return flag;
        }
        return false;
    }

    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal(10);
        BigDecimal num2 = new BigDecimal(10);

        System.out.println("判断结果：" + greaterAllZero(null, num1, num2));

        /**
         System.out.printf("随机字符串：" + getRandomString(6));
         System.out.println("021524:" + isNumeric("021524"));
         System.out.println("021.524:" + isNumeric("021.524"));
         System.out.println("21.524:" + isNumeric("21.524"));
         System.out.println("-21524:" + isNumeric("-21524"));
         System.out.println("-215.24:" + isNumeric("-215.24"));

         String string = "3";
         Integer integer = 25;
         Long aLong = 522255L;
         System.out.println(noNullOrEmpty(integer, aLong));
         Integer[] values = null;

         System.out.println("valid(values)" + valid(values));
         System.out.println("invalid(integer,integer)" + invalid(integer, integer));
         System.out.println("valid(string,string)" + valid(string, ""));
         System.out.println("invalid(string,string)" + invalid(string, ""));
         System.out.println("noNullOrEmpty(string,'')" + noNullOrEmpty(string, ""));
         System.out.println("inStrings('a', 'a2', 'c', 'd')" + inStrings("a", "a2", "b", "c"));
         System.out.println("inIntegers(15, 15, 35, 45)" + inIntegers(15, 15, 35, 45));

         System.out.println("equals(45, 45)" + equals(45, 45));
         System.out.println("equals(35L, 45L)" + equals(35L, 45L));

         ArrayListExt<Integer> arrayList = new ArrayListExt<>();
         arrayList.addExt(1, 6, 3, 4, 3, 6, 3, 1);
         System.out.println("去重后：" + GsonUtil.toJson(repeatArrayList(arrayList)));

         arrayList = new ArrayListExt<>();
         arrayList.addExt(1, 6, 3, 4, 3, 6, 3, 10);
         Integer a = 10;
         Integer b = 10;
         int c = 10;
         System.out.println("a==b:" + (a.equals(b)));
         System.out.println("c==b:" + (c == b));
         System.out.println("a:" + (arrayList.contains(a)));
         System.out.println("b:" + (arrayList.contains(b)));
         System.out.println("c:" + (arrayList.contains(c)));

         HashMap<Integer, String> hashMap = new HashMap<>(16);
         hashMap.put(a, "正确");

         String text = hashMap.get(b);
         System.out.println(text);

         text = hashMap.get(c);
         System.out.println(text);
         **/
    }

    public static String getString(String source) {
        return source == null ? "" : source;
    }
}
