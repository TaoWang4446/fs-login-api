package com.jmdz.common.ext;

import com.jmdz.common.util.StringUtil;

import java.util.ArrayList;

/**
 * StringBuilder 扩展类
 *
 * @author LiCongLu
 * @date 2019-12-16 15:18
 */
@SuppressWarnings("unchecked")
public class StringBuilderExt {
    private StringBuilder builder;

    public StringBuilderExt() {
        this.builder = new StringBuilder();
    }

    public StringBuilderExt(String str) {
        this.builder = new StringBuilder(str);
    }

    public StringBuilderExt append(Object... args) {
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                this.builder.append(args[i]);
            }
        }
        return this;
    }

    public StringBuilderExt append(String... args) {
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                this.builder.append(args[i]);
            }
        }
        return this;
    }

    /**
     * 换新行追加字符串
     *
     * @param args 追加字符串
     * @return
     * @author LiCongLu
     * @date 2019-12-16 15:30
     */
    public StringBuilderExt line(String... args) {
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                this.line().append(args[i]);
            }
        }
        return this;
    }

    public StringBuilderExt line() {
        this.builder.append("\r\n");
        return this;
    }

    /**
     * 换新行追加字符串集合
     *
     * @param lists 字符串集合
     * @return
     * @author LiCongLu
     * @date 2019-12-16 15:38
     */
    public StringBuilderExt line(ArrayList<String>... lists) {
        if (lists != null && lists.length > 0) {
            for (int i = 0; i < lists.length; i++) {
                this.line(lists[i].toArray(new String[]{}));
            }
        }
        return this;
    }

    /**
     * 追加格式化字符串
     *
     * @param format 字符串格式
     * @param args   追加字符串
     * @return
     * @author LiCongLu
     * @date 2019-12-16 15:30
     */
    public StringBuilderExt format(String format, String... args) {
        this.append(StringUtil.format(format, args));
        return this;
    }

    /**
     * 换新行追加格式化字符串
     *
     * @param format 字符串格式
     * @param args   追加字符串
     * @return
     * @author LiCongLu
     * @date 2019-12-16 15:30
     */
    public StringBuilderExt formatLine(String format, String... args) {
        this.line(StringUtil.format(format, args));
        return this;
    }

    /**
     * 清空字符串
     */
    public void clear() {
        this.builder.setLength(0);
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public StringBuilderExt setBuilder(StringBuilder builder) {
        this.builder = builder;
        return this;
    }

    @Override
    public String toString() {
        return this.builder.toString();
    }
}
