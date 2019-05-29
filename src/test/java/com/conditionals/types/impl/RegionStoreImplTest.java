package com.conditionals.types.impl;

import com.conditionals.types.Rate;
import com.conditionals.types.Region;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RegionStoreImplTest {
    @Test
    public void testAddRegion() {
        final RegionStoreImpl store = new RegionStoreImpl();

        store.addRegion(new Region() {

            @Override
            public String getName() {
                return "name1";
            }

            @Override
            public Rate calculateRate(final Date date) {
                return () -> 123.456;
            }
        });

        final Region result = store.getRegion("name1");
        assertEquals("name1", result.getName());
        assertEquals(123.456, result.calculateRate(new Date()).getRate(), 0.0);
    }

    @Test
    public void testAddRegionDuplicate() {
        final RegionStoreImpl store = new RegionStoreImpl();

        store.addRegion(new Region() {

            @Override
            public String getName() {
                return "name1";
            }

            @Override
            public Rate calculateRate(final Date date) {
                return () -> 123.456;
            }
        });

        store.addRegion(new Region() {

            @Override
            public String getName() {
                return "name1";
            }

            @Override
            public Rate calculateRate(final Date date) {
                return () -> 456.123;
            }
        });

        final Region result = store.getRegion("name1");
        assertEquals("name1", result.getName());
        assertEquals(456.123, result.calculateRate(new Date()).getRate(), 0.0);
    }

    @Test
    public void testGetRegionNames() {
        final RegionStoreImpl store = new RegionStoreImpl();

        store.addRegion(new Region() {

            @Override
            public String getName() {
                return "name1";
            }

            @Override
            public Rate calculateRate(final Date date) {
                return () -> 123.456;
            }
        });

        store.addRegion(new Region() {

            @Override
            public String getName() {
                return "name2";
            }

            @Override
            public Rate calculateRate(final Date date) {
                return () -> 456.123;
            }
        });

        final Set<String> expected = new HashSet<>();
        expected.add("name1");
        expected.add("name2");
        assertEquals(expected, store.getRegionNames());
    }

}
