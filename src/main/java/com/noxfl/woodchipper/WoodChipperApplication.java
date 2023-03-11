package com.noxfl.woodchipper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class WoodChipperApplication {

	@Bean
	public WoodChipperTestRunner runner() {
		return new WoodChipperTestRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(WoodChipperApplication.class, args);
	}

}
