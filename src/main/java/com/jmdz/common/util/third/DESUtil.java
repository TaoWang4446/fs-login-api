package com.jmdz.common.util.third;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 参照C#平台，加密、解密帮助类，DotNet.Kernel.DESEncrypt
 *
 * @author LiCongLu
 * @date 2020-06-15 11:36
 */
public class DESUtil {

    /**
     * 加密
     *
     * @param text
     * @return
     */
    public static String encrypt(String text) {
        return encrypt( text, "learun###***" );
    }

    /**
     * 加密
     *
     * @param text
     * @param sKey
     * @return
     */
    public static String encrypt(String text, String sKey) {
        try {
            // 必须是8位，否则会异常
            String md5Key = MD5Util.MD5( sKey, MD5Util.code8 );
            byte[] DESkey = md5Key.getBytes( "UTF-8" );
            byte[] DESIV = md5Key.getBytes( "UTF-8" );

            DESKeySpec keySpec = new DESKeySpec( DESkey );// 设置密钥参数
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DES" );// 获得密钥工厂
            Key key = keyFactory.generateSecret( keySpec );// 得到密钥对象
            AlgorithmParameterSpec iv = new IvParameterSpec( DESIV );// 设置向量
            Cipher enCipher = Cipher.getInstance( "DES/CBC/PKCS5Padding" );
            enCipher.init( Cipher.ENCRYPT_MODE, key, iv );// 设置工作模式为加密模式，给出密钥和向量
            byte[] pasByte = enCipher.doFinal( text.getBytes( "utf-8" ) );
            StringBuilder ret = new StringBuilder();
            for (byte b : pasByte) {
                ret.append( String.format( "%02X", b ) );
            }
            return ret.toString();
        } catch (Exception e) {
            return text;
        }
    }

    /**
     * 解密
     *
     * @param text
     * @return
     */
    public static String decrypt(String text) {
        return decrypt( text, "learun###***" );
    }

    /**
     * 解密
     *
     * @param text
     * @param sKey
     * @return
     */
    public static String decrypt(String text, String sKey) {
        try {

            int len = text.length() / 2;
            byte[] inputByteArray = new byte[len];
            int x = 0;
            for (int i = 0; i < len; i++) {
                int n = Integer.parseInt( text.substring( i * 2, i * 2 + 2 ), 16 );
                inputByteArray[x++] = (byte) (n & 0xff);
                ;
            }

            // 必须是8位，否则会异常
            String md5Key = MD5Util.MD5( sKey, MD5Util.code8 );
            byte[] DESkey = md5Key.getBytes( "UTF-8" );
            byte[] DESIV = md5Key.getBytes( "UTF-8" );

            DESKeySpec keySpec = new DESKeySpec( DESkey );// 设置密钥参数
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DES" );// 获得密钥工厂
            Key key = keyFactory.generateSecret( keySpec );// 得到密钥对象
            AlgorithmParameterSpec iv = new IvParameterSpec( DESIV );// 设置向量

            Cipher deCipher = Cipher.getInstance( "DES/CBC/PKCS5Padding" );
            deCipher.init( Cipher.DECRYPT_MODE, key, iv );
            byte[] pasByte = deCipher.doFinal( inputByteArray );

            return new String( pasByte, "UTF-8" );

        } catch (Exception e) {
            e.printStackTrace();
            return text;
        }
    }

    public static void main(String[] args) {
        try {
            String password = "0000";
            String secretkey = "9A89F511BC0C66F1";
            System.out.println( "result:" + encrypt( password, secretkey ) );
            System.out.println( "message:" + decrypt( encrypt( password, secretkey ), secretkey ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
