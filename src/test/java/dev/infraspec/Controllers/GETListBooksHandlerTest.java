package dev.infraspec.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import dev.infraspec.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class GETListBooksHandlerTest {
    private GETListBooksHandler listBooksHandler;
    private HttpExchange httpExchangeMock;
    private OutputStream outputStreamMock;
    private Headers headersMock;
    @BeforeEach
    void setup() {
        listBooksHandler = new GETListBooksHandler();
        httpExchangeMock = mock(HttpExchange.class);
        outputStreamMock = mock(OutputStream.class);
        headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(), any());
    }

    @Test
    @DisplayName("Returns JSON form of Books")
    void listBooksBody() throws IOException {
        listBooksHandler.handle(httpExchangeMock);
        BookRepository bookRepository = BookRepository.defaultBookRepository();
        Gson gson = new Gson();

        verify(outputStreamMock).write(gson.toJson(bookRepository.getAllAvailableBooks()).getBytes());
    }

    @Test
    @DisplayName("Sets the Headers of the Response")
    void listBooksHeader() throws IOException {
        listBooksHandler.handle(httpExchangeMock);

        verify(headersMock).set("Content-Type", "application/json");
    }

    @Test
    @DisplayName("Sets the Headers of the Response")
    void listBooksStatusCode() throws IOException {
        listBooksHandler.handle(httpExchangeMock);

        verify(httpExchangeMock).sendResponseHeaders(200, 157);
    }

    @Test
    @DisplayName("Closes the Http connection")
    void closeConnection() throws IOException {
        listBooksHandler.handle(httpExchangeMock);

        verify(outputStreamMock).close();
    }
}
