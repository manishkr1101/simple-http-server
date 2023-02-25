package com.manish.example.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EchoHeaderHandler extends SimpleHttpHandler {

    @Override
    public String getPath() {
        return "/echoHeader";
    }

    @Override
    public String buildResponse(HttpExchange he) {
        Headers headers = he.getRequestHeaders();
        Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
        String response = "";
        for (Map.Entry<String, List<String>> entry : entries)
            response += entry.toString() + "\n";

        return response;
    }
}
