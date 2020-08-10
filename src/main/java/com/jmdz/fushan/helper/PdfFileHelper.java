package com.jmdz.fushan.helper;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.jmdz.common.core.ActionException;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.common.util.FileUtil;
import com.jmdz.fushan.model.config.ConfigApp;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.entity.RecordFile;
import com.jmdz.fushan.pad.model.SignImageItem;
import com.jmdz.fushan.pad.model.farewell.FarewellTaskServiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * pdf文件帮助类
 *
 * @author LiCongLu
 * @date 2020-06-12 15:08
 */
@Component("pdfFileHelper")
public class PdfFileHelper {

    @Autowired
    private ConfigApp configApp;

    @Resource
    private PdfHelper pdfHelper;

    @Resource
    private FileHelper fileHelper;


    /**
     * 获取静态资源路径
     *
     * @param dir 目录
     * @return
     * @author LiCongLu
     * @date 2020-06-12 15:13
     */
    private String getResPath(String dir) {
        try {
            File classFile = new File(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath());
            // 包含汉字会出现乱码，需进行转码
            String classPath = new String(java.net.URLDecoder.decode(classFile.getAbsolutePath(), Constants.UTF8));
            File dirFile = new File(classPath, dir);
            if (dirFile.exists()) {
                return dirFile.getAbsolutePath();
            }
        } catch (Exception e) {
            //LogUtil.e( e );
        }
        return "";
    }

    /**
     * 保存签字图片
     *
     * @param farewellItem 告别任务
     * @return
     * @author LiCongLu
     * @date 2020-06-12 15:12
     */
    public RecordFile saveFarewellToPdf(FarewellTaskServiceItem farewellItem) throws ActionException {
        try {
            String dirPath = getResPath("static/htmls");
            File htmlFile = new File(dirPath, "farewelltask.html");
            File fontFile = new File(dirPath, "SIMSUN.TTC");
            if (farewellItem != null && htmlFile.exists() && fontFile.exists()) {

                RecordFile recordFile = new RecordFile();
                recordFile.setFileName(String.valueOf(System.currentTimeMillis()) + ".pdf");

                String newPath = DateUtil.formatPattern(new Date(), DateUtil.PATTERN_10_PATH);
                File pdfPath = new File(fileHelper.getDirPath(Constants.DirPath.pdfDir, newPath));
                File pdfFile = new File(pdfPath, recordFile.getFileName());

                Document document = new Document();
                PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile.getAbsolutePath()));
                //打开文档
                document.open();

                String html = new String(FileUtil.getBytes(htmlFile.getAbsolutePath()), "UTF-8");
                // 逝者信息
                html = pdfHelper.replaceHtml(farewellItem, html, "farewellItem");

                // 告别签字
                SignImageItem signItem = farewellItem.getSignImageItem();

                // 签字图片
                if (DataUtil.valid(signItem.getImagePath())) {
                    File imageFile = new File(fileHelper.getDirPath("", ""), signItem.getImagePath());
                    if (imageFile.exists()) {
                        html = pdfHelper.replaceHtml(html, "imagePath", imageFile.getAbsolutePath());
                    }
                }
                html = pdfHelper.replaceHtml(html, "logtime", DateUtil.formatPattern16(new Date()));
                html = pdfHelper.clearHtml(html);
                pdfHelper.addHtml(document, pdfWriter, fontFile.getAbsolutePath(), html);
                //关闭文档
                document.close();
                recordFile.setFilePath(fileHelper.getUrlPath(pdfFile));
                recordFile.setFileLength(pdfFile.length());
                return recordFile;
            } else {
                // LogUtil.info("因文件不存在而无法生成pdf文件");
            }
        } catch (Exception e) {
            // LogUtil.ex(e);
        }
        return null;
    }

}
