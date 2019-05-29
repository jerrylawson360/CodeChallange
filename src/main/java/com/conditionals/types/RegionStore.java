package com.conditionals.types;

import java.util.Collection;

public interface RegionStore {
    /**
     * Get a Region by name
     * @param name The unique region name
     * @return Region associated with name, or null
     */
    Region getRegion(final String name);

    /**
     * Get collection of region names
     * @return Collection of region names
     */
    Collection<String> getRegionNames();

    /**
     * Add a Region. The Region will replace any existing Region of the same name
     * @param region The Region to add
     */
    void addRegion(final Region region);
}
