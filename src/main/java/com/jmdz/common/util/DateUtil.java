package com.jmdz.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * date工具类
 *
 * @author LiCongLu
 * @date 2019-12-13 16:20
 */
public final class DateUtil {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTERN_19 = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String PATTERN_16 = "yyyy-MM-dd HH:mm";

    /**
     * yyyy-MM-dd
     */
    public static final String PATTERN_10 = "yyyy-MM-dd";

    /**
     * MM-dd
     */
    public static final String PATTERN_12 = "MM-dd";

    /**
     * yyyy/MM/dd
     */
    public static final String PATTERN_10_PATH = "yyyy/MM/dd";

    /**
     * HH:mm
     */
    public static final String PATTERN_HH_4_MM = "HH:mm";

    /**
     * yyyy-MM-dd
     */
    public static final String PATTERN_8 = "yyyyMMdd";

    /**
     * 将日期字符串oldPattern 转化成 newPattern 格式
     *
     * @param date
     * @return
     */
    public static String formatDate(String date, String oldPattern, String newPattern) {
        try {
            if (date != null && date.length() == oldPattern.length()) {
                return new SimpleDateFormat(newPattern).format(
                        new SimpleDateFormat(oldPattern).parse(date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * yyyy-MM-dd hh:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatPattern19(Date date) {
        return formatPattern(date, PATTERN_19);
    }

    /**
     * yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String formatPattern16(Date date) {
        return formatPattern(date, PATTERN_16);
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatPattern10(Date date) {
        return formatPattern(date, PATTERN_10);
    }

    /**
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String formatPattern8(Date date) {
        return formatPattern(date, PATTERN_8);
    }

    /**
     * yyyy-MM-dd HH:mm
     *
     * @param value
     * @return
     */
    public static String format16DateStr(String value) {
        return formatDateStr(value, PATTERN_16);
    }

    /**
     * yyyy-MM-dd
     *
     * @param value
     * @return
     */
    public static String format10DateStr(String value) {
        return formatDateStr(value, PATTERN_10);
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式化
     * @return
     */
    public static String formatPattern(Date date, String pattern) {
        if (date != null) {
            return new SimpleDateFormat(pattern).format(date);
        }
        return "";
    }

    /**
     * 字符串按照特定特使转日期
     *
     * @param source
     * @return
     */
    public static Date parsePattern(String source) {
        if (DataUtil.valid(source)) {
            String pattern = "";
            if (source.length() == PATTERN_8.length()) {
                pattern = PATTERN_8;
            } else if (source.length() == PATTERN_10.length()) {
                pattern = PATTERN_10;
            } else if (source.length() == PATTERN_16.length()) {
                pattern = PATTERN_16;
            } else if (source.length() == PATTERN_19.length()) {
                pattern = PATTERN_19;
            }
            if (pattern.length() > 0) {
                return parsePattern(source, pattern);
            } else {
                return parseToDate(source);
            }
        }
        return null;
    }


    /**
     * 字符串按照特定格式转日期
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date parsePattern(String source, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为java.util.Date<br>
     * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2017-12-12 AD at 22:10:59 PSD'<br>
     * yy/MM/dd HH:mm:ss 如 '2017/12/12 17:55:00'<br>
     * yy/MM/dd HH:mm:ss pm 如 '2017/12/12 17:55:00 pm'<br>
     * yy-MM-dd HH:mm:ss 如 '2017-12-12 17:55:00' <br>
     * yy-MM-dd HH:mm:ss am 如 '2017-12-12 17:55:00 am' <br>
     * yy年MM月dd 如 '2017年12月12日' <br>
     * yy年MM月dd HH:mm:ss 如 '2017年12月12日 17:55:00' <br>
     *
     * @param source String 字符串<br>
     * @return Date 日期<br>
     */
    public static Date parseToDate(String source) {
        try {
            if (DataUtil.valid(source)) {
                SimpleDateFormat formatter;
                int tempPos = source.indexOf("AD");
                source = source.trim();
                formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
                if (tempPos > -1) {
                    //china
                    source = source.substring(0, tempPos) + "公元" + source.substring(tempPos + "AD".length());
                    formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
                }
                tempPos = source.indexOf("-");
                String space = " ";
                String backslash = "/";
                String line = "-";
                String year = "年";
                String colon = ":";
                String am = "am";
                String pm = "pm";
                if (tempPos > -1 && (source.indexOf(space) < 0)) {
                    formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
                } else if ((source.indexOf(backslash) > -1) && (source.indexOf(space) > -1)) {
                    formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                } else if ((source.indexOf(line) > -1) && (source.indexOf(space) > -1)) {
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else if ((source.indexOf(am) > -1) || (source.indexOf(pm) > -1)) {
                    if (source.indexOf(backslash) > -1) {
                        formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
                    } else if (source.indexOf(line) > -1) {
                        formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
                    } else {
                        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                } else if (source.indexOf(year) > -1) {
                    if (source.indexOf(colon) > -1) {
                        formatter = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
                    } else {
                        formatter = new SimpleDateFormat("yyyy年MM月dd");
                    }
                }
                ParsePosition pos = new ParsePosition(0);
                Date time = formatter.parse(source, pos);
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 重新格式化截取字符串
     *
     * @param value
     * @param pattern
     * @return
     */
    public static String formatDateStr(String value, String pattern) {
        if (DataUtil.valid(value) && DataUtil.valid(pattern)) {
            Date date = parsePattern(value);
            if (date != null) {
                return formatPattern(date, pattern);
            }
            int len = pattern.length();
            if (value.length() > len) {
                return value.substring(0, len - 1);
            }
            return value;
        }

        return "";
    }

    /**
     * 得到特定日期前N天的日期
     *
     * @param tempDay    时间
     * @param beforeDays 提前几天
     * @param format     日期格式
     * @return
     * @author guankui
     * @date 2020-03-07 20:36
     */
    public static String getBeforeDay(String tempDay, int beforeDays, String format) {
        if (!DataUtil.valid(tempDay)) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sip = new SimpleDateFormat(format);
        try {
            cal.setTime(sip.parse(tempDay));
            cal.add(Calendar.DATE, -beforeDays);
            return sip.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前时间前N（beforeDays）天的时间
     *
     * @param beforeDays 前几天
     * @param format     日期格式
     * @return
     * @author GuanKui
     * @date 2020/2/27  10:01
     */
    public static String getCurrentDayBefore(int beforeDays, String format) {
        String currentDay = formatPattern(new Date(), format);
        return getBeforeDay(currentDay, beforeDays, format);
    }

    /**
     * 得到几天前日期
     *
     * @param date       当前日期
     * @param beforeDays 前几天
     * @return
     * @author LiCongLu
     * @date 2020-03-07 20:43
     */
    public static Date getBeforeDay(Date date, int beforeDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -beforeDays);
        return calendar.getTime();
    }

    /**
     * 获取Unix时间戳
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-06-15 14:04
     */
    public static String getUnixTime() {
        return Long.toString(System.currentTimeMillis() / 1000L);
    }

    /**
     * 按照Unix时间获取正常日期
     *
     * @param unixTime 时间戳
     * @return
     * @author LiCongLu
     * @date 2020-06-15 14:10
     */
    public static String getDateFromUnixTime(Long unixTime) {
        Long timestamp = unixTime * 1000;
        return formatPattern19(new Date(timestamp));
    }

    public static void main(String[] args) {
        System.out.println(formatPattern16(new Date()));
        System.out.println(new Date());
        System.out.println(System.getProperty("user.timezone"));
    }
}
