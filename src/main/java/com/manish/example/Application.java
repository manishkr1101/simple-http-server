package com.manish.example;

import java.net.InetSocketAddress;

import com.manish.example.beans.Server;
import com.manish.example.beans.SimpleHttpServer;
import com.manish.example.handler.EchoHeaderHandler;
import com.manish.example.handler.JsonResponseHandler;
import com.manish.example.handler.RootHandler;
import com.manish.example.handler.StudentHandler;
import com.sun.net.httpserver.HttpServer;

public class Application {
    static int port;
    public static void main(String[] args) throws Exception {
        System.out.println("Application started");

        processArgs(args);
        SimpleHttpServer server = new SimpleHttpServer();
        server.listen(port);
        server.register(new RootHandler());
        server.register(new EchoHeaderHandler());
        server.register(new JsonResponseHandler());
        server.register(new StudentHandler());

    }



    static void processArgs(String[] args) throws Exception {
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        else {
            throw new Exception("provide port in arguments");
        }
    }
}
