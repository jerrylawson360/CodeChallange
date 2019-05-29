package com.conditionals.types.impl;

import com.conditionals.types.DateRange;
import com.conditionals.util.DateUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;

import java.time.MonthDay;
import java.util.Date;
import java.util.List;

/**
 * Implementation of DateRange interface. This implementation allows for a date wrap to wrap at the end
 * of the year.
 */
public class DateRangeImpl implements DateRange {
    private final List<Range<MonthDay>> ranges;

    /**
     * Construct a new DateRangeImpl for a given range of month/days. The start/end values are to be
     * thought of a linear and can span from the end of the year into the beginning of the (next) year.
     * @param startDay The first month/day of this range, inclusive
     * @param endDay The last month/day of this range, inclusive.
     */
    public DateRangeImpl(final MonthDay startDay, final MonthDay endDay) {
        List<Range<MonthDay>> tmpList = Lists.newArrayList();

        if (endDay.isBefore(startDay)) {
            tmpList.add(Range.closed(startDay, MonthDay.of(12, 31)));
            tmpList.add(Range.closed(MonthDay.of(1, 1), endDay));
        } else {
            tmpList.add(Range.closed(startDay, endDay));
        }
        ranges = ImmutableList.copyOf(tmpList);
    }

    public boolean isWithinRange(final Date date) {
        // Convert date to MonthDay and compare
        final MonthDay when = DateUtils.dateToMonthDay(date);

        return ranges.stream()
            .anyMatch(range -> range.contains(when));
    }
}
