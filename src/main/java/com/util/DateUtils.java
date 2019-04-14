package com.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 获取当天的日期字符串，yyyyMMdd格式，例如20190401
     */
    public static String getDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期、时间，格式 2019-04-01 13:28:23
     */
    public static String getDateAndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 返回字符串表示的float数值且不使用科学计数方法显示
     */
    public static String floatToString(float f) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(f);
    }
}
