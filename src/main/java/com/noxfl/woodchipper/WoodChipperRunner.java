package com.noxfl.woodchipper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

public class WoodChipperRunner implements CommandLineRunner {

    private ConfigurableApplicationContext context;

    @Autowired
    private void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        int durationMs = 1200000;
        String message = String.format("Running for %sms (%s seconds/%s minutes)", durationMs, durationMs / 1000, durationMs / 6000);
        System.out.println(message);
        Thread.sleep(durationMs);

        context.close();
    }
}
