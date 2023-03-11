package woodchipper.extractor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import woodchipper.extractor.ContentExtractorFactory;
import woodchipper.extractor.ContentType;
import woodchipper.extractor.ParsingGuide;

import java.util.HashMap;
import java.util.List;

@Component
public class ContextExtractoryFactoryImpl implements ContentExtractorFactory {

    @Autowired
    private JsonContentExtractor jsonContentExtractorImpl;

    @Override
    public HashMap<String, Object> extract(ContentType contentType, String content, List<ParsingGuide> guides) {

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
