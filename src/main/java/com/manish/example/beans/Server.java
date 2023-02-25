package com.manish.example.beans;

import com.manish.example.exception.PortNotFreeException;

import com.sun.net.httpserver.HttpHandler;

public interface Server {
    void listen();
    void listen(int port) throws Exception;
    void register(String path, HttpHandler handler);
}
