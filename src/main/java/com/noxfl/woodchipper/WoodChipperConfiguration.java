/**
 * 
 */
package com.noxfl.woodchipper;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;
import com.noxfl.woodchipper.messaging.amqp.AmqpHandler;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fernando Nathanael
 *
 */
@Configuration
public class WoodChipperConfiguration {

	// Disconnected from Cloud: Not publishing to Cloud Pub/Sub
	public static final boolean IS_RUN_DISCONNECTED = System.getenv("run-disconnected") != null;

	public static final String INPUT_QUEUE_NAME = System.getProperty("input-queue-name") != null ?
			System.getProperty("input-queue-name") : "wood-chipper";

	@Bean
	public Queue queue() {
		System.out.println("Reading from queue name: " + INPUT_QUEUE_NAME);

		return new Queue(INPUT_QUEUE_NAME);
	}

	@Bean
	public AmqpHandler amqpHandler() {
		return new AmqpHandler();
	}
	
}