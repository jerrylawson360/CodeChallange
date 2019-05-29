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
 * Demo app that prints information just like original app implementation
 */
public class Demo2 extends App {
    public Demo2(final String[] args) throws Exception {
        super(args);
    }

    private double calculateRate(final String regionName, final Date date) {
        return Optional.ofNullable(this.getStore().getRegion(regionName))
            .map(region -> region.calculateRate(date))
            .map(rate -> rate.getRate())
            .orElse(null);
    }

    @Override
    public void run() {
        //Run the scenarios
        double naSummerScenario = calculateRate("North America", DateUtils.createDate(2019, 7, 04));
        double naWinterScenario = calculateRate("North America", DateUtils.createDate(2019, 12, 04));
        double regularScenario = calculateRate("North America", DateUtils.createDate(2019, 5, 04));
        double saSummerScenario = calculateRate("South America", DateUtils.createDate(2020, 1, 07));
        double saWinterScenario = calculateRate("South America", DateUtils.createDate(2019, 8, 17));
        double auSummerScenario = calculateRate("Australia", DateUtils.createDate(2020, 1, 07));
        double auWinterScenario = calculateRate("Australia", DateUtils.createDate(2019, 8, 17));
        double auFallScenario = calculateRate("Australia", DateUtils.createDate(2019, 3, 17));


        System.out.println("--North America--");
        System.out.println("Summer scenario rate is: " + naSummerScenario);
        System.out.println("Winter scenario rate is: " + naWinterScenario);
        System.out.println("Regular scenario rate is:" + regularScenario);
        System.out.println("--South America--");
        System.out.println("Summer scenario rate is: " + saSummerScenario);
        System.out.println("Winter scenario rate is: " + saWinterScenario);
        System.out.println("--Australia--");
        System.out.println("Summer scenario rate is: " + auSummerScenario);
        System.out.println("Winter scenario rate is: " + auWinterScenario);
        System.out.println("Fall scenario rate is:   " + auFallScenario);
   }

    public static void main(String[] args) {
        try {
            final App app = new Demo2(args);

            app.run();

        } catch (Exception e) {
            System.exit(1);
        }
    }
}
