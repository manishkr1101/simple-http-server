package com.manish.example.beans;

public class Response {
    int httpCode;
    String body;

    public Response() {
        this("");
    }

    public Response(String body) {
        this(200, body);
    }

    public Response(int httpCode, String body) {
        this.httpCode = httpCode;
        this.body = body;
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
}
