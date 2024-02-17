package dev.infraspec.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ValidRequestHandler implements HttpHandler {
    private String response;
    public void handle(HttpExchange httpExchange, String response) throws IOException {
        this.response=response;
        handle(httpExchange);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().set("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();
    }
}
