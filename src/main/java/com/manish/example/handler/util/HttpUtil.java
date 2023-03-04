package com.manish.example.handler.util;

import com.google.gson.Gson;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpUtil {
    private static final Gson gson = new Gson();
    public static Map<String, String> decodeQueryString(URI uri) {
        String queryString = uri.getQuery();
        if(queryString == null) return null;
        String[] queries = queryString.split("&");
        Map<String, String> result = new HashMap<>();
        for (String query : queries) {
            String[] keyValue = query.split("=");
            result.put(keyValue[0], keyValue[1]);
        }
        return result;
    }

    @Deprecated
    public static  <T,S> String JSONStringify(Map<T, S> map) {
        String body = "{";
        for(Map.Entry<T, S> entry: map.entrySet()) {
            body = body + entry.getKey() + ": " + entry.getValue() + ",";
        }
        body += "}";
        return body;
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
