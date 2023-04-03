package com.noxfl.woodchipper.extractor;

import java.util.HashMap;

public interface ProductExtractor {

    public HashMap<String, Object> extract(String content);

}