/**
 * 
 */
package com.noxfl.woodchipper.extractor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import com.jayway.jsonpath.ParseContext;
import com.noxfl.woodchipper.extractor.ContentExtractor;
import com.noxfl.woodchipper.extractor.Field;

import org.springframework.stereotype.Component;

/**
 * @author Fernando Nathanael
 *
 */
@Component
class JsonContentExtractor implements ContentExtractor {

	private final Configuration config = Configuration.defaultConfiguration();

	public HashMap<String, Object> extract(String content, List<Field> guides) {

		DocumentContext context = JsonPath.using(config).parse(content);

		return (HashMap<String, Object>) guides
				.stream()
				.collect(Collectors.toMap(
						Field::getName, // Key (Field name)
						guide -> context.read(guide.getPath()) // Value (Field value)
				));
	}

}