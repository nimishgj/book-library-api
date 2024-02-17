package dev.infraspec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InputOutputTest {
    PrintStream originalSystemOut;
    ByteArrayOutputStream outputStream;
    private final InputOutput inputOutput = new InputOutput(new Scanner(System.in));

    @BeforeEach
    public void setup() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Print valid message")
    void printMessage() {
        String message = "Random Message";

        inputOutput.print(message);

        assertEquals(message + "\n", outputStream.toString());
    }

    @Test
    @DisplayName("Get the input from stream")
    void getInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        InputOutput inputOutput = new InputOutput(new Scanner(inputStream));

        int gave = inputOutput.getIntInput();
        System.setIn(System.in);

        assertEquals(gave, 1);
    }

    @Test
    @DisplayName("Doesn't print if null is provided")
    void noPrintMessage() {
        InputOutput inputOutput = new InputOutput(new Scanner(System.in));
        inputOutput.print(null);

        assertNotNull(outputStream.toString());
    }
}
