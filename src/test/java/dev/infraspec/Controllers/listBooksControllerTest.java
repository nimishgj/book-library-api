package dev.infraspec.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpsExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class listBooksControllerTest {
    @Test
    @DisplayName("Sends List of Books")
    void sendListOfBooks() throws IOException {
        ListBooksHandler listBooksHandler = new ListBooksHandler();
        HttpExchange httpExchangeMock = mock(HttpsExchange.class);
        OutputStream outputStreamMock = mock(OutputStream.class);
        listBooksHandler.handle(httpExchangeMock);

        verify(outputStreamMock).write(any());
    }

}
