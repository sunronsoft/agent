package com.lottery.agent.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public enum Format {
        yyyyMMdd("yyyyMMdd"),
        yyyyMMdd_slash("yyyy/MM/dd"),
        yyyyMMdd_dash("yyyy-MM-dd"),
        yyyyMM_dash("yyyy-MM"),
        yyMMdd("yyMMdd"),
        yyyyMMddHHmmss("yyyyMMddHHmmss"),
        yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),
        yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss");

        private String value;
        Format(String value) {
            this.value = value;
        }
    }

    public static Date CurrentDate() {
        return new Date();
    }

    public static int CurrentTime() {
        long unixTime = System.currentTimeMillis() / 1000;
        return (int)unixTime;
    }

    public static Date NextDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static long DiffDate(Date startDate, Date endDate) {
        long difference =  (endDate.getTime()-startDate.getTime())/86400000;
        return Math.abs(difference);
    }
}
