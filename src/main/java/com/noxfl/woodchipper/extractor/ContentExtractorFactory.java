package com.noxfl.woodchipper.extractor;

import java.util.HashMap;
import java.util.List;

public interface ContentExtractorFactory {

    public ContentExtractor getContentExtractor(ContentType contentType);

}