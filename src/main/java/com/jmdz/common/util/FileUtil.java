package com.jmdz.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * FileUtil
 *
 * @author LiCongLu
 * @date 2020-06-12 15:16
 */
public final class FileUtil {
    /**
     * 获取文件字节
     *
     * @param
     * @return
     * @author LiCongLu
     * @date 2020-06-12 15:15
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File( filePath );
            FileInputStream fis = new FileInputStream( file );
            ByteArrayOutputStream bos = new ByteArrayOutputStream( 1024 );
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read( b )) != -1) {
                bos.write( b, 0, n );
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
