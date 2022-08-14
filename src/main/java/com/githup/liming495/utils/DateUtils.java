package com.githup.liming495.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * 日期工具类
 *
 * @author Guppy
 */
public abstract class DateUtils {
    private static final String PATTERN_ONLY_DATE = "yyyy-MM-dd";
    private static final String PATTERN_ONLY_TIME = "HH:mm:ss";
    private static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final HashMap<String, DateTimeFormatter> FORMATS = new HashMap<>();

    static {
        FORMATS.put(PATTERN_ONLY_DATE, ofPattern(PATTERN_ONLY_DATE));
        FORMATS.put(PATTERN_ONLY_TIME, ofPattern(PATTERN_ONLY_TIME));
        FORMATS.put(PATTERN_DATETIME, ofPattern(PATTERN_DATETIME));
    }

    /**
     * 日期对象转字符串，用户指定格式化对象
     *
     * @param date       日期对象
     * @param dateFormat 格式化对象
     * @return 日期对象转字符串
     */
    public static String dateToString(Date date, DateTimeFormatter dateFormat) {
        if (date == null) {
            throw new IllegalArgumentException("date can't be null");
        }
        return dateFormat.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    /**
     * 日期对象转字符串，用户指定格式
     *
     * @param date    日期对象
     * @param pattern 格式化
     * @return 日期对象转字符串
     */
    public static String dateToString(Date date, String pattern) {
        return dateToString(date, getDateTimeFormatter(pattern));
    }

    /**
     * 字符串转日期对象，用户指定格式化对象
     *
     * @param dateStr    日期字符串
     * @param dateFormat 格式化对象
     * @return Date
     */
    public static Date parse(String dateStr, DateTimeFormatter dateFormat) {
        if (dateStr == null) {
            throw new IllegalArgumentException("dateStr can't be null");
        }
        return localDateTimeToDate(LocalDateTime.parse(dateStr, dateFormat));
    }

    /**
     * 字符串转日期对象，用户指定格式
     *
     * @param dateStr 日期字符串
     * @param pattern 格式化
     * @return Date
     * @throws ParseException 解析异常
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        return parse(dateStr, getDateTimeFormatter(pattern));
    }

    /**
     * 根据格式获取格式化对象
     *
     * @param pattern 格式
     * @return 格式化对象
     */
    private static DateTimeFormatter getDateTimeFormatter(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern can't be null");
        }
        DateTimeFormatter dateFormat = FORMATS.get(pattern);
        if (dateFormat == null) {
            dateFormat = ofPattern(pattern);
            FORMATS.put(pattern, dateFormat);
        }
        return dateFormat;
    }

    /**
     * 日期转字符串，格式：yyyy-MM-dd
     *
     * @param date 时间值
     * @return 日期转字符串
     */
    public static String dateToString_only_date(Date date) {
        return dateToString(date, PATTERN_ONLY_DATE);
    }

    /**
     * 当前日期转字符串，格式：yyyy-MM-dd
     *
     * @return 当前日期转字符串
     */
    public static String nowToString_only_date() {
        return dateToString_only_date(new Date());
    }

    /**
     * 字符串转日期，格式：yyyy-MM-dd
     *
     * @param strDate 日期字符串
     * @return Date
     * @throws ParseException 解析异常
     */
    public static Date parse_only_date(String strDate) throws ParseException {
        return parse(strDate, PATTERN_ONLY_DATE);
    }

    /**
     * 日期转字符串，格式：HH:mm:ss
     *
     * @param date 时间值
     * @return 日期转字符串
     */
    public static String dateToString_only_time(Date date) {
        return dateToString(date, PATTERN_ONLY_TIME);
    }

    /**
     * 当前日期转字符串，格式：HH:mm:ss
     *
     * @return 当前日期转字符串
     */
    public static String nowToString_only_time() {
        return dateToString_only_time(new Date());
    }

    /**
     * 字符串转日期，格式：HH:mm:ss
     *
     * @param strDate 日期字符串
     * @return Date
     * @throws ParseException 解析异常
     */
    public static Date parse_only_time(String strDate) throws ParseException {
        return parse(strDate, PATTERN_ONLY_TIME);
    }

    /**
     * 日期转字符串，格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 时间值
     * @return 日期转字符串
     */
    public static String dateToString(Date date) {
        return dateToString(date, PATTERN_DATETIME);
    }

