package dev.infraspec.Controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class ValidRequestHandlerTest {

    private HttpExchange httpExchangeMock;
    private Headers headersMock;
    private OutputStream outputStreamMock;
    @BeforeEach
    void setup() throws IOException {
        httpExchangeMock = mock(HttpExchange.class);
        headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(), any());
        doNothing().when(httpExchangeMock).sendResponseHeaders(200, 1);
        outputStreamMock = mock(OutputStream.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
    }

    @Test
    @DisplayName("Send Response Message")
    void SendsResponseMessage() throws IOException {
        ValidRequestHandler validRequestHandler = new ValidRequestHandler();
        validRequestHandler.handle(httpExchangeMock, "Random String");

        verify(outputStreamMock).write("Random String".getBytes());
    }

    @Test
    @DisplayName("Set the Headers")
    void setHeaders() throws IOException {
        ValidRequestHandler validRequestHandler = new ValidRequestHandler();
        validRequestHandler.handle(httpExchangeMock, "Random String");

        verify(headersMock).set("Content-Type", "application/json");
    }

    @Test
    @DisplayName("Closes The Http connection")
    void closeConnection() throws IOException {
        ValidRequestHandler validRequestHandler = new ValidRequestHandler();
        validRequestHandler.handle(httpExchangeMock, "Random String");

        verify(outputStreamMock).close();
    }
}
