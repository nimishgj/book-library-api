package dev.infraspec.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HelloWorldController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Hello,Yup the server is up and running.\n";
        new ValidRequestHandler().handle(httpExchange, response);
    }
}
