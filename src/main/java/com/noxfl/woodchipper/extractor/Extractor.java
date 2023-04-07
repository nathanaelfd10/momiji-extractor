package com.noxfl.woodchipper.extractor;

import java.util.HashMap;

public interface Extractor {

    public HashMap<String, Object> extract(String content);

}