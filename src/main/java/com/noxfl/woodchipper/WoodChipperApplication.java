package com.noxfl.woodchipper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
public class WoodChipperApplication {

	@Profile("test-runner")
	@Bean
	public WoodChipperTestRunner testRunner() {
		return new WoodChipperTestRunner();
	}

	@Profile("!test-runner")
	@Bean
	public WoodChipperRunner runner() { return new WoodChipperRunner(); }

	public static void main(String[] args) {
		SpringApplication.run(WoodChipperApplication.class, args);
	}

}
