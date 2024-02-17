package dev.infraspec.middleware;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.infraspec.Controllers.ListBooksHandler;

import java.io.IOException;

public class ListBookHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
            if (httpExchange.getRequestMethod().equalsIgnoreCase("GET")) {
                new ListBooksHandler().handle(httpExchange);
            } else {
                httpExchange.sendResponseHeaders(405, -1);
                httpExchange.close();
            }
    }
}
