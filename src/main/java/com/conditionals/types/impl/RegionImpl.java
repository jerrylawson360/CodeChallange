package com.conditionals.types.impl;

import com.conditionals.types.Region;
import com.conditionals.types.Rate;
import com.google.common.collect.Lists;

import java.time.MonthDay;
import java.util.Date;
import java.util.List;

public class RegionImpl implements Region {
    private final String name;
    private final List<DateRangeRate> rates = Lists.newArrayList();
    private Rate defaultRate = null;

    public RegionImpl(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Rate calculateRate(final Date date)
        throws IllegalStateException {

        // Iterate through the date ranges to see if one matches. If not, use the default rate.
        final Rate value = rates.stream()
            .filter(range -> range.isWithinRange(date))
            .findFirst()
            .map(range -> range.getRate())
            .orElse(defaultRate);

        if (value == null) {
            throw new IllegalStateException("No rate found for " + date.toString());
        }
        return value;
    }

    public RegionImpl setDefaultRate(final Rate rate) {
        this.defaultRate = rate;
        return this;
    }

    public RegionImpl addRate(final MonthDay periodStart, final MonthDay periodEnd, final Rate rate) {
        rates.add(new DateRangeRate(periodStart, periodEnd, rate));
        return this;
    }

    private class DateRangeRate extends DateRangeImpl {
        private Rate rate;

        DateRangeRate(final MonthDay periodStart, final MonthDay periodEnd, final Rate rate) {
            super(periodStart, periodEnd);
            this.rate = rate;
        }

        Rate getRate() {
            return this.rate;
        }
    }
}
