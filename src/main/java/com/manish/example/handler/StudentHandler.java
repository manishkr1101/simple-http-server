package com.manish.example.handler;

import com.manish.example.beans.JsonResponse;
import com.manish.example.beans.Response;
import com.manish.example.handler.util.HttpUtil;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.manish.example.handler.constants.RequestMethod.GET;
import static com.manish.example.handler.constants.RequestMethod.POST;

public class StudentHandler extends SimpleHttpHandler{
    Map<Long, String> studentsMap = new HashMap<>();

    public StudentHandler() {
        studentsMap.put(101L, "Harsh");
        studentsMap.put(102L, "Kundan");
        studentsMap.put(103L, "Manish");
    }
    @Override
    public String getPath() {
        return "/api/student";
    }

    @Override
    public Response buildHttpResponse(HttpExchange httpExchange) {
        switch (httpExchange.getRequestMethod()) {
            case GET:
                return buildGetHttpResponse(httpExchange);
            case POST:
                return buildPostHttpResponse(httpExchange);
        }
        return new Response(500, "Method not allowed");
    }

    private Response buildPostHttpResponse(HttpExchange httpExchange) {
        try {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);

            String query = br.readLine();
            return new Response(String.format("{ message: %s }", query));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Response buildGetHttpResponse(HttpExchange httpExchange) {
        Map<String, String> query = HttpUtil.decodeQueryString(httpExchange.getRequestURI());
        String body;

        if(query != null && query.get("id")!=null) {
            Long id = Long.valueOf(query.get("id"));
            String student = studentsMap.get(id);
            if(student != null)
                body = String.format("{%d:%s}", id, student);
            else {
                Response response = new Response(404, "Student with id: " + id + " not found!");
                response.getResponseHeaders().put("Content-Type", "text/html");
                return response;
            }
        }
        else {
            body = HttpUtil.toJson(studentsMap);
        }

        return new Response(body);
    }

    @Override
    public void setResponseHeader(HttpExchange httpExchange) {
        super.setResponseHeader(httpExchange);
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
    }

    @Override
    public boolean matchURI(HttpExchange he) {
        String pathWithQuery = he.getRequestURI().toString();
        boolean exactMatch = getPath().equals(pathWithQuery);
        boolean matchWithQuery = pathWithQuery.startsWith(getPath()) && pathWithQuery.length() > getPath().length() && pathWithQuery.charAt(getPath().length()) == '?';

        return exactMatch || matchWithQuery;
    }
}
