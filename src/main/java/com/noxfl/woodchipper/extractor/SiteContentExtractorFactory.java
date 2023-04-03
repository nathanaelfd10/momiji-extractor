package com.noxfl.woodchipper.extractor;

public interface SiteContentExtractorFactory {

    public ProductExtractor getContentExtractor(SiteContentType siteContentType);

}