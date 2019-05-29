package com.conditionals.ConditionalsPractice;

import com.conditionals.types.Rate;
import com.conditionals.types.Region;
import com.conditionals.types.RegionStore;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Demo {
    private final RegionStore store;

    public Demo(final RegionStore store) {
        this.store = store;
    }

    public void printExampleDatesForRegion(final String regionName, final List<Date> dates) {
        final Region region = store.getRegion(regionName);
        if (region == null) {
            System.out.println("\nERROR: unknown region: " + regionName);
        } else {
            System.out.println(String.format("\n-- %s --", region.getName()));
            dates.stream()
                .forEach(date -> {
                    final Rate rate = Optional.ofNullable(region.calculateRate(date))
                        .orElseGet(() -> new Rate() {
                            @Override
                            public String getName() { return "unknown"; }

                            @Override
                            public double getRate() { return 0.0; }
                        });

                    System.out.println(String.format("  Date: %s rate: %.2f (%s rate)",
                        date, rate.getRate(), rate.getName()));
                });
        }
    }
}
