package com.noxfl.woodchipper.util;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class UriUtils {

    public static String clearParameter(String url) throws URISyntaxException {
        return new URIBuilder(url).clearParameters().toString();
    }

}
