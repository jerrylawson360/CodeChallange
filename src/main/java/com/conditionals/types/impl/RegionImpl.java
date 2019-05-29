package com.conditionals.types.impl;

import com.conditionals.types.Region;
import com.conditionals.types.Rate;
import com.google.common.collect.Lists;

import java.time.MonthDay;
import java.util.Date;
import java.util.List;

/**
 * Region implementation. This implementation contains one or more DateRanges, each of which is assigned
 * a specific Rate to use in that DateRange.
 */
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

    /**
     * Set "default" Rate. To be used by calculateRate() when there is no corresponding DateRange found
     * @param rate The Rate object to use in default scenarios
     * @return this RegionImpl instance
     */
    public RegionImpl setDefaultRate(final Rate rate) {
        this.defaultRate = rate;
        return this;
    }

    /**
     * Add a new Rate, corresponding to a start/end month day range. This implementation allows for a date wrap to 
     * wrap at the end of the year.
     * @param periodStart The first month/day of this range, inclusive
     * @param periodEnd The last month/day of this range, inclusive
     * @param rate The Rate associated with this date range
     * @return this RegionImpl instance
     * @// TODO: 2019-05-29 Check for overlapping ranges here, or create a validation() method to check for overlaps 
     */
    public RegionImpl addRate(final MonthDay periodStart, final MonthDay periodEnd, final Rate rate) {
        rates.add(new DateRangeRate(periodStart, periodEnd, rate));
        return this;
    }

    /**
     * Private class for managing the DateRange + Rate.
     */
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
