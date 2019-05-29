package com.conditionals.types.impl;

import com.conditionals.types.Region;
import com.conditionals.types.RegionStore;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

/**
 * Storage of Regions by name
 */
public class RegionStoreImpl implements RegionStore {
    private Map<String, Region> regions = Maps.newHashMap();

    @Override
    public Region getRegion(final String name) {
        return regions.get(name);
    }

    @Override
    public void addRegion(final Region region) {
        regions.put(region.getName(), region);
    }

    @Override
    public Collection<String> getRegionNames() {
        return regions.keySet();
    }
}
