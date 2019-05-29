package com.conditionals.types.impl;

import java.time.MonthDay;

import com.conditionals.util.DateUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateRangeImplTest {
    @Test
    public void testOutsideOfRange() {
        final DateRangeImpl dateRange = new DateRangeImpl(MonthDay.of(2, 1), MonthDay.of(6, 30));

        assertFalse(dateRange.isWithinRange(DateUtils.createDate(2019, 1, 31)));
        assertFalse(dateRange.isWithinRange(DateUtils.createDate(2019, 7, 1)));
    }

    @Test
    public void testInsideOfRange() {
        final DateRangeImpl dateRange = new DateRangeImpl(MonthDay.of(2, 1), MonthDay.of(6, 30));

        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 3, 15)));
        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 5, 27)));
    }

    @Test
    public void testInsideOfRangeInclusive() {
        final DateRangeImpl dateRange = new DateRangeImpl(MonthDay.of(2, 1), MonthDay.of(6, 30));

        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 2, 1)));
        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 6, 30)));
    }

    @Test
    public void testOutsideOfRangeEndOfYear() {
        final DateRangeImpl dateRange = new DateRangeImpl(MonthDay.of(12, 1), MonthDay.of(6, 30));

        assertFalse(dateRange.isWithinRange(DateUtils.createDate(2019, 11, 30)));
        assertFalse(dateRange.isWithinRange(DateUtils.createDate(2019, 7, 1)));
    }

    @Test
    public void testInsideOfRangeEndOfYear() {
        final DateRangeImpl dateRange = new DateRangeImpl(MonthDay.of(12, 1), MonthDay.of(6, 30));

        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 12, 30)));
        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 6, 15)));
    }


    @Test
    public void testInsideOfRangeInclusiveEndOfYear() {
        final DateRangeImpl dateRange = new DateRangeImpl(MonthDay.of(12, 1), MonthDay.of(6, 30));

        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 12, 1)));
        assertTrue(dateRange.isWithinRange(DateUtils.createDate(2019, 6, 30)));
    }

}
