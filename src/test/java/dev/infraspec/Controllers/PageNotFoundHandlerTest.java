package dev.infraspec.Controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class PageNotFoundHandlerTest {
    @Test
    @DisplayName("Sends Error Message")
    void sendErrorResponseMessage() throws IOException {
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);
        String expectedResponseMessage = "Oops!, you landed somewhere you should not";

        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        pageNotFoundHandler.handle(httpExchangeMock);

        verify(outputStreamMock).write(expectedResponseMessage.getBytes());
    }

    @Test
    @DisplayName("Sets Page not found Response Code")
    void setPageNotFoundResponseCode() throws IOException {
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);

        Headers headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(),any());

        pageNotFoundHandler.handle(httpExchangeMock);

        verify(httpExchangeMock).sendResponseHeaders(404,42);

    }

    @Test
    @DisplayName("Closes the Http connection")
    void closeConnection() throws IOException {
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);

        pageNotFoundHandler.handle(httpExchangeMock);

        verify(outputStreamMock).close();
    }
}