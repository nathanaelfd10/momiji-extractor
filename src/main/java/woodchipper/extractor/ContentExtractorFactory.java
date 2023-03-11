package woodchipper.extractor;

import java.util.HashMap;
import java.util.List;

public interface ContentExtractorFactory {

    public HashMap<String, Object> extract(ContentType contentType, String content, List<ParsingGuide> guides);

}
