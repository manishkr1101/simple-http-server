package com.manish.example.handler;

import com.google.gson.Gson;
import com.manish.example.beans.JsonResponse;
import com.manish.example.beans.Response;
import com.manish.example.handler.util.HttpUtil;
import com.manish.example.pojo.Person;
import com.sun.net.httpserver.HttpExchange;

public class JsonResponseHandler extends SimpleHttpHandler {

    @Override
    public String getPath() {
        return "/json";
    }

    @Override
    public Response buildHttpResponse(HttpExchange httpExchange) {
        Person person = new Person("Manish", 22);
        person.getFriends().add(new Person("Harsh", 21));
        person.getFriends().add(new Person("Kundan", 23));
        String body = HttpUtil.toJson(person);
        return new JsonResponse(200, person);
    }

}
