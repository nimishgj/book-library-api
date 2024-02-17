package dev.infraspec.Controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class PageNotFoundHandlerTest {
    private PageNotFoundHandler pageNotFoundHandler;
    private HttpExchange httpExchangeMock;
    private OutputStream outputStreamMock;
    @BeforeEach
    void setup() {
        pageNotFoundHandler = new PageNotFoundHandler();
        httpExchangeMock = mock(HttpExchange.class);
        outputStreamMock = mock(OutputStream.class);
    }

    @Test
    @DisplayName("Sends Error Message")
    void sendErrorResponseMessage() throws IOException {
        String expectedResponseMessage = "Oops!, you landed somewhere you should not";

        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        pageNotFoundHandler.handle(httpExchangeMock);

        verify(outputStreamMock).write(expectedResponseMessage.getBytes());
    }

    @Test
    @DisplayName("Sets Page not found Response Code")
    void setPageNotFoundResponseCode() throws IOException {
        Headers headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(), any());

        pageNotFoundHandler.handle(httpExchangeMock);

        verify(httpExchangeMock).sendResponseHeaders(404, 42);
    }

    @Test
    @DisplayName("Closes the Http connection")
    void closeConnection() throws IOException {
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);

        pageNotFoundHandler.handle(httpExchangeMock);

        verify(outputStreamMock).close();
    }
}
