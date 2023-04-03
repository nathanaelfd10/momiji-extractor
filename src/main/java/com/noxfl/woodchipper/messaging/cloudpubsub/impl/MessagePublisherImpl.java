package com.noxfl.woodchipper.messaging.cloudpubsub.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.noxfl.woodchipper.messaging.cloudpubsub.MessagePublisher;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MessagePublisherImpl implements MessagePublisher {

    private final String projectId;
    private final String topicId;

    public MessagePublisherImpl(String projectId, String topicId) {
        this.projectId = projectId;
        this.topicId = topicId;

        String initMessage = String.format("Cloud Pub/Sub environment set.\n" +
                        " Project ID: %s\n " +
                        "Topic: %s\n",
                projectId, topicId);

        System.out.println(initMessage);
    }

    public void send(String message) throws InterruptedException, ExecutionException, IOException {
        TopicName topicName = TopicName.of(projectId, topicId);

        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();
            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Published message ID: " + messageId);
        } finally {
            if(publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }

    }

}