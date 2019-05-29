package com.conditionals.types;

import java.util.Date;

/**
 * DateRange interface. This interface is used to determine whether a given Date is within
 * a specific range of month/days, inclusive.
 */
public interface DateRange {
    /**
     * Returns boolean indicating whether the specified date is within the range of this DateRange
     * @param date The date to compare with this range
     * @return boolean, indicating whether the specified date is between start and end of this range, inclusive
     */
    boolean isWithinRange(final Date date);
}
