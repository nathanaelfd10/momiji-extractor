/**
 * 
 */
package woodchipper.extractor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Fernando Nathanael
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class ParsingGuide {
	
	String name;
	Class<?> type;
	String path;

}
