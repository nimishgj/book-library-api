package dev.infraspec.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.infraspec.Book;
import dev.infraspec.BookRepository;

import java.io.IOException;
import java.util.List;

public class GETListBooksHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        BookRepository bookRepository = BookRepository.defaultBookRepository();
        List<Book> books = bookRepository.getAllAvailableBooks();

        Gson gson = new Gson();
        new ValidRequestHandler().handle(httpExchange, gson.toJson(books));
    }
}
