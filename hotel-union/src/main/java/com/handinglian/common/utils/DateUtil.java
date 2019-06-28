package com.handinglian.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author pxl
 * @description
 * @date 2019/4/28 16:57
 */
public class DateUtil {
    public DateUtil() {
    }

    public static String formatDatetime(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    public static String formatDatetimeMinute(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(date);
    }

    public static String formatDate(Date date) {
        return date == null ? "" : (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static Integer todayInt() {
        SimpleDateFormat ONLY_DATE_INT = new SimpleDateFormat("yyyyMMdd");
        String todayText = ONLY_DATE_INT.format(now());
        Integer today = new Integer(todayText);
        return today;
    }

    public static String nowDate() {
        SimpleDateFormat ONLY_DATE = new SimpleDateFormat("yyyy-MM-dd");
        return ONLY_DATE.format(new Date());
    }

    public static Date thatDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(11, 0);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            return cal.getTime();
        }
    }

    public static Date nextDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, 1);
        return thatDay(cal.getTime());
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        cal.set(5, day);
        return thatDay(cal.getTime());
    }

    public static Date tenYearsLater() {
        Calendar cal = Calendar.getInstance();
        cal.add(1, 10);
        return cal.getTime();
    }

    public static Date after1Hour() {
        Calendar cal = Calendar.getInstance();
        cal.add(10, 1);
        return cal.getTime();
    }

    public static Date after24Hour() {
        Calendar cal = Calendar.getInstance();
        cal.add(10, 24);
        return cal.getTime();
    }

    public static Date after1Week() {
        Calendar cal = Calendar.getInstance();
        cal.add(5, 7);
        return cal.getTime();
    }

    public static Date from(String source) {
        if (StringUtil.isBlank(source)) {
            return null;
        } else {
            try {
                String[] timeParts = source.split(":");
                if (timeParts.length == 1) {
                    return (new SimpleDateFormat("yyyy-MM-dd")).parse(source);
                }

                if (timeParts.length == 2) {
                    return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(source);
                }

                if (timeParts.length == 3) {
                    return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(source);
                }
            } catch (ParseException var2) {
                var2.printStackTrace();
            }

            return null;
        }
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = new String[]{"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static Integer parseDateInt(String date10) {
        Integer result = null;

        try {
            SimpleDateFormat DEFAULT = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat DEFAULT_INT = new SimpleDateFormat("yyyyMMdd");
            Date date = DEFAULT.parse(date10);
            result = Integer.parseInt(DEFAULT_INT.format(date));
        } catch (Exception var5) {
            ;
        }

        return result;
    }

    public static String formatDate(Integer date8) {
        String result = "";

        try {
            String dateTime = date8.toString();
            result = dateTime.substring(0, 4) + "-" + dateTime.substring(4, 6) + "-" + dateTime.substring(6);
        } catch (Exception var3) {
            ;
        }

        return result;
    }

    public static int getDateInt(Date date) {
        if (date == null) {
            return getDateInt(new Date());
        } else {
            Integer result = Integer.valueOf(0);

            try {
                SimpleDateFormat DEFAULT_INT = new SimpleDateFormat("yyyyMMdd");
                result = Integer.parseInt(DEFAULT_INT.format(date));
            } catch (Exception var3) {
                ;
            }

            return result.intValue();
        }
    }

    public static long getTenSecondsSpanOneDay(Date date) {
        if (date == null) {
            return 0L;
        } else {
            long timestamp = date.getTime();
            Date today = thatDay(date);
            long timestampToday = today.getTime();
            long result = (timestamp - timestampToday) / 10000L;
            return result;
        }
    }

    public static Date getEndDate(Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.set(10, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, 1);
        return cal.getTime();
    }

    public static Integer firstDateOfThisWeek() {
        return firstDateOfThisWeek(now());
    }

    public static Integer firstDateOfNextWeek() {
        return firstDateOfThisWeek(after1Week());
    }

    public static Integer firstDateOfThisWeek(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        int offset = 0;
        switch(monday.get(7)) {
            case 1:
                offset = -6;
                break;
            case 2:
                offset = 0;
                break;
            case 3:
                offset = -1;
                break;
            case 4:
                offset = -2;
                break;
            case 5:
                offset = -3;
                break;
            case 6:
                offset = -4;
                break;
            case 7:
                offset = -5;
        }

        monday.add(5, offset);
        Integer result = getDateInt(monday.getTime());
        return result;
    }

    public static Integer firstDateOfThisMonth() {
        return firstDateOfThisMonth(now());
    }

    public static Integer firstDateOfNextMonth() {
        Calendar c = Calendar.getInstance();
        c.add(2, 1);
        c.set(5, 1);
        return c.get(1) * 10000 + (c.get(2) + 1) * 100 + c.get(5);
    }

    public static Integer firstDateOfThisMonth(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        int year = monday.get(1);
        int month = monday.get(2) + 1;
        Integer result = (year * 100 + month) * 100 + 1;
        return result;
    }

    public static Date getAfter1Week(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, 7);
        return cal.getTime();
    }

    public static Date getAfter1Month(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.add(2, 1);
        monday.set(5, 1);
        return monday.getTime();
    }
}
