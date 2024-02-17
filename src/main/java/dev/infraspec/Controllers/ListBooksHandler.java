package dev.infraspec.Controllers;

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
        StringBuilder bookList = new StringBuilder();

        bookList.append(String.format("%-5s %-30s %-30s %-10s\n", "Id", "Title", "Author", "Year Published"));
        for (Book book : books) {
            bookList.append(book);
            bookList.append("\n");
        }

        httpExchange.sendResponseHeaders(200, bookList.length());
        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(bookList.toString().getBytes());
        responseBody.close();
    }
}

