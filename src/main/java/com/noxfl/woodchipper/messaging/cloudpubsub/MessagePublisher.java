package com.noxfl.woodchipper.messaging.cloudpubsub;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MessagePublisher {

    public void send(String message) throws InterruptedException, ExecutionException, IOException;

}
