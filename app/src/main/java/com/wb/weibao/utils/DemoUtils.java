package com.wb.weibao.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/27.
 */

public class DemoUtils {



    public static String ConvertTimeFormat(long time ,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * 检测空字符 或者 换行 空格 等情况
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
}
