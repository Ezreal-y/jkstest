package com.jenkins.jkstest.security.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author l
 * @date 2020/6/11 21:03
 * @description
 */
public class SysDateUtil {
    public static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";
    public static final String yyyy_MM_DDHHmmssStr = "yyyy-MM-dd HH:mm:ss";
    /**
     * cron表达式转为日期 返回的是Date
     *
     * @param cron
     * @return Tue May 05 05:05:00 CST 2009
     */
    public static Date getCronToDate(String cron) {

        // "yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);

        Date date = null;
        try {
            date = sdf.parse(cron);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * cron表达式 转换成 返回 2009-05-05 05:05:00格式
     * @param cron
     * @return 返回 2009-05-05 05:05:00格式
     */
    public static String cronToStr(String cron) {
        Date Date = getCronToDate(cron);
        String stringDate = fmtDateToStr(Date);
        return stringDate;
    }
    /**
     * Description:格式化日期,String字符串转化为Date
     * @param date
     * @return 2009-05-05 05:05:00
     */
    public static String fmtDateToStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_DDHHmmssStr);
        return dateFormat.format(date);
    }
}
