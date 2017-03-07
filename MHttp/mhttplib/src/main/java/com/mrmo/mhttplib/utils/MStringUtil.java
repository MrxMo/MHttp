package com.mrmo.mhttplib.utils;

/**
 * 字符串工具类
 * Created by moguangjian on 15/10/10 15:31.
 */
public class MStringUtil {

    /**
     * 判断字符串或长度是否为空（空白也算为空）
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (isObjectNull(string) || string.toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isObjectNull(Object object) {
        if (object == null) {
            return true;
        } else {
            return false;
        }
    }
}
