package com.conditionals.types.impl;

import com.conditionals.types.DateRange;
import com.conditionals.util.DateUtils;

import java.time.MonthDay;
import java.util.Date;

public class DateRangeImpl implements DateRange {
    private final MonthDay startDay;
    private final MonthDay endDay;
    private final boolean wrapsAtYearEnd;

    public DateRangeImpl(final MonthDay startDay, final MonthDay endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.wrapsAtYearEnd = endDay.isBefore(startDay);
    }

    public boolean isWithinRange(final Date date) {
        // Convert date to MonthDay and compare
        final MonthDay when = DateUtils.dateToMonthDay(date);

        if (wrapsAtYearEnd) {
            return !(when.compareTo(endDay) > 0 && when.compareTo(startDay) < 0);
        } else {
            return when.compareTo(endDay) <= 0 && when.compareTo(startDay) >= 0;
        }
    }
}