    /**
     * 当前日期转字符串，格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 当前日期转字符串
     */
    public static String nowToString() {
        return dateToString(new Date());
    }

    /**
     * 字符串转日期，格式：yyyy-MM-dd HH:mm:ss
     *
     * @param strDate 日期字符串
     * @return Date
     * @throws ParseException 解析异常
     */
    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, PATTERN_DATETIME);
    }

    /**
     * 多少年以前
     *
     * @param manyYears 多少年,传正数
     * @return 多少年以前
     */
    public static Date beforeManyYears(Integer manyYears) {
        return beforeManyYears(null, manyYears);
    }

    /**
     * 多少月以前
     *
     * @param manyMonths 多少月,传正数
     * @return 多少月以前
     */
    public static Date beforeManyMonths(Integer manyMonths) {
        return beforeManyMonths(null, manyMonths);
    }

    /**
     * 多少天以前
     *
     * @param manyDays 多少天,传正数
     * @return 多少天以前
     */
    public static Date beforeManyDays(Integer manyDays) {
        return beforeManyDays(null, manyDays);
    }

    /**
     * 多少年以前
     *
     * @param date      日期对象，不传默认是当前
     * @param manyYears 多少年,传正数
     * @return 多少年以前
     */
    public static Date beforeManyYears(Date date, Integer manyYears) {
        if (manyYears > 0) {
            manyYears = -manyYears;
        }
        assert date != null;
        return localDateTimeToDate(dateToLocalDateTime(date).minusYears(manyYears));
    }

    /**
     * 多少年以前
     *
     * @param date       日期对象，不传默认是当前
     * @param manyMonths 多少月,传正数
     * @return 多少月以前
     */
    public static Date beforeManyMonths(Date date, Integer manyMonths) {
        if (manyMonths > 0) {
            manyMonths = -manyMonths;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MONTH, manyMonths);
        return calendar.getTime();
    }

    /**
     * 多少天以前
     *
     * @param date     日期对象，不传默认是当前
     * @param manyDays 多少天,传正数
     * @return 多少天以前
     */
    public static Date beforeManyDays(Date date, Integer manyDays) {
        if (manyDays > 0) {
            manyDays = -manyDays;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DAY_OF_YEAR, manyDays);
        return calendar.getTime();
    }

    /**
     * 多少年以后
     *
     * @param manyYears 多少年,传正数
     * @return 多少年以后
     */
    public static Date afterManyYears(Integer manyYears) {
        return afterManyYears(null, manyYears);
    }

    /**
     * 多少月以后
     *
     * @param manyMonths 多少月,传正数
     * @return 多少年以后
     */
    public static Date afterManyMonths(Integer manyMonths) {
        return afterManyMonths(null, manyMonths);
    }

    /**
     * 多少天以后
     *
     * @param manyDays 多少天,传正数
     * @return 多少天以后
     */
    public static Date afterManyDays(Integer manyDays) {
        return afterManyDays(null, manyDays);
    }

    /**
     * 多少年以后
     *
     * @param date      日期对象，不传默认是当前
     * @param manyYears 多少年,传正数
     * @return 多少年以后
     */
    public static Date afterManyYears(Date date, Integer manyYears) {
        if (manyYears < 0) {
            manyYears = -manyYears;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.YEAR, manyYears);
        return calendar.getTime();
    }

    /**
     * 多少月以后
     *
     * @param date       日期对象，不传默认是当前
     * @param manyMonths 多少月,传正数
     * @return 多少年以后
     */
    public static Date afterManyMonths(Date date, Integer manyMonths) {
        if (manyMonths < 0) {
            manyMonths = -manyMonths;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MONTH, manyMonths);
        return calendar.getTime();
    }

    /**
     * 多少天以后
     *
     * @param date     日期对象，不传默认是当前
     * @param manyDays 多少天,传正数
     * @return 多少天以后
     */
    public static Date afterManyDays(Date date, Integer manyDays) {
        if (manyDays < 0) {
            manyDays = -manyDays;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DAY_OF_YEAR, manyDays);
        return calendar.getTime();
    }

    /**
     * 当天开始的时间
     *
     * @return 当天开始的时间
     */
    public static Date todayBegin() {
        return dayBegin(new Date());
    }

    /**
     * 获得某个日期对象的开始时间
     *
     * @param date 某个日期对象
     * @return 某个日期对象的开始时间
     */
    public static Date dayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当天结束的时间
     *
     * @return 当天结束的时间
     */
    public static Date todayEnd() {
        return dayEnd(new Date());
    }

    /**
     * 获得某个日期对象的结束时间
     *
     * @param date 某个日期对象
     * @return 某个日期对象的结束时间
     */
    public static Date dayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 日期偏移，返回格式:yyyyMMdd
     *
     * @param date   日期
     * @param offset 偏移
     * @return 日期偏移
     */
    public static Date getDateByDayOffset(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    private static final long base = 1388505600000L;// 1970-2014年的毫秒数，做为基数被减掉，以减少长度

    /**
     * 获取2014年1月1日到现在的秒数
     *
     * @return String
     */
    public static String getMyUnixSecStr() {
        return getMyUnixSec().toString();
    }

    /**
     * 获取2014年1月1日到现在的秒数
     *
     * @return Long
     */
    public static Long getMyUnixSec() {
        return System.currentTimeMillis() - base;
    }

    /**
     * 获取2014年1月1日到现在的秒数
     *
     * @param date 时间
     * @return 2014年1月1日到现在的秒数
     */
    public static Long getMyUnixSec(Date date) {
        return date.getTime() - base;
    }

    /**
     * 获取当前时间前几天的时间
     *
     * @param now    当前时间
     * @param timing 前几天的时间
     * @return 当前时间前几天的时间
     */
    public static Date getBeforeTimeOfDay(Date now, int timing) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(now);
        c.add(Calendar.DAY_OF_YEAR, -timing);
        return c.getTime();
    }

    /**
     * 获取当前时间前几天的时间-具体到秒
     *
     * @param now    当前时间
     * @param timing 前几天的时间
     * @return 当前时间前几天的时间
     */
    public static Date getBeforeDateOfTime(Date now, int timing) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(now);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE) - timing, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
        return c.getTime();
    }

    private static long ns = 1000; // 一秒钟的毫秒数

    /**
     * 毫秒转具体秒数
     *
     * @param time 毫秒
     * @return 毫秒转具体秒数
     */
    public static long getdiffTimeSec(long time) {
        return time / ns;
    }

    /**
     * 获取日期目录形式的字符串
     *
     * @return String yyyy-MM-dd
     */
    public static Date nowDate() {
        Calendar cal_now = Calendar.getInstance();
        return cal_now.getTime();
    }

    private static final SimpleDateFormat format_date_Hour = new SimpleDateFormat("HH");

    public static int getDateHour() throws Exception {
        String hour = format_date_Hour.format(new Date());
        return Integer.parseInt(hour);
    }

    private static final SimpleDateFormat format_date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 某日期的开始
     *
     * @param date 某日期
     * @return 某日期的开始
     */
    public static Date dayTimeBegin(String date) {
        Date r = null;
        try {
            r = format_date_time.parse(date + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 某日期的结束
     *
     * @param date 日期
     * @return 日期的结束
     */
    public static Date dayTimeEnd(String date) {
        Date r = null;
        try {
            r = format_date_time.parse(date + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 获取当前时间后几小时的时间
     *
     * @param now    前时间
     * @param timing 后几小时的时间
     * @return 前时间后几小时的时间
     */
    public static Date getAfterTimeOfHours(Date now, int timing) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(now);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY) + timing,
                c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
        return c.getTime();
    }

    /**
     * 获取日期目录形式的字符串
     *
     * @param date 日期
     * @return 日期目录形式的字符串
     */
    public static Date dateToDay(Date date) {
        try {
            String sdate = dateString(date);
            return formatDateDay(sdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取日期目录形式的字符串
     *
     * @param date 日期
     * @return String yyyy-MM-dd
     */
    public static String dateString(Date date) {
        return format_date.format(date);
    }

    /**
     * 将字符串转换成标准时间格式
     *
     * @param date yyyy-MM-dd
     * @return 标准时间
     */
    public static Date formatDateDay(String date) {
        Date new_date = null;
        try {
            new_date = format_date.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new_date;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static List<Date> daysBetween(Date smdate, Date bdate) {
        if (bdate == null || smdate == null || bdate.getTime() < smdate.getTime()) {
            throw new IllegalArgumentException();
        }
        List<Date> dates = new ArrayList<Date>();
        Calendar cal_sm = Calendar.getInstance();
        cal_sm.clear();
        cal_sm.setTime(smdate);
        Calendar cal_big = Calendar.getInstance();
        cal_big.clear();
        cal_big.setTime(bdate);
        while (dateCompare(cal_big, cal_sm)) {
            dates.add(cal_sm.getTime());
            cal_sm.add(Calendar.DAY_OF_YEAR, +1);
        }
        dates.add(cal_big.getTime());
        return dates;
    }

    /**
     * 对比两个日历中的日期哪个大，不对比时间
     *
     * @param c1 日历1
     * @param c2 日历2
     * @return 返回c1是否比c2大
     */
    private static boolean dateCompare(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)
                || (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.DAY_OF_YEAR) > c2.get(Calendar.DAY_OF_YEAR));
    }

    public static Date combinationDateTime(Date d, Date t) {
        String date = dateString(d);
        String time = dateToStrTime(t);
        return formatMinutes(date + " " + time);
    }

    private static final SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");

    /**
     * 获取日期目录形式的字符串
     *
     * @param time 日期
     * @return String HH:mm:ss
     */
    public static String dateToStrTime(Date time) {
        return format_time.format(time);
    }

    private static final SimpleDateFormat format_date_minutes = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 将字符串转换成标准时间格式
     *
     * @param date yyyy-MM-dd HH:mm
     * @return 标准时间
     */
    public static Date formatMinutes(String date) {
        Date new_date = null;
        try {
            new_date = format_date_minutes.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new_date;
    }

    /**
     * 计算某个日期到当前日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static List<Date> daysBetweenNowDate(Date smdate, Date bdate) {
        if (bdate == null || smdate == null || bdate.getTime() < smdate.getTime()) {
            throw new IllegalArgumentException();
        }
        List<Date> dates = new ArrayList<Date>();
        Calendar cal_sm = Calendar.getInstance();
        cal_sm.clear();
        cal_sm.setTime(smdate);
        Calendar cal_big = Calendar.getInstance();
        cal_big.clear();
        cal_big.setTime(bdate);
        Calendar cal_now = Calendar.getInstance();
        while (dateCompare(cal_now, cal_sm)) {
            dates.add(cal_sm.getTime());
            cal_sm.add(Calendar.DAY_OF_YEAR, +1);
            if (dateCompare(cal_sm, cal_big)) {
                break;
            }
        }
        return dates;
    }

    /**
     * 把日期集合转换成字符串集合
     *
     * @param dateList 时间数组
     * @return 字符数组
     */
    public static List<String> dateStringList(List<Date> dateList) {
        List<String> list = new ArrayList<String>();
        for (Date date : dateList) {
            list.add(format_date.format(date));
        }
        return list;
    }

    private static final SimpleDateFormat format_month = new SimpleDateFormat("yyyy-MM");

    /**
     * 将字符串转换成标准时间格式
     *
     * @param date yyyy-MM
     * @return 标准时间
     */
    public static Date formatDateMonth(String date) {
        Date new_date = null;
        try {
            new_date = format_month.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new_date;
    }

    /**
     * 返回指定日期的月的第一天
     *
     * @param date 指定日期的月
     * @return 指定日期的月的第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @param date 指定日期的月
     * @return 指定日期的月的最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 将当前时间转换成标准时间格式
     *
     * @param date yyyy-MM-dd
     * @return 标准时间
     */
    public static Date dateToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前时间后几分钟的时间
     *
     * @param now    当前时间
     * @param timing 后几分钟的时间
     * @return 当前时间后几分钟的时间
     */
    public static Date getAfterTimeOfMinutes(Date now, int timing) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE) + timing, 0);
        return c.getTime();
    }

    /**
     * 将当前时间转换成标准时间格式
     *
     * @param date yyyy-MM-dd HH:mm
     * @return 标准时间
     */
    public static Date dateToDateMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前时间前几小时的时间
     *
     * @param now    当前时间
     * @param timing 前几小时
     * @return 当前时间前几小时的时间
     */
    public static Date getBeforeTimeOfHours(Date now, int timing) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY) - timing,
                c.get(Calendar.MINUTE), 0);
        return c.getTime();
    }

    /**
     * 获取当前时间前几天的时间-具体到分钟
     *
     * @param now    当前时间
     * @param timing 前几天
     * @return 当前时间前几天的时间
     */
    public static Date getBeforeTimeOfDate(Date now, int timing) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE) - timing, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), 0);
        return c.getTime();
    }

    /**
     * 上一小时开始的时间
     *
     * @return 上一小时开始的时间
     */
    public static Date preHourBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY) - 1, 0, 0);
        return c.getTime();
    }

    /**
     * 上一小时结束的时间
     *
     * @return 上一小时结束的时间
     */
    public static Date preHourEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY) - 1, 59, 59);
        return c.getTime();
    }

    /**
     * 上一天开始的时间
     *
     * @return 上一天开始的时间
     */
    public static Date preDayBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE) - 1, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 上一天结束的时间
     *
     * @return 上一天结束的时间
     */
    public static Date preDayEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE) - 1, 23, 59, 59);
        return c.getTime();
    }

    public static Date getMonday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != 2) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        return calendar.getTime();
    }

    private static final SimpleDateFormat format_only_date = new SimpleDateFormat("yyyyMMdd");

    /**
     * 日期偏移，返回格式:yyyyMMdd
     *
     * @param date   时间
     * @param offset 偏移
     * @return 偏移量
     */
    public static String dayOffset(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return format_only_date.format(calendar.getTime());
    }

    /**
     * 根据用户生日计算年龄
     *
     * @param birthday 生日
     * @return 年龄
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }


    private static final DateTimeFormatter DATE_TIME_FORMATTER_DAY = ofPattern(DateStyle.CT_D);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_HOUR = ofPattern(DateStyle.CT_H);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_MIN = ofPattern(DateStyle.CT_MM);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_SEC = ofPattern(DateStyle.CT_S);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_TIMESTAMP_SEC = ofPattern(DateStyle.CP_S);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_TIMESTAMP_MS = ofPattern(DateStyle.CP_SSS);

    /**
     * data 转 LocalDate
     *
     * @param date date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * data 转 LocalDateTime
     *
     * @param date date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * localDateTime 转 Date
     *
     * @param localDateTime localDateTime
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDate 转 Date
     *
     * @param localDate localDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 按照指定的format格式化，LocalDateUtils本身已经定义了一些常用格式，可直接使用，也可自定义
     *
     * @param format 时间格式
     * @return String
     */
    public static String formatNow(String format) {
        return LocalDateTime.now().format(ofPattern(format));
    }

    /**
     * 格式化当前时间【年月日时分秒】
     *
     * @return 20000101000000000
     */
    public static String formatNowOfTimestampMS() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_TIMESTAMP_MS);
    }

    /**
     * 格式化当前时间【年月日时分秒】
     *
     * @return 20000101000000
     */
    public static String formatNowOfTimestampSec() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_TIMESTAMP_SEC);
    }

    /**
     * 格式化当前时间【年-月-日】
     *
     * @return 2000-01-01
     */
    public static String formatNowOfDay() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_DAY);
    }

    /**
     * 格式化当前时间【年-月-日 小时】
     *
     * @return 2000-01-01 08
     */
    public static String formatNowOfHour() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_HOUR);
    }

    /**
     * 格式化当前时间【年-月-日 小时:分钟】
     *
     * @return 2000-01-01 08:00
     */
    public static String formatNowOfMin() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_MIN);
    }

    /**
     * 格式化当前时间【年-月-日 小时:分钟:秒】
     *
     * @return 2000-01-01 08:00:00
     */
    public static String formatNowOfSec() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 格式化时间为yyyy-MM-dd HH:mm:ss，然后进行比较
     *
     * @param date1 比较时间1
     * @param date2 比较时间2
     * @return -1、0、1
     */
    public static int compare(String date1, String date2) {
        LocalDateTime d1 = LocalDateTime.parse(date1, DATE_TIME_FORMATTER_SEC);
        LocalDateTime d2 = LocalDateTime.parse(date2, DATE_TIME_FORMATTER_SEC);
        return d1.compareTo(d2);
    }

    /**
     * 计算两个时间相差的毫秒数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 08:00:02
     * @return 相差的毫秒数：2000
     */
    public static long diffMillis(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toMillis();
    }

    /**
     * 计算两个时间相差的秒数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 08:00:02
     * @return 相差的秒数：2
     */
    public static long diffSec(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toMillis() / 1000;
    }

    /**
     * 计算两个时间相差的分钟数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 08:01:00
     * @return 相差的分钟数：1
     */
    public static long diffMin(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toMinutes();
    }

    /**
     * 计算两个时间相差的小时数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-01 09:00:00
     * @return 相差的小时数：1
     */
    public static long diffHour(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toHours();
    }

    /**
     * 计算两个时间相差的天数
     *
     * @param start 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param end   【年-月-日 小时:分钟:秒】2000-01-03 08:00:00
     * @return 相差的天数：2
     */
    public static long diffDay(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER_SEC);
        LocalDateTime endTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER_SEC);
        return Duration.between(startTime, endTime).toDays();
    }

    /**
     * 让time时间减去指定的年数
     *
     * @param time  【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param years 减去指定的年数
     * @return 减去后的时间
     */
    public static String minusYears(String time, int years) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusYears(years).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的月数
     *
     * @param time   【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param months 减去指定的月数
     * @return 减去后的时间
     */
    public static String minusMonths(String time, int months) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusMonths(months).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的天数
     *
     * @param time 【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param days 要减去的天数，当传入负数时，等于加上指定的天数
     * @return 减去后的时间
     */
    public static String minusDay(String time, int days) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusDays(days).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的小时数
     *
     * @param time  【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param hours 要减去的小时数
     * @return 减去后的时间
     */
    public static String minusHours(String time, int hours) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusHours(hours).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的分钟数
     *
     * @param time    【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param minutes 要减去的分钟数
     * @return 减去后的时间
     */
    public static String minusMin(String time, int minutes) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusMinutes(minutes).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 让time时间减去指定的秒数
     *
     * @param time    【年-月-日 小时:分钟:秒】2000-01-01 08:00:00
     * @param seconds 要减去的秒数
     * @return 减去后的时间
     */
    public static String minusSeconds(String time, int seconds) {
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER_SEC).minusSeconds(seconds).format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 判断当前时间是否为当月的最后一天
     *
     * @return true/false
     */
    public static boolean isLastDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        return Period.between(today, lastDayOfMonth).getDays() == 0;
    }

    /**
     * 判断当前时间是否为当月的第一天
     *
     * @return true/false
     */
    public static boolean isFirstDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return Period.between(today, firstDayOfMonth).getDays() == 0;
    }

    /**
     * 获取当月的最后一天
     *
     * @return yyyy-MM-dd 字符串
     */
    public static String getLastDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        return lastDayOfMonth.format(DATE_TIME_FORMATTER_DAY);
    }

    /**
     * 获取当月的第一天
     *
     * @return yyyy-MM-dd 字符串
     */
    public static String getFirstDayOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return firstDayOfMonth.format(DATE_TIME_FORMATTER_DAY);
    }

    /**
     * string转date
     *
     * @param date           字符串
     * @param originalFormat 当前字符串的日期格式
     * @return new date
     */
    public static Date parseStrToDate(String date, String originalFormat) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(originalFormat);
        LocalDateTime time = LocalDateTime.from(LocalDate.parse(date, formatter).atStartOfDay());
        return localDateTimeToDate(time);
    }

    /**
     * 把date按照指定格式转成字符串
     *
     * @param date   date
     * @param format 格式
     * @return new date
     */
    public static String formatDate(Date date, String format) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.format(ofPattern(format));
    }

    /**
     * 把原时间格式，转换成另一种时间格式
     *
     * @param date           string 时间
     * @param originalFormat 原格式
     * @param newFormat      转换后的格式
     * @return new date
     */
    public static String formatString(String date, String originalFormat, String newFormat) {
        return formatDate(parseStrToDate(date, originalFormat), newFormat);
    }

    /**
     * 获取一天中的开始时间
     *
     * @return 2020-01-01 00:00:00
     */
    public static String dayOfStart() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return localDateTime.format(DATE_TIME_FORMATTER_SEC);
    }

    /**
     * 获取一天中的结束时间
     *
     * @return 2020-01-01 23:59:59
     */
    public static String dayOfEnd() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return localDateTime.format(DATE_TIME_FORMATTER_SEC);
    }
}
