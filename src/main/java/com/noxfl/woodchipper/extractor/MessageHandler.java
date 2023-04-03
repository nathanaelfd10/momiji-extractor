package com.noxfl.woodchipper.extractor;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MessageHandler {

    public void handle(String message) throws IOException, ExecutionException, InterruptedException;

}
