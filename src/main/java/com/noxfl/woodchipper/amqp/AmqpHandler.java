/**
 *
 */
package com.noxfl.woodchipper.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.noxfl.woodchipper.WoodChipperConfiguration;
import com.noxfl.woodchipper.extractor.*;
import com.noxfl.woodchipper.schema.Content;
import com.noxfl.woodchipper.schema.Field;
import com.noxfl.woodchipper.schema.MomijiMessage;
import net.minidev.json.JSONObject;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author Fernando Nathanael
 *
 */
public class AmqpHandler {

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

    private MessageHandler messageHandler;

    @Autowired
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @RabbitHandler
    @RabbitListener(queues = WoodChipperConfiguration.INPUT_QUEUE_NAME)
    public void receive(String message) throws JsonProcessingException, NoSuchFieldException {

        try {

            String output = messageHandler.handle(message);

            template.convertAndSend(WoodChipperConfiguration.OUTPUT_QUEUE_NAME, output);

        } catch (NoSuchFieldException exception) {

            HashMap<String, String> failMessage = new HashMap<>();

            failMessage.put("message", message);
            failMessage.put("error", exception.toString());

            JSONObject failMessageJson = new JSONObject(failMessage);

            template.convertAndSend(WoodChipperConfiguration.OUTPUT_FAIL_QUEUE_NAME, failMessageJson.toString());

            throw new RuntimeException();

        }
    }

}