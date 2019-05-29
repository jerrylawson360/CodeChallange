package com.conditionals.types.impl;

import com.conditionals.types.Rate;
import com.conditionals.util.DateUtils;
import org.junit.Test;

import java.time.MonthDay;

import static org.junit.Assert.*;

public class RegionImplTest {
    @Test
    public void testWithinRange() {
        final RegionImpl region = new RegionImpl("abc123")
            .addRate(MonthDay.of(5, 31), MonthDay.of(9, 1), new RateImpl(11.22))
            .addRate(MonthDay.of(12, 1), MonthDay.of(2, 28), new RateImpl(33.44));

        Rate rate = region.calculateRate(DateUtils.createDate(2021, 5, 31));
        assertEquals(11.22, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2022, 9, 1));
        assertEquals(11.22, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2022, 6, 1));
        assertEquals(11.22, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2022, 8, 31));
        assertEquals(11.22, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2023, 12, 1));
        assertEquals(33.44, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2024, 2, 28));
        assertEquals(33.44, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2023, 12, 2));
        assertEquals(33.44, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2024, 2, 27));
        assertEquals(33.44, rate.getRate(), 0.0);

    }

    @Test
    public void testWithoutRangeNoDefault() {
        final RegionImpl region = new RegionImpl("abc123")
            .addRate(MonthDay.of(5, 31), MonthDay.of(9, 1), new RateImpl(11.22))
            .addRate(MonthDay.of(12, 1), MonthDay.of(2, 28), new RateImpl(33.44));

        try {
            region.calculateRate(DateUtils.createDate(2021, 5, 30));
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {

        } catch (Throwable t) {
            fail("Expected IllegalStateException, but caught " + t.getMessage());
        }

        try {
            region.calculateRate(DateUtils.createDate(2021, 9, 2));
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {

        } catch (Throwable t) {
            fail("Expected IllegalStateException, but caught " + t.getMessage());
        }
    }

    @Test
    public void testWithoutRangeWithDefault() {
        final RegionImpl region = new RegionImpl("abc123")
            .setDefaultRate(() -> 999.888)
            .addRate(MonthDay.of(5, 31), MonthDay.of(9, 1), new RateImpl(11.22))
            .addRate(MonthDay.of(12, 1), MonthDay.of(2, 28), new RateImpl(33.44));

        Rate rate = region.calculateRate(DateUtils.createDate(2021, 5, 30));
        assertEquals(999.888, rate.getRate(), 0.0);

        rate = region.calculateRate(DateUtils.createDate(2021, 9, 2));
        assertEquals(999.888, rate.getRate(), 0.0);
    }
}
