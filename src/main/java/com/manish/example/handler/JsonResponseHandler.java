package com.manish.example.handler;

import com.sun.net.httpserver.HttpExchange;

public class JsonResponseHandler extends SimpleHttpHandler {

    @Override
    public String getPath() {
        return "/json";
    }

    @Override
    public String buildResponse(HttpExchange he) {
        return "{\"name\": \"manish\", \"age\": 22}";
    }

    @Override
    public void setResponseHeader(HttpExchange httpExchange) {
        super.setResponseHeader(httpExchange);
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
    }

}
