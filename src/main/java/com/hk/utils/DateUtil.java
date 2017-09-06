package com.hk.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * User: hk
 * Date: 2017/8/10 下午4:31
 * version: 1.0
 */
public final class DateUtil {

    private static final String DT_STANDARD_DATE = "yyyy-MM-dd";
    private static final String DT_SHORT_DATE = "yyyyMMdd";
    private static final String DT_STANDARD_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String DT_STANDARD_TIME = "HH:mm:ss";
    private static final String DT_SHORT_TIME = "HHmmss";


    /**
     * <p>获取当前日期</p>
     *
     * @return 当前日期（yyyy-MM-dd）
     */
    public static String getCurrDate() {
        return LocalDate.now().toString(DT_STANDARD_DATE);
    }

    /**
     * <p>获取当前日期</p>
     *
     * @return 当前日期（yyyyMMdd）
     */
    public static String getCurrDate8() {
        return new DateTime().toString(DT_SHORT_DATE);
    }

    /**
     * <p>获取当前时间</p>
     *
     * @return 当前时间（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrDateTime() {
        return DateTime.now().toString(DT_STANDARD_DATE_TIME);
    }

    /**
     * <p>获取当前时间（HH:mm:ss）</p>
     *
     * @return 获取当前时间
     */
    public static String getCurrTime() {
        return LocalTime.now().toString(DT_STANDARD_TIME);
    }

    /**
     * <p>获取当前时间（HHmmss）</p>
     *
     * @return 获取当前时间
     */
    public static String getCurrTime6() {
        return LocalTime.now().toString(DT_SHORT_TIME);
    }

    /**
     * <p>获取今天剩余的秒数</p>
     *
     * @return 秒数
     */
    public static int oddSecondOfDay() {
        DateTime start = new DateTime();
        DateTime end = new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return Seconds.secondsBetween(start, end).getSeconds();
    }

    /**
     * <p>获取本周剩余的秒数</p>
     *
     * @return 秒数
     */
    public static int oddSecondOfWeek() {
        DateTime start = new DateTime();
        DateTime end =
            new DateTime().dayOfWeek().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return Seconds.secondsBetween(start, end).getSeconds();
    }

    /**
     * <p>获取本月剩余的秒数</p>
     *
     * @return 秒数
     */
    public static int oddSecondOfMonth() {
        DateTime start = new DateTime();
        DateTime end =
            new DateTime().dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return Seconds.secondsBetween(start, end).getSeconds();
    }

    /**
     * <p>获取今年剩余的秒数</p>
     *
     * @return 秒数
     */
    public static int oddSecondOfYear() {
        DateTime start = new DateTime();
        DateTime end =
            new DateTime().dayOfYear().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return Seconds.secondsBetween(start, end).getSeconds();
    }

    /**
     * <p>今天距离指定日期剩余天数</p>
     * 例如：
     * <pre>
     *     {
     *         当前日期：2017-08-11
     *         oddDayOfDate(2017-08-01) = -10
     *         oddDayOfDate(2017-08-15) = 3
     *     }
     * </pre>
     *
     * @param date 日期参数（yyyy-MM-dd）
     * @return 天数
     */
    public static int oddDayOfDate(String date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime currDate = new DateTime();
        DateTime paramDate = fmt.parseDateTime(date);

        return Days.daysBetween(currDate, paramDate).getDays();

    }

    /**
     * <p>是否包含在区间范围内（yyyy-MM-dd）</p>
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return true/false
     */
    public static boolean intervalDate(String start, String end) {

        DateTime startTime = getMinDateTime(start);
        DateTime endTime = getMaxDateTime(end);

        Interval interval = new Interval(startTime, endTime);
        return interval.contains(new DateTime());
    }

    /**
     * <p>是否包含在区间范围内（yyyy-MM-dd HH:mm:ss）</p>
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return true/false
     */
    public static boolean intervalDateTime(String start, String end) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime startTime = fmt.parseDateTime(start);
        DateTime endTime = fmt.parseDateTime(end);

        Interval interval = new Interval(startTime, endTime);
        return interval.contains(new DateTime());
    }

    /**
     * <p>获取指定日期开始时间(yyyy-MM-dd 00:00:00)</p>
     * <pre>
     *     getMinDateTime("2017-01-01") = "2017-01-01 00:00:00"
     * </pre>
     *
     * @param date (yyyy-MM-dd)
     * @return yyyy-MM-dd 00:00:00
     */
    public static DateTime getMinDateTime(String date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime startTime =
            fmt.parseDateTime(date).withHourOfDay(00).withMinuteOfHour(00).withSecondOfMinute(00);
        return startTime;
    }

    /**
     * <p>获取指定日期开始时间(yyyy-MM-dd 23:59:59)</p>
     * <pre>
     *     getMaxDateTime("2017-01-01") = "2017-01-01 23:59:59"
     * </pre>
     *
     * @param date (yyyy-MM-dd)
     * @return endTime
     */
    public static DateTime getMaxDateTime(String date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime endTime =
            fmt.parseDateTime(date).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return endTime;
    }



    /**
     * <p>是否包含在指定的区间范围内</p>
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return true/false
     */
    public static boolean intervalOfTime(String start, String end) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateStart = getCurrDate() + " " + start;
        String dateEnd = getCurrDate() + " " + end;
        DateTime startTime = fmt.parseDateTime(dateStart);
        DateTime endTime = fmt.parseDateTime(dateEnd);


        Interval interval = new Interval(startTime, endTime);
        return interval.contains(new DateTime());
    }

    /**
     * 字符串转换DateTime
     * @param date 日期
     */
    public static DateTime parseDateTime(String date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return fmt.parseDateTime(date);
    }

    /**
     * 参数日期是否在开当前日期之后
     * @param date 日期【yyyy-MM-dd HH:mm:ss】
     */
    public static boolean isAfterNow(String date){
        DateTime dateTime = parseDateTime(date);
        return dateTime.isAfterNow();
    }

    /**
     * 参数日期是否在开当前日期之后
     * @param date 日期【yyyy-MM-dd HH:mm:ss】
     */
    public static boolean isBeforeNow(String date){
        DateTime dateTime = parseDateTime(date);
        return dateTime.isBeforeNow();
    }



}
