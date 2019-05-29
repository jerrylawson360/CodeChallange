package com.conditionals.ConditionalsPractice;

import com.conditionals.config.AppInit;
import com.conditionals.types.RegionStore;
import com.conditionals.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    private final RegionStore store;

    App() throws Exception {
        final AppInit config = new AppInit();
        store = config.createRegionStore();
    }

    void runDemo() {
        final Demo demo = new Demo(this.store);

        final List<Date> dates = IntStream.range(1, 13)
            .mapToObj(month -> DateUtils.createDate(2019, month, 15))
            .collect(Collectors.toList());

        demo.printExampleDatesForRegion("North America", dates);
        demo.printExampleDatesForRegion("South America", dates);
        demo.printExampleDatesForRegion("Australia", dates);
        demo.printExampleDatesForRegion("Antartica", dates);
    }

    public static void main(String[] args) {
        try {
            final App app = new App();

            app.runDemo();

        } catch (Exception e) {
            System.exit(1);
        }
    }
}
