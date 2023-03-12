/**
 *
 */
package com.noxfl.woodchipper.extractor.impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ParseContext;
import com.noxfl.woodchipper.extractor.ContentExtractor;
import com.noxfl.woodchipper.extractor.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fernando Nathanael
 *
 */
@Component
class JsonContentExtractor implements ContentExtractor {

    public static final boolean IS_ADD_UTC_TIMESTAMP = true;
    private final Configuration config = Configuration.defaultConfiguration();

    private ParseContext parseContext;

    @Autowired
    public void setParseContext(ParseContext parseContext) {
        this.parseContext = parseContext;
    }

    public HashMap<String, Object> extract(String content, List<Field> guides) {

        DocumentContext context = JsonPath.using(config).parse(content);

        HashMap<String, Object> output = (HashMap<String, Object>) guides
                .stream()
                .collect(Collectors.toMap(
                        Field::getName, // Key (Field name)
                        guide -> context.read(guide.getPath()) // Value (Field value)
                ));

        if (IS_ADD_UTC_TIMESTAMP) output.put("timestamp", Instant.now().toString());

        return output;
    }

}