package cn.will.util;

/**
 * Created on 2018-01-14 3:12 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class TimeUtil {

    static int TIME_UINT = 60;

    static String PADDING = "0";

    static String SEPERATOR = ":";

    public static String Secend2Minute(int seconds){
        String time = "";
        String hourStr = "",minStr = "",secStr = "";
        int hour = 0;
        int minute = seconds/TIME_UINT;
        int left = seconds%TIME_UINT;
        if (minute >= TIME_UINT){
            hour = minute/TIME_UINT;
            minute = minute%TIME_UINT;
        }
        hourStr = ""+hour;
        minStr = ""+minute;
        secStr = ""+left;
        //确保两位显示
        if (hour < 10) hourStr = PADDING + hourStr;
        if (minute < 10) minStr = PADDING + minStr;
        if (left < 10) secStr = PADDING + secStr;

        time = minStr + SEPERATOR + secStr;
        if (hour>0) {
            time = hourStr + SEPERATOR + time;
        }
        return time;
    }
}
