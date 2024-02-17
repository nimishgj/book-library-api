package dev.infraspec.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.infraspec.Book;
import dev.infraspec.BookRepository;
import dev.infraspec.Database;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookByIdHandler implements HttpHandler {
    private final Database database;
    public BookByIdHandler(){
        this.database= new Database("jdbc:mysql://localhost:3306/library", "root", "1234");
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (!httpExchange.getRequestMethod().equalsIgnoreCase("GET")) {
            new PageNotFoundHandler().handle(httpExchange);
            return;
        }
        String query = httpExchange.getRequestURI().getQuery();
        Map<String, String> queryParams = getQueryParams(query);

        if (!queryParams.containsKey("id")) {
            String response = "Please provide an id parameter";
            new PageNotFoundHandler().handle(httpExchange, response);
            return;
        }

        String id = queryParams.get("id");
        if (!isNumeric(id)) {
            String response = "Provide a valid Numeric id";
            new PageNotFoundHandler().handle(httpExchange, response);
            return;
        }

        BookRepository bookRepository = BookRepository.defaultBookRepository();
        Optional<Book> book = bookRepository.findBook(Integer.parseInt(id));
        if (book.isEmpty()) {
            new PageNotFoundHandler().handle(httpExchange, "Requested Book is Not Available");
            return;
        }

        Gson gson = new Gson();
        new ValidRequestHandler().handle(httpExchange, gson.toJson(book.get()));
    }

    private Map<String, String> getQueryParams(String query) {
        Map<String, String> queryParams = new HashMap<>();
        if (query != null) {
            String[] params = query.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    queryParams.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return queryParams;
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
