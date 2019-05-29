package com.conditionals.ConditionalsPractice;

import com.conditionals.config.AppConfig;
import com.conditionals.types.Rate;
import com.conditionals.types.Region;
import com.conditionals.types.RegionStore;
import com.conditionals.util.DateUtils;

public class App {
    private final RegionStore store;

    App() {
        final AppConfig config = new AppConfig();
        store = config.createRegionStore();
    }

    void demo() {
        final Region northAmerica = store.getRegion("North America");
        final Region southAmerica = store.getRegion("South America");

        final Rate naSummerScenario = northAmerica.calculateRate(DateUtils.createDate(2019, 7, 04));
        final Rate naWinterScenario = northAmerica.calculateRate(DateUtils.createDate(2019, 12, 04));
        final Rate naRegularScenario = northAmerica.calculateRate(DateUtils.createDate(2019, 5, 04));
        final Rate saSummerScenario = southAmerica.calculateRate(DateUtils.createDate(2020, 1, 07));
        final Rate saWinterScenario = southAmerica.calculateRate(DateUtils.createDate(2019, 8, 17));
        final Rate saRegularScenario = southAmerica.calculateRate(DateUtils.createDate(2019, 5, 04));

        System.out.println("\n\n");
        System.out.println("--North America--");
        System.out.println("Summer scenario rate is: " + naSummerScenario.getRate());
        System.out.println("Winter scenario rate is: " + naWinterScenario.getRate());
        System.out.println("Regular scenario rate is: " + naRegularScenario.getRate());
        System.out.println("--South America--");
        System.out.println("Summer scenario rate is: " + saSummerScenario.getRate());
        System.out.println("Winter scenario rate is: " + saWinterScenario.getRate());
        System.out.println("Regular scenario rate is: " + saRegularScenario.getRate());
    }

    public static void main(String[] args) {
        final App app = new App();

        app.demo();
    }
}
