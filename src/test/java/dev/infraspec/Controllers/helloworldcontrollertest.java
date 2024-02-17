package dev.infraspec.Controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class helloworldcontrollertest {
    private HelloWorldController helloWorldController;
    private HttpExchange httpExchangeMock;
    private Headers headersMock;
    private OutputStream outputStreamMock;
    @BeforeEach
    void setup() {
        helloWorldController = new HelloWorldController();
        httpExchangeMock = mock(HttpExchange.class);
        outputStreamMock = mock(OutputStream.class);
        headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(), any());
    }
    @Test
    @DisplayName("Sends Hello world Message")
    void helloWorldMessage() throws IOException {
        helloWorldController.handle(httpExchangeMock);

        verify(outputStreamMock).write("Hello,Yup the server is up and running.\n".getBytes());
    }

    @Test
    @DisplayName("Closing the Output Stream")
    void checkOutputStream() throws IOException {
        helloWorldController.handle(httpExchangeMock);

        verify(outputStreamMock).close();
    }

    @Test
    @DisplayName("Sending the Correct Response code")
    void checkResponseCode() throws IOException {
        String expectedResponseString = "Hello,Yup the server is up and running.\n";
        helloWorldController.handle(httpExchangeMock);

        verify(httpExchangeMock).sendResponseHeaders(200, expectedResponseString.length());
    }
}