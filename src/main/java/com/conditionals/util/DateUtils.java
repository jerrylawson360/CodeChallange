package com.conditionals.util;

import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * Create Date object, from year/month/dayOfMonth
     * @param year Year, eg., 2019
     * @param month Month, 1-12
     * @param day Day of Month, 1-31
     * @return Newly created Date object
     */
    public static Date createDate(int year, int month, int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * Convert java.util.Date into a java.time.MonthDay
     * @param date The Date to convert
     * @return The corresponding MonthDay object
     */
    public static MonthDay dateToMonthDay(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Calendar month is zero-based; MonthDay month is 1-based.
        return MonthDay.of(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }
}
