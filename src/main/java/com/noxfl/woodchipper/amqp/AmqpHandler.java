/**
 * 
 */
package com.noxfl.woodchipper.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.noxfl.woodchipper.extractor.*;
import net.minidev.json.JSONObject;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Fernando Nathanael
 *
 */
@RabbitListener(queues = "hello")
public class AmqpHandler {

	private ContentExtractorFactory contentExtractorFactory;

	@Autowired
	public void setContentExtractorFactory(ContentExtractorFactory contentExtractorFactory) {
		this.contentExtractorFactory = contentExtractorFactory;
	}

	private RabbitTemplate template;

	@Autowired
	public void setTemplate(RabbitTemplate template) {
		this.template = template;
	}

	private Queue queue;

	@Autowired
	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	private final Configuration config = Configuration.defaultConfiguration();

	public MomijiMessage parseMessage(String message) throws JsonProcessingException {

		MomijiMessage momijiMessage = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(message, MomijiMessage.class);

		return momijiMessage;
	}

	@RabbitHandler
	public void receive(String message) throws JsonProcessingException {

		List<Field> guides = new ArrayList<>();

		MomijiMessage momijiMessage = parseMessage(message);

		HashMap<String, Object> extractedFields = new HashMap<>();

		for(var guide : momijiMessage.getJob().getContentParsingGuides()) {
			String sourceName = guide.getSource();
			Content content = momijiMessage.getJob().getContents().stream().filter(content -> content.getName().equals(sourceName));
			HashMap<String, Object> fields = contentExtractorFactory.extract(content.getContentType(), content.getContent(), guides);

			extractedFields.putAll(fields);
		}

		JSONObject extractedFieldsAsJson = new JSONObject(extractedFields);

		send(extractedFieldsAsJson.toString());

	}

	public void send(String message) {
		template.convertAndSend(queue.getName(), message);
	}

}