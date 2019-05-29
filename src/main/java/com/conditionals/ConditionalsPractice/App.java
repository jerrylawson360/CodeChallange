package com.conditionals.ConditionalsPractice;

import com.conditionals.config.AppInit;
import com.conditionals.types.RegionStore;

public class App implements Runnable {
    private final RegionStore store;

    App(final String[] args) throws Exception {
        final AppInit config = new AppInit();
        store = config.createRegionStore();
    }

    protected RegionStore getStore() {
        return this.store;
    }

    public static void main(String[] args) {
        try {
            final App app = new App(args);

            app.run();

        } catch (Exception e) {
            System.exit(1);
        }
    }

    @Override
    public void run() {
        System.out.println("No run implemented for " + this.getClass());
    }
}
