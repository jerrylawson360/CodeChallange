package com.conditionals.types;

import java.util.Date;

public interface Region {
    /**
     * Get the name of the Region
     * @return Unique region name
     */
    String getName();

    /**
     * Get the rate for this region, for a given Date
     * @param date The Date for which the caller is requesting the rate
     * @return double The rate for the given Date
     */
    Rate calculateRate(final Date date);
}
