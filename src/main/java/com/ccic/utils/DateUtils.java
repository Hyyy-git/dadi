package com.ccic.utils;


import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: DateUtils
 * @Description: 日期时间工具类
 * @date 2017年1月12日 下午2:37:57
 */
public enum DateUtils {

    INSTANCE;

    /**
     * @param date
     * @param format 默认为yyyy-MM-dd
     * @description 日期转换为字符串
     */
    public String DateToStr(Date date, String format) {
        if (date == null)
            return null;
        if (StringUtils.isEmpty(format))
            format = "yyyy-MM-dd";
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * @param pattern
     * @param strDateTime
     * @return
     * @description 字符串转换成日期
     */
    public Date StrToDate(String pattern, String strDateTime) {
        Date date = null;
        if (strDateTime == null || pattern == null) {
            return null;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formatter.setLenient(false);
            date = formatter.parse(strDateTime);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * @param date
     * @param days
     * @return
     * @Description: 日期+天数计算方法
     */
    public String canlandarAddDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }

    /**
     * @param date
     * @return
     * @Description: 日期 获取日期所在月份最后一天
     */
    public Date getLastDayOfThisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();//创建一个实例
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * @param before
     * @return
     * @description 获取 前before个月的今天
     */
    public Date beforeMonthAsToday(int before) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - before);
        return calendar.getTime();
    }

    /**
     * @return
     * @description 获取当月的第一天的 0点0时
     */
    public Date firstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @return
     * @description 获取 当月的最后一天的最后一秒
     */
    public Date lastTimeOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * @param sdate
     * @param edate
     * @return
     * @description 获取两个时间之间的时间差
     */
    public Map<String, Integer> getDifferUnitTime(Date sdate, Date edate) {
        Calendar calendar = Calendar.getInstance();
        long st = sdate.getTime();
        long et = edate.getTime();
        calendar.setTimeInMillis(Math.abs(et - st));
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("YEAR", calendar.get(Calendar.YEAR) - 1970);
        map.put("MONTH", calendar.get(Calendar.MONTH));
        map.put("DAY_OF_YEAR", calendar.get(Calendar.DAY_OF_YEAR) - 1);
        map.put("HOUR_OF_DAY", calendar.get(Calendar.HOUR_OF_DAY) - 8);
        map.put("MINUTE", calendar.get(Calendar.MINUTE));
        map.put("SECOND", calendar.get(Calendar.SECOND));
        return map;
    }

    /**
     * @param date
     * @param
     * @description 日期转换增加时分秒
     */
    public static Date DateToStrFirst(Date date, String timesource) {
        if (date == null)
            return null;

        String format = "yyyy-MM-dd";
        String strDateMin = new SimpleDateFormat(format).format(date) + " " + timesource;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(strDateMin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param date
     * @param format 默认为yyyy-MM-dd
     * @description 日期转换为字符串
     */
    public String TimeToStr(Date date, String format) {
        if (date == null)
            return null;
        if (StringUtils.isEmpty(format))
            format = "yyyyMMdd HH:mm:ss";
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取当天开始时间前一天
     *
     * @return
     */
    public String getDayStart() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startStr = simpleDateFormat.format(dayStart);
        return startStr;
    }

    /**
     * String(yyyy-MM-dd HH:mm:ss)转10位时间戳
     * @param time
     * @return
     */
    public  Integer StringToTimestamp(String time){
        int times = 0;
        try {
            times = (int) ((Timestamp.valueOf(time).getTime())/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(times==0){
            new Exception("日期转为10位时间戳失败");
        }
        return times;
    }

    /**
     *  获取当前时间之前或之后几小时
     * @param hour
     * @return
     */
    public String getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /**
     * 获取当前时间之前或之后几分钟
     * @param minute
     * @return
     */
    public static String getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }
}
