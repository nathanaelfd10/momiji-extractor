package com.noxfl.woodchipper.extractor.impl;

import com.noxfl.woodchipper.extractor.Extractor;
import com.noxfl.woodchipper.extractor.SiteContentExtractorFactory;
import com.noxfl.woodchipper.extractor.SiteContentType;
import com.noxfl.woodchipper.extractor.productlist.BukalapakCategoryHtml;
import com.noxfl.woodchipper.extractor.productlist.TokopediaCategoryJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentExtractorFactoryImpl implements SiteContentExtractorFactory {

    private TokopediaCategoryJson tokopediaCategoryJson;

    @Autowired
    private void setTokopediaCategoryJson(TokopediaCategoryJson tokopediaCategoryJson) {
        this.tokopediaCategoryJson = tokopediaCategoryJson;
    }

    private BukalapakCategoryHtml bukalapakCategoryHtml;

    @Autowired
    private void setBukalapakCategoryHtml(BukalapakCategoryHtml bukalapakCategoryHtml) {
        this.bukalapakCategoryHtml = bukalapakCategoryHtml;
    }

    @Override
    public Extractor getContentExtractor(SiteContentType siteContentType) {
        switch (siteContentType) {
//            case JSON -> {
//                return jsonContentExtractorImpl;
//            }
            case TOKOPEDIA_CATEGORY_JSON -> {
                return tokopediaCategoryJson;
            }

            case BUKALAPAK_CATEGORY_HTML -> {
                return bukalapakCategoryHtml;
            }

            default -> throw new IllegalArgumentException("No processing function available for content type: " + siteContentType);
        }
    }

}
