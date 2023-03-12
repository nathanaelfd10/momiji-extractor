package com.noxfl.woodchipper.extractor;

import com.noxfl.woodchipper.schema.ContentType;

public interface ContentExtractorFactory {

    public ContentExtractor getContentExtractor(ContentType contentType);

}