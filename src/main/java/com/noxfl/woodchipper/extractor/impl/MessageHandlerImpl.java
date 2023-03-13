package com.noxfl.woodchipper.extractor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.noxfl.woodchipper.extractor.ContentExtractorFactory;
import com.noxfl.woodchipper.extractor.MessageHandler;
import com.noxfl.woodchipper.schema.Content;
import com.noxfl.woodchipper.schema.Field;
import com.noxfl.woodchipper.schema.MomijiMessage;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class MessageHandlerImpl implements MessageHandler {

    public static final boolean IS_SOURCE_NAME_CASE_SENSITIVE = true;

    private ContentExtractorFactory contentExtractorFactory;

    @Autowired
    public void setContentExtractorFactory(ContentExtractorFactory contentExtractorFactory) {
        this.contentExtractorFactory = contentExtractorFactory;
    }

    private final Configuration config = Configuration.defaultConfiguration();

    @Override
    public String handle(MomijiMessage momijiMessage) throws NoSuchFieldException {

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

        JSONObject extractedFieldsJson = new JSONObject(extractedFields);

        return new JSONObject(extractedFieldsJson).toString();
    }

    @Override
    public String handle(String message) throws NoSuchFieldException, JsonProcessingException {
        MomijiMessage momijiMessage = parseMessage(message);

        return handle(momijiMessage);
    }

    public MomijiMessage parseMessage(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(message, MomijiMessage.class);
    }

}
