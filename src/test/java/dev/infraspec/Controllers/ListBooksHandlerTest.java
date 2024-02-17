package dev.infraspec.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import dev.infraspec.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class ListBooksHandlerTest {
    @Test
    @DisplayName("Returns JSON form of Books")
    void listBooks() throws IOException {
        ListBooksHandler listBooksHandler = new ListBooksHandler();
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);
        Headers headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(),any());

        listBooksHandler.handle(httpExchangeMock);
        BookRepository bookRepository = BookRepository.defaultBookRepository();
        Gson gson = new Gson();

        verify(outputStreamMock).write(gson.toJson(bookRepository.getAllAvailableBooks()).getBytes());
    }
}