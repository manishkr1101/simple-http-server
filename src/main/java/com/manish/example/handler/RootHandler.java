package com.manish.example.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RootHandler extends SimpleHttpHandler {
    @Override
    public String getPath() {
        return "/";
    }

    @Override
    public String buildResponse(HttpExchange he) {
        return "<h1> new hellow world </h1>";
    }
    
}
