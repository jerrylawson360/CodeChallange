package com.conditionals.config;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RegionConfig {
    public String name;
    public List<RateConfig> rates;
    public Double defaultRate;

    public String toString() {
        return "\n  " + name +
            "\n     defaultRate: " +
            Optional.ofNullable(defaultRate)
                .map(d -> d.toString())
                .orElse("null") +
            "\n     rates: " +
            rates.stream()
                .map(r -> "\n        " + r.toString())
                .collect(Collectors.joining());
    }
}
