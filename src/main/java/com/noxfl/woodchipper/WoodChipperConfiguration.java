/**
 * 
 */
package com.noxfl.woodchipper;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;
import com.noxfl.woodchipper.amqp.AmqpHandler;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fernando Nathanael
 *
 */
@Configuration
public class WoodChipperConfiguration {

	public static final String INPUT_QUEUE_NAME = "wood-chipper";

	public static final String OUTPUT_QUEUE_NAME = "next";

	@Bean
	public Queue hello() {
		System.out.println("Reading from queue name: " + INPUT_QUEUE_NAME);

		return new Queue(INPUT_QUEUE_NAME);
	}

	@Bean
	public AmqpHandler amqpHandler() {
		return new AmqpHandler();
	}

	@Bean
	public ParseContext parseContext() {
		com.jayway.jsonpath.Configuration configuration = com.jayway.jsonpath.Configuration.defaultConfiguration();
		return JsonPath.using(configuration);
	}
	
}