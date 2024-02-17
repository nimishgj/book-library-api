package dev.infraspec.Controllers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import dev.infraspec.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class BookByIdHandlerTest {
    private BookByIdHandler bookByIdHandler;
    private HttpExchange httpExchangeMock;
    private OutputStream outPutStreamMock;
    @BeforeEach
    void setUp() {
        bookByIdHandler = new BookByIdHandler();
        httpExchangeMock = mock(HttpExchange.class);
        outPutStreamMock = mock(OutputStream.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outPutStreamMock);
    }

    @Test
    @DisplayName("Sends Page not found for invalid Request Method")
    void invalidRequestMethod() throws IOException {
        when(httpExchangeMock.getRequestMethod()).thenReturn("POST");
        bookByIdHandler.handle(httpExchangeMock);

        verify(outPutStreamMock).write("Oops!, you landed somewhere you should not".getBytes());
    }

    @Test
    @DisplayName("Sends Page not found for no Parameter provided")
    void noIdParameter() throws IOException, URISyntaxException {
        when(httpExchangeMock.getRequestMethod()).thenReturn("GET");
        when(httpExchangeMock.getRequestURI()).thenReturn(new URI("http://localhost:8080/books/"));
        bookByIdHandler.handle(httpExchangeMock);

        verify(outPutStreamMock).write("Please provide an id parameter".getBytes());
    }

    @Test
    @DisplayName("sends Page not found for invalid format of Parameter")
    void invalidIdParameter() throws IOException, URISyntaxException {
        when(httpExchangeMock.getRequestMethod()).thenReturn("GET");
        when(httpExchangeMock.getRequestURI()).thenReturn(new URI("http://localhost:8080/books/" + "?id=asdfasdf"));
        bookByIdHandler.handle(httpExchangeMock);

        verify(outPutStreamMock).write("Provide a valid Numeric id".getBytes());
    }

    @Test
    @DisplayName("sends Page not found for invalid Book id")
    void invalidBookIdParameter() throws IOException, URISyntaxException {
        when(httpExchangeMock.getRequestMethod()).thenReturn("GET");
        when(httpExchangeMock.getRequestURI()).thenReturn(new URI("http://localhost:8080/books/" + "?id=100"));
        bookByIdHandler.handle(httpExchangeMock);

        verify(outPutStreamMock).write("Requested Book is Not Available".getBytes());
    }

    @Test
    @DisplayName("sends Book for valid Book id")
    void validBookIdParameter() throws IOException, URISyntaxException {
        when(httpExchangeMock.getRequestMethod()).thenReturn("GET");
        Headers headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(), any());
        when(httpExchangeMock.getRequestURI()).thenReturn(new URI("http://localhost:8080/books/" + "?id=1"));
        Gson gson = new Gson();
        Book book = new Book(1, "someTitle", "someAuthor", 1982);

        bookByIdHandler.handle(httpExchangeMock);

        verify(outPutStreamMock).write(gson.toJson(book).getBytes());
    }
}