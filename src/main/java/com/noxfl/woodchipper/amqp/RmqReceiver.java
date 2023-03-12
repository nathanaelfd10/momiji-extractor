/**
 * 
 */
package com.noxfl.woodchipper.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
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
import java.util.Optional;

/**
 * @author Fernando Nathanael
 *
 */
public class RmqReceiver {

	public static final boolean IS_SOURCE_NAME_CASE_SENSITIVE = true;

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

		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MomijiMessage momijiMessage = objectMapper.readValue(message, MomijiMessage.class);

		return momijiMessage;
	}

//	@RabbitHandler
//	@RabbitListener(queues = WoodChipperConfiguration.WOOD_CHIPPER_QUEUE_NAME)
//	public void receive(String message) {
//		System.out.println(message);
//	}

	@RabbitHandler
	@RabbitListener(queues = WoodChipperConfiguration.WOOD_CHIPPER_QUEUE_NAME)
	public void receive(String message) throws JsonProcessingException, NoSuchFieldException {

		MomijiMessage momijiMessage = parseMessage(message);

		HashMap<String, Object> extractedFields = new HashMap<>();

		for(var guide : momijiMessage.getJob().getContentParsingGuides()) {
			String sourceName = guide.getSource();

			List<Field> fields = guide.getFields();

			List<Content> availableContents = momijiMessage.getJob().getContents();

			Optional<Content> foundContent = availableContents.stream()
					.filter(ctn ->
							IS_SOURCE_NAME_CASE_SENSITIVE
									? ctn.getName().equals(sourceName) // Case sensitive if true
									: ctn.getName().equalsIgnoreCase(sourceName) // Ignores case if false
					)
					.findFirst();

			if(foundContent.isEmpty()) throw new NoSuchFieldException("No content with such name: " + sourceName);

			Content content = foundContent.get();

			HashMap<String, Object> outputFields = contentExtractorFactory.getContentExtractor(content.getContentType()).extract(content.getContent(), fields);

			extractedFields.putAll(outputFields);

		}

		JSONObject extractedFieldsAsJson = new JSONObject(extractedFields);

		System.out.println(extractedFieldsAsJson.toString());

		send(extractedFieldsAsJson.toString());

	}

	public void send(String message) {
		template.convertAndSend("next", message);
	}

}