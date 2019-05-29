package com.conditionals.config;

import com.conditionals.types.Region;
import com.conditionals.types.RegionStore;
import com.conditionals.types.impl.RegionImpl;
import com.conditionals.types.impl.RegionStoreImpl;
import com.conditionals.types.impl.RateImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.Optional;

public class AppInit {
    final AppConfig config;

    public AppInit() throws Exception {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        try {
            // Read configuration from resource in jar
            // TODO: allow for reading from external (outside of jar) file.
            final File file = new File(getClass().getClassLoader().getResource("application.yml").getFile());
            config = mapper.readValue(file, AppConfig.class);
        } catch (Exception e) {
            System.out.println("Caught exception reading file:" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Create the region store object
     * @return Newly initialized RegionStore
     */
    public RegionStore createRegionStore() {
        final RegionStore store = new RegionStoreImpl();

        config.regions.stream()
            .forEach(regionConfig -> store.addRegion(createRegion(regionConfig)));

        return store;
    }

    // Create a Region object, based on a RegionConfig
    private Region createRegion(final RegionConfig regionConfig) {
        final RegionImpl region = new RegionImpl(regionConfig.name)
                .setDefaultRate(new RateImpl("Default", Optional.ofNullable(regionConfig.defaultRate).orElse(config.defaultRate)));

        regionConfig.rates.stream()
            .forEach(rate -> region.addRate(rate.from.getValue(), rate.to.getValue(), new RateImpl(rate.getName(), rate.getRate())));

        return region;
    }
}
