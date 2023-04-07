/**
 *
 */
package com.noxfl.woodchipper.extractor.impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.noxfl.woodchipper.schema.message.Field;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fernando Nathanael
 *
 */
//@Component
@Deprecated
class JsonContentExtractor {

    public static final boolean IS_ADD_UTC_TIMESTAMP = true;
    private final Configuration config = Configuration.defaultConfiguration();

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