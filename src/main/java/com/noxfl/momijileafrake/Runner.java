package com.noxfl.momijileafrake;

import com.noxfl.momijileafrake.extractor.ContentExtractorFactory;
import com.noxfl.momijileafrake.extractor.ContentType;
import com.noxfl.momijileafrake.extractor.ParsingGuide;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner implements CommandLineRunner {

    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    public void setConfigurableApplicationContext(ConfigurableApplicationContext configurableApplicationContext) {
        this.configurableApplicationContext = configurableApplicationContext;
    }

    private ContentExtractorFactory contentExtractorFactory;

    @Autowired
    public void setContentExtractorFactoryImpl(ContentExtractorFactory contentExtractorFactory) {
        this.contentExtractorFactory = contentExtractorFactory;
    }


    @Override
    public void run(String... args) throws Exception {

        String jsonString = "{\n" +
                " \"status\": 200,\n" +
                " \"data\": {\n" +
                "  \"name\": \"Galaxy Buds 2 Pro\",\n" +
                "  \"price\": 3000000,\n" +
                "  \"description\": \"dudududu\",\n" +
                "  \"seller_info\": {\n" +
                "     \"id\": \"samsungid\",\n" +
                "     \"name\": \"Samsung Official Store\",\n" +
                "     \"rating\": 4.953\n" +
                "   }\n" +
                "  }\n" +
                "}";

        ContentType contentType = ContentType.JSON;

        List<ParsingGuide> parsingGuides = new ArrayList<>();

        parsingGuides.add(new ParsingGuide("name", Integer.class, "$.data.name"));
        parsingGuides.add(new ParsingGuide("price", Integer.class, "$.data.price"));
        parsingGuides.add(new ParsingGuide("description", Integer.class, "$.data.description"));
        parsingGuides.add(new ParsingGuide("seller_id", Integer.class, "$.data.seller_info.id"));
        parsingGuides.add(new ParsingGuide("seller_name", Integer.class, "$.data.seller_info.name"));
        parsingGuides.add(new ParsingGuide("seller_rating", Integer.class, "$.data.seller_info.rating"));

        HashMap<String, Object> fields = contentExtractorFactory.extract(contentType, jsonString, parsingGuides);

        for(Map.Entry<String, Object> field : fields.entrySet()) {
            String name = field.getKey();
            Object value = field.getValue();
            String message = String.format("%s: %s (%s)", name, value, value.getClass().getSimpleName());
            System.out.println(message);
        }

        JSONObject output = new JSONObject(fields);

        System.out.println(output.toString());

    }
}
