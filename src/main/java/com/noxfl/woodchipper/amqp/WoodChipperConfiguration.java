/**
 * 
 */
package com.noxfl.woodchipper.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fernando Nathanael
 *
 */
@Configuration
public class WoodChipperConfiguration {

	public static final String WOOD_CHIPPER_QUEUE_NAME = "wood-chipper";

	@Bean
	public Queue hello() {
		System.out.println("Reading from queue name: " + WOOD_CHIPPER_QUEUE_NAME);

		return new Queue(WOOD_CHIPPER_QUEUE_NAME);
	}

	@Bean
	public RmqReceiver amqpHandler() {
		return new RmqReceiver();
	}
	
}