package com.jmdz.common.util.third;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5Util
 *
 * @author LiCongLu
 * @date 2020-06-15 11:35
 */
public class MD5Util {

    public static final int code8 = 8;
    public static final int code16 = 16;
    public static final int code32 = 32;

    /**
     * 截取MD5字符串，去除-分隔符
     * code=8->(0,8)
     * code=16->(8,24)
     * code=32->(MD5)
     * 其它，返回空字符串。
     *
     * @param str
     * @param code
     * @return
     */
    public static String MD5(String str, int code) {
        String encrypt = "";
        String md5 = makeMD5(str).replace("-", "");
        if (code == MD5Util.code8 && md5.length() >= MD5Util.code8) {
            encrypt = md5.substring(0, MD5Util.code8);
        } else if (code == MD5Util.code16 && md5.length() >= (MD5Util.code8 + MD5Util.code16)) {
            encrypt = md5.substring(MD5Util.code8, (MD5Util.code8 + MD5Util.code16));

        } else if (code == MD5Util.code32 && md5.length() == MD5Util.code32) {
            encrypt = md5;
        }
        return encrypt;
    }

    /**
     * 制造MD5字符串，举例：DE-AE-DE-2B-ED-E7-9B-D4-63-03-67-DB-F5-7F-0A-1D
     * 去除分隔符后，相当于C#里，System.Web.Security.FormsAuthentication.HashPasswordForStoringInConfigFile(str, "MD5")
     * 转化异常时，返回空字符串
     *
     * @param str
     * @return
     */
    public static String makeMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bPwd = md.digest();
            String pwd = new BigInteger(1, bPwd).toString(16);
            if (pwd.length() % 2 == 1) {
                pwd = "0" + pwd;
            }
            int length = pwd.length();
            StringBuffer sb = new StringBuffer(length + length / 2 - 1);
            for (int i = 0; i < length; i += 2) {
                sb.append(pwd.substring(i, i + 2));
                if (i + 2 < length) {
                    sb.append("-");
                }
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 截取MD5字符串，去除-分隔符
     * code=8->(0,8)
     * code=16->(8,24)
     * code=32->(MD5)
     * 其它，返回空字符串。
     *
     * @param str
     * @param code
     * @return
     */
    public static String md5(String str, int code) {
        String encrypt = "";
        String md5 = makeMD5(str).replace("-", "");
        if (code == code8 && md5.length() >= code8) {
            encrypt = md5.substring(0, code8);
        } else if (code == code16 && md5.length() >= (code8 + code16)) {
            encrypt = md5.substring(code8, (code8 + code16));

        } else if (code == code32 && md5.length() == code32) {
            encrypt = md5;
        }
        return encrypt;
    }
}
