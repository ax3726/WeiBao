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
}
