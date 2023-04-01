package com.noxfl.woodchipper.extractor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.noxfl.woodchipper.WoodChipperConfiguration;
import com.noxfl.woodchipper.extractor.ContentExtractorFactory;
import com.noxfl.woodchipper.extractor.MessageHandler;
import com.noxfl.woodchipper.messaging.cloudpubsub.MessagePublisher;
import com.noxfl.woodchipper.schema.Content;
import com.noxfl.woodchipper.schema.Field;
import com.noxfl.woodchipper.schema.MomijiMessage;
import net.minidev.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class MessageHandlerImpl implements MessageHandler {

    public static final boolean IS_SOURCE_NAME_CASE_SENSITIVE = true;

    private ContentExtractorFactory contentExtractorFactory;

    @Autowired
    public void setContentExtractorFactory(ContentExtractorFactory contentExtractorFactory) {
        this.contentExtractorFactory = contentExtractorFactory;
    }

    private final Configuration config = Configuration.defaultConfiguration();

    private MessagePublisher messagePublisher;

    @Autowired
    public void setMessagePublisher(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @Override
    public void handle(String message) throws NoSuchFieldException, IOException, ExecutionException, InterruptedException {

        MomijiMessage momijiMessage = parseMessage(message);

        HashMap<String, Object> extractedFields = new HashMap<>();

        for(var guide : momijiMessage.getJob().getContentParsingGuides()) {
            String sourceName = guide.getSource();

            List<Field> fields = guide.getFields();

            List<Content> availableContents = momijiMessage.getJob().getContents();

            Optional<Content> foundContent = availableContents.stream()
                    .filter(content ->
                            IS_SOURCE_NAME_CASE_SENSITIVE
                                    ? content.getName().equals(sourceName) // Case sensitive if true
                                    : content.getName().equalsIgnoreCase(sourceName) // Ignores case if false
                    )
                    .findFirst();

            if(foundContent.isEmpty()) throw new NoSuchFieldException("No content with such name: " + sourceName);

            Content content = foundContent.get();

            HashMap<String, Object> outputFields = contentExtractorFactory
                    .getContentExtractor(content.getContentType())
                    .extract(content.getContent(), fields);

            extractedFields.putAll(outputFields);

        }

        extractedFields.entrySet().forEach(System.out::println);

        String output = new ObjectMapper().writeValueAsString(extractedFields);

        messagePublisher.send(output);
    }

    public static MomijiMessage parseMessage(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(message, MomijiMessage.class);
    }

}
