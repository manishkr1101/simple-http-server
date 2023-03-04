package com.manish.example.handler;

import com.manish.example.beans.ErrorResponse;
import com.manish.example.beans.JsonResponse;
import com.manish.example.beans.Response;
import com.manish.example.handler.util.HttpUtil;
import com.manish.example.pojo.Student;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static com.manish.example.handler.constants.RequestMethod.GET;
import static com.manish.example.handler.constants.RequestMethod.POST;

public class StudentHandler extends SimpleHttpHandler{
    List<Student> students;

    public StudentHandler() {
        students = new ArrayList<>();
        students.add(new Student("Harsh"));
        students.add(new Student("Kundan"));
        students.add(new Student("Manish"));
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
        return ErrorResponse.message(500, "Method not allowed");
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
        Response response;

        if(query != null && query.get("id")!=null) {
            Long id = Long.valueOf(query.get("id"));
            Optional<Student> student = students.stream().filter(x -> x.getId() == id).findFirst();
            response = student.isPresent() ? new JsonResponse(student.get()) : ErrorResponse.message("student not found");
        }
        else {
            response = new JsonResponse(students);
        }

        return response;
    }

    @Override
    public boolean matchURI(HttpExchange he) {
        String pathWithQuery = he.getRequestURI().toString();
        boolean exactMatch = getPath().equals(pathWithQuery);
        boolean matchWithQuery = pathWithQuery.startsWith(getPath()) && pathWithQuery.length() > getPath().length() && pathWithQuery.charAt(getPath().length()) == '?';

        return exactMatch || matchWithQuery;
    }
}
