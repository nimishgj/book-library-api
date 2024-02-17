package dev.infraspec.middleware;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.Database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.valueOf;

public class Dummy implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        InputStream requestBody = httpExchange.getRequestBody();
        StringBuilder requestBodyString = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = requestBody.read(buffer)) != -1) {
            requestBodyString.append(new String(buffer, 0, bytesRead));
        }
        Gson gson = new Gson();

        Book book = gson.fromJson(valueOf(requestBodyString), Book.class);
        BookRepository bookRepository;
        try {
            bookRepository = new BookRepository(new Database("jdbc:mysql://localhost:3306/library", "root", "1234"));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        try {
            bookRepository.addBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Book> books = bookRepository.getAllAvailableBooks();
        books.forEach(book1 -> System.out.println(book1.toString()));
        requestBody.close();
    }
}
