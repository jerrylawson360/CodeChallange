package com.conditionals.config;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.MonthDay;

public class DateConfig {
    public MonthDay monthDay;

    private DateConfig(int month, int day) {
        this.monthDay = MonthDay.of(month, day);
    }

    public MonthDay getValue() {
        return MonthDay.of(monthDay.getMonth(), monthDay.getDayOfMonth());
    }

    public String toString() {
        return String.format("%d-%d", monthDay.getMonthValue(), monthDay.getDayOfMonth());
    }

    @JsonCreator
    public static DateConfig create(final String str) {
        final String[] values = str.split("-");
        if (values.length != 2) {
            throw new IllegalArgumentException("Expected mm-dd for Month/Day " + str);
        }

        try {
            final int month = Integer.parseInt(values[0]);
            final int day = Integer.parseInt(values[1]);

            return new DateConfig(month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse " + str + "; " + e.getLocalizedMessage());
        }

    }
}
