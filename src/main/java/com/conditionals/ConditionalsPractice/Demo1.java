package com.conditionals.ConditionalsPractice;

import com.conditionals.types.Rate;
import com.conditionals.types.Region;
import com.conditionals.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Demo app that prints all the rates, month-by-month, for each known region.
 */
public class Demo1 extends App {
    public Demo1(final String[] args) throws Exception {
        super(args);
    }

    @Override
    public void run() {
        final List<Date> dates = IntStream.range(1, 13)
            .mapToObj(month -> DateUtils.createDate(2019, month, 15))
            .collect(Collectors.toList());

        printExampleDatesForRegion("North America", dates);
        printExampleDatesForRegion("South America", dates);
        printExampleDatesForRegion("Australia", dates);
        printExampleDatesForRegion("Antartica", dates);
    }


    public void printExampleDatesForRegion(final String regionName, final List<Date> dates) {
        final Region region = getStore().getRegion(regionName);
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

    public static void main(String[] args) {
        try {
            final App app = new Demo1(args);

            app.run();

        } catch (Exception e) {
            System.exit(1);
        }
    }
}
