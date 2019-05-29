package com.conditionals.config;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Configuration class for application, meant to be used when reading configuration via yaml parser
 */
public class AppConfig {
    public double defaultRate;
    public List<RegionConfig> regions;

    public String toString() {
        return "AppConfig:" +
            "\n  defaultRate: " + defaultRate +
            "\n  regions: " +
            regions.stream()
                .map(r -> "\n    " + r.toString())
                .collect(Collectors.joining());
    }
}
