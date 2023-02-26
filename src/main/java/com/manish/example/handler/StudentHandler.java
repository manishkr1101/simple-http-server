package com.manish.example.handler;

import com.manish.example.beans.Response;
import com.manish.example.handler.util.HttpUtil;
import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;

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
            body = HttpUtil.JSONStringify(studentsMap);
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
