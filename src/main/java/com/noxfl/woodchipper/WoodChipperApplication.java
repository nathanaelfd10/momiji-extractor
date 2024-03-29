package com.noxfl.woodchipper;

import com.noxfl.woodchipper.messaging.cloudpubsub.MessagePublisher;
import com.noxfl.woodchipper.messaging.cloudpubsub.impl.MessagePublisherImpl;
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
	public WoodChipperRunner runner() {
		String messageStatus = WoodChipperConfiguration.IS_RUN_DISCONNECTED ?
				"Not publishing to Cloud Pub/Sub because run-disconnected is set to true." :
				"Publishing to Cloud Pub/Sub";
		System.out.println("Status: " + messageStatus);

		return new WoodChipperRunner();
	}

	@Bean
	public MessagePublisher messagePublisher() {
		final String projectId = System.getProperty("projectId");
		final String topicId = System.getProperty("topicId");
		return new MessagePublisherImpl(projectId, topicId);
	}

	public static void main(String[] args) {
		SpringApplication.run(WoodChipperApplication.class, args);
	}

}
