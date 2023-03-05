package com.manish.example.handler;

import java.io.IOException;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.*;

import com.manish.example.beans.Response;
import com.sun.net.httpserver.HttpExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SimpleHttpHandler {
    Logger logger = LoggerFactory.getLogger(SimpleHttpHandler.class);
    public abstract String getPath();
    @Deprecated
    public String buildResponse(HttpExchange he) { return ""; };
    public Response buildHttpResponse(HttpExchange httpExchange) {
        return new Response(buildResponse(httpExchange));
    }

    public void setResponseHeader(HttpExchange httpExchange) {
        setBasicResponseHeader(httpExchange);
    }

    // can be modified
    public void before(HttpExchange he) throws IOException {  }

    // template function
    public void handle(HttpExchange he) throws IOException {
//        System.out.println("Request handled by " + getPath());
        describe(he);
        if(!matchURI(he)) {
            handlePageNotFoundError(he);
            return;
        }
        before(he);
        Response response = buildHttpResponse(he);
        setResponseHeader(he);
        setResponseHeader(he, response);
        writeResponse(he, response);
        // he.close();
    }

    private void handlePageNotFoundError(HttpExchange httpExchange) throws IOException {
        String response = "Could not found this page :(";
        httpExchange.sendResponseHeaders(404, response.length());
        httpExchange.getResponseBody().write(response.getBytes());
        httpExchange.getResponseBody().close();
    }

    public boolean matchURI(HttpExchange he) {
        return getPath().equalsIgnoreCase(he.getRequestURI().getPath());
    }

    private void setBasicResponseHeader(HttpExchange httpExchange) {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Credentials-Header", "*");
    }

    private void setResponseHeader(HttpExchange httpExchange, Response response) {
        for(Map.Entry<String, String> entry: response.getResponseHeaders().entrySet()) {
            httpExchange.getResponseHeaders().add(entry.getKey(), entry.getValue());
        }
    }

    private void writeResponse(HttpExchange he, Response res) throws IOException {
        String body = res.getBody();
        he.sendResponseHeaders(res.getHttpCode(), body.length());
        OutputStream os = he.getResponseBody();
        os.write(body.getBytes());
        os.close();
    }

    // util functions
    private void describe(HttpExchange he) {
//        Set<Map.Entry<String, List<String>>> entries = he.getRequestHeaders().entrySet();
//        String response = "";
//        for (Map.Entry<String, List<String>> entry : entries)
//            response += entry.toString() + "\n";
//        System.out.print("Request headers: \n" + response);
        String line = String.format(
                "[%s] - %s %s   [Host: %s, User-agent: %s]",
                new Timestamp(System.currentTimeMillis()),
                he.getRequestMethod(),
                he.getRequestURI().toString(),
                he.getRequestHeaders().getFirst("Host"),
                he.getRequestHeaders().getFirst("User-agent")
        );
        System.out.println(line);
    }
}
