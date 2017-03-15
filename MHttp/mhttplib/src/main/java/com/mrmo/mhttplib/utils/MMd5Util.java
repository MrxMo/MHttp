
package com.mrmo.mhttplib.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  * MD5加密工具类
 */
public class MMd5Util {

    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }


    /**
     * MD5加密，32位 小写
     * @param str
     * @return
     */
    public static String getMD5_32(String str){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * MD5加密，32位 大写
     * @param str
     * @return
     */
    public static String getMD5_32UpperCase(String str){
        return getMD5_32(str).toUpperCase();
    }

    /**
     * MD5加密，16位 小写
     * @param str
     * @return
     */
    public static String getMD5_16(String str){
        return getMD5_32(str).substring(8, 24);
    }

    /**
     * MD5加密，16位 大写
     * @param str
     * @return
     */
    public static String getMD5_16UpperCase(String str){
        return getMD5_32(str).substring(8, 24).toUpperCase();
    }


}

