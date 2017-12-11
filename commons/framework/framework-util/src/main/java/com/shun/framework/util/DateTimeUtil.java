package com.shun.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: mew <p />
 * Time: 17/11/6 16:33  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DateTimeUtil {

    public static final SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat datetimeFormart = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat datetimeFormart_min = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public DateTimeUtil() {
    }

    public static String getWeek(String strDate) {
        Date date = null;
        try {
            date = shortDateFormat.parse(strDate);
        } catch (ParseException var3) {
            var3.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (new SimpleDateFormat("EEEE")).format(calendar.getTime());
    }

    public static Date shotParseDate(String datetimeStr) {
        try {
            return datetimeStr != null && datetimeStr.length() != 0 ? shortDateFormat.parse(datetimeStr) : null;
        } catch (ParseException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date parseDate(String datetimeStr) {
        try {
            return datetimeFormart.parse(datetimeStr);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date parseLongDate(String datetimeStr) {
        try {
            return longDateFormat.parse(datetimeStr);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String getDate(Date date) {
        return shortDateFormat.format(date);
    }

    public static String getTime(Date date) {
        return longDateFormat.format(date);
    }

    public static String getTime4Min(Date date) {
        return datetimeFormart_min.format(date);
    }

    public static String getDateTime(Date date) {
        return datetimeFormart.format(date);
    }

    public static String getLongDateTime(Date date) {
        return longDateFormat.format(date);
    }

    public static String getDateTime() {
        return datetimeFormart.format(new Date());
    }

    public static Date getShortDate(String dateStr) {
        try {
            return shortDateFormat.parse(dateStr);
        } catch (ParseException var2) {
            return null;
        }
    }

    public static Date getDate(String dateStr) {
        try {
            return datetimeFormart.parse(dateStr);
        } catch (ParseException var2) {
            return null;
        }
    }

    public static String getShortToday() {
        Date today = new Date();
        return shortDateFormat.format(today);
    }

    public static String getLongToday() {
        Date today = new Date();
        return longDateFormat.format(today);
    }

    public static String getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Date date = new Date(calendar.getTimeInMillis());
        return longDateFormat.format(date);
    }

    public static String getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        Date date = new Date(calendar.getTimeInMillis());
        return longDateFormat.format(date);
    }

    public static String getDateStart(String strDate) {
        if (strDate != null && !"".equals(strDate.trim())) {
            Date date = null;
            try {
                date = shortDateFormat.parse(strDate);
            } catch (ParseException var3) {
                var3.printStackTrace();
            }
            return longDateFormat.format(date);
        } else {
            return "";
        }
    }

    public static String getDateEnd(String strDate) {
        if (strDate != null && !"".equals(strDate.trim())) {
            Date date = null;
            try {
                date = shortDateFormat.parse(strDate);
            } catch (ParseException var3) {
                var3.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 0);
            return longDateFormat.format(calendar.getTime());
        } else {
            return "";
        }
    }

    public static String getMondayOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(7) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(5, -day_of_week + 1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getSundayOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(7) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(5, -day_of_week + 1);
        calendar.add(5, 6);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getMondayOfPreviousWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(7) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(5, -day_of_week + 1);
        calendar.add(5, -7);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getSundayOfPreviousWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(7) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(5, -day_of_week + 1);
        calendar.add(5, -1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getMondayOfNextWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(7) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(5, -day_of_week + 1);
        calendar.add(5, 7);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getSundayOfNextWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(7) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(5, -day_of_week + 1);
        calendar.add(5, 13);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getFirstDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getLastDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.add(2, 1);
        calendar.add(5, -1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getFirstDayOfPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.add(2, -1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getLastDayOfPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.add(5, -1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getFirstDayOfNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, 1);
        calendar.set(5, 1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static String getLastDayOfNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return shortDateFormat.format(calendar.getTime());
    }

    public static int getTotalDaysOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(6, 1);
        calendar.roll(6, -1);
        int totalDays = calendar.get(6);
        return totalDays;
    }

    public static int getLastDayOfMonth(int year, int month) {
        if (month != 1 && month != 3 && month != 5 && month != 7 && month != 8 && month != 10 && month != 12) {
            if (month != 4 && month != 6 && month != 9 && month != 11) {
                if (month == 2) {
                    return isLeapYear(year) ? 29 : 28;
                } else {
                    return 0;
                }
            } else {
                return 30;
            }
        } else {
            return 31;
        }
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static String getFirstDayOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, 1);
        calendar.set(6, 1);
        Date date = new Date(calendar.getTimeInMillis());
        return shortDateFormat.format(date);
    }

    public static String getLastDayOfThisYear() {
        Date date = new Date();
        String years = (new SimpleDateFormat("yyyy")).format(date);
        return years + "-12-31";
    }

    public static String getFirstDayOfPreviousYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, -1);
        calendar.set(2, 1);
        calendar.set(6, 1);
        Date date = new Date(calendar.getTimeInMillis());
        return shortDateFormat.format(date);
    }

    public static long getDays(String date1, String date2) {
        if (date1 != null && !"".equals(date1.trim())) {
            if (date2 != null && !"".equals(date2.trim())) {
                Date date = null;
                Date mydate = null;
                try {
                    date = shortDateFormat.parse(date1);
                    mydate = shortDateFormat.parse(date2);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
                long day = 0L;
                if (date.before(mydate)) {
                    day = (mydate.getTime() - date.getTime()) / 86400000L;
                } else {
                    day = (date.getTime() - mydate.getTime()) / 86400000L;
                }
                return day;
            } else {
                return 0L;
            }
        } else {
            return 0L;
        }
    }

    public static Date getToday4zero() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    public static void main(String[] args) {
        System.out.println(getToday4zero());
    }

}
