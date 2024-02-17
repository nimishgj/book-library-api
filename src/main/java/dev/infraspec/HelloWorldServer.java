package dev.infraspec;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import dev.infraspec.Controllers.HelloWorldController;

public class HelloWorldServer {
    private final HttpServer server;

    private final int PORT;

    public HelloWorldServer() throws IOException {
        PORT=8000;
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
    }

    public void start() throws IOException {
        server.createContext("/hello", new HelloWorldController());
        server.setExecutor(null);
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
