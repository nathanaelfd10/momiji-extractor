package com.noxfl.woodchipper.extractor.impl;

import com.noxfl.woodchipper.extractor.ProductExtractor;
import com.noxfl.woodchipper.extractor.SiteContentExtractorFactory;
import com.noxfl.woodchipper.extractor.SiteContentType;
import com.noxfl.woodchipper.extractor.productlist.TokopediaCategoryJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentExtractorFactoryImpl implements SiteContentExtractorFactory {

    @Autowired
    private TokopediaCategoryJson tokopediaCategoryJson;

    @Override
    public ProductExtractor getContentExtractor(SiteContentType siteContentType) {
        switch (siteContentType) {
//            case JSON -> {
//                return jsonContentExtractorImpl;
//            }
            case TOKOPEDIA_CATEGORY_JSON -> {
                return tokopediaCategoryJson;
            }

            default -> throw new IllegalArgumentException("No processing function available for content type: " + siteContentType);
        }
    }

}
