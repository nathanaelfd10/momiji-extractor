package com.noxfl.woodchipper.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noxfl.woodchipper.schema.MomijiMessage;

public interface MessageHandler {

    public String handle(MomijiMessage message) throws NoSuchFieldException;

    public String handle(String message) throws NoSuchFieldException, JsonProcessingException;

}
