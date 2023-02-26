package com.manish.example.handler.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    public static Map<String, String> decodeQueryString(URI uri) {
        String queryString = uri.getQuery();
        String[] queries = queryString.split("&");
        Map<String, String> result = new HashMap<>();
        for (String query : queries) {
            String[] keyValue = query.split("=");
            result.put(keyValue[0], keyValue[1]);
        }
        return result;
    }
}
