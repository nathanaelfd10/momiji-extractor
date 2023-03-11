/**
 * 
 */
package com.noxfl.woodchipper.extractor;

import java.util.HashMap;
import java.util.List;

/**
 * @author Fernando Nathanael
 *
 */
public interface ContentExtractor {
	
	public HashMap<String, Object> extract(String content, List<Field> guides);

}
