package com.manish.example.beans;

import java.util.HashMap;
import java.util.Map;

public class Response {
    int httpCode;
    String body;
    Map<String, String> responseHeaders;

    public Response() {
        this("");
    }

    public Response(String body) {
        this(200, body);
    }

    public Response(int httpCode, String body) {
        this.httpCode = httpCode;
        this.body = body;
        this.responseHeaders = new HashMap<>();
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
}
