package com.noxfl.woodchipper.extractor;

public interface SiteContentExtractorFactory {

    public Extractor getContentExtractor(SiteContentType siteContentType);

}