package com.hztx.util.util;

/**
 * Created by Xukai on 2016/4/20.
 * 字符串工具类。
 */
public class StringUtil {

    /**
     * 判断字符串是否为空。
     */
    public static boolean isBlank(String string) {
        return string == null || string.equals("");
    }

    /**
     * 判断字符串是否为空。
     */
    public static boolean isNotBlank(String string) {
        return !(isBlank(string));
    }

    /**
     * 如果不会空，返回原字符串。
     * 如果为空，返回null
     */
    public static String nullOrNotBlank(String string) {
        if (isBlank(string))
            return null;
        else
            return string;
    }

    /**
     * 删除字符串最尾部字符。
     */
    public static String deleteTail(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

}
