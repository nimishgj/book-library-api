package dev.infraspec;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldServerTest {
    HelloWorldServer server;

    @BeforeEach
    public void setUp() throws Exception {
        server = new HelloWorldServer();
        server.start();
    }

    @AfterEach
    public void setDown() {
        server.stop();
    }

    @Test
    public void testHelloWorldEndpoint() throws IOException {
        int port = 8000;
        URL url = new URL("http://localhost:" + port + "/hello");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        assertEquals(200, httpURLConnection.getResponseCode());
        assertEquals("Hello,Yup the server is up and running.", response.toString().trim());
    }
}

