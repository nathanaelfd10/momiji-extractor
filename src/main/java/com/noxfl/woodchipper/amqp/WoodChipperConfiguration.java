/**
 * 
 */
package com.noxfl.woodchipper.amqp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP.Queue;

/**
 * @author Fernando Nathanael
 *
 */
@Configuration
public class WoodChipperConfiguration {

	@Bean
	public Queue hello() {
		return new Queue();
	}
	
}
