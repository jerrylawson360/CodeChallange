package com.conditionals.config;

import com.conditionals.types.Rate;

/**
 * Configuration class for Rate, meant to be used when reading configuration via yaml parser
 */
public class RateConfig implements Rate {
    public String name;
    public DateConfig from;
    public DateConfig to;
    public double rate;

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String toString() {
        return "  " + name + "  dateRange: " + from.toString() + " -> " + to.toString() + " : " + rate;
    }
}
