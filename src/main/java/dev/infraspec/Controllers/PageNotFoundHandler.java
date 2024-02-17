package dev.infraspec.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class PageNotFoundHandler implements HttpHandler {
    private String response;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        this.response = "Oops!, you landed somewhere you should not";

        httpExchange.sendResponseHeaders(404, response.length());
        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();
    }

    public void handle(HttpExchange httpExchange, String response) throws IOException {
        this.response = response;
        httpExchange.sendResponseHeaders(404, response.length());
        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();
    }
}
