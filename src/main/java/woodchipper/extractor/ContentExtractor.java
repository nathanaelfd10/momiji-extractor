/**
 * 
 */
package woodchipper.extractor;

import java.util.HashMap;
import java.util.List;

/**
 * @author Fernando Nathanael
 *
 */
public interface ContentExtractor {
	
	public HashMap<String, Object> extract(String content, List<ParsingGuide> guides);

}
