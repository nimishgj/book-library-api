package dev.infraspec.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpsExchange;
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
        HttpExchange httpExchangeMock = mock(HttpsExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);

        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        helloWorldController.handle(httpExchangeMock);

        verify(outputStreamMock).write("Hello,Yup the server is up and running.\n".getBytes());
    }

    @Test
    @DisplayName("Closing the Output Stream")
    void checkOutputStream() throws IOException {
        HelloWorldController helloWorldController = new HelloWorldController();

        HttpExchange httpExchangeMock = mock(HttpsExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);

        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        helloWorldController.handle(httpExchangeMock);

        verify(outputStreamMock).close();
    }

    @Test
    @DisplayName("Sending the Correct Response code")
    void checkResponseCode() throws IOException {
        HelloWorldController helloWorldController = new HelloWorldController();
        String expectedResponseString = "Hello,Yup the server is up and running.\n";
        HttpExchange httpExchangeMock = mock(HttpsExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);

        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);
        helloWorldController.handle(httpExchangeMock);

        verify(httpExchangeMock, times(1)).sendResponseHeaders(200, expectedResponseString.length());
    }
}