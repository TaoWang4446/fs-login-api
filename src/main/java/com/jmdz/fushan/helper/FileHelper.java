package com.jmdz.fushan.helper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jmdz.common.util.DataUtil;
import com.jmdz.common.util.DateUtil;
import com.jmdz.fushan.model.config.ConfigApp;
import com.jmdz.fushan.model.config.Constants;
import com.jmdz.fushan.model.entity.RecordImage;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.Hashtable;

/**
 * FileHelper
 *
 * @author LiCongLu
 * @date 2020-06-12 14:02
 */
@Component("fileHelper")
public class FileHelper {
    @Autowired
    private ConfigApp configApp;

    /**
     * 得到保存了路径，自动生成yyyyMMdd目录
     *
     * @param dirPath 主目录
     * @param newPath 新目录
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:02
     */
    public String getDirPath(String dirPath, String newPath) {
        File dirFile = new File(configApp.getDirPath() + dirPath, newPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile.getAbsolutePath();
    }

    /**
     * 拼接路径
     *
     * @param file 文件
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:03
     */
    public String getUrlPath(File file) {
        return file.getAbsolutePath().substring(configApp.getDirPath().length()).replace("\\", "/");
    }

    /**
     * 获取文件后缀名
     *
     * @param filename 文件名称
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:03
     */
    public String getSuffix(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 保存图片文件
     *
     * @param file 保存图片文件
     * @return
     * @author LiCongLu
     * @date 2020-06-12 14:03
     */
    public RecordImage saveImageFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                RecordImage recordImage = new RecordImage();
                String suffix = getSuffix(file.getOriginalFilename());
                recordImage.setImageName(String.valueOf(System.currentTimeMillis()) + suffix);

                /**
                 * 保存图片
                 */
                String newPath = DateUtil.formatPattern(new Date(), DateUtil.PATTERN_10_PATH);
                File imagePath = new File(getDirPath(Constants.DirPath.imageDir, newPath));
                File imageFile = new File(imagePath, recordImage.getImageName());
                file.transferTo(imageFile);

                recordImage.setImagePath(getUrlPath(imageFile));
                recordImage.setImageLength(imageFile.length());

                File thumbPath = new File(getDirPath(Constants.DirPath.thumbDir, newPath));
                File thumbFile = new File(thumbPath, recordImage.getImageName());

                /**
                 * 缓存图片
                 */
                Thumbnails.of(imageFile)
                        .size(Constants.ThumbSize, Constants.ThumbSize)
                        .toFile(thumbFile);

                recordImage.setThumbPath(getUrlPath(thumbFile));
                return recordImage;
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * 生成文件对象
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:39
     */
    private File getDirFile(String urPath) {
        urPath = urPath.replace("/", "\\");
        return new File(configApp.getDirPath(), urPath);
    }

    /**
     * 删除无效路径
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:38
     */
    private void deleteUrlPath(String urlPath) {
        try {
            if (DataUtil.valid(urlPath)) {
                File file = getDirFile(urlPath);
                if (file != null && file.exists()) {
                    file.deleteOnExit();
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 删除图片文件
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-09 15:38
     */
    public void deleteImageFile(RecordImage recordImage) {
        if (recordImage != null) {
            deleteUrlPath(recordImage.getImagePath());
            deleteUrlPath(recordImage.getThumbPath());
        }
    }

    /**
     * 生成二维码图片
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-07-16 08:51
     */
    public RecordImage saveQRCodeImage(String text) {
        try {
            int width = Constants.QRCodeSize;
            int height = Constants.QRCodeSize;
            String format = "png";

            RecordImage recordImage = new RecordImage();
            String suffix = "." + format;
            recordImage.setImageName(String.valueOf(System.currentTimeMillis()) + suffix);

            /**
             * 保存图片
             */
            String newPath = DateUtil.formatPattern(new Date(), DateUtil.PATTERN_10_PATH);
            File imagePath = new File(getDirPath(Constants.DirPath.imageDir, newPath));
            File imageFile = new File(imagePath, recordImage.getImageName());

            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, Constants.UTF8);
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 2);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToPath(bitMatrix, format, imageFile.toPath());

            recordImage.setImagePath(getUrlPath(imageFile));
            recordImage.setImageLength(imageFile.length());

            File thumbPath = new File(getDirPath(Constants.DirPath.thumbDir, newPath));
            File thumbFile = new File(thumbPath, recordImage.getImageName());

            /**
             * 缓存图片
             */
            Thumbnails.of(imageFile)
                    .size(Constants.ThumbSize, Constants.ThumbSize)
                    .toFile(thumbFile);

            recordImage.setThumbPath(getUrlPath(thumbFile));
            return recordImage;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param filePath 文件相对路径
     * @return
     * @author LiCongLu
     * @date 2020-07-16 08:55
     */
    public boolean isFileExists(String filePath) {
        File dirFile = new File(configApp.getDirPath(), filePath);
        return dirFile.exists();
    }
}
