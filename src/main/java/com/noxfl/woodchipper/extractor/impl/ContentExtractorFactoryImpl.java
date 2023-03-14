package com.noxfl.woodchipper.extractor.impl;

import com.noxfl.woodchipper.extractor.ContentExtractor;
import com.noxfl.woodchipper.extractor.ContentExtractorFactory;
import com.noxfl.woodchipper.schema.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentExtractorFactoryImpl implements ContentExtractorFactory {

    @Autowired
    private JsonContentExtractor jsonContentExtractorImpl;

    @Override
    public ContentExtractor getContentExtractor(ContentType contentType) {
        switch (contentType) {
            case JSON -> {
                return jsonContentExtractorImpl;
            }
            default -> throw new IllegalArgumentException("No processing function available for content type: " + contentType);
        }
    }

}
