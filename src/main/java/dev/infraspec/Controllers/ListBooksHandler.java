package dev.infraspec.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.infraspec.Book;
import dev.infraspec.BookRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ListBooksHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        BookRepository bookRepository = BookRepository.defaultBookRepository();
        List<Book> books = bookRepository.getAllAvailableBooks();

        Gson gson = new Gson();
        String jsonBooks = gson.toJson(books);
        httpExchange.getResponseHeaders().set("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, jsonBooks.getBytes().length);
        System.out.println(jsonBooks.getBytes().length);

        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(jsonBooks.getBytes());
        responseBody.close();
    }
}

