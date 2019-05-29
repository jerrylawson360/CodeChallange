package com.conditionals.types;

public interface Rate {
    /**
     * Get user-friendly name of rate
     * @return Name of this Rate
     */
    String getName();

    /**
     * Get rate value
     * @return double rate value to use
     */
    double getRate();
}
