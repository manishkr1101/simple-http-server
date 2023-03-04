package com.manish.example.beans;

import com.google.gson.Gson;

public class JsonResponse extends Response{
    static final Gson gson = new Gson();
    public JsonResponse() {
        this(new Object());
    }
    public JsonResponse(Object body) {
        this(200, body);
    }

    public JsonResponse(int httpCode, Object body) {
        super(httpCode,"");
        this.body = gson.toJson(body);
        this.responseHeaders.put("Content-Type", "application/json");
    }
}
