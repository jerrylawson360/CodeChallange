package com.conditionals.types.impl;

import com.conditionals.types.Rate;

public class RateImpl implements Rate {
    private final double rate;

    public RateImpl(final double rate) {
        this.rate = rate;
    }

    @Override
    public double getRate() {
        return this.rate;
    }
}
