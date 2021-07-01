package com.ticketsystem.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommUtils {

	public static String stringDateFormate(String dateString) {
        DateFormat format1 = new SimpleDateFormat("yyyyMMdd");//日期格式
        Date date = null;
        try {
            date = format1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String new_date_string = format2.format(date);
        return new_date_string;
    }
	/**
     * 秒转时分秒
     * @param second
     * @return
     */
	public static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        
        return hours+":"+minutes+":"+second;
    }
}
