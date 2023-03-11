package com.noxfl.woodchipper.extractor.impl;

import com.noxfl.woodchipper.extractor.ContentExtractorFactory;
import com.noxfl.woodchipper.extractor.ContentType;
import com.noxfl.woodchipper.extractor.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ContextExtractoryFactoryImpl implements ContentExtractorFactory {

    @Autowired
    private JsonContentExtractor jsonContentExtractorImpl;

    @Override
    public HashMap<String, Object> extract(ContentType contentType, String content, List<Field> guides) {

        HashMap<String, Object> fields;

        switch (contentType) {
            case JSON -> {
                fields = jsonContentExtractorImpl.extract(content, guides);
                return fields;
            }
            default -> {
                throw new IllegalArgumentException(
                        "No processing function available for content type: " + contentType.toString());
            }
        }

    }

}
