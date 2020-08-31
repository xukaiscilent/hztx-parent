package com.hztx.util.util;

import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by XuKai on 2017/4/20.
 * 日期工具类。
 */
public class DateUtil {

    // 每秒的毫秒数
    private static final long MILLIS_PER_SECOND = 1000;
    //每分钟的毫秒数
    private static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    // 每小时的毫秒数
    private static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    // 每天的毫秒数
    private static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    //默认格式
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    //DateFormat cache
    private static final Map<String, DateFormat> dateFormatMap = new ConcurrentHashMap<String, DateFormat>();

    static {
        dateFormatMap.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
        dateFormatMap.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        dateFormatMap.put("yyyy-MM-dd hh:mm:ss", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        dateFormatMap.put("yyyy/MM/dd", new SimpleDateFormat("yyyy/MM/dd"));
        dateFormatMap.put("yyyy/MM/dd hh:mm:ss", new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"));
        dateFormatMap.put("yyyy/MM/dd HH:mm:ss", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        dateFormatMap.put("yyyyMMdd", new SimpleDateFormat("yyyyMMdd"));
        dateFormatMap.put("yyyyMMddhhmmss", new SimpleDateFormat("yyyyMMddhhmmss"));
    }


    // 私有构造
    DateUtil() {
    }


    /**
     * 获取指定日期所在月最初一天凌晨
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        date =calendar.getTime();
        return DateUtil.getMorning(date);
    }

    /**
     * 获取指定日期所在月最后一天凌晨
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        date=calendar.getTime();
        return DateUtil.getNight(date);
    }


    /**
     * 获取两个日期之间的日期
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 日期列表
     */
    public static List<Date> dateSplit(Date startDate, Date endDate) {
                /* Date start=getNight(startDate);
                 Date end=getMorning(endDate);*/
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(startDate);
        for (int i = 1; i < step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    + (24 * 60 * 60 * 1000)));// 减一天
        }
        return dateList;
    }

    //获取当前日期一周前日期的凌晨
    public static Date getWeekAgo(Date date) {
        Date weekAgo = new Date(date.getTime() - 7 * (24 * 60 * 60 * 1000));
        return getMorning(weekAgo);

    }

    /**
     * 获取当前日期三十天 前的凌晨
     *
     * @param date
     * @return
     */
    public static Date getMonthAgo(Date date) {
        Date monthAgo = new Date(date.getTime() - 30 * (24 * 60 * 60 * 1000));
        return getMorning(monthAgo);
    }

    /**
     * 获取此日期的同一天凌晨零点的Date
     */
    public static Date getMorning(Date date) {
        String dateStr = formatToString(date, "yyyyMMdd");
        return fromString(dateStr, "yyyyMMdd");
    }

    public static Date getStartYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), 0, 1);
        String dateStr = formatToString(calendar.getTime(), "yyyyMMdd");
        return fromString(dateStr, "yyyyMMdd");
    }

    public static Date getEndYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), 11, 31);
        String dateStr = formatToString(calendar.getTime(), "yyyyMMdd");
        return fromString(dateStr, "yyyyMMdd");
    }

    /**
     * 获取此日期的同一天第二天凌晨零点的Date
     */
    public static Date getNight(Date date) {
        long time = getMorning(date).getTime();
        long night = time + MILLIS_PER_DAY;
        return new Date(night);
    }

    /**
     * 判断两个日期是否为同一天。
     */
    public static boolean isOneDay(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Date==>String
     */
    public static String formatToString(Date date, String pattern) {
        // 时间非空判断
        //Assert.notNull(date);
        if(date == null){
            return null;
        }else{
            DateFormat dateFormat;
            // 获取时间格式对应的转换器
            if (StringUtil.isBlank(pattern)) {
                dateFormat = dateFormatMap.get(DEFAULT_FORMAT);
            } else {
                dateFormat = dateFormatMap.get(pattern);
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormatMap.put(pattern, dateFormat);
                }
            }
            return dateFormat.format(date);
        }
    }

    /**
     * @throws ParseException
     */
    public static Date fromString(String dateString, String pattern) {
        Assert.hasLength(dateString);
        DateFormat dateFormat;
        if (StringUtil.isBlank(pattern)) {
            dateFormat = dateFormatMap.get(DEFAULT_FORMAT);
        } else {
            dateFormat = dateFormatMap.get(pattern);
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(pattern);
                dateFormatMap.put(pattern, dateFormat);
            }
        }
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期解析错误。");
        }
    }


    /**
     * 加秒
     */
    public static Date addSecond(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, 1);
        return calendar.getTime();
    }

    /**
     * 加天
     */
    public static Date addDays(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, int second) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
    public static Date addHour(Date date, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 前一天
     */
    public static Date beforeDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 是否是周末。
     */
    public static boolean isWeekEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return dayOfWeek < 1 || dayOfWeek > 5;
    }

    /**
     * 判断日期是否合法。
     * <p/>
     * 这里只是简单判断，并未判断润月和每月的天数不同。只要天数不大于32就认为合法。
     */
    public static boolean judgeDate(int year, int month, int day) {
        return !(year < 2000 || year > 9999 || month < 1 || month > 12 || day < 1 || day > 32);
    }

    /**
     * 输入日期转换为cron表达式
     * @param date
     * @return
     */
    public static String getCron(Date date){
        String dateFormat="ss mm HH dd MM ?";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static Date stringSimpleToDate(String dateString){
      Date date = null;
      if(dateString != null&&!dateString.trim().isEmpty()){
          date = fromString(dateString,"yyyy-MM-dd HH:mm:ss");
      }
      return date;
    }

}
