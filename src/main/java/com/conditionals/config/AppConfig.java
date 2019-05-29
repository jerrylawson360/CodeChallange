package com.conditionals.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
