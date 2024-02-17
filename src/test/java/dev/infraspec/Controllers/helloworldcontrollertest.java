package dev.infraspec.Controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class helloworldcontrollertest {
    @Test
    @DisplayName("Sends Hello world Message")
    void helloWorldMessage() throws IOException {
        HelloWorldController helloWorldController = new HelloWorldController();
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);
        Headers headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(),any());
        helloWorldController.handle(httpExchangeMock);

        verify(outputStreamMock).write("Hello,Yup the server is up and running.\n".getBytes());
    }

    @Test
    @DisplayName("Closing the Output Stream")
    void checkOutputStream() throws IOException {
        HelloWorldController helloWorldController = new HelloWorldController();
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);
        Headers headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(),any());
        helloWorldController.handle(httpExchangeMock);
        helloWorldController.handle(httpExchangeMock);

        verify(outputStreamMock,atLeastOnce()).close();
    }

    @Test
    @DisplayName("Sending the Correct Response code")
    void checkResponseCode() throws IOException {
        HelloWorldController helloWorldController = new HelloWorldController();
        String expectedResponseString = "Hello,Yup the server is up and running.\n";
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);
        Headers headersMock = mock(Headers.class);
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        when(httpExchangeMock.getResponseHeaders()).thenReturn(headersMock);
        doNothing().when(headersMock).set(any(),any());
        helloWorldController.handle(httpExchangeMock);
        helloWorldController.handle(httpExchangeMock);

        verify(httpExchangeMock,atLeastOnce()).sendResponseHeaders(200, expectedResponseString.length());
    }
}