package dev.infraspec;

import dev.infraspec.Controllers.BookByIdHandler;
import dev.infraspec.Controllers.HelloWorldController;
import dev.infraspec.middleware.ListBookHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServer {
    private final com.sun.net.httpserver.HttpServer server;

    private final int PORT;

    public HttpServer() throws IOException {
        PORT=8000;
        server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(PORT), 0);
    }

    public void start() throws IOException {
        server.createContext("/hello", new HelloWorldController());
        server.createContext("/listBooks", new ListBookHandler());
        server.createContext("/book/", new BookByIdHandler());
        server.setExecutor(null);
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
