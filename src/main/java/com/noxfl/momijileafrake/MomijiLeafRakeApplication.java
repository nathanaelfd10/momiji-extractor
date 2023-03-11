package com.noxfl.momijileafrake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class MomijiLeafRakeApplication {

	@Bean
	public Runner runner() {
		return new Runner();
	}

	public static void main(String[] args) {
		SpringApplication.run(MomijiLeafRakeApplication.class, args);
	}

}
