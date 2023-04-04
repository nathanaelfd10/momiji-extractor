package com.noxfl.woodchipper.extractor.productlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noxfl.woodchipper.extractor.ProductExtractor;
import com.noxfl.woodchipper.schema.tokopedia.category.productcard.LabelGroup;
import com.noxfl.woodchipper.schema.tokopedia.category.productcard.TokopediaCategoryProductCard;
import com.noxfl.woodchipper.util.UriUtils;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Optional;

@Component
public class TokopediaCategoryJson implements ProductExtractor {

    @Override
    public HashMap<String, Object> extract(String content) {

        TokopediaCategoryProductCard tokopediaCategoryProductCard;
        try {
            tokopediaCategoryProductCard = new ObjectMapper().readValue(content, TokopediaCategoryProductCard.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Object> outputFields = new HashMap<>();

        try {
            outputFields.put("url", UriUtils.clearParameter(tokopediaCategoryProductCard.getUrl()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Optional<LabelGroup> totalSoldLabel = tokopediaCategoryProductCard.getLabelGroups().stream()
                .filter(label -> label.getPosition().equalsIgnoreCase("integrity"))
                .findFirst();

        outputFields.put("name", tokopediaCategoryProductCard.getName());
        outputFields.put("price", tokopediaCategoryProductCard.getPrice());
        outputFields.put("price_original", tokopediaCategoryProductCard.getOriginalPrice());
        outputFields.put("total_sold", totalSoldLabel.isPresent() ? totalSoldLabel.get().getTitle() : 0);
        outputFields.put("rating", tokopediaCategoryProductCard.getRating());
        outputFields.put("seller_id", tokopediaCategoryProductCard.getShop().getId());
        outputFields.put("seller_url", tokopediaCategoryProductCard.getShop().getName());
        outputFields.put("seller_name", tokopediaCategoryProductCard.getShop().getLocation());

        return outputFields;
    }
}
