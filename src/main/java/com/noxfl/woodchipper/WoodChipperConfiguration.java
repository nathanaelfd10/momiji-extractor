/**
 * 
 */
package com.noxfl.woodchipper;

import com.noxfl.woodchipper.messaging.amqp.impl.AmqpHandler;
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
	public static final boolean IS_RUN_DISCONNECTED = System.getProperty("run-disconnected") != null
			&& Boolean.parseBoolean(System.getProperty("run-disconnected"));

	public static final String INPUT_QUEUE_NAME = "wood-chipper";

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