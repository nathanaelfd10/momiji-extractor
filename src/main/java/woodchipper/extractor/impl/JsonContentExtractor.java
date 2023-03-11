/**
 * 
 */
package woodchipper.extractor.impl;

import java.util.HashMap;
import java.util.List;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import woodchipper.extractor.ContentExtractor;
import woodchipper.extractor.ParsingGuide;

import org.springframework.stereotype.Component;

/**
 * @author Fernando Nathanael
 *
 */
@Component
class JsonContentExtractor implements ContentExtractor {

	private final Configuration config = Configuration.defaultConfiguration();

	public HashMap<String, Object> extract(String content, List<ParsingGuide> guides) {

		DocumentContext context = JsonPath.using(config).parse(content);

		HashMap<String, Object> fields = new HashMap<>();

		for (var guide : guides) {
			String name = guide.getName();
			String path = guide.getPath();

			Object value = context.read(path);

			fields.put(name, value);
		}

		return fields;
	}

}