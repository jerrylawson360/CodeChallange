package com.conditionals.types.impl;

import com.conditionals.types.Rate;

/**
 * Simple implementation of Rate interface, containing the minimal data
 */
public class RateImpl implements Rate {
    private final String name;
    private final double rate;

    public RateImpl(final String name, final double rate) {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getRate() {
        return this.rate;
    }
}
