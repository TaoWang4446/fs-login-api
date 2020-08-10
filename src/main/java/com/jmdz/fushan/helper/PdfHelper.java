package com.jmdz.fushan.helper;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.jmdz.common.base.BaseBean;
import com.jmdz.common.util.BeanUtil;
import com.jmdz.common.util.DataUtil;
import com.jmdz.fushan.model.config.Constants;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * PdfHelper
 *
 * @author LiCongLu
 * @date 2020-06-12 14:12
 */
@Component("pdfHelper")
public class PdfHelper {

    /**
     * 添加字符串
     *
     * @param doc      文档
     * @param writer   输入
     * @param fontPath 字体路径
     * @param html     html文本
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:12
     */
    public void addHtml(Document doc, PdfWriter writer, String fontPath, String html) throws IOException {
        byte[] array = html.getBytes(Constants.UTF8);
        InputStream stream = new ByteArrayInputStream(array);
        XMLWorkerHelper.getInstance().parseXHtml(writer, doc, stream, null, Charset.forName(Constants.UTF8), new SongFontFactory(fontPath));
    }

    /**
     * 批量替换属性值  ${" + name + "." + key + "}
     *
     * @param bean 实体
     * @param html html文本
     * @param name 属性名字
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:12
     */
    public <T extends BaseBean> String replaceHtml(T bean, String html, String name) {
        if (bean != null && DataUtil.valid(name)) {
            HashMap<String, String> hashMap = BeanUtil.toHashMap(bean);
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                String value = entry.getValue();
                String key = entry.getKey();
                if (value != null) {
                    html = html.replace("${" + name + "." + key + "}", value);
                } else {
                    html = html.replace("${" + name + "." + key + "}", "");
                }
            }
        }
        return html;
    }

    /**
     * 替换文本
     *
     * @param html  页面文本
     * @param name  属性名字
     * @param value 属性值
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:13
     */
    public String replaceHtml(String html, String name, String value) {
        if (DataUtil.valid(name)) {
            if (value != null) {
                html = html.replace("${" + name + "}", value);
            } else {
                html = html.replace("${" + name + "}", "");
            }
        }
        return html;
    }

    /**
     * 替换循环，$list{listName}  ${itemName.key} #list{listName}
     *
     * @param beans    实体集合
     * @param html     html文本
     * @param listName list标记
     * @param itemName item标记
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:14
     */
    public <T extends BaseBean> String replaceListHtml(ArrayList<T> beans, String html, String listName, String itemName) {
        if (beans != null && beans.size() > 0 && DataUtil.valid(listName)) {
            String replaceStart = "$list{" + listName + "}";
            String replaceEnd = "#list{" + listName + "}";
            while (html.contains(replaceStart)) {
                int start = html.indexOf(replaceStart);
                int len = html.substring(start).indexOf(replaceEnd);
                String spaceHtml = html.substring(start, start + len + replaceEnd.length());
                String listHtml = spaceHtml.replace(replaceStart, "").replace(replaceEnd, "");
                StringBuilder builder = new StringBuilder();
                for (T bean : beans) {
                    builder.append(replaceHtml(bean, listHtml, itemName));
                }
                html = html.replace(spaceHtml, builder.toString());
            }
        }
        return html;
    }

    /**
     * 清除多余标记
     *
     * @param html html文本
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:15
     */
    public String clearHtml(String html) {
        if (DataUtil.valid(html)) {
            html = clear4Mark(html, "${", "}");
        }
        return html;
    }

    /**
     * 清除DIV区域
     *
     * @param html    html文本
     * @param divName div名称
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:15
     */
    public String clearDivHtml(String html, String divName) {
        if (DataUtil.valid(html)) {
            html = clear4Mark(html, "$div{" + divName + "}", "#div{" + divName + "}");
        }
        return html;
    }

    /**
     * 清除list区域
     *
     * @param html     html文本
     * @param listName list标记
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:16
     */
    public String clearListHtml(String html, String listName) {
        if (DataUtil.valid(html)) {
            html = clear4Mark(html, "$list{" + listName + "}", "#list{" + listName + "}");
        }
        return html;
    }

    /**
     * 删掉代码里注释 <!-- -->
     *
     * @param html html文本
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:16
     */
    public String clearNoteHtml(String html) {
        if (DataUtil.valid(html)) {
            html = clear4Mark(html, "<!--", "-->");
        }
        return html;
    }

    /**
     * 清除区域
     *
     * @param html     html文本
     * @param startStr 开始标记
     * @param endStr   结束标记
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:16
     */
    public String clear4Mark(String html, String startStr, String endStr) {
        while (html.contains(startStr)) {
            int start = html.indexOf(startStr);
            int len = html.substring(start).indexOf(endStr);
            String space = html.substring(start, start + len + endStr.length());
            html = html.replace(space, "");
        }
        return html;
    }

    /**
     * 删除脚本
     *
     * @param html   html文本
     * @param target 目标文本
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:17
     */
    public String clearHtml(String html, String... target) {
        if (target != null && target.length > 0) {
            for (int i = 0; i < target.length; i++) {
                html = html.replace(target[i], "");
            }
        }
        return html;
    }

    /**
     * 字体工厂
     */
    public static class SongFontFactory implements FontProvider {
        private String fontPath;

        public SongFontFactory(String fontPath) {
            this.fontPath = fontPath;
        }

        @Override
        public boolean isRegistered(String fontName) {
            return false;
        }

        @Override
        public Font getFont(String fontName, String encoding, boolean embedded, float size, int style, BaseColor baseColor) {
            try {
                BaseFont bf3 = BaseFont.createFont(fontPath + ",1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                Font fontContent = new Font(bf3, size, style);
                return fontContent;
            } catch (Exception e) {
                // LogUtil.e( e );
            }
            return null;
        }
    }

}
