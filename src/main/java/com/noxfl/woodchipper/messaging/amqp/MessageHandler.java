package com.noxfl.woodchipper.messaging.amqp;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MessageHandler {

    public void handle(String message) throws IOException, ExecutionException, InterruptedException;

}
