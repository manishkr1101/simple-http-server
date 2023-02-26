package com.manish.example.beans;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.manish.example.exception.PortNotFreeException;
import com.manish.example.handler.RootHandler;
import com.manish.example.handler.SimpleHttpHandler;
import com.sun.net.httpserver.*;

public class SimpleHttpServer implements Server {
    private int port = 3000;
    private HttpServer server;

    @Override
    public void listen() {
        try {
            listen(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listen(int port) throws Exception {
        if(port == 80) throw new PortNotFreeException("Port: " + port + " is being used.");
        
        server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Listening on port " + port);
        server.start();
    }

    public void register(String path, HttpHandler handler) {
        System.out.println("registering path: " + path);
        server.createContext(path, handler);
    }

    public <T extends SimpleHttpHandler> void register(T handler) {
        System.out.println("registering path: " + handler.getPath());
        server.createContext(handler.getPath(), new HttpHandler() {

            @Override
            public void handle(HttpExchange arg0) throws IOException {
                handler.handle(arg0);
            }
            
        });
    }

    public HttpServer getHttpServer() {
        return server;
    }
}
