package com.noxfl.woodchipper.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noxfl.woodchipper.schema.MomijiMessage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MessageHandler {

    public void handle(String message) throws NoSuchFieldException, IOException, ExecutionException, InterruptedException;

}
