package com.manish.example.handler;

import com.manish.example.beans.Response;
import com.sun.net.httpserver.HttpExchange;

public class JsonResponseHandler extends SimpleHttpHandler {

    @Override
    public String getPath() {
        return "/json";
    }

    @Override
    public Response buildHttpResponse(HttpExchange httpExchange) {
        String body = "{\"name\": \"manish\", \"age\": 22}";
        return new Response(200, body);
    }

    @Override
    public void setResponseHeader(HttpExchange httpExchange) {
        super.setResponseHeader(httpExchange);
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
    }

}
