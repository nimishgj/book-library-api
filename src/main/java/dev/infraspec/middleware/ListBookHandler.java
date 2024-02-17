package dev.infraspec.middleware;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.infraspec.Controllers.GETListBooksHandler;
import dev.infraspec.Controllers.PageNotFoundHandler;

import java.io.IOException;

public class ListBookHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (httpExchange.getRequestMethod().equalsIgnoreCase("GET")) {
            new GETListBooksHandler().handle(httpExchange);
        } else {
            new PageNotFoundHandler().handle(httpExchange);
        }
    }
}
