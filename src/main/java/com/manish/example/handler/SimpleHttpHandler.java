package com.manish.example.handler;

import java.io.IOException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class SimpleHttpHandler {

    public abstract String getPath();
    public abstract String buildResponse(HttpExchange he);

    // can be modified
    public void before(HttpExchange he) throws IOException {
        System.out.println("request coming to " + getPath());
        describe(he);
    }

    // template function
    public void handle(HttpExchange he) throws IOException {
        before(he);
        String response = buildResponse(he);
        writeResponse(he, response);
        he.close();
    }

    private void writeResponse(HttpExchange he, String response) throws IOException {
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    // util functions
    private void describe(HttpExchange he) throws IOException {
        System.out.println("Request method " + he.getRequestMethod());
        Set<Map.Entry<String, List<String>>> entries = he.getRequestHeaders().entrySet();
        String response = "";
        for (Map.Entry<String, List<String>> entry : entries)
            response += entry.toString() + "\n";
        System.out.print("Request headers: \n" + response);
        System.out.println("Request URI " + he.getRequestURI());
        System.out.println("Request body " + he.getRequestBody().readAllBytes().toString());
    }
}
