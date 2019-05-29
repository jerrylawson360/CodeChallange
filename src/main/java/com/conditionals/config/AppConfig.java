package com.conditionals.config;

import com.conditionals.types.Region;
import com.conditionals.types.RegionStore;
import com.conditionals.types.impl.RegionImpl;
import com.conditionals.types.impl.RegionStoreImpl;
import com.conditionals.types.impl.RateImpl;
import com.google.common.collect.ImmutableList;

import java.time.MonthDay;
import java.util.List;

public class AppConfig {
    // TODO get these rates from exernal config file
    private final double regularRate = 11.11;
    private final double summerRate = 22.22;
    private final double winterRate = 7.77;

    public RegionStore createRegionStore() {
        final RegionStore store = new RegionStoreImpl();

        createRegions().stream()
            .forEach(regionConfig -> store.addRegion(regionConfig));

        return store;
    }

    private List<Region> createRegions() {
        // TODO read external config file
        final List<Region> regions = ImmutableList.<Region>builder()

            // North America
            .add(new RegionImpl("North America")
                .setDefaultRate(new RateImpl(regularRate))
                .addRate(MonthDay.of(5, 31), MonthDay.of(9, 1), new RateImpl(summerRate))
                .addRate(MonthDay.of(12, 1), MonthDay.of(2, 28), new RateImpl(winterRate)))

            // South America
            .add(new RegionImpl("South America")
                .setDefaultRate(new RateImpl(regularRate))
                .addRate(MonthDay.of(12, 1), MonthDay.of(2, 28), new RateImpl(summerRate))
                .addRate(MonthDay.of(5, 31), MonthDay.of(9, 1), new RateImpl(winterRate)))

            .build();

        return regions;
    }
}
