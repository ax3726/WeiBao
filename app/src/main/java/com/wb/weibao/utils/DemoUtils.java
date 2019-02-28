package com.wb.weibao.utils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/9/27.
 */

public class DemoUtils {



    public static String ConvertTimeFormat(long time ,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(new Date(time));
    }
    public static Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
        }
        return date;
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

    public static <T> String ListToString(ArrayList<String> array, String Separator) {
        if (array==null) {
            return "";
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            String str = (String) array.get(i);
            csvBuilder.append(str);
            csvBuilder.append(Separator);
        }
        String csv = csvBuilder.toString();
        csv = csv.substring(0, csv.length() - Separator.length());
        return csv;
    }
}
