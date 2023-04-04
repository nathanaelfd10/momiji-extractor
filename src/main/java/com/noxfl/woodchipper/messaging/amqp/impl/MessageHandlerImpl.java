package com.noxfl.woodchipper.messaging.amqp.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noxfl.woodchipper.WoodChipperConfiguration;
import com.noxfl.woodchipper.extractor.SiteContentExtractorFactory;
import com.noxfl.woodchipper.messaging.amqp.MessageHandler;
import com.noxfl.woodchipper.extractor.SiteContentType;
import com.noxfl.woodchipper.messaging.cloudpubsub.MessagePublisher;
import com.noxfl.woodchipper.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class MessageHandlerImpl implements MessageHandler {

    private final MessagePublisher messagePublisher;

    private final SiteContentExtractorFactory siteContentExtractorFactory;

    @Autowired
    public MessageHandlerImpl(MessagePublisher messagePublisher, SiteContentExtractorFactory siteContentExtractorFactory) {
        this.messagePublisher = messagePublisher;
        this.siteContentExtractorFactory = siteContentExtractorFactory;
    }

    private SiteContentType getSiteContentType(String siteName, PageType pageType, ContentType formatType) {
        // WARNING: Naming order is crucial.
        // The naming in this app is: siteName + pageType + contentType.
        String siteContentTypeString = Arrays.stream(new String[] { siteName, pageType.toString(), formatType.toString() })
                .map(String::toUpperCase)
                .collect(Collectors.joining("_"));

        return SiteContentType.valueOf(siteContentTypeString);
    }

    private String hashMapToJsonString(HashMap<String, Object> fields) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(fields);
    }

    @Override
    public void handle(String message) throws IOException, ExecutionException, InterruptedException {

        MomijiMessage momijiMessage = parseMessage(message);

        Job job = momijiMessage.getJob();

        SiteContentType siteContentType = getSiteContentType(
                job.getSite().getName(),
                job.getPageType(),
                job.getContentType()
        );

        HashMap<String, Object> outputFields = siteContentExtractorFactory
                .getContentExtractor(siteContentType)
                .extract(momijiMessage.getJob().getContent().getProduct());

        // TODO Add functions for extra fields

        String outputMessage = hashMapToJsonString(outputFields);

        System.out.println(outputMessage);

        if(!WoodChipperConfiguration.IS_RUN_DISCONNECTED) messagePublisher.send(outputMessage);
    }

//    @Override
//    public void handle(String message) throws NoSuchFieldException, IOException, ExecutionException, InterruptedException {
//
//        MomijiMessage momijiMessage = parseMessage(message);
//
//        HashMap<String, Object> extractedFields = new HashMap<>();
//
//        for(var guide : momijiMessage.getJob().getContentParsingGuides()) {
//            String sourceName = guide.getSource();
//
//            List<Field> fields = guide.getFields();
//
//            List<Content> availableContents = momijiMessage.getJob().getContents();
//
//            Optional<Content> foundContent = availableContents.stream()
//                    .filter(content ->
//                            IS_SOURCE_NAME_CASE_SENSITIVE
//                                    ? content.getName().equals(sourceName) // Case sensitive if true
//                                    : content.getName().equalsIgnoreCase(sourceName) // Ignores case if false
//                    )
//                    .findFirst();
//
//            if(foundContent.isEmpty()) throw new NoSuchFieldException("No content with such name: " + sourceName);
//
//            Content content = foundContent.get();
//
//            HashMap<String, Object> outputFields = contentExtractorFactory
//                    .getContentExtractor(content.getContentType())
//                    .extract(content.getContent(), fields);
//
//            extractedFields.putAll(outputFields);
//
//        }
//
//        extractedFields.entrySet().forEach(System.out::println);
//
//        String output = new ObjectMapper().writeValueAsString(extractedFields);
//
//        messagePublisher.send(output);
//    }

    public static MomijiMessage parseMessage(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(message, MomijiMessage.class);
    }

}
