package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleDisplayTest {
    PrintStream originalSystemOut;
    ByteArrayOutputStream outputStream;

    @Test
    @DisplayName("Print valid Welcome Message")
    void printWelcomeMessage() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        DisplayManager consoleDisplay = new DisplayManager();
        consoleDisplay.printWelcomeMessage();

        assertEquals("Welcome to the Library\n", outputStream.toString());
    }

    @Test
    @DisplayName("Print valid message")
    void printMessage() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        DisplayManager consoleDisplay = new DisplayManager();
        String message = "Random Message";
        consoleDisplay.print(message);

        assertEquals(message + "\n", outputStream.toString());
    }
}