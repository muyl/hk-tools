package com.hk.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * User: hk
 * Date: 2017/8/10 下午4:31
 * version: 1.0
 */
public class DateUtil {

    private static final String DT_STANDARD = "yyyy-MM-dd";
    private static final String DT_SHORT_DATE = "yyyyMMdd";
    private static final String DT_LONG_DATE = "yyyy-MM-dd HH:mm:ss";
    private static final String DT_TIME = "HH:mm:ss";

    /**
     * 获取当前日期
     *
     * @return 当前日期（yyyy-MM-dd）
     */
    public static String getCurrDate() {
        return LocalDate.now().toString(DT_STANDARD);
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期（yyyyMMdd）
     */
    public static String getCurrShortDate() {
        return new DateTime().toString(DT_SHORT_DATE);
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrDateTime() {
        return DateTime.now().toString(DT_LONG_DATE);
    }

    /**
     * 获取当前时间（HH:mm:ss）
     *
     * @return 获取当前时间
     */
    public static String getCurrTime() {
        return LocalTime.now().toString(DT_TIME);
    }

    /**
     * 获取今天剩余的秒数
     *
     * @return 秒数
     */
    public static int oddSecondOfDay() {
        DateTime start = new DateTime();
        DateTime end = new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return Seconds.secondsBetween(start, end).getSeconds();
    }

    /**
     * 获取本周剩余的秒数
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
     * 获取本月剩余的秒数
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
     * 获取今年剩余的秒数
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
     * 今天距离指定日期剩余天数
     * <p>
     * 例如：
     * <pre>
     *     {
     *         当前日期：2017-08-11
     *         oddDayOfDate(2017-08-01) = -10
     *         oddDayOfDate(2017-08-15) = 3
     *     }
     * </pre>
     * @param date 日期参数（yyyy-MM-dd）
     * @return 天数
     */
    public static int oddDayOfDate(String date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime currDate = new DateTime();
        DateTime paramDate = fmt.parseDateTime(date);

        return Days.daysBetween(currDate,paramDate).getDays();

    }

    /**
     * 是否包含在区间范围内（yyyy-MM-dd）
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 比较结果
     */
    public static boolean containsIntervalOfDate(String start, String end) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime startTime =
            fmt.parseDateTime(start).withHourOfDay(00).withMinuteOfHour(00).withSecondOfMinute(00);
        DateTime endTime =
            fmt.parseDateTime(end).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);


        Interval interval = new Interval(startTime, endTime);
        return interval.contains(new DateTime());
    }

    /**
     * 是否包含在区间范围内（yyyy-MM-dd HH:mm:ss）
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 比较结果
     */
    public static boolean containsIntervalOfDateTime(String start, String end) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime startTime = fmt.parseDateTime(start);
        DateTime endTime = fmt.parseDateTime(end);

        Interval interval = new Interval(startTime, endTime);
        return interval.contains(new DateTime());
    }



    /**
     * 是否包含在指定的区间范围内
     * @param start 开始时间
     * @param end 结束时间
     * @return 比较结果
     */
    public static boolean containsIntervalOfTime(String start, String end) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateStart = getCurrDate() + " " + start;
        String dateEnd = getCurrDate() + " " + end;
        DateTime startTime = fmt.parseDateTime(dateStart);
        DateTime endTime = fmt.parseDateTime(dateEnd);


        Interval interval = new Interval(startTime, endTime);
        return interval.contains(new DateTime());
    }







}
