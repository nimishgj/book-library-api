package dev.infraspec.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GETListBooksHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Database database = new Database("jdbc:mysql://localhost:3306/library", "root", "1234");
        BookRepository bookRepository = null;
        try {
            bookRepository = new BookRepository(database);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Book> books = bookRepository.getAllAvailableBooks();

        Gson gson = new Gson();
        new ValidRequestHandler().handle(httpExchange, gson.toJson(books));
    }
}
