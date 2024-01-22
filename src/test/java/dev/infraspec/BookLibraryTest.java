package dev.infraspec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookLibraryTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Print Welcome Message")
    public void testValidWelcomeMessage() {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.startApplication();

        String printedOutput = outputStreamCaptor.toString().trim();

        assertTrue(printedOutput.contains("Welcome to the Book Library"));
    }

    @Test
    @DisplayName("Print List of Books")
    public void printListOfBooks() {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.startApplication();

        String printedOutput = outputStreamCaptor.toString().trim();

        assertTrue(printedOutput.contains("List Of Books:\n-REWORK\n-REMOTE"));
    }
}
